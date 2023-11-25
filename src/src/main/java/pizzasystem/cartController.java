package pizzasystem;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableCell;


public class cartController 
{
    private CartService cartService = App.getServiceCart();

    @FXML
    private TableView<CartItem> cartTable;              //table to display items in cart
    
    @FXML
    private Label totalLabel;                       //fxml items needed for page

    @FXML
    private Label checkoutMessage;

    @FXML
    private TableColumn<CartItem, Void> removeColumn;               //column in cartable for remove button

    public void initialize() {
           removeColumn.setCellFactory(column -> {
                return new TableCell<CartItem, Void>() {
                    private final Button removeButton = new Button("Remove");                   //puts a Remove button in each row of the remove column
            
                    {
                        removeButton.setOnAction(event -> {
                            CartItem item = getTableView().getItems().get(getIndex());          //action to be done when pressed
                            removeItemFromCart(item);
                        });
                    }
            
                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);                                       //handle updating the button depening on conditions
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

            TableColumn<CartItem, String> quantityColumn = new TableColumn<>("Quantity");               //initalize columns
            quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
                    
            cartTable.getColumns().addAll(itemColumn, priceColumn, quantityColumn, removeColumn);
        }
        updateCart();                                                       
        totalLabel.setText("$"+calculateTotal());                       
    }

    @FXML
    public void updateCart() 
    {                                                                                             //make ObservableArray here
        cartTable.setItems(cartService.getCartItems());             //set items of cart         (iterate rows of order table where App.getCustomer matches customerid in table. Create new cartitem for each row and add cartitem to obserbable array  )
    }
    

    public void goToCheckout() throws IOException
    {
        if(cartTable.getItems().size() >= 1)
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("placeOrder.fxml"));
            Dialog<Button> dialog = new Dialog<>();
            dialog.setTitle("Payment Information");
            dialog.getDialogPane().setContent(loader.load());

            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL);

            dialog.showAndWait(); 
        }
        else
        {
            checkoutMessage.setText("Cart Empty!");
        }

    }

    private double calculateTotal()
    {
        double total = 0.0;

        for(CartItem item : cartTable.getItems())               //total money
        {
            total+= Double.parseDouble(item.getPrice().substring(0)) * (item.getQuantity());
        }

        return total;
    }

    private void removeItemFromCart(CartItem item) {
        cartService.removeFromCart(item);                                   //remove item in the cart   
        updateCart();                                                   // check if quantity is >1 then just update quantity. Also, get the order using the index remove from database.
        initialize();                                                       
    }

    @FXML
    private void goBackToHome(ActionEvent event) throws IOException {
        App.setRoot("home"); // Load the home page layout again
    }

}
