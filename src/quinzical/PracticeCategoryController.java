package quinzical;

import javafx.event.ActionEvent;
import java.io.IOException;

public class PracticeCategoryController {
    public void onStartClick(ActionEvent event) throws IOException {
        new SceneSwitcher().switchScene(event, "PracticeAnswer.fxml");
    }
}
