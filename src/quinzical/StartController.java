package quinzical;

import javafx.event.ActionEvent;
import quinzical.model.GameManager;
import java.io.IOException;


public class StartController {
    public void onPlayClick(ActionEvent event) throws Exception {
        PlayBoardController controller = new SceneSwitcher().
                switchScene(event, "PlayQuestionBoard.fxml").getController();
        controller.initData(new GameManager());

    }
    public void onPracticeClick(ActionEvent event) throws IOException {
        new SceneSwitcher().switchScene(event, "PracticeCategory.fxml");

    }

}
