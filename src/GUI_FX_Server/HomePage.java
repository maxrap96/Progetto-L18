package GUI_FX_Server;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HomePage extends Application {

    @Override
    public void start(Stage primaryStage) {

        // Definizione dello stage principale e della barra del menu
        primaryStage.setTitle("Home");

        Toolbar1 toolbar1 = new Toolbar1(primaryStage);

        // Creazione scritta correlata da un logo
        Label label = new Label("Welcome to project Nobildonno Home Page");
        label.setTextFill(Color.FIREBRICK);
        label.setFont(Font.font("Rockwell", 50));
        label.setWrapText(true);

        ImageView img = new ImageView("GUI_FX_Server/download.jpg");
        img.setFitHeight(250);
        img.setFitWidth(250);

        label.setGraphic(img);

        // Creazione toolbar
        VBox vBox = new VBox();
        vBox.getChildren().addAll(toolbar1);
        vBox.setFillWidth(true);

        // Creazione link al sito del progetto
        VBox linkBox = new VBox();
        linkBox.setFillWidth(true);

        Hyperlink hyperlink = new Hyperlink();
        hyperlink.setText("Click here for Project L-18 web site!");

        final String url = "https://github.com/IngSW-unipv/Progetto-L18";

        hyperlink.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                getHostServices().showDocument(url);    // Pagina web aperta nel browser
            }
        });

        HBox hbox = new HBox();
        hbox.getChildren().addAll(hyperlink);
        linkBox.getChildren().addAll(hbox);

        // Creazione bottoni
        ButtonBar buttonBar = new ButtonBar();

        Button menuButton = new Button("Menu");
        ButtonBar.setButtonData(menuButton, ButtonBar.ButtonData.LEFT);
        menuButton.setPrefSize(85, 50);

        Button statsButton = new Button("Statistiche");
        ButtonBar.setButtonData(statsButton, ButtonBar.ButtonData.LEFT);
        buttonBar.setLayoutX(100);
        statsButton.setPrefSize(85, 50);

        buttonBar.getButtons().addAll(menuButton, statsButton);

        // Gestione pressione del bottone "Statistiche"
        statsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new StatsPage(primaryStage);
            }
        });

        menuButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new MenuTable(primaryStage);
            }
        });

        // Aggiunta elementi nel Pane
        AnchorPane anchor = new AnchorPane(vBox, label, buttonBar, hyperlink);
        anchor.setStyle("-fx-background-color: lavender");

        anchor.setTopAnchor(vBox, 0.0);
        anchor.setLeftAnchor(vBox, 0.0);
        anchor.setRightAnchor(vBox, 0.0);

        anchor.setTopAnchor(label, 100.0);
        anchor.setLeftAnchor(label, 0.0);
        anchor.setRightAnchor(label, 0.0);

        anchor.setBottomAnchor(buttonBar, 40.0);
        anchor.setLeftAnchor(buttonBar, 80.0);
        anchor.setRightAnchor(buttonBar, 80.0);

        anchor.setLeftAnchor(hyperlink, 0.0);
        anchor.setBottomAnchor(hyperlink, 0.0);

        // Impostazioni scena e stage principale
        Scene scene = new Scene(anchor, 800, 550);

        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        //primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.setMinWidth(primaryStage.getWidth());
        primaryStage.setMinHeight(primaryStage.getHeight());

        // Avvio Server e client di Prova
        new ServerConnection(80).run();
        new ClientProva1("localhost", 80).run();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}