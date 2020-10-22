package main.java.quinzical.games;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import main.java.quinzical.utility.SceneSwitcher;
import main.java.quinzical.model.GameManager;

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

}
