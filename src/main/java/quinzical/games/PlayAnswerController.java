package main.java.quinzical.games;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import main.java.quinzical.model.GameManager;
import main.java.quinzical.model.Question;
import main.java.quinzical.utility.AlertBuilder;
import main.java.quinzical.utility.HelperThread;
import main.java.quinzical.utility.SceneSwitcher;
import main.java.quinzical.utility.StringSpeaker;

public class PlayAnswerController {
    private GameManager game;
    private StringSpeaker stringSpeaker;
    private SceneSwitcher sceneSwitcher = new SceneSwitcher();
    @FXML private Label tempQuestionLabel;
    @FXML private Label prefixLabel;
    @FXML private TextField answerTextBox;
    @FXML private Label timeLabel;
    @FXML private Pane interactablesPane;

    private int caretPosition = 0;
    private final int initTime = 30;
    private int timeRemaining = initTime;
    private Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(1), e -> {
                timeRemaining--;
                timeLabel.setText(String.valueOf(timeRemaining));
            })
    );

    public void initData(GameManager game, StringSpeaker stringSpeaker) throws Exception {
        this.game = game;
        this.stringSpeaker = stringSpeaker;
        tempQuestionLabel.setText(this.game.getCurrentQuestion().getClue());
        prefixLabel.setText(this.game.getCurrentQuestion().getPrefix());

        // Waits for clue to be spoken by StringSpeaker, then
        // enables initially disabled GUI components and starts Timer
        HelperThread helperThread = new HelperThread(this.stringSpeaker,
                this.game.getCurrentQuestion().getClue(), this.interactablesPane, this.timeline);
        helperThread.start();

        // Timer setup
        this.timeLabel.setText(String.valueOf(timeRemaining));
        this.timeline.setCycleCount(initTime);
        this.timeline.setOnFinished(e -> Platform.runLater(() -> {
            try {
                returnOrFinish();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }));
    }

    /**
     * Creates a alert for the player when the click the submit button
     * Message depends on if they got the question correct, incorrect, or
     * left the textfield empty
     * If correct/incorrect, takes them back to question board
     * and counts the question as answered
     * @param event
     * @throws Exception
     */
    public void onSubmitClick(Event event) throws Exception {
        String userAnswer = answerTextBox.getText().trim();
        Question currentQuestion = this.game.getCurrentQuestion();
        Alert alert;

        if (userAnswer.isBlank()) {
            alert = new AlertBuilder().answerType(AlertBuilder.AnswerType.INVALID_INPUT).build();
            alert.showAndWait();
            return;
        }
        else if (currentQuestion.checkAnswer(userAnswer)) {
            alert = new AlertBuilder()
                    .answerType(AlertBuilder.AnswerType.CORRECT)
                    .userAnswer(userAnswer)
                    .points(Integer.toString(currentQuestion.getPoints())).build();
            this.game.changePoints(currentQuestion.getPoints(), true);
        }
        else {
            alert = new AlertBuilder()
                    .answerType(AlertBuilder.AnswerType.PLAY_INCORRECT)
                    .userAnswer(userAnswer)
                    .trueAnswer(currentQuestion.getAnswers()).build();
        }

        this.game.saveGame();
        this.timeline.stop();
        alert.showAndWait();
        this.returnOrFinish();

    }

    /**
     * When the user clicks the Don't know button, displays to them
     * an alert that shows the answer to them - counts question as answered
     * and user is taken back to question board
     * @param event
     * @throws Exception
     */
    public void onDontKnowClick(ActionEvent event) throws Exception {
        Question currentQuestion = this.game.getCurrentQuestion();
        Alert alert = new AlertBuilder()
                .answerType(AlertBuilder.AnswerType.PLAY_INCORRECT)
                .trueAnswer(currentQuestion.getAnswers()).build();
        alert.setTitle("Don't know");
        alert.setHeaderText("Don't know? Here is the answer...");
        alert.setAlertType(Alert.AlertType.INFORMATION);
        this.timeline.stop();
        alert.showAndWait();
        this.returnOrFinish();
    }

    /**
     * This method checks if the are still questions remaning to
     * be answered in the game. If there are, user is taken to question board
     * If no more questions remain, user is taken to the Reward page
     * @throws Exception
     */
    public void returnOrFinish() throws Exception {
        this.stringSpeaker.stopSpeak();
        this.timeline.stop();
        this.game.getCurrentQuestion().setAnswered(true);

        // display an Out of time alert to user if they ran out of time
        if (this.timeRemaining == 0) {
            Question currentQuestion = this.game.getCurrentQuestion();
            Alert alert = new AlertBuilder()
                    .answerType(AlertBuilder.AnswerType.PLAY_INCORRECT)
                    .trueAnswer(currentQuestion.getAnswers()).build();
            alert.setTitle("Out of time!");
            alert.setHeaderText("You ran out of time!");
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.showAndWait();
        }

        if (!this.game.questionsExist()) {
            RewardController controller = sceneSwitcher.switchScene(this.timeLabel, "/main/java/quinzical/games/resources/Reward.fxml").
                    getController();
            controller.initData(this.game, this.stringSpeaker);

        } else {
            PlayBoardController controller = sceneSwitcher.
                    switchScene(this.timeLabel, "/main/java/quinzical/games/resources/PlayBoard.fxml").getController();
            controller.initData(this.game, this.stringSpeaker);
        }

    }

    public void onReplayClueClick() throws Exception {
        this.stringSpeaker.stopSpeak();
        this.stringSpeaker.speakString(this.game.getCurrentQuestion().getClue());
    }

    /**
     * Check if keys entered on keyboard are Enter key which will prompt to submit
     * Also tracks the textbox caret location
     */
    public void checkAnswerOnEnterKey(KeyEvent keyEvent) throws Exception {
        this.caretPosition = answerTextBox.getCaretPosition()+1;
        if (keyEvent.getCode() == KeyCode.ENTER) {
            onSubmitClick(keyEvent);
        }
    }

    /**
     * Tracks the caret location when the textbox is clicked on
     */
    public void onTextBoxClick() {
        this.caretPosition = answerTextBox.getCaretPosition();
    }

    /**
     * Method is called when one of the on-scren keyboard buttons are clicked
     * Inserts the clicked character into the position of the answer textfield that
     * the user had last had their caret on
     * @param event
     */
    public void onVowelClick(Event event) {
        String vowel = ((Button) event.getSource()).getText();
        try {
            this.answerTextBox.insertText(this.caretPosition, vowel);
            this.answerTextBox.requestFocus();
            this.answerTextBox.positionCaret(this.caretPosition +1);
        } catch (IndexOutOfBoundsException ex) {
            this.answerTextBox.insertText(0, vowel);
            this.answerTextBox.requestFocus();
            this.answerTextBox.positionCaret(1);
        }
        this.answerTextBox.requestFocus();
        this.answerTextBox.positionCaret(this.caretPosition +1);
        this.caretPosition = answerTextBox.getCaretPosition();
    }





}
