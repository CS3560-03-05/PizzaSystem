module pizzasystem {
    requires javafx.controls;
    requires javafx.fxml;

    opens pizzasystem to javafx.fxml;
    exports pizzasystem;
}
