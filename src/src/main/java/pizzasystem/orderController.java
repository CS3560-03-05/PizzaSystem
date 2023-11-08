package pizzasystem;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class orderController 
{
    @FXML
    private void buildPizza(ActionEvent event) throws IOException {
        App.setRoot("build"); 
    }

    @FXML
    private void viewPrePizzas(ActionEvent event) throws IOException {
        App.setRoot("preBuild"); 
    }

    @FXML
    private void goBackToHome(ActionEvent event) throws IOException {
        App.setRoot("home"); 
    }
}
