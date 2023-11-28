package pizzasystem;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;

public class Customer 
{
    private String fName;
    private String lName;
    private  String email;
    private  String password;
    private static ObservableList<CartItem> cartItems = FXCollections.observableArrayList();


    public Customer(String fName, String lName, String email, String password)
    {
        this.fName = fName;
        this.lName = lName;
        this.email= email;
        this.password = password;
    }

    public String insertCustomer() throws SQLException
    {
        String stmnt = "INSERT INTO pizzadbsystem.customer (FirstName, LastName, Email, Password) VALUES (?, ?, ?, ?)";
        if(!checkEmail())
        {
            try(PreparedStatement ptStmnt = App.getConnector().prepareStatement(stmnt))
            {
                ptStmnt.setString(1, this.fName);
                ptStmnt.setString(2, this.lName);
                ptStmnt.setString(3, this.email);
                ptStmnt.setString(4, this.password);
                ptStmnt.executeUpdate();
            }
            return "Registration Sucessful!";
        }
        else
        {
            return "Email already in system";
        }

    }

    public int getIndex() throws SQLException
    {
        String stmnt = "SELECT customerId FROM customer WHERE Email = ? AND Password = ?";
        PreparedStatement pStmnt = App.getConnector().prepareStatement(stmnt);
        pStmnt.setString(1, this.email);
        pStmnt.setString(2, this.password);
        ResultSet rs = pStmnt.executeQuery();
        if(rs.next())
        {
            return rs.getInt("customerId");
        }
        else
        {
            return -1;
        }
    }


    private boolean checkEmail() throws SQLException
    {
        String query = "SELECT COUNT(*) FROM pizzadbsystem.customer WHERE Email = ?";
        try(PreparedStatement pStmnt = App.getConnector().prepareStatement(query))
        {
            pStmnt.setString(1, this.email);
            try(ResultSet rs = pStmnt.executeQuery())
            {
                if(rs.next())
                {
                    int count = rs.getInt(1);
                    return count > 0;
                }
            }
        }
        return false;
    }

    public static String addToCart(int pizzaId) throws SQLException
    {
        String stmnt = "INSERT INTO pizzadbsystem.ordertable (customerId, pizzaId, quantity) VALUES (?, ?, ?)";
        String query = "SELECT quantity FROM ordertable WHERE pizzaId = ?";
        String sqlUpdate = "UPDATE ordertable SET quantity = ? WHERE pizzaId = ?";
        if(App.getCustomer() != null)
        {
            PreparedStatement pStmnt2 = App.getConnector().prepareStatement(query);
            PreparedStatement ptStmnt = App.getConnector().prepareStatement(stmnt);
            PreparedStatement ptStmnt3 = App.getConnector().prepareStatement(sqlUpdate);
            pStmnt2.setInt(1, pizzaId);
            ResultSet rs = pStmnt2.executeQuery();
            int orderQuantity = 0;
            if(rs.next())
            {
                for(int i = 0; i < cartItems.size(); i++)
                {
                    if(cartItems.get(i).getItem().equals(Pizza.getPizzaName(pizzaId)))                       
                    {
                        cartItems.get(i).setQuantity(cartItems.get(i).getQuantity()+1);
                    }
                }
                ptStmnt3.setInt(1, rs.getInt("quantity")+1);
                ptStmnt3.setInt(2, pizzaId);
                ptStmnt3.executeUpdate();
                orderQuantity = rs.getInt("quantity")+1;
            }
            else
            {
                ptStmnt.setInt(1, App.getCustomer().getIndex());
                ptStmnt.setInt(2, pizzaId);
                orderQuantity = 1;
                ptStmnt.setInt(3, 1);
                cartItems.add(new CartItem(Pizza.getPizzaName(pizzaId), Pizza.getPizzaPrice(pizzaId), orderQuantity));
                ptStmnt.executeUpdate();
            }
            return "Item Added to cart!";
        }
        else
        {
            return "Please Login or Register.";
        }
    }

    public static  void removeFromCart(CartItem item) throws SQLException
    {
        String query = "SELECT quantity FROM ordertable WHERE pizzaId = ?";
        String sqlDelete = "DELETE FROM ordertable WHERE pizzaId = ?";
        String sqlUpdate = "UPDATE ordertable SET quantity = ? WHERE pizzaId = ? ";
        String sqlSelect = "SELECT pizzaId FROM pizza WHERE Name = ?";
        PreparedStatement quantity = App.getConnector().prepareStatement(query);
        PreparedStatement pStmnt = App.getConnector().prepareStatement(sqlDelete);
        PreparedStatement pStmnt2 = App.getConnector().prepareStatement(sqlUpdate);
        PreparedStatement pStmnt3 = App.getConnector().prepareStatement(sqlSelect);
        pStmnt3.setString(1, item.getItem());
        ResultSet rs2 = pStmnt3.executeQuery();
        rs2.next();
        int pizzaId = rs2.getInt("pizzaId");
        quantity.setInt(1, pizzaId);
        ResultSet rs3 = quantity.executeQuery();
        rs3.next();
        int num = rs3.getInt("quantity");
        if(num > 1)
        {
            pStmnt2.setInt(1, num-1);
            pStmnt2.setInt(2, pizzaId);
            pStmnt2.executeUpdate();
        }
        else
        {
            pStmnt.setInt(1, pizzaId);  
            pStmnt.executeUpdate(); 
        }

    }
    public static  void removeFromTable(CartItem item)
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

    public static ObservableList<CartItem> getCartItems() 
    {
        return cartItems;   
    }

    public  String getEmail()
    {
        return email;
    }

    public  String getPassword()
    {
        return password;
    }
}
