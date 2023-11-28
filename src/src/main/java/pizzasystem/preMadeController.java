package pizzasystem;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class preMadeController 
{
    @FXML
    private Label status;                                                                 //make sure customer is not null
    
    @FXML
    private void orderChickenBbq(ActionEvent event) throws SQLException 
    {
        status.setText(Customer.addToCart(1));
    }

    @FXML
    private void orderHawaiian(ActionEvent event) throws SQLException 
    {
        status.setText(Customer.addToCart(2));
    }

    @FXML
    private void orderMeatLovers(ActionEvent event) throws SQLException 
    {
        status.setText(Customer.addToCart(0));
    }

    @FXML
    private void goBackToHome(ActionEvent event) throws IOException {
         App.setRoot("home");
    }


}
