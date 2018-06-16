package ServerGUI;

import ServerSide.ClassOfObservableLists;
import ServerSide.ServerConnection;
import ServerSide.StringCommandList;
import javafx.application.Application;
import javafx.application.Platform;
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

public class HomePage extends Application implements StringCommandList {
    private ClassOfObservableLists classOfObservableLists = new ClassOfObservableLists();
    private ServerConnection server;
    private final String URL = "https://github.com/IngSW-unipv/Progetto-L18";

    @Override
    public void start(Stage primaryStage) {
        // Avvio della connessione server
        this.server = new ServerConnection(80, classOfObservableLists);
        server.start();
        server.setSelectedClient(0);

        // Definizione dello stage principale e della barra del menu
        primaryStage.setTitle("Home");
        ToolbarServer toolbarServer = new ToolbarServer(server);
        MenuPage menuPage = new MenuPage(primaryStage, classOfObservableLists.getObservMenu(), server);
        StatsPage statsPage = new StatsPage(primaryStage, classOfObservableLists, server);

        // Creazione scritta correlata da un logo
        Label label = new Label("Welcome to project Nobildonno Home Page");
        label.setTextFill(Color.FIREBRICK);
        label.setFont(Font.font("Rockwell", 50));
        label.setWrapText(true);

        Image img = new Image("logo.jpg");
        Circle circle = new Circle(110);
        ImagePattern pattern = new ImagePattern(img);
        circle.setFill(pattern);

        label.setGraphic(circle);

        // Creazione toolbar
        VBox vBox = new VBox();
        vBox.getChildren().addAll(toolbarServer);
        vBox.setFillWidth(true);

        // Creazione link al sito del progetto
        VBox linkBox = new VBox();
        linkBox.setFillWidth(true);

        Hyperlink hyperlink = new Hyperlink();
        hyperlink.setText("Click here for Project L-18 web site!");

        hyperlink.setOnAction(e -> {
            getHostServices().showDocument(URL);    // Pagina web aperta nel browser
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
        stackPane.getChildren().addAll(anchor, menuPage.getvBox(), statsPage.getMainPanel());
        menuPage.getvBox().setVisible(false);
        anchor.setVisible(true);
        statsPage.getMainPanel().setVisible(false);
        toolbarServer.Action(anchor, menuPage, statsPage);

        // Gestione pressione del bottone "Statistiche"
        statsButton.setOnAction(event -> {
            menuPage.getvBox().setVisible(false);
            anchor.setVisible(false);
            statsPage.getMainPanel().setVisible(true);
            server.chooseCommandExecutedByThread(SEND_STATS);
            server.chooseCommandExecutedByThread(SEND_COINS);
            server.chooseCommandExecutedByThread(SEND_DATA);
            server.chooseCommandExecutedByThread(SEND_MENU);
        });

        // Gestione pressione del bottone "Menu"
        menuButton.setOnAction( event -> {
            menuPage.getvBox().setVisible(true);
            statsPage.getMainPanel().setVisible(false);
            anchor.setVisible(false);
            server.chooseCommandExecutedByThread(SEND_MENU);
        });

        VBox toolbarBox = new VBox(toolbarServer, stackPane);
        VBox.setVgrow(toolbarServer, Priority.ALWAYS);
        VBox.setVgrow(stackPane, Priority.ALWAYS);
        Scene scene = new Scene(toolbarBox, 800, 550);

        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
        primaryStage.setMinWidth(primaryStage.getWidth());
        primaryStage.setMinHeight(primaryStage.getHeight());

        // Termina l'applicazione alla chiusura
        primaryStage.setOnCloseRequest(t -> {
            Platform.exit();
            System.exit(0);
        });
    }
}
