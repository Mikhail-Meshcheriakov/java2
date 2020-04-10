package lesson4.sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        Controller controller =loader.getController();
        primaryStage.setTitle("Chat");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(400);
        controller.messageTextField.requestFocus();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
