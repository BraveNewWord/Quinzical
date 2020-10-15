package quinzical;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import quinzical.model.Category;
import quinzical.model.GameManager;

import java.util.ArrayList;
import java.util.List;

public class PlayCategorySelectionController {
    private GameManager game;
    private StringSpeaker stringSpeaker;
    private ObservableList<Category> chosenCategories = FXCollections.observableArrayList();

    @FXML private ComboBox<String> categoriesBox;
    @FXML private TableView<Category> categoryTable;
    @FXML private TableColumn<Category, String> categoryCol;
    @FXML private Label errorLabel;

    public void initData(GameManager game, StringSpeaker stringSpeaker) throws Exception {
        this.game = game;
        this.stringSpeaker = stringSpeaker;

        this.game.getCategories();
        for(Category category : this.game.categories()) {
            categoriesBox.getItems().add(category.getName());
        }

        this.categoryCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.categoryTable.setPlaceholder(new Label("Add some categories!"));
    }

    public void onAddClick() {
        if (this.chosenCategories.size() == 5) {
            this.errorLabel.setText("You have added 5 categories already\n" +
                    "Make room for more by removing some chosen categories");
            return;
        }

        String categoryString = categoriesBox.getValue();
        for(Category category : this.game.getChosenCategories()) {
            if(categoryString.equals(category.getName())) {
                this.errorLabel.setText("You have already added " + category.getName());
                return;
            }
        }

        for(Category category : this.game.categories()) {
            if(categoryString.equals(category.getName())) {
                this.game.addChosenCategory(category);
                this.errorLabel.setText("");
            }
        }

        this.chosenCategories.setAll(this.game.getChosenCategories());
        this.categoryTable.setItems(chosenCategories);
    }

    public void onRemoveClick() {
        this.game.removeChosenCategory(this.categoryTable.getSelectionModel().getSelectedItem());
        this.categoryTable.getItems().removeAll(this.categoryTable.getSelectionModel().getSelectedItem());

    }
}
