package main.java.quinzical.leaderboard;

import javafx.event.ActionEvent;
import main.java.quinzical.start.StartController;
import main.java.quinzical.utility.SceneSwitcher;

import java.io.IOException;

public class LeaderboardController {
    public void onReturnClick(ActionEvent event) throws IOException {
        StartController controller = new SceneSwitcher().
                switchScene(event, "/main/java/quinzical/start/resources/Start.fxml").
                getController();
    }
}
