package pizzasystem;

public class CartItem           //temporary class for testing remove this class with the proper one
{
    private String item;
    private String price;
    private String quantity;
    public CartItem(String item, String price, String quantity) {
        this.item = item;
        this.price = price;
        this.quantity = quantity;
    }

    public String getItem() {
        return item;
    }

    public String getPrice() {
        return price;
    }

    public String getQuantity() {
        return quantity;
    }
}
