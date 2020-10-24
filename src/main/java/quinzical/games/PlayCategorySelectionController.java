package main.java.quinzical.games;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.quinzical.utility.SceneSwitcher;
import main.java.quinzical.utility.StringSpeaker;
import main.java.quinzical.model.Category;
import main.java.quinzical.model.GameManager;

public class PlayCategorySelectionController {
    private GameManager game;
    private StringSpeaker stringSpeaker;
    private SceneSwitcher sceneSwitcher = new SceneSwitcher();
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

        // adds available categories to dropdown combobox
        for(Category category : this.game.getCategories()) {
            categoriesBox.getItems().add(category.getName());
        }

        // Set up TableView of chosen categories
        this.categoryCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.categoryTable.setPlaceholder(new Label("Add some categories!"));
        this.chosenCategories.setAll(this.game.getChosenCategories());
        this.categoryTable.setItems(chosenCategories);
    }

    /**
     * Method attempts to add a user's selected category from the dropdown menu
     * to chosen categories
     * Displays message to user depending on the result of the attempt
     * Whether category is successfully added,
     * category is already selected or no category selected
     */
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

    /**
     * Method attempts to remove a selected category from the
     * chosen categories table
     * Displays message to user if it was successful or not
     */
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
        NZInternationalPageController controller = sceneSwitcher.
                switchScene(event, "/main/java/quinzical/games/resources/NZInternationalPage.fxml").getController();
        controller.initData(this.game, this.stringSpeaker);
    }

    /**
     * Checks user has 5 categories selected and takes them to question board
     * if done so. If not display to user to add more categories
     * @param event
     * @throws Exception
     */
    public void onStartClick(ActionEvent event) throws Exception{
        if (this.chosenCategories.size() == 5) {
            this.game.setGameMode(GameManager.GameMode.NEW_ZEALAND);
            PlayBoardController controller = sceneSwitcher.
                    switchScene(event, "/main/java/quinzical/games/resources/PlayBoard.fxml").getController();
            controller.initData(this.game, this.stringSpeaker);
        } else if (this.chosenCategories.size() == 4){
            this.errorLabel.setText("You need to add " + (5-chosenCategories.size()) + " more category");
        } else {
            this.errorLabel.setText("You need to add " + (5-chosenCategories.size()) + " more categories");
        }


    }
}
