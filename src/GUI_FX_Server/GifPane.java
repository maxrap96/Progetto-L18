package GUI_FX_Server;

import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.Group;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.IOException;

public class GifPane extends BorderPane {

    final private ImageView GC0 = new ImageView(loadImage("src/GifFolder/GifCoffee_0.png"));
    final private ImageView GC1 = new ImageView(loadImage("src/GifFolder/GifCoffee_1.png"));
    final private ImageView GC2 = new ImageView(loadImage("src/GifFolder/GifCoffee_2.png"));
    final private ImageView GC3 = new ImageView(loadImage("src/GifFolder/GifCoffee_3.png"));
    final private ImageView GC4 = new ImageView(loadImage("src/GifFolder/GifCoffee_4.png"));
    final private ImageView GC5 = new ImageView(loadImage("src/GifFolder/GifCoffee_5.png"));
    final private ImageView GC6 = new ImageView(loadImage("src/GifFolder/GifCoffee_6.png"));
    final private ImageView GC7 = new ImageView(loadImage("src/GifFolder/GifCoffee_7.png"));
    final private ImageView GC8 = new ImageView(loadImage("src/GifFolder/GifCoffee_8.png"));
    final private ImageView GC9 = new ImageView(loadImage("src/GifFolder/GifCoffee_9.png"));
    private ImageView[] slides;
    Group group;
    ProgressBar progressBar;

    public GifPane() {
        // Inizializzo Group() in cui conterrò le immagini
        group = new Group();
        this.setBottom(group);

        // Inizializzo vettore immagini
        this.slides = new ImageView[]{GC0, GC1, GC2, GC3, GC4, GC5, GC6, GC7, GC8, GC9};

        // Inizializzo la progress bar
        progressBar = new ProgressBar();
        // Imposto l'Id per usare file CSS
        progressBar.setId("pb");
        progressBar.setPrefWidth(Double.MAX_VALUE);
        this.setTop(progressBar);
    }

    public void start() {
        SequentialTransition slideshow = new SequentialTransition();

        for (ImageView slide : slides) {

            SequentialTransition sequentialTransition = new SequentialTransition();

            FadeTransition fadeIn = getFadeTransition(slide, 1.0, 400);

            sequentialTransition.getChildren().addAll(fadeIn);
            slide.setOpacity(0);
            group.getChildren().add(slide);
            slideshow.getChildren().add(sequentialTransition);
        }
        slideshow.play();
    }

    /**
     * Funzione che imposta la transizione.
     *
     * @param imageView immagine da passare al costruttore FadeTransition.
     * @param Value valore di dissolvenza.
     * @param durationInMilliseconds durata della transizione.
     *
     * Nota: Value è impostato uguale in quanto non voglio alcun effetto dissolvenza, ma solo un'immagine dopo
     * l'altra.
     */

    public FadeTransition getFadeTransition(ImageView imageView, double Value, int durationInMilliseconds) {
        FadeTransition ft = new FadeTransition(Duration.millis(durationInMilliseconds), imageView);
        ft.setFromValue(Value);
        ft.setToValue(Value);
        return ft;

    }

    /**
     * Funzione che carica un'immagine.
     *
     * @param url percorso da cui carico l'immagine.
     */
    private Image loadImage(String url){
        Image imgTmp = null;
        try {
            FileInputStream input = new FileInputStream(url);
            imgTmp = new Image(input);
            input.close();
            return imgTmp;
        } catch (IOException e){
            e.getMessage();
        }
        return imgTmp;
    }
}
