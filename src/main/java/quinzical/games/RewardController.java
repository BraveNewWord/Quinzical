package main.java.quinzical.games;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.java.quinzical.utility.SceneSwitcher;
import main.java.quinzical.model.GameManager;

import java.io.IOException;
import java.util.Optional;

public class RewardController {
    @FXML
    private Label scoreLabel;
    private GameManager game;
    private SceneSwitcher sceneSwitcher = new SceneSwitcher();

    public void initData(GameManager game) {
        this.scoreLabel.setText(game.dispPoints());
        this.game = game;
    }

    public void onReplayClick(ActionEvent event) throws Exception {
        this.game.resetGame();
        this.game.saveGame();
        sceneSwitcher.switchScene(event, "/main/java/quinzical/start/resources/Start.fxml");
    }
    /*
     * Creates a TextInputDialog that user can enter their name into
     * to submit for the leaderboard
     */
    public void onSubmitScore() throws IOException {
        // Dialog creation
        TextInputDialog submitScoreDialog = new TextInputDialog();
        submitScoreDialog.setTitle("Submit score");
        DialogPane dialogPane = submitScoreDialog.getDialogPane();
        dialogPane.setContentText("Name:");
        dialogPane.setHeaderText("Enter your name");
        dialogPane.getStylesheets().add(
                getClass().getResource("/main/java/quinzical/css/style.css").toExternalForm());
        dialogPane.getStyleClass().add("alert");

        // Display dialog and check user inputs (button clicked and text typed)
        // Keep prompting user if they clicked OK, but did not enter anything
        boolean done = false;
        while (!done) {
            Optional<String> result = submitScoreDialog.showAndWait();
            if (result.isPresent()) {
                TextField textField = submitScoreDialog.getEditor();
                if (textField.getText() != null && !textField.getText().strip().isEmpty()) {
                    System.out.println(textField.getText());
                    done = true;
                } else {
                    dialogPane.setHeaderText("Try again");
                }
            } else {
                System.out.println("cancelled");
                done = true;
            }
        }
    }



}
