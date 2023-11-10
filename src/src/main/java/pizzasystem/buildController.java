package pizzasystem;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class buildController 
{
    private Label crustLabel;

    @FXML
    private ChoiceBox<String> crustChoiceBox;

    @FXML
    private Label toppingsLabel;

    @FXML
    private CheckBox pepperoniCheckBox;

    @FXML
    private CheckBox mushroomsCheckBox;

    @FXML
    private CheckBox olivesCheckBox;

    @FXML
    private CheckBox sausageCheckBox;

    @FXML
    private CheckBox bPepperCheckBox;

    @FXML
    private CheckBox baconCheckBox;

    @FXML
    private Label sizeLabel;

    @FXML
    private ChoiceBox<String> sizeChoiceBox;

    @FXML
    private Button addToCartButton;

    @FXML
    private void initialize() {
        crustChoiceBox.setItems(FXCollections.observableArrayList("Thin Crust", "Pan Crust", "Stuffed Crust"));
        sizeChoiceBox.setItems(FXCollections.observableArrayList("Small", "Medium", "Large"));
    }

    @FXML
    private void addToCart(ActionEvent event) {
        // Retrieve user selections
        String selectedCrust = crustChoiceBox.getValue();
        String selectedSize = sizeChoiceBox.getValue();
        boolean hasPepperoni = pepperoniCheckBox.isSelected();
        boolean hasMushrooms = mushroomsCheckBox.isSelected();
        boolean hasOlives = olivesCheckBox.isSelected();
        boolean hasSausage = sausageCheckBox.isSelected();
        boolean hasBacon = baconCheckBox.isSelected();
        boolean hasBPepper = bPepperCheckBox.isSelected();

        //String[] customPizza = [selectedCrust, selectedSize, hasPepperoni, hasMushrooms, hasOlives]


        //System.out.println("Custom pizza added to the cart: " + customPizza.toString());
    }

    @FXML
    private void goBackToHome(ActionEvent event) throws IOException {
         App.setRoot("home");
    }
}
