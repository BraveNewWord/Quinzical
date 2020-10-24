package main.java.quinzical.model;

import java.io.Serializable;

public class Score implements Serializable {
    private String userName;
    private int scoreValue;
    private String section;

    public Score(String userName, int scoreValue, GameManager.GameMode gameMode) {
        this.userName = userName;
        this.scoreValue = scoreValue;
        switch (gameMode) {
            case NEW_ZEALAND:
                this.section = "New Zealand";
                break;
            case INTERNATIONAL:
                this.section = "International";
                break;
            case NONE:
                this.section = "None";
        }
    }

    public void printScore() {
        System.out.println("----------------\n" +
                "Name: " + this.userName + "\n" +
                "Score: " + scoreValue + "\n" +
                "Section: " + section);
    }
}
