package quinzical;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import quinzical.model.Category;
import quinzical.model.GameManager;

import java.util.ArrayList;
import java.util.List;

public class PlayCategorySelectionController {
    private GameManager game;
    private StringSpeaker stringSpeaker;
    @FXML
    private ComboBox<String> categoriesBox;

    public void initData(GameManager game, StringSpeaker stringSpeaker) throws Exception {
        this.game = game;
        this.stringSpeaker = stringSpeaker;

        this.game.getCategories();

        for(Category category : this.game.categories()) {
            categoriesBox.getItems().add(category.getName());
        }
    }

    public void onAddClick() {
        String categoryString = categoriesBox.getValue();

        for(Category category : this.game.categories()) {
            if(categoryString.equals(category.getName())) {
                
            }
        }
    }
}
