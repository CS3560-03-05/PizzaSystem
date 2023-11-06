package pizzasystem;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PrimaryController {
   
    @FXML
    private void switchToRegister() throws IOException {
        App.setRoot("register");
    }
    @FXML
    private void switchToOrder() throws IOException
    {
        App.setRoot("order");
    }

    @FXML
    private void goToLogin() throws IOException
    {
        App.setRoot("login");
    }
}
