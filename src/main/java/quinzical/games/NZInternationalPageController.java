package main.java.quinzical.games;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import main.java.quinzical.utility.SceneSwitcher;
import main.java.quinzical.utility.StringSpeaker;
import main.java.quinzical.model.GameManager;
import main.java.quinzical.start.StartController;

import java.io.IOException;
import java.util.Optional;

public class NZInternationalPageController {
    private GameManager game;
    private StringSpeaker stringSpeaker;
    private SceneSwitcher sceneSwitcher = new SceneSwitcher();
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
                PlayBoardController pbc = sceneSwitcher.
                        switchScene(event, "/main/java/quinzical/games/resources/PlayBoard.fxml").getController();
                pbc.initData(this.game, this.stringSpeaker);
                break;
            case INTERNATIONAL:
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Start NZ section");
                alert.setHeaderText("You have an ongoing International Game");
                alert.setContentText("Would you like to start the NZ section?" +
                        "\nAll winnings will be removed and questions reset\n" +
                        "in the International section");
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add(
                        getClass().getResource("/main/java/quinzical/css/style.css").toExternalForm());
                dialogPane.getStyleClass().add("alert");
                Optional<ButtonType> buttonType = alert.showAndWait();

                if (buttonType.isPresent() && buttonType.get() == ButtonType.OK) {
                    this.game.setGameMode(GameManager.GameMode.NEW_ZEALAND);
                    if (this.game.gameStarted()) {
                        this.game.resetGame();
                    }
                    this.game.clearCategories();
                    PlayCategorySelectionController controller = sceneSwitcher.
                            switchScene(event, "/main/java/quinzical/games/resources/PlayCategorySelection.fxml").getController();
                    controller.initData(this.game, this.stringSpeaker);
                }
                break;
            case NONE:
                this.game.clearCategories();
                PlayCategorySelectionController controller = sceneSwitcher.
                        switchScene(event, "/main/java/quinzical/games/resources/PlayCategorySelection.fxml").getController();
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
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add(
                        getClass().getResource("/main/java/quinzical/css/style.css").toExternalForm());
                dialogPane.getStyleClass().add("alert");
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
                PlayBoardController controller = sceneSwitcher.
                        switchScene(event, "/main/java/quinzical/games/resources/PlayBoard.fxml").getController();
                controller.initData(this.game, this.stringSpeaker);
                break;
        }
    }

    public void onReturnClick(ActionEvent event) throws IOException {
        StartController controller = sceneSwitcher.switchScene(event, "/main/java/quinzical/start/resources/Start.fxml").
                getController();
        controller.initData(this.stringSpeaker);
    }

    public void onResetClick(ActionEvent event) throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Reset confirmation");
        alert.setHeaderText("Are you sure you want to reset?");
        alert.setContentText("All winnings will be removed and questions reset\nThere is no way to reverse this");
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(
                getClass().getResource("/main/java/quinzical/css/style.css").toExternalForm());
        dialogPane.getStyleClass().add("alert");
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.isPresent() && buttonType.get() == ButtonType.OK) {
            this.game.resetGame();
            this.game.saveGame();
        }
        NZInternationalPageController controller = sceneSwitcher.switchScene(event, "/main/java/quinzical/games/resources/NZInternationalPage.fxml").
                getController();
        controller.initData(this.game, this.stringSpeaker);
    }
}

