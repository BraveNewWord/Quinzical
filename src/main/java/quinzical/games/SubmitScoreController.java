package main.java.quinzical.games;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class SubmitScoreController {
    @FXML private TextField nameTextField;
    @FXML private Label errorLabel;

    public void onSubmitClick() {
        String userName = nameTextField.getText();
        if (userName.isBlank()) {
            errorLabel.setVisible(true);
        }
    }

    public void onCancelClick() {

    }
}
