package quinzical;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import quinzical.model.GameManager;
import quinzical.model.PracticeManager;


public class StartController {
    @FXML private Slider voiceSpeedSlider;
    @FXML private Button quitButton;
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

    public void onPracticeClick(ActionEvent event) throws Exception {
//        new SceneSwitcher().switchScene(event, "PracticeCategory.fxml");
    	PracticeCategoryController controller = new SceneSwitcher().
                switchScene(event, "PracticeCategory.fxml").getController();
        controller.initialize(new PracticeManager(), this.stringSpeaker);
    }

    public void onSliderChanged() {
        double sliderValue = voiceSpeedSlider.getValue();

        this.stringSpeaker.setSpeed(sliderValue);
    }

    public void onTestSpeedClick() throws Exception {
        this.stringSpeaker.speakString("So, what do you think of this speed?" +
                " Is it too fast or too slow?");
    }

    public void onQuitClick() {
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }

}
