package quinzical;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Start.fxml"));
        primaryStage.setTitle("Quinzical");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setResizable(false);
        primaryStage.show();
        //Below demonstrates the alerts created by AlertBuilder
        //comment it out/delete it when not needed
        //new AlertBuilder().testAlerts();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
