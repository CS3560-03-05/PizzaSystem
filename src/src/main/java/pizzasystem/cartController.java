package pizzasystem;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableCell;


public class cartController 
{
    private CartService cartService = App.getServiceCart();

    @FXML
    private TableView<CartItem> cartTable;
    
    @FXML
    private Label totalLabel;

    @FXML
    private Label checkoutMessage;

    @FXML
    private TableColumn<CartItem, Void> removeColumn;

    public void initialize() {
           removeColumn.setCellFactory(column -> {
                return new TableCell<CartItem, Void>() {
                    private final Button removeButton = new Button("Remove");
            
                    {
                        removeButton.setOnAction(event -> {
                            CartItem item = getTableView().getItems().get(getIndex());
                            removeItemFromCart(item);
                        });
                    }
            
                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(removeButton);
                        }
                    }
                };
            });    
        if(cartTable.getColumns().isEmpty())
        {
            TableColumn<CartItem, String> itemColumn = new TableColumn<>("Item");
            itemColumn.setCellValueFactory(new PropertyValueFactory<>("item"));

            TableColumn<CartItem, String> priceColumn = new TableColumn<>("Price");
            priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

            TableColumn<CartItem, String> quantityColumn = new TableColumn<>("Quantity");               //CartItem is temporary for testing
            quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
                    
            cartTable.getColumns().addAll(itemColumn, priceColumn, quantityColumn, removeColumn);
        }
        updateCart();
        totalLabel.setText("$"+calculateTotal());
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
            total+= Double.parseDouble(item.getPrice().substring(0)) * (item.getQuantity());
        }

        return total;
    }

    private void removeItemFromCart(CartItem item) {
        cartService.removeFromCart(item);
        updateCart();
        initialize();
    }

    @FXML
    private void goBackToHome(ActionEvent event) throws IOException {
        App.setRoot("home"); // Load the home page layout again
    }

}
