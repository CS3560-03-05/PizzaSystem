package pizzasystem;



public class CartItem           //temporary class for testing remove this class with the proper one
{
    private String item;
    private double price;
    private int quantity;
    public CartItem(String item, double price, int quantity) {
        this.item = item;
        this.price = price;
        this.quantity = quantity;
    }

    public String getItem() {
        return item;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int str)
    {
        this.quantity = str;
    }


}
