package main.java.quinzical.model;

import java.io.Serializable;
import java.util.Arrays;

/*
 Class to represent a question in the game
 Contains its point worth, question string, answer string, and whether it is answered or not
 */
public class Question implements Serializable {
    private Integer points;
    private String clue;
    private String answerPrefix;

    private String[] answers;
    private boolean answered = false;

    public Question(String clue, String answerPrefix, String answer) {
        this.clue = macronSubstitute(clue);
        this.answerPrefix = answerPrefix;
        // Replacing Maori long vowels with double letter equivalents (may change)
        this.answers = macronSubstitute(answer.strip().toLowerCase())
                .split("/");

    }
    public String macronSubstitute(String oString) {
        return oString.replace("ā", "aa")
                .replace("ē", "ee")
                .replace("ī", "ii")
                .replace("ō", "oo")
                .replace("ū", "uu");
    }

    public boolean checkAnswer(String userAnswer) {
        for (String answer : this.answers) {
            if (userAnswer.toLowerCase().equals(answer)) {
                return true;
            }
        }
        return false;
    }

    public Integer getPoints() {
        return this.points;
    }
    public String getClue() {
        return this.clue;
    }
    public String getPrefix() {
        return this.answerPrefix.substring(0,1).toUpperCase() + this.answerPrefix.substring(1);
    }
    public String getAnswers() {
        return Arrays.toString(answers).replaceAll("[\\[\\]]", "");
    }
    public boolean isAnswered() {
        return this.answered;
    }
    public void setAnswered(boolean value) {
        this.answered = value;
    }
    public void setPoints(Integer points) {
        this.points = points;
    }
}
