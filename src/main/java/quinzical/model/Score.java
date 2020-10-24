package main.java.quinzical.model;

import java.io.Serializable;

public class Score implements Serializable, Comparable<Score>{
    private int rank;
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

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getRank() {
        return this.rank;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getSection() {
        return this.section;
    }

    public int getScoreValue() {
        return this.scoreValue;
    }

    public void printScore() {
        System.out.println("----------------\n" +
                "Name: " + this.userName + "\n" +
                "Score: " + scoreValue + "\n" +
                "Section: " + section);
    }

    @Override
    public int compareTo(Score score) {
        if(this.scoreValue > score.scoreValue) {
            return -1;
        } else if (this.scoreValue < score.scoreValue) {
            return 1;
        }
        return 0;
    }
}
