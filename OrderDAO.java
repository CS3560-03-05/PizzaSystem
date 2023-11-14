import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDAO {

    public static void insertOrder(int customerID, int pizzaID, int quantity) {
        String sql = "INSERT INTO OrderTable (CustomerID, PizzaID, Quantity, TotalAmount, OrderDate) " +
                     "VALUES (?, ?, ?, ?, NOW())";

        try (Connection connection = DBConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Calculate TotalAmount based on Pizza Price and Quantity
            double pizzaPrice = getPizzaPrice(pizzaID);
            double totalAmount = pizzaPrice * quantity;

            statement.setInt(1, customerID);
            statement.setInt(2, pizzaID);
            statement.setInt(3, quantity);
            statement.setDouble(4, totalAmount);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getCustomerInfo(int customerID) {
        String sql = "SELECT Name, Address, Email, Phone FROM Customer WHERE CustomerID = ?";

        try (Connection connection = DBConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, customerID);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return "Name: " + resultSet.getString("Name") +
                            "\nAddress: " + resultSet.getString("Address") +
                            "\nEmail: " + resultSet.getString("Email") +
                            "\nPhone: " + resultSet.getString("Phone");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "Customer not found";
    }

    private static double getPizzaPrice(int pizzaID) {
        // Implement this method to retrieve pizza price from the Pizza table
        // You can follow a similar pattern as in getCustomerInfo method
        return 0.0;
    }
}
