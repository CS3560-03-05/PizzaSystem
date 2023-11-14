package pizzasystem;

import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {
   
    @FXML
    private void switchToRegister() throws IOException {            //goes to register page
        App.setRoot("register");
    }

    @FXML
    private void goToLogin() throws IOException
    {                                                                //goes to login page
        App.setRoot("login");
    }

    @FXML
    private void goToCart() throws IOException
    {
        App.setRoot("cart");                //goes to gotocart page
    }

    @FXML
    private void goToOrder() throws IOException             //goes to order page
    {
        App.setRoot("order");
    }
}
