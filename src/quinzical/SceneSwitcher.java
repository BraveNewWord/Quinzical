package quinzical;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneSwitcher {
    public FXMLLoader switchScene(Event event, String pageName) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(pageName));
        Parent root = loader.load();
        loader.getController();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        return loader;
    }

    public FXMLLoader switchScene(Node node, String pageName) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(pageName));
        Parent root = loader.load();
        loader.getController();

        Scene scene = new Scene(root);
        Stage stage = (Stage) (node.getScene().getWindow());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        return loader;
    }
}
