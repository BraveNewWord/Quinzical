package main.java.quinzical.games;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import main.java.quinzical.utility.SceneSwitcher;
import main.java.quinzical.utility.StringSpeaker;
import main.java.quinzical.model.Category;
import main.java.quinzical.model.GameManager;
import main.java.quinzical.model.Question;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class PlayBoardController {
    @FXML
    private Label label1, label2, label3, label4, label5, scoreLabel;

    @FXML
    private Button button01, button02, button03, button04, button05,
    button11, button12, button13, button14, button15,
    button21, button22, button23, button24, button25,
    button31, button32, button33,button34, button35,
    button41, button42, button43, button44, button45;

    @FXML
    private GridPane questionGrid;

    private List<Label> labels;
    private List<List<Button>> colButtons;
    private GameManager game;
    private StringSpeaker stringSpeaker;
    private SceneSwitcher sceneSwitcher = new SceneSwitcher();

    /**
     * Sets up all the GUI components of the question board and
     * Loads model data from GameManager which are used to update GUI components
     * such as question labels and the score counter
     * @param game
     * @param stringSpeaker
     * @throws Exception
     */
    public void initData(GameManager game, StringSpeaker stringSpeaker) throws Exception {
        // setup the question label and button components of the question board GUI
        this.labels = Arrays.asList(label1,label2,label3,label4,label5);
        this.colButtons = Arrays.asList(Arrays.asList(button01,button02,button03,button04,button05),
                Arrays.asList(button11,button12,button13,button14,button15),
                Arrays.asList(button21,button22,button23,button24,button25),
                Arrays.asList(button31,button32,button33,button34,button35),
                Arrays.asList(button41,button42,button43,button44,button45)
        );
        questionGrid.getStyleClass().add("grid-pane");

        // setup the model part of the question board
        this.game = game;
        this.stringSpeaker = stringSpeaker;
        if (!this.game.gameStarted()) {
            for (Category category : this.game.getChosenCategories()) {
                for (int i = 0; i < 5; i++) {
                    Question randQuestion = category.getRandomQuestion();
                    randQuestion.setPoints(i * 100 + 100);
                }
            }
            this.game.setStarted(true);
        }

        // Change the content of labels and buttons with the
        // data from the model obtained above
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
        this.checkInternationalUnlock();
    }

    /**
     * Checks if the user has answered 2 categories fully - on NZ section usually
     * On their first time finishing 2 categories, displays to them
     * an alert that tells them the International section is unlocked
     */
    public void checkInternationalUnlock() {
        this.game.countCategoriesComplete();
        if (this.game.getTwoCategoriesComplete() &&
                !this.game.getInternationalUnlocked() &&
                this.game.getGameMode() != GameManager.GameMode.INTERNATIONAL) {
            this.game.setInternationalUnlocked(true);

            // alert creation
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("International section unlocked");
            alert.setHeaderText("You have unlocked the International section!");
            alert.setContentText("Two categories have been fully answered\n" +
                    "You can return to start the International section now\n" +
                    "or continue with the NZ section");

            // alert styling
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
                    getClass().getResource("/main/java/quinzical/css/style.css").toExternalForm());
            dialogPane.getStyleClass().add("alert");

            alert.showAndWait();
        }
    }

    /**
     * When a question button is clicked, this method
     * finds the relevant question selected and takes the user
     * to an answering page to answer it
     * @param event
     * @throws Exception
     */
    public void onButtonClick(ActionEvent event) throws Exception {
        Button chosenButton = (Button)event.getSource();
        int catInd = Character.getNumericValue(chosenButton.getId().charAt(6));
        int questionInd = Character.getNumericValue(chosenButton.getId().charAt(7))-1;
        Question chosenQuestion = game.getChosenCategories().get(catInd).getChosenQuestions().get(questionInd);
        game.setCurrentQuestion(chosenQuestion);
        PlayAnswerController controller = sceneSwitcher.switchScene(event, "/main/java/quinzical/games/resources/PlayAnswer.fxml").
                getController();
        controller.initData(this.game, this.stringSpeaker);
    }

    /**
     * When reset button is clicked, gives the user a
     * confirmation alert to cancel the reset in case they
     * changed their mind or clicking it was a mistake
     * @param event
     * @throws Exception
     */
    public void onResetClick(ActionEvent event) throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Reset confirmation");
        alert.setHeaderText("Are you sure you want to reset?");
        alert.setContentText("All winnings will be removed and questions reset\nThere is no way to reverse this");

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(
                getClass().getResource("/main/java/quinzical/css/style.css").toExternalForm());
        dialogPane.getStyleClass().add("alert");

        Optional<ButtonType> buttonType = alert.showAndWait();

        if (buttonType.isPresent() && buttonType.get() == ButtonType.OK) {
            this.game.resetAndLock();
            this.game.saveGame();
            NZInternationalPageController controller = sceneSwitcher.switchScene(event,
                    "/main/java/quinzical/games/resources/NZInternationalPage.fxml").
                    getController();
            controller.initData(this.game, this.stringSpeaker);

        }
    }

    public void onReturnClick(ActionEvent event) throws Exception {
        NZInternationalPageController controller = sceneSwitcher.switchScene(event, "/main/java/quinzical/games/resources/NZInternationalPage.fxml").
                getController();
        controller.initData(this.game, this.stringSpeaker);
    }
}
