package quinzical;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import quinzical.model.GameManager;

import java.io.IOException;

public class PlayAnswerController {
    private GameManager game;
    @FXML private Label tempQuestionLabel; //REMOVE THIS WHEN SPOKEN CLUE IS IMPLEMENTED
    @FXML private Label prefixLabel;

    public void initData(GameManager game) {
        this.game = game;
        tempQuestionLabel.setText(this.game.getCurrentQuestion().getClue());
        prefixLabel.setText(this.game.getCurrentQuestion().getPrefix());
    }
    public void onSubmitClick(ActionEvent event) throws Exception {
        this.game.getCurrentQuestion().setAnswered(true);
        PlayBoardController controller = new SceneSwitcher().
                switchScene(event, "PlayQuestionBoard.fxml").getController();
        controller.initData(this.game);
    }

    public void onDontKnowClick() {

    }

    public void onReplayClueClick() {

    }
}
