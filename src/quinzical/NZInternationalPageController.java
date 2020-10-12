package quinzical;


import javafx.event.ActionEvent;
import quinzical.model.GameManager;
import quinzical.model.PracticeManager;

import java.io.IOException;

public class NZInternationalPageController {
    private GameManager game;
    private StringSpeaker stringSpeaker;

    public void initData(GameManager game, StringSpeaker stringSpeaker) throws Exception {
        this.game = game;
        this.stringSpeaker = stringSpeaker;
    }

    public void onNZClick(ActionEvent event) throws Exception {
        PlayBoardController controller = new SceneSwitcher().
                switchScene(event, "PlayQuestionBoard.fxml").getController();
        controller.initData(this.game, this.stringSpeaker);
    }
}

