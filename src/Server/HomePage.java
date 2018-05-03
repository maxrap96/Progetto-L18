package Server;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HomePage extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Home");

        Label label = new Label("Welcome to project Nobildonno Home Page");
        label.setTextFill(Color.FIREBRICK);
        label.setFont(Font.font("Rockwell", 50));
        label.setAlignment(Pos.TOP_CENTER);
        label.setWrapText(true);

        ImageView img = new ImageView("file:src/Server/download.jpg");
        img.setFitHeight(250);
        img.setFitWidth(250);
        label.setGraphic(img);

        Button buttonMenu = new Button("Menu");

        final Scene scene = new Scene(label, 700, 450);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
