package pizzasystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    static ArrayList<String> people = new ArrayList<>();                //temporary for testing
    static ArrayList<String> passwords = new ArrayList<>();
    private static CartService cartService = new CartService();


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

    public static void main(String[] args) {
        launch();
    }

    public static CartService getServiceCart()
    {
        return cartService;                             //returns cart service temporary (for testing)
    }

}