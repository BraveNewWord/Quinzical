package quinzical;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import quinzical.model.GameManager;
import java.io.IOException;



public class StartController {
    @FXML private Slider voiceSpeedSlider;
    private StringSpeaker stringSpeaker = new StringSpeaker();

    public void initData(StringSpeaker stringSpeaker) {
        this.stringSpeaker = stringSpeaker;
        this.voiceSpeedSlider.setValue(2-this.stringSpeaker.getVoiceSpeed());
    }

    public void onPlayClick(ActionEvent event) throws Exception {
        PlayBoardController controller = new SceneSwitcher().
                switchScene(event, "PlayQuestionBoard.fxml").getController();
        controller.initData(new GameManager(), this.stringSpeaker);

    }
    public void onPracticeClick(ActionEvent event) throws IOException {
        new SceneSwitcher().switchScene(event, "PracticeCategory.fxml");

    }

    public void onSliderChanged() {
        double sliderValue = voiceSpeedSlider.getValue();

        this.stringSpeaker.setSpeed(sliderValue);
    }

    public void onTestSpeedClick() throws Exception {
        this.stringSpeaker.speakString("So, what do you think of this speed?" +
                " Is it too fast or too slow?");
    }

}
