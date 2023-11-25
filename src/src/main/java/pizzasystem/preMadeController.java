package pizzasystem;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class preMadeController 
{
    private CartService cartService = App.getServiceCart();             //temporary used for testing 

    @FXML
    private Label status;                                                                 //make sure customer is not null
    
    @FXML
    private void orderChickenBbq(ActionEvent event) {
        CartItem cartItem = new CartItem("ChickenBBQ", "6", 1);                 //Add new entry to order table with App.getCustomer()'s id  and bbqchicken's pizza id
        cartService.addToCart(cartItem);                                                    //Check if pizza id exist in customer's orders if it just update quanitty
        status.setText("Added to cart");
    }

    @FXML
    private void orderHawaiian(ActionEvent event) {
        // Handle the Hawaiian pizza order here
    }

    @FXML
    private void orderMeatLovers(ActionEvent event) {
        // Handle the Meat Lovers pizza order here
    }

    @FXML
    private void goBackToHome(ActionEvent event) throws IOException {
         App.setRoot("home");
    }
}
