package quinzical;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import quinzical.model.GameManager;

public class RewardController {
    @FXML
    private Label scoreLabel;

    public void initData(GameManager game) {
        this.scoreLabel.setText(game.dispPoints());
    }

}
