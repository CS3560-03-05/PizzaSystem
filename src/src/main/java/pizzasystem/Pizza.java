package pizzasystem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Pizza 
{

    public static String getPizzaName(int index) throws SQLException
    {
        String query = "SELECT Name FROM pizza WHERE pizzaID = ?";
        PreparedStatement stmnt = App.getConnector().prepareStatement(query);
        stmnt.setInt(1, index);
        ResultSet rs =stmnt.executeQuery();
        if(rs.next())
        {
            return rs.getString("Name");
        }
        else
        {
            return "error";
        }
    }

        public static int getPizzaPrice(int index) throws SQLException
    {
        String query = "SELECT Price FROM pizza WHERE pizzaID = ?";
        PreparedStatement stmnt = App.getConnector().prepareStatement(query);
        stmnt.setInt(1, index);
        ResultSet rs =stmnt.executeQuery();
        if(rs.next())
        {
            return rs.getInt("Price");
        }
        else
        {
            return -1;
        }
    }

}

