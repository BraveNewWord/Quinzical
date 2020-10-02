package quinzical;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import quinzical.model.PracticeManager;

public class PracticeAnswerController {
	@FXML private Label clue, answer, attempts;
	@FXML private TextField text;
	@FXML private Button submit, replay, exit;
	private PracticeManager pm;
	private int numAttempts;
	private String input;
	private StringSpeaker stringSpeaker;
	
	public void initialize(PracticeManager pm, StringSpeaker stringSpeaker) throws Exception {
		this.stringSpeaker = stringSpeaker;
		this.pm=pm;
		this.pm.setQuestions();
		this.pm.chooseQuestion();
		clue.setText(pm.getQuestion().getClue());
		answer.setText("");
		attempts.setText("Attempts: 1/3");
		numAttempts=1;
		this.stringSpeaker.speakString(pm.getQuestion().getClue());
	}
	
	public void onSubmitClick(ActionEvent event) throws Exception {
		String correctAnswer=pm.getQuestion().getAnswers();
		input=text.getText().trim();
		if(pm.getQuestion().checkAnswer(input)) {
			Alert alert = new AlertBuilder()
	                .answerType(AlertBuilder.AnswerType.PRAC_CORRECT)
	                .userAnswer(input).build();
	        alert.show();
			this.stringSpeaker.speakString("correct");
			PracticeCategoryController controller = new SceneSwitcher().
	                switchScene(event, "PracticeCategory.fxml").getController();
	        controller.initialize(new PracticeManager(), this.stringSpeaker);
		}else {
			if(numAttempts==1) {
				Alert alert = new AlertBuilder()
		                .answerType(AlertBuilder.AnswerType.PRAC_INCORRECT)
		                .userAnswer(input).build();
		        alert.show();
		        attempts.setText("attempts: 2/3");
				this.stringSpeaker.speakString("incorrect");
			}else if(numAttempts==2) {
				String hint=pm.getQuestion().getAnswers().substring(0, 1).toUpperCase();
				Alert alert = new AlertBuilder()
		                .answerType(AlertBuilder.AnswerType.FINAL_ATTEMPT)
		                .userAnswer(input).hint(hint).build();
		        alert.show();
		        attempts.setText("attempts: 3/3");
				answer.setText("You are given the first latter of the answer: "+hint);
				this.stringSpeaker.speakString("incorrect");
			}else if(numAttempts==3) {
				Alert alert = new AlertBuilder()
		                .answerType(AlertBuilder.AnswerType.PLAY_INCORRECT)
		                .userAnswer(input).trueAnswer(correctAnswer).build();
		        alert.show();
				answer.setText("The correct answer was: "+correctAnswer);
				this.stringSpeaker.speakString("The correct answer was: "+correctAnswer);
			}
			numAttempts++;
		}
	}
	
	public void onReplyClick(ActionEvent event) throws Exception {
		this.stringSpeaker.speakString(pm.getQuestion().getClue());
	}
	
	public void onExitClick(ActionEvent event) throws Exception {
		PracticeCategoryController controller = new SceneSwitcher().
                switchScene(event, "PracticeCategory.fxml").getController();
        controller.initialize(new PracticeManager(), this.stringSpeaker);
	}
}
