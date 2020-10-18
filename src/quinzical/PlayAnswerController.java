package quinzical;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import quinzical.model.GameManager;
import quinzical.model.Question;

public class PlayAnswerController {
    private GameManager game;
    private StringSpeaker stringSpeaker;
    @FXML private Label tempQuestionLabel;
    @FXML private Label prefixLabel;
    @FXML private TextField answerTextBox;
    @FXML private Label timeLabel;

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
        this.stringSpeaker.speakString(this.game.getCurrentQuestion().getClue());

        this.timeLabel.setText(String.valueOf(timeRemaining));
        this.timeline.setCycleCount(initTime);
        this.timeline.play();
        this.timeline.setOnFinished(e -> {
            try {
                this.returnOrFinish();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

    }



    public void onSubmitClick(Event event) throws Exception { ;
        String userAnswer =answerTextBox.getText().trim();
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

    public void returnOrFinish() throws Exception {
        this.timeline.stop();
        this.game.getCurrentQuestion().setAnswered(true);
        if (this.timeRemaining == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Out of Time!");
            alert.setHeaderText("You ran out of time!");
            alert.setContentText("You will be taken back to the question board");
            alert.show();
        }
        if (!this.game.questionsExist()) {
            RewardController controller = new SceneSwitcher().switchScene(this.timeLabel, "Reward.fxml").
                    getController();
            controller.initData(this.game);

        } else {
            PlayBoardController controller = new SceneSwitcher().
                    switchScene(this.timeLabel, "PlayQuestionBoard.fxml").getController();
            controller.initData(this.game, this.stringSpeaker);
        }

    }

    public void onReplayClueClick() throws Exception {
        this.stringSpeaker.speakString(this.game.getCurrentQuestion().getClue());
    }

    public void checkAnswerOnEnterKey(KeyEvent keyEvent) throws Exception {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            onSubmitClick(keyEvent);
        }
    }





}
