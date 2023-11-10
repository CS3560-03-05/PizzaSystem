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

    @FXML
    private void goToLogin() throws IOException
    {
        App.setRoot("login");
    }

    @FXML
    private void goToCart() throws IOException
    {
        App.setRoot("cart");
    }

    @FXML
    private void goToOrder() throws IOException
    {
        App.setRoot("order");
    }
}
