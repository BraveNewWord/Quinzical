package quinzical;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import quinzical.model.Category;
import quinzical.model.GameManager;
import quinzical.model.Question;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class PlayBoardController {
    @FXML
    private Label label1;
    @FXML private Label label2;
    @FXML private Label label3;
    @FXML private Label label4;
    @FXML private Label label5;

    @FXML private Button button01; @FXML private Button button02;
    @FXML private Button button03; @FXML private Button button04; @FXML private Button button05;
    @FXML private Button button11; @FXML private Button button12;
    @FXML private Button button13; @FXML private Button button14; @FXML private Button button15;
    @FXML private Button button21; @FXML private Button button22;
    @FXML private Button button23; @FXML private Button button24; @FXML private Button button25;
    @FXML private Button button31; @FXML private Button button32;
    @FXML private Button button33; @FXML private Button button34; @FXML private Button button35;
    @FXML private Button button41; @FXML private Button button42;
    @FXML private Button button43; @FXML private Button button44; @FXML private Button button45;
    @FXML private Button resetButton;

    private GameManager game;

    public void initData(GameManager game) throws Exception {
        List<Label> labels = Arrays.asList(label1,label2,label3,label4,label5);
        List<List<Button>> colButtons = Arrays.asList(Arrays.asList(button01,button02,button03,button04,button05),
                Arrays.asList(button11,button12,button13,button14,button15),
                Arrays.asList(button21,button22,button23,button24,button25),
                Arrays.asList(button31,button32,button33,button34,button35),
                Arrays.asList(button41,button42,button43,button44,button45)
        );
        this.game = game;
        this.game.getCategories();
        Category randCat;
        for (int i = 0; i < 5; i++) {
            randCat = game.getRandomCategory();
            for (int j = 0; j < 5; j++) {
                Question randQuestion = randCat.getRandomQuestion();
                randQuestion.setPoints(j * 100 + 100);
            }
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
                //System.out.println("CLUE: " + randQuestion.getClue());
                //System.out.println("PREFIX: " + randQuestion.getPrefix());
                //System.out.println("ANSWERS: " + randQuestion.getAnswers());
                //System.out.println("POINTS: " + randQuestion.getPoints());
            }

        }
    }

    public void onButtonClick(ActionEvent event) throws IOException {
        new SceneSwitcher().switchScene(event, "PlayAnswer.fxml");
    }
}
