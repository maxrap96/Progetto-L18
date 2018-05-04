package GUI_FX_Server;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
//TODO MJ SCUSA PER IL DISORDINE
public class HomePage extends Application {

    @Override
    public void start(Stage primaryStage) {

        // Definizione dello stage principale e della barra del menu
        primaryStage.setTitle("Home");
        MenuBar mb = new MenuBar1();

        // Creazione GridPane
        GridPane gPane = new GridPane();
        gPane.setPrefSize(800, 550); // Size base del pane all'avvio
        gPane.setMinSize(800, 550);  //Size minimo del pane
        ColumnConstraints Col = new ColumnConstraints();    // Server per far crescere tutta la colonna quando si
        Col.setHgrow(Priority.ALWAYS);                      // aumenta la finestra
        gPane.getColumnConstraints().addAll(Col);

        // Creazione scritta correlata da un logo
        Label label = new Label("Welcome to project Nobildonno Home Page");
        label.setTextFill(Color.FIREBRICK);
        label.setFont(Font.font("Rockwell", 50));
        label.setWrapText(true);

        ImageView img = new ImageView("./ServerImages/download.jpg");
        img.setFitHeight(250);
        img.setFitWidth(250);

        label.setGraphic(img);
        //label.setAlignment(Pos.BOTTOM_CENTER);    Dovrebbe posizionare il testo ma non funziona hahaha

        // Creazione VBox, commento da rivedere (@luce)
        VBox vBox = new VBox(mb);
        vBox.setFillWidth(true);

        // Creazione bottoni
        ButtonBar buttonBar = new ButtonBar();

        Button menuButton = new Button("Menu");
        ButtonBar.setButtonData(menuButton, ButtonBar.ButtonData.OTHER);
        menuButton.setPrefSize(85, 50);
        //TODO MJ: aggiungere actionevent e eventhandler ai bottoni (button.setOnAction)

        Button statsButton = new Button("Statistiche");
        ButtonBar.setButtonData(statsButton, ButtonBar.ButtonData.OTHER);
        statsButton.setPrefSize(85, 50);

        buttonBar.getButtons().addAll(menuButton, statsButton);

        // Aggiunta elementi nel Pane
        gPane.setVgap(10);
        gPane.add(vBox, 0,0);
        gPane.add(label, 0,1);
        gPane.add(buttonBar, 0, 2);

        //Group root = new Group();
        //root.getChildren().addAll(p);
        //root.setAutoSizeChildren(true);

        //Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds(); // FUNZIONE PER PRENDERE LA GRANDEZZA DELLO
                                                                         // SCHERMO MA CHE NON FUNZIONA PERFETTAMENTE

        Scene scene = new Scene(gPane, 800, 550);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
