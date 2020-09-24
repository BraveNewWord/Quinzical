package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Start extends Application {
	private Label label1, label2;
	private Button btn1, btn2;
	private Slider slider;
	
	@Override
    public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Jeopardy!");
			
			label1 = new Label();
			label1.setFont(new Font(50));
	        label1.setText("Welcome");
			label1.setTranslateY(-50);
			
			label2 = new Label();
	        label2.setText("Voice speed:");
			label2.setTranslateX(-150);
			label2.setTranslateY(150);
			
			btn1 = new Button();
	        btn1.setText("Play");
	        btn1.setTranslateY(50);
	        btn1.setMaxWidth(100);
	        btn1.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	
	            }
	        });
	        
	        btn2 = new Button();
	        btn2.setText("Practice");
	        btn2.setTranslateY(100);
	        btn2.setMaxWidth(100);
	        btn2.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	
	            }
	        });
	        
	        slider = new Slider();
	        slider.setTranslateX(50);
	        slider.setTranslateY(150);
	        slider.setMaxWidth(300);

	        StackPane root = new StackPane();
	        root.getChildren().add(btn1);
	        root.getChildren().add(btn2);
	        root.getChildren().add(label1);
	        root.getChildren().add(label2);
	        root.getChildren().add(slider);
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
