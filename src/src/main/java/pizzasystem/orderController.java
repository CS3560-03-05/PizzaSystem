package pizzasystem;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class orderController 
{
    @FXML
    private void buildPizza(ActionEvent event) throws IOException {
        App.setRoot("build");                                               //Presents build your own pizza option
    }

    @FXML
    private void viewPrePizzas(ActionEvent event) throws IOException {                  //presents order prebuilt pizza option
        App.setRoot("preBuild"); 
    }

    @FXML
    private void goBackToHome(ActionEvent event) throws IOException {
        App.setRoot("home"); 
    }
}
