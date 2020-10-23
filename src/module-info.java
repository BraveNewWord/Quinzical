module SE206Project {
    requires javafx.fxml;
    requires javafx.controls;
	requires java.desktop;
	requires javafx.graphics;

    opens main.java.quinzical;
    opens main.java.quinzical.model to javafx.base;
    opens main.java.quinzical.start to javafx.fxml;
    opens main.java.quinzical.games to javafx.fxml;
    opens main.java.quinzical.practice to javafx.fxml;
    opens main.java.quinzical.leaderboard to javafx.fxml;
    opens main.java.quinzical.utility to javafx.fxml;
}