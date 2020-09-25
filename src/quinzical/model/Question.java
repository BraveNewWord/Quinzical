package quinzical.model;

import java.util.Arrays;

/*
 Class to represent a question in the game
 Contains its point worth, question string, answer string, and whether it is answered or not
 */
public class Question {
    private Integer points;
    private String question;
    private String answerPrefix;
    private String answer;
    private String[] answers;
    private boolean answered = false;

    public Question(String question, String answerPrefix, String answer) {
        //this.points = Integer.parseInt(points);
        this.question = question;
        this.answerPrefix = answerPrefix;
        // Replacing Maori long vowels with double letter equivalents (may change)
        this.answers = answer.strip().toLowerCase()
                .replace("ā", "aa")
                .replace("ē", "ee")
                .replace("ī", "ii")
                .replace("ō", "oo")
                .replace("ū", "uu")
                .split("/");
        System.out.println("CLUE: " + this.question);
        System.out.println("PREFIX: " + this.getPrefix());
        System.out.println("ANSWERS: " + this.getAnswers());
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
    public String getQuestion() {
        return this.question;
    }
    public String getPrefix() {
        return this.answerPrefix.substring(0,1).toUpperCase() + this.answerPrefix.substring(1);
    }
    public String getAnswers() {
        return Arrays.toString(answers).replaceAll("\\[|\\]", "");
    }
    public boolean isAnswered() {
        return this.answered;
    }
    public void setAnswered(boolean value) {
        this.answered = value;
    }
}
