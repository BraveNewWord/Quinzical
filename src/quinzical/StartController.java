package quinzical;

import javafx.event.ActionEvent;

import java.io.IOException;

public class StartController {
    public void onPlayClick(ActionEvent event) throws IOException {
        new SceneSwitcher().switchScene(event, "PlayQuestionBoard.fxml");
    }


}
