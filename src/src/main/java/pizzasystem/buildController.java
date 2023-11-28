package pizzasystem;

import java.io.IOException;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class buildController 
{
    @FXML
    private Label statusLabel;

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
    private void addCustom(ActionEvent event) throws SQLException {
        // Retrieve user selections
        String selectedCrust = crustChoiceBox.getValue();
        String selectedSize = sizeChoiceBox.getValue();
        boolean hasPepperoni = pepperoniCheckBox.isSelected();                          //make sure customer is not null
        boolean hasMushrooms = mushroomsCheckBox.isSelected();
        boolean hasOlives = olivesCheckBox.isSelected();
        boolean hasSausage = sausageCheckBox.isSelected();
        boolean hasBacon = baconCheckBox.isSelected();                                  //same concept as premade 
        boolean hasBPepper = bPepperCheckBox.isSelected();

        String pizzaDesc = "";
        if(App.getCustomer() != null )
        {
            if(hasPepperoni)
            {
                pizzaDesc+= "Pepperoni, ";
            }
            if(hasMushrooms)
            {
                pizzaDesc+="Mushrooms, ";
            }
            if(hasOlives)
            {
                pizzaDesc+="Olives, ";
            }
            if(hasSausage)
            {
                pizzaDesc+="Sausage, ";
            }
            if(hasBacon)
            {
                pizzaDesc+="Bacon, ";
            }
            if(hasBPepper)
            {
                pizzaDesc+="Bellpepper, ";
            }
            if(selectedCrust != null && selectedSize != null && pizzaDesc !="")
            {
                pizzaDesc += selectedCrust+", ";
                pizzaDesc  += selectedSize;
                double price = 0;
                if(selectedSize.equals("Small"))
                {
                    price = 9.00;
                }
                else if(selectedSize.equals("Medium"))
                {
                    price = 11.00;
                }
                else if(selectedSize.equals("Large"))
                {
                    price = 12.50;
                }
                if(price >0 )
                {
                    Customer.addCustomPizza(price, pizzaDesc);
                    statusLabel.setText("Added to Cart!");
                }
                else
                {
                    statusLabel.setText("Invalid Pizza");
                }
            }
            else
            {
                statusLabel.setText("Invalid Pizza");
            }
 
        }
        else
        {
            statusLabel.setText("Please Login or Register");
        }
       
    }

    @FXML
    private void goBackToHome(ActionEvent event) throws IOException {
         App.setRoot("home");
    }
}
