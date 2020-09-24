package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Reward extends Application {
	private Label label1, label2;
	private Button btn;
	
	@Override
    public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Jeopardy!");
			
			label1 = new Label();
			label1.setFont(new Font(40));
	        label1.setText("Congratulations");
			label1.setTranslateY(-50);
			
			label2 = new Label();
	        label2.setText("Score: 100");
			label2.setFont(new Font(30));
			label2.setTranslateY(0);
			
			btn = new Button();
	        btn.setText("Play again");
	        btn.setTranslateY(100);
	        btn.setMaxWidth(100);
	        btn.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	
	            }
	        });

	        StackPane root = new StackPane();
	        root.getChildren().add(btn);
	        root.getChildren().add(label1);
	        root.getChildren().add(label2);
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
