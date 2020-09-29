package quinzical;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import quinzical.model.GameManager;
import quinzical.model.Question;

import java.io.IOException;

public class PlayAnswerController {
    private GameManager game;
    @FXML private Label tempQuestionLabel; //REMOVE THIS WHEN SPOKEN CLUE IS IMPLEMENTED
    @FXML private Label prefixLabel;
    @FXML private TextField answerTextBox;

    public void initData(GameManager game) {
        this.game = game;
        tempQuestionLabel.setText(this.game.getCurrentQuestion().getClue());
        prefixLabel.setText(this.game.getCurrentQuestion().getPrefix());
    }
    public void onSubmitClick(ActionEvent event) throws Exception {
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
        this.game.getCurrentQuestion().setAnswered(true);

        alert.showAndWait();

        PlayBoardController controller = new SceneSwitcher().
                switchScene(event, "PlayQuestionBoard.fxml").getController();
        controller.initData(this.game);

    }

    public void onDontKnowClick(ActionEvent event) throws Exception {
        Question currentQuestion = this.game.getCurrentQuestion();
        Alert alert = new AlertBuilder()
                .answerType(AlertBuilder.AnswerType.PLAY_INCORRECT)
                .trueAnswer(currentQuestion.getAnswers()).build();
        alert.setTitle("Don't know");
        alert.setHeaderText("Don't know? Here is the answer...");
        alert.setAlertType(Alert.AlertType.INFORMATION);
        alert.showAndWait();

        this.game.getCurrentQuestion().setAnswered(true);
        PlayBoardController controller = new SceneSwitcher().
                switchScene(event, "PlayQuestionBoard.fxml").getController();
        controller.initData(this.game);
    }

    public void onReplayClueClick() {

    }
}
