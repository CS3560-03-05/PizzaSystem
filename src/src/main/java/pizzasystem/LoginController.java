package pizzasystem;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController 
{
    
    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;            //fields for login

    @FXML
    private Label statusLabel;

        @FXML
    private void loginUser(ActionEvent event) {

       
        String email = emailField.getText();
        String password = passwordField.getText();                  
        String message ="";
        if(!email.contains("@"))
        {
            message = "Invalid input";              //checks for valid email
        }
        else if(App.people.contains(email) && App.passwords.contains(password))         
        {
            message = "Logged in!";                                         //logic is temporary used for testing
        }
        else
        {
            message = "Account does not exist please register an account.";
        }

        // Display a message or perform any other post-registration actions
        statusLabel.setText(message);
    }

    @FXML
    private void goBackToHome(ActionEvent event) throws IOException 
    {
        App.setRoot("home"); // Load the home page layout again
    }

    @FXML
    private void goToRegister(ActionEvent event) throws IOException
    {
        App.setRoot("register");
    }

}
