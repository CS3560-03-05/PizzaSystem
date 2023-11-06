package pizzasystem;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class RegisterController {

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    @FXML
    private Label statusLabel;


    @FXML
    private void registerUser(ActionEvent event) {
        // Registration logic here
        // After registration, set the flag to true if you want to go back to the home page
       
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String message ="";
        if(!email.contains("@"))
        {
            message = "Invalid input";
        }
        else if(!App.people.contains(email))
        {
            message = "Registration successful:\n"
                + "First Name: " + firstName + "\n"
                + "Last Name: " + lastName + "\n"
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
