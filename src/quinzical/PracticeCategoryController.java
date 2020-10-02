package quinzical;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import quinzical.model.Category;
import quinzical.model.GameManager;
import quinzical.model.PracticeManager;

public class PracticeCategoryController {
	@FXML private ComboBox<String> cb;
	private PracticeManager pm;
	private List<Category> categories=new ArrayList<Category>();
	private List<String> categoriesString=new ArrayList<String>();
	
	public void initialize(PracticeManager pm) throws Exception {
		this.pm=pm;
		this.pm.setCategories();
		categories=pm.getCategories();
		for(Category c:categories) {
			categoriesString.add(c.getName());
			cb.getItems().add(c.getName());
		}
	}
	
	public void onStartClick(ActionEvent event) throws Exception {
		String categoryString=cb.getValue();
		for(Category c:categories) {
			if(categoryString.equals(c.getName())) {
				this.pm.chooseCategory(c);
			}
		}
        PracticeAnswerController controller = new SceneSwitcher().
                switchScene(event, "PracticeAnswer.fxml").getController();
        controller.initialize(this.pm);
    }
	
	public void onExitClick(ActionEvent event) throws Exception {
		StartController controller = new SceneSwitcher().
                switchScene(event, "Start.fxml").getController();
	}
}
