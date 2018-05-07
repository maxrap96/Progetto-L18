package GUI_FX_Server;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TestGif extends Application {



        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage primaryStage) {
            GridPane root = new GridPane();
            GifPane gifPane = new GifPane();

            // Creo un bottone di avvio
            Button start = new Button("Start");
            start.setMaxSize(300,300);
            start.setMinSize(0,0);
            start.setFont(Font.font("Rage Italic", 30));

            // Aggiungo il bottone
            start.setWrapText(true);
            root.add(start, 0,0);

            start.setOnAction(event -> {
                root.add(gifPane, 0 ,1);
                gifPane.start();
            });

            root.getStylesheets().add("GUI_FX_Server/Style.css");
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.setMaximized(true);
        }

}

