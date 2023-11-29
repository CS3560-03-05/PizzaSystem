package pizzasystem;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    @FXML
    private TableView<CartItem> cartTable;              //table to display items in cart
    
    @FXML
    private Label totalLabel;                       //fxml items needed for page

    @FXML
    private Label checkoutMessage;

    @FXML
    private TableColumn<CartItem, Void> removeColumn;               //column in cartable for remove button

    public void initialize() throws SQLException {
           removeColumn.setCellFactory(column -> {
                return new TableCell<CartItem, Void>() {
                    private final Button removeButton = new Button("Remove");                   //puts a Remove button in each row of the remove column
            
                    {
                        removeButton.setOnAction(event -> {
                            CartItem item = getTableView().getItems().get(getIndex());          //action to be done when pressed
                            try {
                                removeItemFromCart(item);
                            } catch (SQLException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
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
    public void updateCart() throws SQLException 
    {                                            
        cartTable.getItems().clear();    
        String stmnt = "SELECT ordertable.pizzaId, ordertable.quantity, pizza.price, pizza.Name " +"FROM ordertable " +"JOIN pizza ON ordertable.pizzaId = pizza.pizzaId " +"WHERE ordertable.customerId = ?";
        int index = App.getCustomer().getIndex();
        PreparedStatement pStmnt = App.getConnector().prepareStatement(stmnt);
        pStmnt.setInt(1, index);
        ResultSet rs = pStmnt.executeQuery();
        while(rs.next())
        {
            String pizza = rs.getString("Name");
            int quantity = rs.getInt("quantity");
            float price = rs.getFloat("Price");

            CartItem cartItem = new CartItem(pizza, price, quantity);
            cartTable.getItems().add(cartItem);
        }
        totalLabel.setText("$"+calculateTotal());                       
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
            total+= (item.getPrice()) * (item.getQuantity());
        }

        return total;
    }

    private void removeItemFromCart(CartItem item) throws SQLException 
    {
        String updateQuery = "UPDATE ordertable SET quantity = quantity - 1 WHERE pizzaId = ? AND customerId = ?";
        String deleteQuery = "DELETE FROM ordertable WHERE pizzaId = ? AND customerId = ?";
        String query = "SELECT quantity FROM ordertable WHERE pizzaId = ? AND customerId = ?";

        PreparedStatement updateStatement = App.getConnector().prepareStatement(updateQuery);
        PreparedStatement deleteStatement = App.getConnector().prepareStatement(deleteQuery);
        PreparedStatement quantity = App.getConnector().prepareStatement(query);

        int pizzaId = Pizza.getPizzaId(item.getItem());
        int customerId = App.getCustomer().getIndex(); 
        quantity.setInt(1, pizzaId);
        quantity.setInt(2, customerId);
        ResultSet qrs = quantity.executeQuery();
        qrs.next();
        int currentQuantity = qrs.getInt("Quantity");

        if (currentQuantity > 1) 
        {
            for(int i = 0; i < cartTable.getItems().size(); i++)
            {
                if(cartTable.getItems().get(i).getQuantity() > 1)
                {
                    cartTable.getItems().get(i).setQuantity(currentQuantity-1);
                }
            }            
            updateStatement.setInt(1, pizzaId);
            updateStatement.setInt(2, customerId);
            updateStatement.executeUpdate();
        } 
        else 
        {
            cartTable.getItems().remove(item);
            deleteStatement.setInt(1, pizzaId);
            deleteStatement.setInt(2, customerId);
            deleteStatement.executeUpdate();
        }        
        updateCart();                                
    }

    @FXML
    private void goBackToHome(ActionEvent event) throws IOException {
        App.setRoot("home"); // Load the home page layout again
    }

}
