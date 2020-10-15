package quinzical;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import quinzical.model.Category;
import quinzical.model.GameManager;

import java.util.ArrayList;
import java.util.List;

public class PlayCategorySelectionController {
    private GameManager game;
    private StringSpeaker stringSpeaker;


    @FXML private ComboBox<String> categoriesBox;
    @FXML private TableView<Category> categoryTable;
    @FXML private TableColumn<Category, String> categoryCol;

    @FXML private TableColumn<?, ?> actionCol;


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
                this.game.addChosenCategory(category);
            }
        }
        ObservableList<Category> chosenCategories = FXCollections.observableArrayList();
        chosenCategories.setAll(this.game.getChosenCategories());
        this.categoryCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.categoryTable.setItems(chosenCategories);
    }
}
