package Server;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
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
//TODO MJ SCUSA PER IL DISORDINE
public class HomePage extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Home");
        //primaryStage.setFullScreen(true); PER METTERE ALL' AVVIO IL FULLSCREEN
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
        vBox.setFillWidth(true);

        GridPane p = new GridPane();
        p.setPrefSize(800, 550); //SIZE BASE DELL PANE ALL'AVVIO
        p.setMinSize(800, 550);  //SIZE MINIMO DELL PANE

        ColumnConstraints Col = new ColumnConstraints();    // QUESTA PARTE SERVE PER FAR CRESCERE TUTTA LA COLONNA
        Col.setHgrow(Priority.ALWAYS);                      // QUADO SI AUMENTA LA FINESTRA
        p.getColumnConstraints().addAll(Col);

        p.setVgap(10);
        p.add(vBox, 0,0);
        p.add(label, 0,1);
        Button buttonMenu = new Button("Menu");

        //Group root = new Group();
        //root.getChildren().addAll(p);
        //root.setAutoSizeChildren(true);

        //Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds(); FUNZIONE PER PREDERE LA GRANDEZZA DELLO
                                                                         // SCHERMO MA CHE NON FUNZIONA PERFETTAMENTE

        Scene scene = new Scene(p, 800, 550);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
