package pizzasystem;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class RegisterController {

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;                //creating fields to register

    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    @FXML
    private Label statusLabel;


    @FXML
    private void registerUser(ActionEvent event) throws SQLException {

       
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();                        //gets data from fields
        String password = passwordField.getText();
        String message ="";
        Customer customer = new Customer(firstName, lastName, email, password);
        if(!email.contains("@"))
        {
            message = "Invalid input";          //checks if valid email
        }
        else
        {
            message = customer.insertCustomer();
            App.setCustomer(customer);
        }
            
        // Display a message or perform any other post-registration actions
        statusLabel.setText(message);
    }

    @FXML
    private void goBackToHome(ActionEvent event) throws IOException {
         App.setRoot("home"); // Load the home page layout again
    }

    @FXML
    private void goToLogin(ActionEvent event) throws IOException
    {
        App.setRoot("login");
    }
}
