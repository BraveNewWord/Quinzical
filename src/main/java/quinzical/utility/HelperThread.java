package main.java.quinzical.utility;

import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.layout.Pane;

/**
 * Helper thread that allows for a clue to be spoken fully
 * by StringSpeaker before interactable GUI components can be used
 */
public class HelperThread extends Thread{
    private StringSpeaker stringSpeaker;
    private String spokenString;
    private Pane interactablesPane;
    private Timeline timeline;

    public HelperThread(StringSpeaker stringSpeaker, String spokenString,
                        Pane interactablesPane, Timeline timeline) {
        this.stringSpeaker = stringSpeaker;
        this.spokenString = spokenString;
        this.interactablesPane = interactablesPane;
        this.timeline = timeline;
    }

    @Override
    public void run() {
        // Speaks string and waits for process to complete
        try {
            this.stringSpeaker.speakString(spokenString).waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Creates task to be done (enabling GUI components and start timer)
        // after waiting for StringSpeaker to finish
        CompletedTaskPaper paper = new CompletedTaskPaper(this.interactablesPane, this.timeline);
        Platform.runLater(paper);
    }
}
