package pizzasystem;

import javafx.collections.FXCollections;            //Helps share data between prebuild, buildyourown, and the cart
import javafx.collections.ObservableList;

public class CartService 
{
    private ObservableList<CartItem> cartItems = FXCollections.observableArrayList();

    public ObservableList<CartItem> getCartItems() {
        return cartItems;   
    }

    public void addToCart(CartItem item) {
        cartItems.add(item);
    }

    public void clear()
    {
        cartItems.clear();
    }

}
