package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class PracticeCategories extends Application {
	private Label label;
	private Button btn;
	private ComboBox<String> cb;
	
	@Override
    public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Jeopardy!");
			
			label = new Label();
			label.setFont(new Font(25));
	        label.setText("Select category:");
			label.setTranslateY(-100);
			
			cb = new ComboBox<String>();
	        cb.getItems().addAll(
	            "1",
	            "2",
	            "3",
	            "4",
	            "5"
	        );
	        cb.setTranslateY(-50);
	        cb.setMaxWidth(200);
	        
	        btn = new Button();
	        btn.setText("Start");
	        btn.setTranslateX(200);
	        btn.setTranslateY(150);
	        btn.setMaxSize(70, 30);
	        btn.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	
	            }
	        });

	        StackPane root = new StackPane();
	        root.getChildren().add(btn);
	        root.getChildren().add(label);
	        root.getChildren().add(cb);
	        primaryStage.setScene(new Scene(root, 600, 400));
	        primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
	
	public static void main(String[] args) {
		launch(args);
	}
}
