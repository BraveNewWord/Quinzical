package main.java.quinzical.games;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.java.quinzical.utility.SceneSwitcher;
import main.java.quinzical.model.GameManager;

import java.io.IOException;

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

    public void onSubmitScore() throws IOException {
        Stage submitScorePopup = new Stage();
        submitScorePopup.setResizable(false);
        submitScorePopup.setTitle("Submit score");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/SubmitScore.fxml"));
        Parent root = loader.load();
        SubmitScoreController controller = loader.getController();

        Scene submitScene = new Scene(root,300,200);
        submitScorePopup.setScene(submitScene);
        submitScorePopup.showAndWait();
    }

}
