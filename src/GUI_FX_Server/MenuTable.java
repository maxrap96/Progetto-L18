package GUI_FX_Server;

import ServerSide.ServerConnection;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

import static ServerSide.StringCommandList.OVERWRITE_MENU;
import static ServerSide.StringCommandList.SEND_MENU;

public class MenuTable extends TableView {
    private Tabella[] tabella;
    private VBox vBox = new VBox();
    private ObservableList<String> obsvMenu;
    private TableView<Tabella> tableView = new TableView<>();
    private ObservableList<Tabella> data = FXCollections.observableArrayList();
    private ArrayList<String> menu = new ArrayList<>();
    private ServerConnection serverConnection;

    public MenuTable(Stage stage, ObservableList<String> obsvMenu, ServerConnection serverConnection) {
        this.obsvMenu = obsvMenu;
        this.serverConnection = serverConnection;

        obsvMenu.addListener((ListChangeListener) change -> Platform.runLater(() -> {
            // Aggiorna UI
            System.out.println("Detected a change! ");
            setTabella(obsvMenu);
        }));

        // Creazione colonne
        // Aggiunta ad ogni colonna i valori contenuti nella classe tabella e resi i valori modificabili
        TableColumn id = new TableColumn("ID");
        this.initTableColumn(id,"id");

        id.setOnEditCommit( new EventHandler<TableColumn.CellEditEvent<Tabella, String>>() {
            public void handle( TableColumn.CellEditEvent<Tabella, String> event) {
                ((Tabella) event.getTableView().getItems().get(event.getTablePosition().getRow())
                ).setId(event.getNewValue());
                changeMenu();
            }
        });

        TableColumn tipo = new TableColumn("Tipo");
        this.initTableColumn(tipo,"tipo");
        tipo.setOnEditCommit( new EventHandler<TableColumn.CellEditEvent<Tabella, String>>() {
            public void handle( TableColumn.CellEditEvent<Tabella, String> event) {
                ((Tabella) event.getTableView().getItems().get(event.getTablePosition().getRow())
                ).setTipo(event.getNewValue());
                changeMenu();
            }
        });

        TableColumn nome = new TableColumn("Nome");
        this.initTableColumn(nome,"nome");
        nome.setOnEditCommit( new EventHandler<TableColumn.CellEditEvent<Tabella, String>>() {
            public void handle( TableColumn.CellEditEvent<Tabella, String> event) {
                ((Tabella) event.getTableView().getItems().get(event.getTablePosition().getRow())
                ).setNome(event.getNewValue());
                changeMenu();
            }
        });

        TableColumn costo = new TableColumn("Costo");
        this.initTableColumn(costo,"costo");
        costo.setOnEditCommit( new EventHandler<TableColumn.CellEditEvent<Tabella, String>>() {
            public void handle( TableColumn.CellEditEvent<Tabella, String> event) {
                ((Tabella) event.getTableView().getItems().get(event.getTablePosition().getRow())
                ).setCosto(event.getNewValue());
                changeMenu();
            }
        });

        TableColumn Q_max = new TableColumn("Q_max");
        this.initTableColumn(Q_max,"q_max");
        Q_max.setOnEditCommit( new EventHandler<TableColumn.CellEditEvent<Tabella, String>>() {
            public void handle( TableColumn.CellEditEvent<Tabella, String> event) {
                ((Tabella) event.getTableView().getItems().get(event.getTablePosition().getRow())
                ).setQ_max(event.getNewValue());
                changeMenu();
            }
        });

        TableColumn temp = new TableColumn("Temp");
        this.initTableColumn(temp,"temp");
        temp.setOnEditCommit( new EventHandler<TableColumn.CellEditEvent<Tabella, String>>() {
            public void handle( TableColumn.CellEditEvent<Tabella, String> event) {
                ((Tabella) event.getTableView().getItems().get(event.getTablePosition().getRow())
                ).setTemp(event.getNewValue());
                changeMenu();
            }
        });

        TableColumn dose = new TableColumn("Dose");
        this.initTableColumn(dose,"dose");
        dose.setOnEditCommit( new EventHandler<TableColumn.CellEditEvent<Tabella, String>>() {
            public void handle( TableColumn.CellEditEvent<Tabella, String> event) {
                ((Tabella) event.getTableView().getItems().get(event.getTablePosition().getRow())
                ).setDose(event.getNewValue());
                changeMenu();
            }
        });

        TableColumn latte = new TableColumn("Latte");
        this.initTableColumn(latte,"latte");
        latte.setOnEditCommit( new EventHandler<TableColumn.CellEditEvent<Tabella, String>>() {
            public void handle( TableColumn.CellEditEvent<Tabella, String> event) {
                ((Tabella) event.getTableView().getItems().get(event.getTablePosition().getRow())
                ).setLatte(event.getNewValue());
                changeMenu();
            }
        });

        TableColumn acqua = new TableColumn("Acqua");
        this.initTableColumn(acqua,"acqua");
        acqua.setOnEditCommit( new EventHandler<TableColumn.CellEditEvent<Tabella, String>>() {
            public void handle( TableColumn.CellEditEvent<Tabella, String> event) {
                ((Tabella) event.getTableView().getItems().get(event.getTablePosition().getRow())
                ).setAcqua(event.getNewValue());
                changeMenu();
            }
        });

        TableColumn vodka = new TableColumn("Vodka");
        this.initTableColumn(vodka,"vodka");
        vodka.setOnEditCommit( new EventHandler<TableColumn.CellEditEvent<Tabella, String>>() {
            public void handle( TableColumn.CellEditEvent<Tabella, String> event) {
                ((Tabella) event.getTableView().getItems().get(event.getTablePosition().getRow())
                ).setVodka(event.getNewValue());
                changeMenu();
            }
        });

        tableView.setItems(data);
        tableView.setEditable(false);
        tableView.setStyle("-fx-font: 16px Serif");
        tableView.getColumns().addAll(id, tipo, nome, costo, Q_max, temp, dose, latte, acqua, vodka);
        tableView.setEditable(true);

        vBox.getChildren().addAll(tableView);
        vBox.setFillWidth(true);
        VBox.setVgrow(tableView, Priority.ALWAYS);
        vBox.prefHeightProperty().bind(stage.heightProperty());
        vBox.prefWidthProperty().bind(stage.widthProperty());
    }

    /**
     * Classe menu utilizzata per l'aggiunta dei dati nella tabella.
     */
    public static class Tabella {
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

    public void setTabella(ObservableList<String> obsvMenu) {
        tabella = new Tabella[obsvMenu.size()];
        data.clear();
        for (int i = 0; i < obsvMenu.size(); i++ ) {
            menu.add(obsvMenu.get(i));
            if (!obsvMenu.get(i).contains("*")) {
                tabella[i] = new Tabella(obsvMenu.get(i));
                }

            }
        for (int j = 2; j < tabella.length; j++) {
            data.addAll(tabella[j]);
        }

    }

    public VBox getvBox() {
        return vBox;
    }

    private void initTableColumn(TableColumn tableColumn, String name) {
        tableColumn.setCellValueFactory(new PropertyValueFactory<Tabella, String>(name));
        tableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    private void changeMenu() {
        menu.clear();
        menu.add("* le righe con * vengono saltate nella lettura");
        menu.add("* ID  TIPO  \tNOME  \t    COSTO  Q_MAX TEMP DOSE latte acqua\tvodka");
        for (int row = 0; row < 11; row++) {
            String tmp = "";
            for (int column = 0; column < 10; column++ ) {
               tmp = tmp + (String)tableView.getColumns().get(column).getCellObservableValue(row).getValue();
               if (!(column == 9)){
                   tmp = tmp + "\t";
               }
            }
            menu.add(tmp);
        }
        menu.add("*");
    }

    public void sendMenu(){
        obsvMenu.clear();
        obsvMenu.addAll(menu);
        serverConnection.chooseCommandExecutedByThread(OVERWRITE_MENU, 0);
    }
}
