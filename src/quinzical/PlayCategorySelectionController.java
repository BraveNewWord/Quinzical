package quinzical;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import quinzical.model.Category;
import quinzical.model.GameManager;

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
        if (this.game.getCategories().isEmpty()) {
            this.game.readCategories("categories");
        }
        for(Category category : this.game.getCategories()) {
            categoriesBox.getItems().add(category.getName());
        }

        this.categoryCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.categoryTable.setPlaceholder(new Label("Add some categories!"));
        this.chosenCategories.setAll(this.game.getChosenCategories());
        this.categoryTable.setItems(chosenCategories);
    }

    public void onAddClick() {
        if (this.chosenCategories.size() == 5) {
            this.errorLabel.setText("You have added 5 categories already\n" +
                    "Make room for more by removing some chosen categories");
            return;
        }

        try {
            // Check if category is already chosen
            String categoryString = categoriesBox.getValue();
            for(Category category : this.game.getChosenCategories()) {
                if(categoryString.equals(category.getName())) {
                    this.errorLabel.setText("You have already added " + category.getName());
                    return;
                }
            }
            // Try add category to chosenCategories, update table
            for(Category category : this.game.getCategories()) {
                if(categoryString.equals(category.getName())) {
                    this.game.addChosenCategory(category);
                    this.errorLabel.setText(category.getName() + " was added!");
                    break;
                }
            }
            this.chosenCategories.setAll(this.game.getChosenCategories());
            this.categoryTable.setItems(chosenCategories);

        } catch (NullPointerException npe) {
            this.errorLabel.setText("Please select a category to add");
        }
    }

    public void onRemoveClick() {
        try {
            Category selectedCategory = this.categoryTable.getSelectionModel().getSelectedItem();
            this.game.removeChosenCategory(selectedCategory);
            this.categoryTable.getItems().removeAll(selectedCategory);
            this.errorLabel.setText(selectedCategory.getName() + " was removed");
        } catch (NullPointerException npe) {
            this.errorLabel.setText("You didn't select any category to remove");
        }
    }

    public void onExitClick(ActionEvent event) throws Exception{
        NZInternationalPageController controller = new SceneSwitcher().
                switchScene(event, "NZInternationalPage.fxml").getController();
        controller.initData(this.game, this.stringSpeaker);
    }

    public void onStartClick(ActionEvent event) throws Exception{
        if (this.chosenCategories.size() == 5) {
            this.game.setGameMode(GameManager.GameMode.NEW_ZEALAND);
            PlayBoardController controller = new SceneSwitcher().
                    switchScene(event, "PlayQuestionBoard.fxml").getController();
            controller.initData(this.game, this.stringSpeaker);
        } else if (this.chosenCategories.size() == 4){
            this.errorLabel.setText("You need to add " + (5-chosenCategories.size()) + " more category");
        } else {
            this.errorLabel.setText("You need to add " + (5-chosenCategories.size()) + " more categories");
        }


    }
}
