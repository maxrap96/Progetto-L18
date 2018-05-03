package Server;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class HomePage extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Home");
        //primaryStage.setFullScreen(true);
        MenuBar mb = new MenuBar1();

        Label label = new Label("Welcome to project Nobildonno Home Page");
        label.setTextFill(Color.FIREBRICK);
        label.setFont(Font.font("Rockwell", 50));
        label.setWrapText(true);

        ImageView img = new ImageView("file:src/Server/download.jpg");
        img.setFitHeight(250);
        img.setFitWidth(250);

        label.setGraphic(img);
        label.setAlignment(Pos.BOTTOM_CENTER);
        VBox vBox = new VBox(mb);
        GridPane p = new GridPane();
        p.setVgap(10);
        p.add(vBox, 0,0);
        p.add(label, 0,2);
        Button buttonMenu = new Button("Menu");
        Group root = new Group();
        root.getChildren().addAll(p);
       // p.setGridLinesVisible(true);
        //Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        Scene scene = new Scene(root, 800, 550);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
