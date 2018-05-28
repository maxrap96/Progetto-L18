package GUI_FX_Server;

import ServerSide.ServerConnection;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

public class HomePage extends Application {

    //private static ArrayList<String> stats = new ArrayList<>();
    private static ObservableList<String> observStats = FXCollections.observableArrayList();
    //private static ArrayList<String> menu =  new ArrayList<>();
    private static ObservableList<String> observMenu = FXCollections.observableArrayList();
    //private static ArrayList<String> coins =  new ArrayList<>();
    private static ObservableList<String> observCoins = FXCollections.observableArrayList();
    //private static ArrayList<String> data =  new ArrayList<>();
    private static ObservableList<String> observData = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) {

        // Definizione dello stage principale e della barra del menu
        primaryStage.setTitle("Home");
        Toolbar1 toolbar1 = new Toolbar1();
        MenuTable menuTable = new MenuTable(primaryStage, observMenu);
        StatsPage statsPage = new StatsPage(primaryStage, observStats, observData, observCoins);

        // Creazione scritta correlata da un logo
        Label label = new Label("Welcome to project Nobildonno Home Page");
        label.setTextFill(Color.FIREBRICK);
        label.setFont(Font.font("Rockwell", 50));
        label.setWrapText(true);

        Image img = new Image("GUI_FX_Server/logo.jpg");
        Circle circle = new Circle(110);
        ImagePattern pattern = new ImagePattern(img);
        circle.setFill(pattern);

        label.setGraphic(circle);

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
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(anchor,menuTable.getvBox(),statsPage.getMainPanel());
        menuTable.getvBox().setVisible(false);
        anchor.setVisible(true);
        statsPage.getMainPanel().setVisible(false);
        toolbar1.Action(anchor,menuTable,statsPage);

        // Gestione pressione del bottone "Statistiche"
        statsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                menuTable.getvBox().setVisible(false);
                anchor.setVisible(false);
                statsPage.getMainPanel().setVisible(true);
            }
        });

        menuButton.setOnAction( event -> {

            menuTable.getvBox().setVisible(true);
            anchor.setVisible(false);
            statsPage.getMainPanel().setVisible(false);
            /*// Serve solo per vedere che venga caricato il valore corretto
            if (!menu.isEmpty()){
                for (String string : menu){
                    System.out.println(string);
                }
            }*/
        });

        VBox vBox1 = new VBox(toolbar1,stackPane);
        VBox.setVgrow(toolbar1,Priority.ALWAYS);
        VBox.setVgrow(stackPane,Priority.ALWAYS);
        Scene scene = new Scene(vBox1, 800, 550);

        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        //primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.setMinWidth(primaryStage.getWidth());
        primaryStage.setMinHeight(primaryStage.getHeight());
    }

    public static void main(String[] args) {
        new ServerConnection(80, observStats, observMenu, observCoins, observData).start();
        Application.launch(args);
    }
}