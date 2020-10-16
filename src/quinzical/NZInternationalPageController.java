package quinzical;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import quinzical.model.GameManager;

import java.io.IOException;
import java.util.Optional;

public class NZInternationalPageController {
    private GameManager game;
    private StringSpeaker stringSpeaker;
    @FXML private Button internationalButton;


    public void initData(GameManager game, StringSpeaker stringSpeaker) throws Exception {
        this.game = game;
        this.stringSpeaker = stringSpeaker;
        this.game.countCategoriesComplete();
        if (this.game.getTwoCategoriesComplete()) {
            this.internationalButton.setDisable(false);
        }
    }

    public void onNZClick(ActionEvent event) throws Exception {
        switch (this.game.getGameMode()) {
            case NEW_ZEALAND:
                PlayBoardController pbc = new SceneSwitcher().
                        switchScene(event, "PlayQuestionBoard.fxml").getController();
                pbc.initData(this.game, this.stringSpeaker);
                break;
            case INTERNATIONAL:
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Start NZ section");
                alert.setHeaderText("You have an ongoing International Game");
                alert.setContentText("Would you like to start the NZ section?" +
                        "\nAll winnings will be removed and questions reset\n" +
                        "in the International section");
                Optional<ButtonType> buttonType = alert.showAndWait();

                if (buttonType.isPresent() && buttonType.get() == ButtonType.OK) {
                    this.game.setGameMode(GameManager.GameMode.NEW_ZEALAND);
                    if (this.game.gameStarted()) {
                        this.game.resetGame();
                    }
                    this.game.clearCategories();
                    PlayCategorySelectionController controller = new SceneSwitcher().
                            switchScene(event, "PlayCategorySelection.fxml").getController();
                    controller.initData(this.game, this.stringSpeaker);
                }
                break;
            case NONE:
                this.game.clearCategories();
                PlayCategorySelectionController controller = new SceneSwitcher().
                        switchScene(event, "PlayCategorySelection.fxml").getController();
                controller.initData(this.game, this.stringSpeaker);
                break;
        }
    }

    public void onInternationalClick(ActionEvent event) throws Exception{
        switch (this.game.getGameMode()) {
            case NEW_ZEALAND:
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Start International section");
                alert.setHeaderText("You have an ongoing New Zealand Game");
                alert.setContentText("Would you like to start the International section?" +
                        "\nAll winnings will be removed and questions\n" +
                        "reset in the NZ section");
                Optional<ButtonType> buttonType = alert.showAndWait();
                if (!(buttonType.isPresent() && buttonType.get() == ButtonType.OK)) {
                    return;
                }
            case NONE:
                this.game.resetGame();
                this.game.clearCategories();
                this.game.setGameMode(GameManager.GameMode.INTERNATIONAL);
                this.game.readCategories("categories/international");
                for (int i = 0; i < 5; i++) {
                    this.game.getRandomCategory();
                }
            case INTERNATIONAL:
                PlayBoardController controller = new SceneSwitcher().
                        switchScene(event, "PlayQuestionBoard.fxml").getController();
                controller.initData(this.game, this.stringSpeaker);
                break;
        }
    }

    public void onReturnClick(ActionEvent event) throws IOException {
        StartController controller = new SceneSwitcher().switchScene(event, "Start.fxml").
                getController();
        controller.initData(this.stringSpeaker);
    }
}

