package pizzasystem;

import java.io.IOException;

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
    private void registerUser(ActionEvent event) {

       
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();                        //gets data from fields
        String password = passwordField.getText();
        String message ="";
        if(!email.contains("@"))
        {
            message = "Invalid input";          //checks if valid email
        }
        else if(!App.people.contains(email))
        {
            message = "Registration successful:\n"
                + "First Name: " + firstName + "\n"         //sees if email in db 
                + "Last Name: " + lastName + "\n"           //create new customer in customer table
                + "Email: " + email;
            App.people.add(email);
            App.passwords.add(password);
        }
        else
        {
            message = "You already have an account please login.";
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
