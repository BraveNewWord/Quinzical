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

public class PracticeAnswer extends Application {
	private Label label1, label2, label3, label4;
	private TextField text;
	private Button btn1, btn2;
	
	@Override
    public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Jeopardy!");
			
			label1 = new Label();
			label1.setFont(new Font(25));
	        label1.setText("Enter your answer:");
			label1.setTranslateY(-100);
			
			label2 = new Label();
			label2.setFont(new Font(20));
	        label2.setText("The national animal of New Zealand");
			label2.setTranslateY(-50);
			
			label3 = new Label();
			label3.setFont(new Font(20));
	        label3.setText("The correct answer was: Mount Manganui");
			label3.setTranslateY(100);
			
			label4 = new Label();
	        label4.setText("Attempts: 1/3");
			label4.setTranslateX(-250);
			label4.setTranslateY(150);
			
			text = new TextField();
			text.setMaxWidth(300);
			
			btn1 = new Button();
	        btn1.setText("Submit");
	        btn1.setTranslateY(50);
	        btn1.setMaxWidth(100);
	        btn1.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	
	            }
	        });
	        
	        btn2 = new Button();
	        btn2.setText("Replay clue");
	        btn2.setTranslateY(150);
	        btn2.setMaxWidth(100);
	        btn2.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	
	            }
	        });

	        StackPane root = new StackPane();
	        root.getChildren().add(btn1);
	        root.getChildren().add(btn2);
	        root.getChildren().add(label1);
	        root.getChildren().add(label2);
	        root.getChildren().add(label3);
	        root.getChildren().add(label4);
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
