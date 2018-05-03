package GUI_FX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

;import java.io.FileInputStream;


public class VendingMachine extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Hot Drinks Vending Machine");

        FileInputStream input = new FileInputStream("src/GUI_FX/I.JPG");
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);

        GridPane beveragePane = new GridPane();
        beveragePane.getChildren().add(imageView);

        Scene scene = new Scene(beveragePane, 400, 200, Color.LIGHTGRAY);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
