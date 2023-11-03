package pizzasystem;

import java.io.IOException;
import javafx.fxml.FXML;

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
}
