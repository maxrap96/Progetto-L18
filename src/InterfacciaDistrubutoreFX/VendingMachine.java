package InterfacciaDistrubutoreFX;

import javafx.application.Application;
<<<<<<< HEAD
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
=======
import javafx.scene.layout.VBox;
>>>>>>> 898badacc2a08793944ea0a2cfd8ab478ae32e18
import javafx.stage.Stage;

;import java.io.FileInputStream;

<<<<<<< HEAD

public class VendingMachine extends Application {
=======
    private VBox vBox = new VBox();

    public static void main(String[] args) {
        launch(args);
    }
>>>>>>> 898badacc2a08793944ea0a2cfd8ab478ae32e18

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Hot Drinks Vending Machine");

        FileInputStream input = new FileInputStream("src/InterfacciaDistrubutoreFX/I.JPG");
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
