package GUI_FX_Server;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.Style;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static Server.FileServer.fileMenuServer;

public class MenuTable extends TableView {

    File fileMenu = fileMenuServer;
    menu[] menuV;

    public MenuTable(Stage stage) {

        menuV = new menu[initRows()];
        this.setTextField();
        TableView<menu> tableView = new TableView<>();

        ObservableList<menu> data =
                FXCollections.observableArrayList();

        for(int i = 0; i < menuV.length; i++) {
            data.addAll(menuV[i]);
        }

        TableColumn id = new TableColumn("ID");
        id.setCellValueFactory(new PropertyValueFactory<menu, String>("id"));
        TableColumn tipo = new TableColumn("Tipo");
        tipo.setCellValueFactory(new PropertyValueFactory<menu, String>("tipo"));
        TableColumn nome = new TableColumn("Nome");
        nome.setCellValueFactory(new PropertyValueFactory<menu, String>("nome"));
        TableColumn costo = new TableColumn("Costo");
        costo.setCellValueFactory(new PropertyValueFactory<menu, String>("costo"));
        TableColumn Q_max = new TableColumn("Q_max");
        Q_max.setCellValueFactory(new PropertyValueFactory<menu, String>("q_max"));
        TableColumn temp = new TableColumn("Temp");
        temp.setCellValueFactory(new PropertyValueFactory<menu, String>("temp"));
        TableColumn dose = new TableColumn("Dose");
        dose.setCellValueFactory(new PropertyValueFactory<menu, String>("dose"));
        TableColumn latte = new TableColumn("Latte");
        latte.setCellValueFactory(new PropertyValueFactory<menu, String>("latte"));
        TableColumn acqua = new TableColumn("Acqua");
        acqua.setCellValueFactory(new PropertyValueFactory<menu, String>("acqua"));
        TableColumn vodka = new TableColumn("vodka");
        vodka.setCellValueFactory(new PropertyValueFactory<menu, String>("vodka"));
        tableView.setItems(data);
        tableView.setEditable(false);
        tableView.setStyle("-fx-font: 16px Serif");

        tableView.getColumns().addAll(id, tipo, nome, costo, Q_max, temp, dose, latte, acqua, vodka);

        Group root = new Group();

        GridPane mainPanel = new GridPane();

        Toolbar1 toolbar1 = new Toolbar1(stage);
        Button save = new Button("Save");
        toolbar1.getItems().add(save);

        //MenuBar1 menuBar1 = new MenuBar1(stage);
        VBox vBox = new VBox();
        vBox.getChildren().addAll(toolbar1/*, menuBar1*/, tableView);
        vBox.setFillWidth(true);
        VBox.setVgrow(toolbar1, Priority.ALWAYS);
        VBox.setVgrow(tableView, Priority.ALWAYS);

        stage.setTitle("MenuTable");

        Scene scene = new Scene(root, 800, 550, Color.WHITESMOKE);

        mainPanel.addRow(0,vBox);

        vBox.prefHeightProperty().bind(scene.heightProperty());
        vBox.prefWidthProperty().bind(scene.widthProperty());

        mainPanel.prefHeightProperty().bind(scene.heightProperty());
        mainPanel.prefWidthProperty().bind(scene.widthProperty());
        root.getChildren().addAll(mainPanel);
        stage.setScene(scene);
        stage.show();

    }



    /**
     * Funzione che scrive il testo nei textField.
     */
    private void setTextField(){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileMenu.getPath()));
            String tmp;
            int index = 0;
            while((tmp = bufferedReader.readLine()) != null){
                if (!tmp.contains("*")){
                    menuV[index] = new menu(tmp.split("\t"));
                    index++;
                }
            }
            bufferedReader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private int initRows(){
        try{
            BufferedReader bufferedReader = new BufferedReader( new FileReader(fileMenuServer.getPath()));
            String tmp;
            int rows = 0; // Parto da 2, una riga per ID... e una vuota nel caso di aggiunte future.
            while ((tmp = bufferedReader.readLine()) != null){
                if (!tmp.contains("*")){
                    rows++;
                }
            }
            bufferedReader.close();
            return rows;
        } catch (IOException e){
            e.printStackTrace();
        }
        return 0;
    }

    public static class menu {

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

        private menu(String[] ID) {
            this.id = new SimpleStringProperty(ID[0]);
            this.tipo = new SimpleStringProperty(ID[1]);
            this.nome = new SimpleStringProperty(ID[2]);
            this.costo = new SimpleStringProperty(ID[3]);
            this.q_max = new SimpleStringProperty(ID[4]);
            this.temp = new SimpleStringProperty(ID[5]);
            this.dose = new SimpleStringProperty(ID[6]);
            this.latte = new SimpleStringProperty(ID[7]);
            this.acqua = new SimpleStringProperty(ID[8]);
            this.vodka = new SimpleStringProperty(ID[9]);
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
}

