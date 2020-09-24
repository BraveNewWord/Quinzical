package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GameAnswer extends Application {
	private Label label;
	private TextField text;
	private Button btn1, btn2, btn3;
	
	@Override
    public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Jeopardy!");
			
			label = new Label();
			label.setFont(new Font(25));
	        label.setText("Enter your answer:");
			label.setTranslateY(-100);
			
			text = new TextField();
			text.setMaxWidth(300);
			
			btn1 = new Button();
	        btn1.setText("Submit");
	        btn1.setTranslateX(-75);
	        btn1.setTranslateY(50);
	        btn1.setMaxWidth(100);
	        btn1.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	
	            }
	        });
	        
	        btn2 = new Button();
	        btn2.setText("Don't know");
	        btn2.setTranslateX(75);
	        btn2.setTranslateY(50);
	        btn2.setMaxWidth(100);
	        btn2.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	
	            }
	        });
	        
	        btn3 = new Button();
	        btn3.setText("Replay clue");
	        btn3.setTranslateY(150);
	        btn2.setMaxWidth(100);
	        btn3.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	
	            }
	        });

	        StackPane root = new StackPane();
	        root.getChildren().add(btn1);
	        root.getChildren().add(btn2);
	        root.getChildren().add(btn3);
	        root.getChildren().add(label);
	        root.getChildren().add(text);
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
