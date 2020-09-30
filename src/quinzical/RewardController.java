package quinzical;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import quinzical.model.GameManager;

import java.io.IOException;

public class RewardController {
    @FXML
    private Label scoreLabel;
    private GameManager game;

    public void initData(GameManager game) {
        this.scoreLabel.setText(game.dispPoints());
        this.game = game;
    }

    public void onReplayClick(ActionEvent event) throws Exception {
        this.game.resetGame();
        this.game.saveGame();
        new SceneSwitcher().switchScene(event, "Start.fxml");

    }


}
