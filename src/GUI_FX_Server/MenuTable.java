package GUI_FX_Server;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;


public class MenuTable extends TableView {

    Tabella[] tabella;
    VBox vBox = new VBox();
    private ArrayList<String> menuDati;

    public MenuTable(Stage stage, ArrayList<String> menuDati) {
        this.menuDati = menuDati;
        tabella = new Tabella[menuDati.size()];
        TableView<Tabella> tableView = new TableView<>();

        for(int i = 0; i < menuDati.size();i++ ){
            if(!menuDati.get(i).startsWith("*")) {
                tabella[i] = new Tabella(menuDati.get(i));
            }
        }

        ObservableList<Tabella> data = FXCollections.observableArrayList();
        for(int i = 0; i < tabella.length; i++) {
            data.addAll(tabella[i]);
        }

        //Aggiungo ad ogni colonna i valori contenuti nella classe menu
        TableColumn id = new TableColumn("ID");
        id.setCellValueFactory(new PropertyValueFactory<Tabella, String>("id"));
        TableColumn tipo = new TableColumn("Tipo");
        tipo.setCellValueFactory(new PropertyValueFactory<Tabella, String>("tipo"));
        TableColumn nome = new TableColumn("Nome");
        nome.setCellValueFactory(new PropertyValueFactory<Tabella, String>("nome"));
        TableColumn costo = new TableColumn("Costo");
        costo.setCellValueFactory(new PropertyValueFactory<Tabella, String>("costo"));
        TableColumn Q_max = new TableColumn("Q_max");
        Q_max.setCellValueFactory(new PropertyValueFactory<Tabella, String>("q_max"));
        TableColumn temp = new TableColumn("Temp");
        temp.setCellValueFactory(new PropertyValueFactory<Tabella, String>("temp"));
        TableColumn dose = new TableColumn("Dose");
        dose.setCellValueFactory(new PropertyValueFactory<Tabella, String>("dose"));
        TableColumn latte = new TableColumn("Latte");
        latte.setCellValueFactory(new PropertyValueFactory<Tabella, String>("latte"));
        TableColumn acqua = new TableColumn("Acqua");
        acqua.setCellValueFactory(new PropertyValueFactory<Tabella, String>("acqua"));
        TableColumn vodka = new TableColumn("vodka");
        vodka.setCellValueFactory(new PropertyValueFactory<Tabella, String>("vodka"));

        tableView.setItems(data);
        tableView.setEditable(false);
        tableView.setStyle("-fx-font: 16px Serif");

        tableView.getColumns().addAll(id, tipo, nome, costo, Q_max, temp, dose, latte, acqua, vodka);

        //Creazione toolbar e aggiunta tasto Save ad essa
        Button save = new Button("Save");

        vBox.getChildren().addAll(tableView);
        vBox.setFillWidth(true);;
        VBox.setVgrow(tableView, Priority.ALWAYS);

        vBox.prefHeightProperty().bind(stage.heightProperty());
        vBox.prefWidthProperty().bind(stage.widthProperty());
    }

    /**
     * Classe menu utilizzata per l'aggiunta dei dati nella tabella
     */
    public static class Tabella {

        private  SimpleStringProperty id;
        private  SimpleStringProperty tipo;
        private  SimpleStringProperty nome;
        private  SimpleStringProperty  costo;
        private  SimpleStringProperty  q_max;
        private  SimpleStringProperty  temp;
        private  SimpleStringProperty  dose;
        private  SimpleStringProperty  latte;
        private  SimpleStringProperty  acqua;
        private  SimpleStringProperty vodka;

        private Tabella(String menuDati) {
            String[] dati = menuDati.split("\t");
            this.id = new SimpleStringProperty(dati[0]);
            this.tipo = new SimpleStringProperty(dati[1]);
            this.nome = new SimpleStringProperty(dati[2]);
            this.costo = new SimpleStringProperty(dati[3]);
            this.q_max = new SimpleStringProperty(dati[4]);
            this.temp = new SimpleStringProperty(dati[5]);
            this.dose = new SimpleStringProperty(dati[6]);
            this.latte = new SimpleStringProperty(dati[7]);
            this.acqua = new SimpleStringProperty(dati[8]);
            this.vodka = new SimpleStringProperty(dati[9]);
        }


        public String getId() {
            return id.get();
        }

        public SimpleStringProperty idProperty() {
            return id;
        }

        public String getTipo() {
            return tipo.get();
        }

        public SimpleStringProperty tipoProperty() {
            return tipo;
        }

        public String getNome() {
            return nome.get();
        }

        public SimpleStringProperty nomeProperty() {
            return nome;
        }

        public String getCosto() {
            return costo.get();
        }

        public SimpleStringProperty costoProperty() {
            return costo;
        }

        public String getQ_max() {
            return q_max.get();
        }

        public SimpleStringProperty q_maxProperty() {
            return q_max;
        }

        public String getTemp() {
            return temp.get();
        }

        public SimpleStringProperty tempProperty() {
            return temp;
        }

        public String getDose() {
            return dose.get();
        }

        public SimpleStringProperty doseProperty() {
            return dose;
        }

        public String getLatte() {
            return latte.get();
        }

        public SimpleStringProperty latteProperty() {
            return latte;
        }

        public String getAcqua() {
            return acqua.get();
        }

        public SimpleStringProperty acquaProperty() {
            return acqua;
        }

        public String getVodka() {
            return vodka.get();
        }

        public SimpleStringProperty vodkaProperty() {
            return vodka;
        }


    }

    public VBox getvBox() {
        return vBox;
    }
}


