package quinzical;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import quinzical.model.GameManager;

public class PlayAnswerController {
    private GameManager game;
    @FXML private Label tempQuestionLabel; //REMOVE THIS WHEN SPOKEN CLUE IS IMPLEMENTED
    @FXML private Label prefixLabel;

    public void initData(GameManager game) {
        this.game = game;
        tempQuestionLabel.setText(this.game.getCurrentQuestion().getClue());
        prefixLabel.setText(this.game.getCurrentQuestion().getPrefix());
    }
}
