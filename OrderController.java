public class OrderController {

    // Assume you have text fields for customer ID, pizza ID, and quantity
    // Also, assume you have labels or text areas to display customer information

    @FXML
    private TextField customerIdField;

    @FXML
    private TextField pizzaIdField;

    @FXML
    private TextField quantityField;

    @FXML
    private TextArea customerInfoArea;

    @FXML
    private void placeOrder(ActionEvent event) {
        int customerID = Integer.parseInt(customerIdField.getText());
        int pizzaID = Integer.parseInt(pizzaIdField.getText());
        int quantity = Integer.parseInt(quantityField.getText());

        OrderDAO.insertOrder(customerID, pizzaID, quantity);
        String customerInfo = OrderDAO.getCustomerInfo(customerID);

        // Display customer information in the TextArea
        customerInfoArea.setText(customerInfo);
    }
}
