package pizzasystem;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PlaceOrderController 
{
    @FXML
    private TextField paymentTextField;

    @FXML
    private Label checkoutMessage;


    @FXML
    private void placeOrder() {
        String payment = paymentTextField.getText();
        if(payment.length() == 12)
        {
            checkoutMessage.setText("Order Placed!");
        }
        else
        {
            checkoutMessage.setText("Invalid Payment method.");
        }
    }
}
