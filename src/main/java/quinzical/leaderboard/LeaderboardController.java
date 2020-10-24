package main.java.quinzical.leaderboard;

import javafx.event.ActionEvent;
import main.java.quinzical.model.HighScores;
import main.java.quinzical.start.StartController;
import main.java.quinzical.utility.SceneSwitcher;
import main.java.quinzical.utility.StringSpeaker;

import java.io.IOException;

public class LeaderboardController {
    private StringSpeaker stringSpeaker;
    private HighScores highScores = new HighScores();

    public void initData(StringSpeaker stringSpeaker) {
        this.stringSpeaker = stringSpeaker;
    }

    public void onReturnClick(ActionEvent event) throws IOException {
        StartController controller = new SceneSwitcher().
                switchScene(event, "/main/java/quinzical/start/resources/Start.fxml").
                getController();
        controller.initData(this.stringSpeaker);

    }
}
