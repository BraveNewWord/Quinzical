package main.java.quinzical.practice;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import main.java.quinzical.utility.AlertBuilder;
import main.java.quinzical.utility.SceneSwitcher;
import main.java.quinzical.utility.StringSpeaker;
import main.java.quinzical.model.PracticeManager;

public class PracticeAnswerController {
	@FXML private Label clue, answer, attempts, prefixLabel;
	@FXML private TextField text;
	@FXML private Button submit, replay, exit;
	private PracticeManager pm;
	private int numAttempts;
	private String input;
	private StringSpeaker stringSpeaker;
	private SceneSwitcher sceneSwitcher = new SceneSwitcher();
	
	public void initialize(PracticeManager pm, StringSpeaker stringSpeaker) throws Exception {
		this.stringSpeaker = stringSpeaker;
		this.pm=pm;
		this.pm.setQuestions();
		this.pm.chooseQuestion();
		this.prefixLabel.setText(pm.getQuestion().getPrefix());
		clue.setText(pm.getQuestion().getClue());
		answer.setText("");
		attempts.setText("Attempts: 3/3");
		numAttempts=1;
		this.stringSpeaker.speakString(pm.getQuestion().getClue());
	}
	public void checkAnswerOnEnterKey(KeyEvent keyEvent) throws Exception {
		if (keyEvent.getCode() == KeyCode.ENTER) {
			onSubmitClick(keyEvent);
		}
	}
	public void onSubmitClick(Event event) throws Exception {
		String correctAnswer=pm.getQuestion().getAnswers();
		input=text.getText().trim();
		if (input.isBlank()) {
			Alert alert = new AlertBuilder().answerType(AlertBuilder.AnswerType.INVALID_INPUT).build();
			alert.showAndWait();
			return;
		}

		if(pm.getQuestion().checkAnswer(input)) {
			Alert alert = new AlertBuilder()
	                .answerType(AlertBuilder.AnswerType.PRAC_CORRECT)
	                .userAnswer(input).build();
			this.stringSpeaker.speakString("correct");
	        alert.showAndWait();
			this.onExitClick(event);
		}else {
			if(numAttempts==1) {
				Alert alert = new AlertBuilder()
		                .answerType(AlertBuilder.AnswerType.PRAC_INCORRECT)
		                .userAnswer(input).build();
				this.stringSpeaker.speakString("incorrect");
				alert.showAndWait();
		        attempts.setText("Attempts: 2/3");

			}else if(numAttempts==2) {
				String hint=pm.getQuestion().getAnswers().substring(0, 1).toUpperCase();
				Alert alert = new AlertBuilder()
		                .answerType(AlertBuilder.AnswerType.FINAL_ATTEMPT)
		                .userAnswer(input).hint(hint).build();
				this.stringSpeaker.speakString("incorrect");
				alert.showAndWait();
		        attempts.setText("Attempts: 1/3");
				answer.setText("You are given the first letter of the answer: "+hint);

			}else if(numAttempts==3) {
				Alert alert = new AlertBuilder()
		                .answerType(AlertBuilder.AnswerType.PLAY_INCORRECT)
		                .userAnswer(input).trueAnswer(correctAnswer).build();
				answer.setText("The correct answer was: "+correctAnswer);
				this.stringSpeaker.speakString("The correct answer was: "+correctAnswer);
				alert.showAndWait();
				this.onExitClick(event);

			}
			numAttempts++;
		}
	}
	
	public void onReplyClick(ActionEvent event) throws Exception {
		this.stringSpeaker.speakString(pm.getQuestion().getClue());
	}
	
	public void onExitClick(Event event) throws Exception {
		PracticeCategoryController controller = sceneSwitcher.
                switchScene(event, "/main/java/quinzical/practice/resources/PracticeCategory.fxml").getController();
        controller.initialize(new PracticeManager(), this.stringSpeaker);
	}
}
