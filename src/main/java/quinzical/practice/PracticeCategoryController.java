package main.java.quinzical.practice;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import main.java.quinzical.utility.SceneSwitcher;
import main.java.quinzical.utility.StringSpeaker;
import main.java.quinzical.model.Category;
import main.java.quinzical.model.PracticeManager;
import main.java.quinzical.start.StartController;

public class PracticeCategoryController {
	@FXML private ComboBox<String> cb;
	@FXML private Label errorLabel;
	private PracticeManager pm;
	private List<Category> categories=new ArrayList<Category>();
	private List<String> categoriesString=new ArrayList<String>();
	private StringSpeaker stringSpeaker;
	private SceneSwitcher sceneSwitcher = new SceneSwitcher();
	
	public void initialize(PracticeManager pm, StringSpeaker stringSpeaker) throws Exception {
		errorLabel.setText("");
		this.stringSpeaker = stringSpeaker;
		this.pm=pm;
		this.pm.setCategories();
		categories=pm.getCategories();
		for(Category c:categories) {
			categoriesString.add(c.getName());
			cb.getItems().add(c.getName());
		}
	}
	
	public void onStartClick(ActionEvent event) throws Exception {
		try {
			String categoryString=cb.getValue();
			for(Category c:categories) {
				if(categoryString.equals(c.getName())) {
					this.pm.chooseCategory(c);
				}
			}
	        PracticeAnswerController controller = sceneSwitcher.
	                switchScene(event, "/main/java/quinzical/practice/resources/PracticeAnswer.fxml").getController();
	        controller.initialize(this.pm, this.stringSpeaker);
		}catch (Exception e){
			errorLabel.setText("Please select a category");
		}
    }
	
	public void onExitClick(ActionEvent event) throws Exception {
		StartController controller = sceneSwitcher.
                switchScene(event, "/main/java/quinzical/start/resources/Start.fxml").getController();
		controller.initData(this.stringSpeaker);
	}
}
