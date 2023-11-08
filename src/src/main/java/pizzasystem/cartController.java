package pizzasystem;

import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class cartController 
{
    private CartService cartService = App.getServiceCart();

    @FXML
    private TableView<CartItem> cartTable;
    
    @FXML
    private Label totalLabel;

    @FXML
    private Label checkoutMessage;

    public void initialize() {
        if(cartTable.getColumns().isEmpty())
        {
            TableColumn<CartItem, String> itemColumn = new TableColumn<>("Item");
            itemColumn.setCellValueFactory(new PropertyValueFactory<>("item"));

            TableColumn<CartItem, String> priceColumn = new TableColumn<>("Price");
            priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

            TableColumn<CartItem, String> quantityColumn = new TableColumn<>("Quantity");               //CartItem is temporary for testing
            quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            cartTable.getColumns().addAll(itemColumn, priceColumn, quantityColumn);
        }

        updateCart();
        totalLabel.setText("$"+calculateTotal());
    }


    public void removeItemFromCart(String item) {
    }

    @FXML
    public void updateCart() 
    {
        cartTable.setItems(cartService.getCartItems());
    }
    

    public void goToCheckout()
    {
        checkoutMessage.setText("Order Confirmed!");
    }

    private double calculateTotal()
    {
        double total = 0.0;

        for(CartItem item : cartTable.getItems())
        {
            total+= Double.parseDouble(item.getPrice().substring(0)) * Integer.parseInt(item.getQuantity());
        }

        return total;
    }

    @FXML
    private void goBackToHome(ActionEvent event) throws IOException {
        App.setRoot("home"); // Load the home page layout again
    }
}
