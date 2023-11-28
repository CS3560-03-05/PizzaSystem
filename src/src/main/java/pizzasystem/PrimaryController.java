package pizzasystem;

import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PrimaryController 
{
    @FXML
    private Label statusLabel;
   
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
    private void goToCart() throws IOException, SQLException
    {
        if(App.getCustomer() != null)
        {
            App.setRoot("cart"); 
        }
        else
        {
            statusLabel.setText("Please Register or Login!");
        }
                       //goes to gotocart page
    }

    @FXML
    private void goToOrder() throws IOException             //goes to order page
    {
        App.setRoot("order");
    }
}
