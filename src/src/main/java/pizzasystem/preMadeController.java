package pizzasystem;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class preMadeController 
{
    private CartService cartService = App.getServiceCart();

    @FXML
    private Label status;
    
    @FXML
    private void orderMargherita(ActionEvent event) {
        CartItem cartItem = new CartItem("Margherita", "6", "1");
        cartService.addToCart(cartItem);
        status.setText("Added to cart");
    }

    @FXML
    private void orderPepperoni(ActionEvent event) {
        // Handle the Pepperoni pizza order here
    }

    @FXML
    private void orderVegetarian(ActionEvent event) {
        // Handle the Vegetarian pizza order here
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
