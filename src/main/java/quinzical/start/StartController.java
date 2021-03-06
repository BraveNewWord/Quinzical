package main.java.quinzical.start;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import main.java.quinzical.games.NZInternationalPageController;
import main.java.quinzical.leaderboard.LeaderboardController;
import main.java.quinzical.practice.PracticeCategoryController;
import main.java.quinzical.utility.SceneSwitcher;
import main.java.quinzical.utility.StringSpeaker;
import main.java.quinzical.model.GameManager;
import main.java.quinzical.model.PracticeManager;

import java.io.IOException;


public class StartController {
    @FXML private Slider voiceSpeedSlider;
    @FXML private Button quitButton;
    private StringSpeaker stringSpeaker = new StringSpeaker();
    private SceneSwitcher sceneSwitcher = new SceneSwitcher();

    public void initData(StringSpeaker stringSpeaker) {
        this.stringSpeaker = stringSpeaker;
        this.voiceSpeedSlider.setValue(2-this.stringSpeaker.getVoiceSpeed());
    }

    public void onPlayClick(ActionEvent event) throws Exception {
        try {
            this.stringSpeaker.stopSpeak();
        } catch (NullPointerException npe) {
            //
        }
        NZInternationalPageController controller = sceneSwitcher.
                switchScene(event, "/main/java/quinzical/games/resources/NZInternationalPage.fxml").getController();
        controller.initData(new GameManager(), this.stringSpeaker);

    }

    public void onLeaderboardClick(ActionEvent event) throws IOException {
        try {
            this.stringSpeaker.stopSpeak();
        } catch (NullPointerException npe) {
            //
        }
        LeaderboardController controller = sceneSwitcher.
                switchScene(event, "/main/java/quinzical/leaderboard/resources/Leaderboard.fxml").getController();
        controller.initData(this.stringSpeaker);
    }


    public void onPracticeClick(ActionEvent event) throws Exception {
        try {
            this.stringSpeaker.stopSpeak();
        } catch (NullPointerException npe) {
            //
        }
    	PracticeCategoryController controller = sceneSwitcher.
                switchScene(event, "/main/java/quinzical/practice/resources/PracticeCategory.fxml").getController();
        controller.initialize(new PracticeManager(), this.stringSpeaker);
    }

    public void onSliderChanged() {
        double sliderValue = voiceSpeedSlider.getValue();

        this.stringSpeaker.setSpeed(sliderValue);
    }

    public void onTestSpeedClick() throws Exception {
        try {
            this.stringSpeaker.stopSpeak();
        } catch (NullPointerException npe) {
            //
        }
        this.stringSpeaker.speakString("So, what do you think of this speed?" +
                " Is it too fast or too slow?");
    }

    public void onQuitClick() {
        try {
            this.stringSpeaker.stopSpeak();
        } catch (NullPointerException npe) {
            //
        }
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }

}
