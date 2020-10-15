package quinzical;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import quinzical.model.GameManager;
import quinzical.model.PracticeManager;

import java.io.IOException;

public class NZInternationalPageController {
    private GameManager game;
    private StringSpeaker stringSpeaker;
    @FXML private Button internationalButton;


    public void initData(GameManager game, StringSpeaker stringSpeaker) throws Exception {
        this.game = game;
        this.stringSpeaker = stringSpeaker;
        this.game.countCategoriesComplete();
        if (this.game.getTwoCategoriesComplete()) {
            this.internationalButton.setDisable(false);
        }
    }

    public void onNZClick(ActionEvent event) throws Exception {
        if (this.game.gameStarted()) {
            PlayBoardController controller = new SceneSwitcher().
                    switchScene(event, "PlayQuestionBoard.fxml").getController();
            controller.initData(this.game, this.stringSpeaker);
        } else {
            PlayCategorySelectionController controller = new SceneSwitcher().
                    switchScene(event, "PlayCategorySelection.fxml").getController();
            controller.initData(this.game, this.stringSpeaker);
        }
    }

    public void onReturnClick(ActionEvent event) throws IOException {
        StartController controller = new SceneSwitcher().switchScene(event, "Start.fxml").
                getController();
        controller.initData(this.stringSpeaker);
    }
}

