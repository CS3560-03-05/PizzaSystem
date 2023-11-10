package pizzasystem;

import javafx.collections.FXCollections;            //Helps share data between prebuild, buildyourown, and the cart
import javafx.collections.ObservableList;

public class CartService 
{
    private ObservableList<CartItem> cartItems = FXCollections.observableArrayList();

    public ObservableList<CartItem> getCartItems() {
        return cartItems;   
    }

    public void addToCart(CartItem item) 
    {
        boolean flag =false;
        for(int i = 0; i < cartItems.size(); i++)
        {
            if(cartItems.get(i).getItem().equals(item.getItem()))
            {
                flag =true;
                cartItems.get(i).setQuantity(cartItems.get(i).getQuantity()+1);
            }
        }
        if(!flag)
        {
            cartItems.add(item);
        }
    }

    public void removeFromCart(CartItem item)
    {
        boolean flag =false;
        for(int i = 0; i < cartItems.size(); i++)
        {
            if(cartItems.get(i).getQuantity() > 1)
            {
                flag =true;
                cartItems.get(i).setQuantity(cartItems.get(i).getQuantity()-1);
            }
        }
        if(!flag)
        {
            cartItems.remove(item);
        }
    }
}
