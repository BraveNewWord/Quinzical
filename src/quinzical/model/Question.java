package quinzical.model;
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
        this.answers = answer.strip().toLowerCase().split("/");
        System.out.println("CLUE: " + this.question);
        System.out.println("PREFIX: " + this.answerPrefix);
        System.out.println("ANSWERS: " + answers.toString());
    }

    public boolean checkAnswer(String userAnswer) {
        if (userAnswer.equals(this.answer.toLowerCase())) {
            return true;
            //return points;
        }
        return false;
    }

    public Integer getPoints() {
        return this.points;
    }
    public String getQuestion() {
        return this.question;
    }
    public String getAnswer() {
        return this.answer;
    }
    public boolean isAnswered() {
        return this.answered;
    }
    public void setAnswered(boolean value) {
        this.answered = value;
    }
}
