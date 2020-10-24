package main.java.quinzical.leaderboard;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.quinzical.model.HighScores;
import main.java.quinzical.model.Score;
import main.java.quinzical.start.StartController;
import main.java.quinzical.utility.SceneSwitcher;
import main.java.quinzical.utility.StringSpeaker;


import java.io.IOException;

public class LeaderboardController {
    @FXML private TableView<Score> leaderBoard;
    @FXML private TableColumn<Score, Integer>  rankColumn;
    @FXML private TableColumn<Score, String> nameColumn;
    @FXML private TableColumn<Score, String> sectionColumn;
    @FXML private TableColumn<Score, Integer> scoreColumn;

    private StringSpeaker stringSpeaker;
    private HighScores highScores = new HighScores();


    public void initData(StringSpeaker stringSpeaker) {
        this.stringSpeaker = stringSpeaker;

        this.leaderBoard.setPlaceholder(new Label("No high scores yet!"));
        this.rankColumn.setReorderable(false);
        this.nameColumn.setReorderable(false);
        this.sectionColumn.setReorderable(false);
        this.scoreColumn.setReorderable(false);

        this.rankColumn.setCellValueFactory(new PropertyValueFactory<>("rank"));
        this.nameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        this.sectionColumn.setCellValueFactory(new PropertyValueFactory<>("section"));
        this.scoreColumn.setCellValueFactory(new PropertyValueFactory<>("scoreValue"));

        this.leaderBoard.setItems(this.getScores());

    }

    public void onReturnClick(ActionEvent event) throws IOException {
        StartController controller = new SceneSwitcher().
                switchScene(event, "/main/java/quinzical/start/resources/Start.fxml").
                getController();
        controller.initData(this.stringSpeaker);
    }

    public ObservableList<Score> getScores() {
        ObservableList<Score> obsHighScores = FXCollections.observableArrayList();
        obsHighScores.addAll(this.highScores.getScores());
        return obsHighScores;
    }
}
