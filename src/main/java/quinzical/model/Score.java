package main.java.quinzical.model;

import java.io.Serializable;

public class Score implements Serializable {
    private String userName;
    private int scoreValue;
    private String section;

    public Score(String userName, GameManager game) {
        this.userName = userName;
        this.scoreValue = game.getPoints();
        switch (game.getGameMode()) {
            case NEW_ZEALAND:
                this.section = "New Zealand";
            case INTERNATIONAL:
                this.section = "International";
            case NONE:
                this.section = "None";
        }
    }
}
