package pizzasystem;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    private void loginUser(ActionEvent event) throws SQLException {

       
        String email = emailField.getText();
        String password = passwordField.getText();                  
        String message ="";
        if(!email.contains("@"))
        {
            message = "Invalid input";              //checks for valid email
        }
        else
        {
            message = login(email, password);
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

    public String login(String email, String password) throws SQLException
    {
        if(checkEmail(email))
        {
            String query = "SELECT customerId, FirstName, LastName, Email, Password FROM customer WHERE Email = ? AND Password = ?";
            PreparedStatement pStmnt = App.getConnector().prepareStatement(query);
            pStmnt.setString(1, email);
            pStmnt.setString(2, password);
            ResultSet rs = pStmnt.executeQuery();
            if(rs.next())
            {
                Customer temp = new Customer(rs.getString("FirstName"), rs.getString("LastName"), rs.getString("Email"), rs.getString("Password"));
                App.setCustomer(temp);
                return "Logged in!";
            }
            else
            {
                return "Incorrect email or password";
            }
        }
        else
        {
            return "Email does not exist please register";
        }
    }

    private boolean checkEmail(String email) throws SQLException
    {
        String query = "SELECT COUNT(*) FROM pizzadbsystem.customer WHERE Email = ?";
        try(PreparedStatement pStmnt = App.getConnector().prepareStatement(query))
        {
            pStmnt.setString(1, email);
            try(ResultSet rs = pStmnt.executeQuery())
            {
                if(rs.next())
                {
                    int count = rs.getInt(1);
                    return count > 0;
                }
            }
        }
        return false;
    }

}
