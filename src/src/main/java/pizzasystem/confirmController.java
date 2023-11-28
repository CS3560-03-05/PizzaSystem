package pizzasystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class confirmController 
{

    @FXML
    private Label confirmationLabel;

    @FXML
    private ListView<String> orderListView;

    public void initialize() throws SQLException 
    {
        populateOrders();
    }
    private void populateOrders() throws SQLException
    {
        ObservableList<String> orders = FXCollections.observableArrayList();
        String orderQuery = "SELECT o.idorderTable, o.quantity, p.pizzaId, p.description " +"FROM ordertable o " +"JOIN pizza p ON o.pizzaId = p.pizzaId " +"WHERE o.customerId = ?";
        PreparedStatement pOrder = App.getConnector().prepareStatement(orderQuery);
        pOrder.setInt(1, App.getCustomer().getIndex());
        ResultSet rs = pOrder.executeQuery();
        int index = 0;
        while (rs.next()) 
        {
            int orderId = rs.getInt("idorderTable");
            int quantity = rs.getInt("quantity");
            index+=quantity;
            int pizzaId = rs.getInt("pizzaId");
            String name = Pizza.getPizzaName(pizzaId);
            String description = getDescriptionFromPizzaTable(pizzaId);

            String order = String.format("%s - %s - Quantity: %d", name, description, quantity);
            orders.add(order);
            deleteOrder(orderId);
        }
        confirmationLabel.setText("Order Placed! Your order will be avaliable for pick up in "+(+index*5+20)+ " minutes");
        orderListView.setItems(orders);
    }

    private String getDescriptionFromPizzaTable(int pizzaId) throws SQLException 
    {
        String descriptionQuery = "SELECT Description FROM pizza WHERE pizzaId = ?";
        PreparedStatement dStmnt = App.getConnector().prepareStatement(descriptionQuery);
                dStmnt.setInt(1, pizzaId);
                ResultSet rs = dStmnt.executeQuery();

                if (rs.next()) {
                    return rs.getString("Description");
                }
        return ""; 
    }

    private void deleteOrder(int orderId) throws SQLException 
    {
        String deleteOrderQuery = "DELETE FROM ordertable WHERE idorderTable = ?";

        PreparedStatement deleteOrderStatement = App.getConnector().prepareStatement(deleteOrderQuery);
        deleteOrderStatement.setInt(1, orderId);
        deleteOrderStatement.executeUpdate();
    }

    @FXML
    private void goBackToHome(ActionEvent event) throws IOException 
    {
        App.setRoot("home"); 
    }


}
