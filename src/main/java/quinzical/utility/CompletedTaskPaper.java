package main.java.quinzical.utility;

import javafx.animation.Timeline;
import javafx.scene.layout.Pane;

/**
 * This "paper" is created once the HelperThread has waited for the StringSpeaker to finish
 * It will act to update the interactable components in the GUI so that are enabled
 * and also start the timer on the answering page
 */
public class CompletedTaskPaper implements Runnable{

    private Pane interactablesPane;
    private Timeline timeline;

    public CompletedTaskPaper(Pane interactablesPane, Timeline timeline) {
        this.interactablesPane = interactablesPane;
        this.timeline = timeline;
    }

    @Override
    public void run() {
        this.interactablesPane.setDisable(false);
        this.timeline.play();
    }
}
