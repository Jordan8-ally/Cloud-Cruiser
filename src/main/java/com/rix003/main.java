package cloud_cruiser.src.main.java.com.rix003;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("weather.fxml"));
        Scene scene = new Scene(root, 800, 600);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Cloud Cruiser");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}