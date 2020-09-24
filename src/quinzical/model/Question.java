package quinzical.model;
/*
 Class to represent a question in the game
 Contains its point worth, question string, answer string, and whether it is answered or not
 */
public class Question {
    private Integer points;
    private String question;
    private String answer;
    private boolean answered = false;

    public Question(String points, String question, String answer) {
        this.points = Integer.parseInt(points);
        this.question = question;
        this.answer = answer.strip();
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
