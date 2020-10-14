package quinzical;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import quinzical.model.Category;
import quinzical.model.GameManager;
import quinzical.model.Question;
import java.io.IOException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class PlayBoardController {
    @FXML
    private Label label1;
    @FXML private Label label2;
    @FXML private Label label3;
    @FXML private Label label4;
    @FXML private Label label5;
    @FXML private Label scoreLabel;


    @FXML
    private Button button01, button02, button03, button04, button05,
    button11, button12, button13, button14, button15,
    button21, button22, button23, button24, button25,
    button31, button32, button33,button34, button35,
    button41, button42, button43, button44, button45;
    private List<Label> labels;
    private List<List<Button>> colButtons;
    private GameManager game;
    private StringSpeaker stringSpeaker;

    public void initData(GameManager game, StringSpeaker stringSpeaker) throws Exception {
        this.labels = Arrays.asList(label1,label2,label3,label4,label5);
        this.colButtons = Arrays.asList(Arrays.asList(button01,button02,button03,button04,button05),

                Arrays.asList(button11,button12,button13,button14,button15),
                Arrays.asList(button21,button22,button23,button24,button25),
                Arrays.asList(button31,button32,button33,button34,button35),
                Arrays.asList(button41,button42,button43,button44,button45)
        );
        this.game = game;
        this.stringSpeaker = stringSpeaker;
        if (!this.game.gameStarted()) {
            if (this.game.categories().isEmpty()) {
                this.game.getCategories();
            }
            Category randCat;
            for (int i = 0; i < 5; i++) {
                randCat = game.getRandomCategory();
                for (int j = 0; j < 5; j++) {
                    Question randQuestion = randCat.getRandomQuestion();
                    randQuestion.setPoints(j * 100 + 100);
                }
            }
            this.game.setStarted(true);
        }
        for (int i = 0; i < 5; i++) {
            Category chosenCat = game.getChosenCategories().get(i);
            labels.get(i).setText(chosenCat.getName());
            for (int j = 0; j < 5; j++) {
                Question chosenQuestion = chosenCat.getChosenQuestions().get(j);
                colButtons.get(i).get(j).setText(Integer.toString(chosenQuestion.getPoints()));
                if ((j == 0 || chosenCat.getChosenQuestions().get(j - 1).isAnswered())
                    && (!chosenQuestion.isAnswered())) {
                        colButtons.get(i).get(j).setDisable(false);
                }

                if (chosenQuestion.isAnswered()) {
                    colButtons.get(i).get(j).setVisible(false);
                }
            }
        }
        scoreLabel.setText(game.dispPoints());
        this.game.saveGame();
    }

    public void onButtonClick(ActionEvent event) throws Exception {
        Button chosenButton = (Button)event.getSource();
        int catInd = Character.getNumericValue(chosenButton.getId().charAt(6));
        int questionInd = Character.getNumericValue(chosenButton.getId().charAt(7))-1;
        Question chosenQuestion = game.getChosenCategories().get(catInd).getChosenQuestions().get(questionInd);
        game.setCurrentQuestion(chosenQuestion);
        PlayAnswerController controller = new SceneSwitcher().switchScene(event, "PlayAnswer.fxml").
                getController();
        controller.initData(this.game, this.stringSpeaker);
    }

    public void onResetClick(ActionEvent event) throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Reset confirmation");
        alert.setHeaderText("Are you sure you want to reset?");
        alert.setContentText("All winnings will be removed and questions reset\nThere is no way to reverse this");
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.isPresent() && buttonType.get() == ButtonType.OK) {
            this.game.resetGame();
            this.game.saveGame();
            new SceneSwitcher().switchScene(event, "Start.fxml");
        }
    }

    public void onReturnClick(ActionEvent event) throws IOException {
        StartController controller = new SceneSwitcher().switchScene(event, "Start.fxml").
                getController();
        controller.initData(this.stringSpeaker);
    }
}
