package pizzasystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Connection db;
    private static Customer customer;


    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("home"), 520, 820);          //window size (let me know if too small)
        stage.setScene(scene);                                      //scene when starting app
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {               //sets the root of app
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));      //loadsFXMLS
        return fxmlLoader.load();
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        db =DriverManager.getConnection("jdbc:mysql://localhost:3306/pizzadbsystem", "root", "hydroConfig7!");
        launch();
    }

    public static Connection getConnector()
    {
        return db;
    }

    public static Customer getCustomer() throws SQLException
    {
        return customer;
    }
    public static void setCustomer(Customer c)
    {
        customer = c;
    }

}