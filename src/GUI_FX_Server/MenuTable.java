package GUI_FX_Server;

import ServerSide.ServerConnection;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

import static ServerSide.StringCommandList.OVERWRITE_MENU;

public class MenuTable extends TableView {
    private RowTabel[] rowtabel;
    private VBox vBox = new VBox();
    private ObservableList<String> obsvMenu;
    private TableView<RowTabel> tableView = new TableView<>();
    private ObservableList<RowTabel> data = FXCollections.observableArrayList();
    private ArrayList<String> menu = new ArrayList<>();
    private ServerConnection serverConnection;

    public MenuTable(Stage stage, ObservableList<String> obsvMenu, ServerConnection serverConnection) {
        this.obsvMenu = obsvMenu;
        this.serverConnection = serverConnection;

        obsvMenu.addListener((ListChangeListener) change -> Platform.runLater(() -> {
            // Aggiorna UI
            System.out.println("Detected a change! ");
            setRowtabel(obsvMenu);
        }));

        tableView.setEditable(true);

        // Creazione colonne
        // Aggiunta ad ogni colonna dei valori contenuti nella classe rowtabel e resi i valori modificabili
        TableColumn id = new TableColumn("ID");
        this.initTableColumn(id,"id");

        TableColumn tipo = new TableColumn("Tipo");
        this.initTableColumn(tipo,"tipo");
        tipo.setOnEditCommit( new EventHandler<TableColumn.CellEditEvent<RowTabel, String>>() {
            public void handle( TableColumn.CellEditEvent<RowTabel, String> event) {
                ((RowTabel) event.getTableView().getItems().get(event.getTablePosition().getRow())
                ).setTipo(event.getNewValue());
                changeMenu();
            }
        });

        TableColumn nome = new TableColumn("Nome");
        this.initTableColumn(nome,"nome");
        nome.setOnEditCommit( new EventHandler<TableColumn.CellEditEvent<RowTabel, String>>() {
            public void handle( TableColumn.CellEditEvent<RowTabel, String> event) {
                ((RowTabel) event.getTableView().getItems().get(event.getTablePosition().getRow())
                ).setNome(event.getNewValue());
                changeMenu();
            }
        });

        TableColumn costo = new TableColumn("Costo");
        this.initTableColumn(costo,"costo");
        costo.setOnEditCommit( new EventHandler<TableColumn.CellEditEvent<RowTabel, String>>() {
            public void handle( TableColumn.CellEditEvent<RowTabel, String> event) {
                ((RowTabel) event.getTableView().getItems().get(event.getTablePosition().getRow())
                ).setCosto(event.getNewValue());
                changeMenu();
            }
        });

        TableColumn Q_max = new TableColumn("Q_max");
        this.initTableColumn(Q_max,"q_max");
        Q_max.setOnEditCommit( new EventHandler<TableColumn.CellEditEvent<RowTabel, String>>() {
            public void handle( TableColumn.CellEditEvent<RowTabel, String> event) {
                ((RowTabel) event.getTableView().getItems().get(event.getTablePosition().getRow())
                ).setQ_max(event.getNewValue());
                changeMenu();
            }
        });

        TableColumn temp = new TableColumn("Temp");
        this.initTableColumn(temp,"temp");
        temp.setOnEditCommit( new EventHandler<TableColumn.CellEditEvent<RowTabel, String>>() {
            public void handle( TableColumn.CellEditEvent<RowTabel, String> event) {
                ((RowTabel) event.getTableView().getItems().get(event.getTablePosition().getRow())
                ).setTemp(event.getNewValue());
                changeMenu();
            }
        });

        TableColumn dose = new TableColumn("Dose");
        this.initTableColumn(dose,"dose");
        dose.setOnEditCommit( new EventHandler<TableColumn.CellEditEvent<RowTabel, String>>() {
            public void handle( TableColumn.CellEditEvent<RowTabel, String> event) {
                ((RowTabel) event.getTableView().getItems().get(event.getTablePosition().getRow())
                ).setDose(event.getNewValue());
                changeMenu();
            }
        });

        TableColumn latte = new TableColumn("Latte");
        this.initTableColumn(latte,"latte");
        latte.setOnEditCommit( new EventHandler<TableColumn.CellEditEvent<RowTabel, String>>() {
            public void handle( TableColumn.CellEditEvent<RowTabel, String> event) {
                ((RowTabel) event.getTableView().getItems().get(event.getTablePosition().getRow())
                ).setLatte(event.getNewValue());
                changeMenu();
            }
        });

        TableColumn acqua = new TableColumn("Acqua");
        this.initTableColumn(acqua,"acqua");
        acqua.setOnEditCommit( new EventHandler<TableColumn.CellEditEvent<RowTabel, String>>() {
            public void handle( TableColumn.CellEditEvent<RowTabel, String> event) {
                ((RowTabel) event.getTableView().getItems().get(event.getTablePosition().getRow())
                ).setAcqua(event.getNewValue());
                changeMenu();
            }
        });

        TableColumn vodka = new TableColumn("Vodka");
        this.initTableColumn(vodka,"vodka");
        vodka.setOnEditCommit( new EventHandler<TableColumn.CellEditEvent<RowTabel, String>>() {
            public void handle( TableColumn.CellEditEvent<RowTabel, String> event) {
                ((RowTabel) event.getTableView().getItems().get(event.getTablePosition().getRow())
                ).setVodka(event.getNewValue());
                changeMenu();
            }
        });


        final TextField addID = new TextField();
        addID.setPromptText("Id");
        addID.setMaxWidth(75);
        final TextField addTipo = new TextField();
        addTipo.setMaxWidth(75);
        addTipo.setPromptText("Tipo");
        final TextField addNome = new TextField();
        addNome.setMaxWidth(75);
        addNome.setPromptText("Nome");
        final TextField addCosto = new TextField();
        addCosto.setMaxWidth(75);
        addCosto.setPromptText("Costo");
        final TextField addQ_max = new TextField();
        addQ_max.setMaxWidth(75);
        addQ_max.setPromptText("Q_max");
        final TextField addTemp = new TextField();
        addTemp.setMaxWidth(75);
        addTemp.setPromptText("Temp");
        final TextField addDose = new TextField();
        addDose.setMaxWidth(75);
        addDose.setPromptText("Dose");
        final TextField addLatte = new TextField();
        addLatte.setMaxWidth(75);
        addLatte.setPromptText("Latte");
        final TextField addAcqua = new TextField();
        addAcqua.setMaxWidth(75);
        addAcqua.setPromptText("Acqua");
        final TextField addVodka = new TextField();
        addVodka.setMaxWidth(75);
        addVodka.setPromptText("Vodka");

        final Button addButton = new Button("Add");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String adddata = addID.getText()+"\t"+addTipo.getText()+"\t"+addNome.getText()+"\t"+addCosto.getText()
                        +"\t"+addQ_max.getText()+"\t"+addTemp.getText()+"\t"+addDose.getText() +"\t"+addLatte.getText()
                        +"\t"+addAcqua.getText()+"\t"+addVodka.getText();
                data.add(new RowTabel(adddata));
                addID.clear();
                addTipo.clear();
                addNome.clear();
                addCosto.clear();
                addQ_max.clear();
                addTemp.clear();
                addDose.clear();
                addLatte.clear();
                addAcqua.clear();
                addVodka.clear();
                changeMenu();
            }
        });
        final Button remButton = new Button("Remove");
        remButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int index = tableView.getSelectionModel().getSelectedIndex();
                data.remove(index);
                changeId(index);
            }
        });

        addButton.setPrefWidth(50);
        final HBox hb = new HBox();
        hb.getChildren().addAll(addID,addTipo,addNome,addCosto,addQ_max,addTemp,addDose,addLatte,addAcqua,
                addVodka);
        hb.setSpacing(3);
        final HBox hBox = new HBox(addButton,remButton);
        tableView.setItems(data);
        tableView.setStyle("-fx-font: 16px Serif");
        tableView.getColumns().addAll(id, tipo, nome, costo, Q_max, temp, dose, latte, acqua, vodka);
        tableView.setEditable(true);

        vBox.getChildren().addAll(tableView,hb,hBox);
        vBox.setFillWidth(true);
        VBox.setVgrow(tableView, Priority.ALWAYS);
        vBox.prefHeightProperty().bind(stage.heightProperty());
        vBox.prefWidthProperty().bind(stage.widthProperty());

    }

    /**
     * Classe menu utilizzata per l'aggiunta dei dati nella rowtabel.
     */
    public static class RowTabel {
        private SimpleStringProperty id;
        private SimpleStringProperty tipo;
        private SimpleStringProperty nome;
        private SimpleStringProperty costo;
        private SimpleStringProperty q_max;
        private SimpleStringProperty temp;
        private SimpleStringProperty dose;
        private SimpleStringProperty latte;
        private SimpleStringProperty acqua;
        private SimpleStringProperty vodka;

        private RowTabel(String menuDati) {
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

        public SimpleStringProperty idProperty() {
            return id;
        }

        public SimpleStringProperty tipoProperty() {
            return tipo;
        }

        public SimpleStringProperty nomeProperty() {
            return nome;
        }

        public SimpleStringProperty costoProperty() {
            return costo;
        }

        public SimpleStringProperty q_maxProperty() {
            return q_max;
        }

        public SimpleStringProperty tempProperty() {
            return temp;
        }

        public SimpleStringProperty doseProperty() {
            return dose;
        }

        public SimpleStringProperty latteProperty() {
            return latte;
        }

        public SimpleStringProperty acquaProperty() {
            return acqua;
        }

        public SimpleStringProperty vodkaProperty() {
            return vodka;
        }

        public String getId() {
            return id.get();
        }

        public String getTipo() {
            return tipo.get();
        }

        public String getNome() {
            return nome.get();
        }

        public String getCosto() {
            return costo.get();
        }

        public String getQ_max() {
            return q_max.get();
        }

        public String getTemp() {
            return temp.get();
        }

        public String getDose() {
            return dose.get();
        }

        public String getLatte() {
            return latte.get();
        }

        public String getAcqua() {
            return acqua.get();
        }

        public String getVodka() {
            return vodka.get();
        }

        public void setId(String id) {
            this.id.set(id);
        }

        public void setTipo(String tipo) {
            this.tipo.set(tipo);
        }

        public void setNome(String nome) {
            this.nome.set(nome);
        }

        public void setCosto(String costo) {
            this.costo.set(costo);
        }

        public void setQ_max(String q_max) {
            this.q_max.set(q_max);
        }

        public void setTemp(String temp) {
            this.temp.set(temp);
        }

        public void setDose(String dose) {
            this.dose.set(dose);
        }

        public void setLatte(String latte) {
            this.latte.set(latte);
        }

        public void setAcqua(String acqua) {
            this.acqua.set(acqua);
        }

        public void setVodka(String vodka) {
            this.vodka.set(vodka);
        }
    }

    public void setRowtabel(ObservableList<String> obsvMenu) {
        int size = 0;
        for(int i = 0;i<obsvMenu.size();i++){
            if(!obsvMenu.get(i).contains("*")){
            size++;}
        }

        rowtabel = new RowTabel[size];
        data.clear();
        int tabindex = 0;
        for (int i = 0; i < obsvMenu.size(); i++ ) {
            menu.add(obsvMenu.get(i));
            if (!obsvMenu.get(i).contains("*")) {
                rowtabel[tabindex] = new RowTabel(obsvMenu.get(i));
                tabindex++;
            }

        }
        for (int j = 0; j < size; j++) {
            data.addAll(rowtabel[j]);
        }
    }

    public VBox getvBox() {
        return vBox;
    }

    private void initTableColumn(TableColumn tableColumn, String name) {
        tableColumn.setCellValueFactory(new PropertyValueFactory<RowTabel, String>(name));
        tableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    private void changeMenu() {
        menu.clear();
        menu.add("* le righe con * vengono saltate nella lettura");
        menu.add("* ID  TIPO  \tNOME  \t    COSTO  Q_MAX TEMP DOSE latte acqua\tvodka");
        for (int row = 0; row < tableView.getItems().size(); row++) {
            String tmp = "";
            for (int column = 0; column < 10; column++ ) {
                tmp = tmp + tableView.getColumns().get(column).getCellObservableValue(row).getValue();
                if (!(column == 9)){
                    tmp = tmp + "\t";
                }
            }
            if(!tmp.equals("")){
            menu.add(tmp);}
        }
        menu.add("*");
    }
    private void changeId(int index){
        for(int i = index; i < data.size(); i++){
            String newId = "0"+(i+1);
            tableView.getItems().get(i).setId(newId);
            changeMenu();
            //data.get(i).setId(newId);
        }
    }
    public void sendMenu(){
        obsvMenu.clear();
        obsvMenu.addAll(menu);
        serverConnection.chooseCommandExecutedByThread(OVERWRITE_MENU);
    }
}


