module SE206Project {
    requires javafx.fxml;
    requires javafx.controls;
	requires java.desktop;
	requires javafx.graphics;

    opens quinzical;
    opens quinzical.model to javafx.base;
}