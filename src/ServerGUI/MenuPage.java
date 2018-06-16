package ServerGUI;

import ServerSide.ServerConnection;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
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

public class MenuPage extends TableView {
    private RowTable[] rowTable;
    private VBox vBox = new VBox();
    private ObservableList<String> obsvMenu;
    private TableView<RowTable> tableView = new TableView<>();
    private ObservableList<RowTable> data = FXCollections.observableArrayList();
    private ArrayList<String> menu = new ArrayList<>();
    private ServerConnection serverConnection;

    public MenuPage(Stage stage, ObservableList<String> obsvMenu, ServerConnection serverConnection) {
        this.obsvMenu = obsvMenu;
        this.serverConnection = serverConnection;

        obsvMenu.addListener((ListChangeListener) change -> Platform.runLater(() -> {
            // Aggiorna UI
            System.out.println("Detected a change!");
            setRowTable(obsvMenu);
        }));

        tableView.setEditable(true);

        // Creazione colonne
        // Aggiunta ad ogni colonna dei valori contenuti nella classe rowTable e resi i valori modificabili
        TableColumn id = new TableColumn("ID");
        this.initTableColumn(id,"id");

        TableColumn type = new TableColumn("Tipo");
        this.initTableColumn(type,"type");
        type.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<RowTable, String>>() {
            public void handle(TableColumn.CellEditEvent<RowTable, String> event) {
                ((RowTable) event.getTableView().getItems().get(event.getTablePosition().getRow())
                ).setType(event.getNewValue());
                changeMenu();
            }
        });

        TableColumn name = new TableColumn("Nome");
        this.initTableColumn(name,"name");
        name.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<RowTable, String>>() {
            public void handle(TableColumn.CellEditEvent<RowTable, String> event) {
                ((RowTable) event.getTableView().getItems().get(event.getTablePosition().getRow())
                ).setName(event.getNewValue());
                changeMenu();
            }
        });

        TableColumn price = new TableColumn("Costo");
        this.initTableColumn(price,"price");
        price.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<RowTable, String>>() {
            public void handle(TableColumn.CellEditEvent<RowTable, String> event) {
                ((RowTable) event.getTableView().getItems().get(event.getTablePosition().getRow())
                ).setPrice(event.getNewValue());
                changeMenu();
            }
        });

        TableColumn Q_max = new TableColumn("Q_max");
        this.initTableColumn(Q_max,"q_max");
        Q_max.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<RowTable, String>>() {
            public void handle(TableColumn.CellEditEvent<RowTable, String> event) {
                ((RowTable) event.getTableView().getItems().get(event.getTablePosition().getRow())
                ).setQ_max(event.getNewValue());
                changeMenu();
            }
        });

        TableColumn temp = new TableColumn("Temp");
        this.initTableColumn(temp,"temp");
        temp.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<RowTable, String>>() {
            public void handle(TableColumn.CellEditEvent<RowTable, String> event) {
                ((RowTable) event.getTableView().getItems().get(event.getTablePosition().getRow())
                ).setTemp(event.getNewValue());
                changeMenu();
            }
        });

        TableColumn dose = new TableColumn("Dose");
        this.initTableColumn(dose,"dose");
        dose.setOnEditCommit( new EventHandler<TableColumn.CellEditEvent<RowTable, String>>() {
            public void handle( TableColumn.CellEditEvent<RowTable, String> event) {
                ((RowTable) event.getTableView().getItems().get(event.getTablePosition().getRow())
                ).setDose(event.getNewValue());
                changeMenu();
            }
        });

        TableColumn milk = new TableColumn("Latte");
        this.initTableColumn(milk,"milk");
        milk.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<RowTable, String>>() {
            public void handle(TableColumn.CellEditEvent<RowTable, String> event) {
                ((RowTable) event.getTableView().getItems().get(event.getTablePosition().getRow())
                ).setMilk(event.getNewValue());
                changeMenu();
            }
        });

        TableColumn water = new TableColumn("Acqua");
        this.initTableColumn(water,"water");
        water.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<RowTable, String>>() {
            public void handle(TableColumn.CellEditEvent<RowTable, String> event) {
                ((RowTable) event.getTableView().getItems().get(event.getTablePosition().getRow())
                ).setWater(event.getNewValue());
                changeMenu();
            }
        });

        TableColumn vodka = new TableColumn("Vodka");
        this.initTableColumn(vodka,"vodka");
        vodka.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<RowTable, String>>() {
            public void handle(TableColumn.CellEditEvent<RowTable, String> event) {
                ((RowTable) event.getTableView().getItems().get(event.getTablePosition().getRow())
                ).setVodka(event.getNewValue());
                changeMenu();
            }
        });

        final TextField addID = createTextField("Id");
        final TextField addType = createTextField("Tipo");
        final TextField addName = createTextField("Nome");
        final TextField addPrice = createTextField("Costo");
        final TextField addQ_max = createTextField("Q_Max");
        final TextField addTemp = createTextField("Temp");
        final TextField addDose = createTextField("Dose");
        final TextField addMilk = createTextField("Latte");
        final TextField addWater = createTextField("Acqua");
        final TextField addVodka = createTextField("Vodka");

        final Button addButton = new Button("Add");
        addButton.setOnAction(event -> {
                String stringToAdd = addID.getText() + "\t" + addType.getText() + "\t" + addName.getText() + "\t" +
                        addPrice.getText() + "\t" + addQ_max.getText() + "\t" + addTemp.getText() + "\t" +
                        addDose.getText() + "\t" + addMilk.getText() + "\t" + addWater.getText() + "\t" +
                        addVodka.getText();
                data.add(new RowTable(stringToAdd));
                addID.clear();
                addType.clear();
                addName.clear();
                addPrice.clear();
                addQ_max.clear();
                addTemp.clear();
                addDose.clear();
                addMilk.clear();
                addWater.clear();
                addVodka.clear();
                changeMenu();
        });
        final Button remButton = new Button("Remove");
        remButton.setOnAction(event -> {
                int index = tableView.getSelectionModel().getSelectedIndex();
                data.remove(index);
                changeId(index);
        });

        addButton.setPrefWidth(50);
        final HBox hb = new HBox();
        hb.getChildren().addAll(addID, addType, addName, addPrice, addQ_max, addTemp, addDose, addMilk, addWater,
                addVodka);
        hb.setSpacing(3);
        final HBox hBox = new HBox(addButton,remButton);
        tableView.setItems(data);
        tableView.setStyle("-fx-font: 16px Serif");
        tableView.getColumns().addAll(id, type, name, price, Q_max, temp, dose, milk, water, vodka);
        tableView.setEditable(true);

        vBox.getChildren().addAll(tableView,hb,hBox);
        vBox.setFillWidth(true);
        VBox.setVgrow(tableView, Priority.ALWAYS);
        vBox.prefHeightProperty().bind(stage.heightProperty());
        vBox.prefWidthProperty().bind(stage.widthProperty());
    }

    private TextField createTextField(String nameOfTextField) {
        TextField textFieldTmp = new TextField();
        textFieldTmp.setMaxWidth(75);
        textFieldTmp.setPromptText(nameOfTextField);
        return textFieldTmp;
    }



    /**
     * Classe menu utilizzata per l'aggiunta dei dati nella rowTable.
     */
    public static class RowTable {
        private SimpleStringProperty id;
        private SimpleStringProperty type;
        private SimpleStringProperty name;
        private SimpleStringProperty price;
        private SimpleStringProperty q_max;
        private SimpleStringProperty temp;
        private SimpleStringProperty dose;
        private SimpleStringProperty milk;
        private SimpleStringProperty water;
        private SimpleStringProperty vodka;

        private RowTable(String menuDati) {
            String[] dati = menuDati.split("\t");
            this.id = new SimpleStringProperty(dati[0]);
            this.type = new SimpleStringProperty(dati[1]);
            this.name = new SimpleStringProperty(dati[2]);
            this.price = new SimpleStringProperty(dati[3]);
            this.q_max = new SimpleStringProperty(dati[4]);
            this.temp = new SimpleStringProperty(dati[5]);
            this.dose = new SimpleStringProperty(dati[6]);
            this.milk = new SimpleStringProperty(dati[7]);
            this.water = new SimpleStringProperty(dati[8]);
            this.vodka = new SimpleStringProperty(dati[9]);
        }

        public SimpleStringProperty idProperty() {
            return id;
        }

        public SimpleStringProperty typeProperty() {
            return type;
        }

        public SimpleStringProperty nameProperty() {
            return name;
        }

        public SimpleStringProperty priceProperty() {
            return price;
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

        public SimpleStringProperty milkProperty() {
            return milk;
        }

        public SimpleStringProperty waterProperty() {
            return water;
        }

        public SimpleStringProperty vodkaProperty() {
            return vodka;
        }

        public void setId(String id) {
            this.id.set(id);
        }

        public void setType(String type) {
            this.type.set(type);
        }

        public void setName(String name) {
            this.name.set(name);
        }

        public void setPrice(String price) {
            this.price.set(price);
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

        public void setMilk(String milk) {
            this.milk.set(milk);
        }

        public void setWater(String water) {
            this.water.set(water);
        }

        public void setVodka(String vodka) {
            this.vodka.set(vodka);
        }
    }

    protected void setRowTable(ObservableList<String> obsvMenu) {
        int size = 0;
        for (int i = 0; i < obsvMenu.size(); i++) {
            if (!obsvMenu.get(i).contains("*")) {
                size++;
            }
        }

        rowTable = new RowTable[size];
        int tabindex = 0;
        data.clear();
        menu.clear();
        for (int i = 0; i < obsvMenu.size(); i++ ) {
            menu.add(obsvMenu.get(i));
            if (!obsvMenu.get(i).contains("*")) {
                rowTable[tabindex] = new RowTable(obsvMenu.get(i));
                tabindex++;
            }
        }
        for (int j = 0; j < size; j++) {
            data.addAll(rowTable[j]);
        }
    }

    protected VBox getvBox() {
        return vBox;
    }

    private void initTableColumn(TableColumn tableColumn, String name) {
        tableColumn.setCellValueFactory(new PropertyValueFactory<RowTable, String>(name));
        tableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    private void changeMenu() {
        menu.clear();
        menu.add("* le righe con * vengono saltate nella lettura");
        menu.add("* ID  TIPO  \tNOME  \t    COSTO  Q_MAX TEMP DOSE milk water\tvodka");
        for (int row = 0; row < tableView.getItems().size(); row++) {
            String tmp = "";
            for (int column = 0; column < 10; column++ ) {
                tmp = tmp + tableView.getColumns().get(column).getCellObservableValue(row).getValue();
                if (!(column == 9)) {
                    tmp = tmp + "\t";
                }
            }
            if (!tmp.equals("")) {
                menu.add(tmp);
            }
        }
        menu.add("*");
    }

    private void changeId(int index) {
        for (int i = index; i < data.size(); i++) {
            String newId = "0" + (i + 1);
            tableView.getItems().get(i).setId(newId);
            changeMenu();
            //data.get(i).setId(newId);
        }
    }

    protected void sendMenu() {
        obsvMenu.clear();
        obsvMenu.addAll(menu);
        menu.clear();
        serverConnection.chooseCommandExecutedByThread(OVERWRITE_MENU);
    }
}