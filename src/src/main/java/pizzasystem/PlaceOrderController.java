package pizzasystem;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PlaceOrderController 
{
    @FXML
    private TextField paymentTextField;

    @FXML
    private Label checkoutMessage;

    @FXML
    private Button placeOrderButton;


    @FXML
    private void placeOrder() throws IOException {
        String payment = paymentTextField.getText();
        if(payment.length() == 12)
        {
            loadConfirmationPage();
            Stage stage = (Stage) placeOrderButton.getScene().getWindow();

            stage.close();
        }
        else
        {
            checkoutMessage.setText("Invalid Payment method.");
        }
    }

    private void loadConfirmationPage() throws IOException 
    {
        App.setRoot("confirmation");
    }
}
