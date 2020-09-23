package quinzical;

import javafx.scene.control.Alert;

public class AlertBuilder {
    public enum AnswerType {
        CORRECT,
        PLAY_INCORRECT,
        PRAC_INCORRECT,
        FINAL_ATTEMPT
    }
    private Alert alert;
    private AnswerType answerType;
    private String userAnswer = "USER_ANSWER";
    private String trueAnswer = "TRUE_ANSWER";
    private String points = "999";

    private String userAttempts = "100";
    private String totalAttempts = "300";
    private String hint = "";

    public AlertBuilder answerType(AnswerType answerType) {
        this.answerType = answerType;
        return this;
    }
    public AlertBuilder userAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
        return this;
    }
    public AlertBuilder trueAnswer(String trueAnswer) {
        this.trueAnswer = trueAnswer;
        return this;
    }
    public AlertBuilder points(String points) {
        this.points = points;
        return this;
    }
    // Below setters are mainly used for Practice module alerts
    public AlertBuilder userAttempts(String userAttempts) {
        this.userAttempts = userAttempts;
        return this;
    }
    public AlertBuilder totalAttempts(String totalAttempts) {
        this.totalAttempts = totalAttempts;
        return this;
    }
    public AlertBuilder hint(String hint) {
        this.hint = hint;
        return this;
    }

    public Alert build() {
        switch (this.answerType) {
            case CORRECT:
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Correct");
                alert.setHeaderText(userAnswer + " was correct!");
                alert.setContentText("You gained " + points + " points");
                break;
            case PLAY_INCORRECT:
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Incorrect");
                alert.setHeaderText(userAnswer + " was incorrect");
                alert.setContentText("The correct answer was: " + trueAnswer);
                break;
            case PRAC_INCORRECT:
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Incorrect");
                alert.setHeaderText(userAnswer + " was incorrect");
                alert.setContentText("You have " + userAttempts + "/" + totalAttempts + " attempts left");
                break;
            case FINAL_ATTEMPT:
                alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Incorrect");
                alert.setHeaderText(userAnswer + " was incorrect");
                alert.setContentText("You have one more attempt" +
                        "\nHere is the first letter of the correct answer: " + hint);
                break;
        }
        return alert;
    }

    public void testAlerts() {
        // A Correct alert should have at minimum the fields set below:
        Alert alert = new AlertBuilder()
                .answerType(AlertBuilder.AnswerType.CORRECT)
                .userAnswer("Carotene")
                .points("100").build();
        alert.show();
        // A PLAY_INCORRECT alert should have at minimum the fields set below:
        Alert alert2 = new AlertBuilder()
                .answerType(AlertBuilder.AnswerType.PLAY_INCORRECT)
                .userAnswer("Barotene")
                .trueAnswer("Carotene").build();
        alert2.show();
        // A PRAC_INCORRECT alert should have at minimum the fields set below:
        Alert alert3 = new AlertBuilder()
                .answerType(AlertBuilder.AnswerType.PRAC_INCORRECT)
                .userAnswer("Bernard")
                .userAttempts("2")
                .totalAttempts("3").build();
        alert3.show();
        // A FINAL_ATTEMPT alert should have at minimum the fields set below:
        Alert alert4 = new AlertBuilder()
                .answerType(AlertBuilder.AnswerType.FINAL_ATTEMPT)
                .userAnswer("Lacrosse")
                .hint("C")
                .build();
        alert4.show();
    }
}
