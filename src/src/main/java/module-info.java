module pizzasystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens pizzasystem to javafx.fxml;
    exports pizzasystem;
}
