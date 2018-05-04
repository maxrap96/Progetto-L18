package GUI_FX;

import Distributore.Distributore;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

;import java.awt.*;
import java.io.FileInputStream;


public class VendingMachine extends Application {

    Distributore distributore = new Distributore();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth() / 2;
        double height = screenSize.getHeight() / 2;

        primaryStage.setTitle("Hot Drinks Vending Machine");

        // Creo il pannello radice a cui attaccare tutti gli altri, ad ora è uno StackPane, ma se ne possono usare
        // di più comodi all'accorrenza.
        StackPane root = new StackPane();

        FileInputStream input = new FileInputStream("src/GUI_FX/I.JPG");
        Image image = new Image(input);
        input.close();

        // Creo le dimensioni per lo sfondo
        BackgroundSize backgroundSize =
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false,
                        false, false, true);

        // Creo l'immagine di sfondo
        BackgroundImage changeNameWhenFinalImage =
                new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER, backgroundSize);

        // Metto lo sfondo
        root.setBackground(new Background(changeNameWhenFinalImage));



        GridPane beveragePane = new BeverageGrid(distributore);
        root.getChildren().add(beveragePane);

        Scene scene = new Scene(root, width, height, Color.LIGHTGRAY);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
