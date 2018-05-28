package GUI_FX_Server;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;


public class MenuTable extends TableView {

    Tabella[] tabella;
    VBox vBox = new VBox();
    private ArrayList<String> menuDati;

    public MenuTable(Stage stage, ObservableList<String> menuDati) {
        this.menuDati = menuDati;
        tabella = new Tabella[menuDati.size()];
        TableView<Tabella> tableView = new TableView<>();

        for(int i = 0; i < menuDati.size();i++ ){
            if(!menuDati.get(i).startsWith("*")) {
                tabella[i] = new Tabella(menuDati.get(i));
            }
        }

        ObservableList<Tabella> data = FXCollections.observableArrayList();
        for(int i = 1; i < tabella.length; i++) {
            data.addAll(tabella[i]);
        }

        //Creo colonne aggiungo ad ogni colonna i valori contenuti nella classe tabella e rendo i valori modificabili
        TableColumn id = new TableColumn("ID");
        id.setCellValueFactory(new PropertyValueFactory<Tabella, String>("id"));
        id.setCellFactory(TextFieldTableCell.forTableColumn());
        id.setOnEditCommit( new EventHandler<TableColumn.CellEditEvent<Tabella, String>>() {
            public void handle ( TableColumn.CellEditEvent<Tabella, String> event) {
                ((Tabella) event.getTableView().getItems().get(
                        event.getTablePosition().getRow())
                ).setId(event.getNewValue());
            }
        });

        TableColumn tipo = new TableColumn("Tipo");
        tipo.setCellValueFactory(new PropertyValueFactory<Tabella, String>("tipo"));
        tipo.setCellFactory(TextFieldTableCell.forTableColumn());
        tipo.setOnEditCommit( new EventHandler<TableColumn.CellEditEvent<Tabella, String>>() {
            public void handle ( TableColumn.CellEditEvent<Tabella, String> event) {
                ((Tabella) event.getTableView().getItems().get(
                        event.getTablePosition().getRow())
                ).setTipo(event.getNewValue());
            }
        });

        TableColumn nome = new TableColumn("Nome");
        nome.setCellValueFactory(new PropertyValueFactory<Tabella, String>("nome"));
        nome.setCellFactory(TextFieldTableCell.forTableColumn());
        nome.setOnEditCommit( new EventHandler<TableColumn.CellEditEvent<Tabella, String>>() {
            public void handle ( TableColumn.CellEditEvent<Tabella, String> event) {
                ((Tabella) event.getTableView().getItems().get(
                        event.getTablePosition().getRow())
                ).setNome(event.getNewValue());
            }
        });

        TableColumn costo = new TableColumn("Costo");
        costo.setCellValueFactory(new PropertyValueFactory<Tabella, String>("costo"));
        costo.setCellFactory(TextFieldTableCell.forTableColumn());
        costo.setOnEditCommit( new EventHandler<TableColumn.CellEditEvent<Tabella, String>>() {
            public void handle ( TableColumn.CellEditEvent<Tabella, String> event) {
                ((Tabella) event.getTableView().getItems().get(
                        event.getTablePosition().getRow())
                ).setCosto(event.getNewValue());
            }
        });

        TableColumn Q_max = new TableColumn("Q_max");
        Q_max.setCellValueFactory(new PropertyValueFactory<Tabella, String>("q_max"));
        Q_max.setCellFactory(TextFieldTableCell.forTableColumn());
        Q_max.setOnEditCommit( new EventHandler<TableColumn.CellEditEvent<Tabella, String>>() {
            public void handle ( TableColumn.CellEditEvent<Tabella, String> event) {
                ((Tabella) event.getTableView().getItems().get(
                        event.getTablePosition().getRow())
                ).setQ_max(event.getNewValue());
            }
        });

        TableColumn temp = new TableColumn("Temp");
        temp.setCellValueFactory(new PropertyValueFactory<Tabella, String>("temp"));
        temp.setCellFactory(TextFieldTableCell.forTableColumn());
        temp.setOnEditCommit( new EventHandler<TableColumn.CellEditEvent<Tabella, String>>() {
            public void handle ( TableColumn.CellEditEvent<Tabella, String> event) {
                ((Tabella) event.getTableView().getItems().get(
                        event.getTablePosition().getRow())
                ).setTemp(event.getNewValue());
            }
        });

        TableColumn dose = new TableColumn("Dose");
        dose.setCellValueFactory(new PropertyValueFactory<Tabella, String>("dose"));
        dose.setCellFactory(TextFieldTableCell.forTableColumn());
        dose.setOnEditCommit( new EventHandler<TableColumn.CellEditEvent<Tabella, String>>() {
            public void handle ( TableColumn.CellEditEvent<Tabella, String> event) {
                ((Tabella) event.getTableView().getItems().get(
                        event.getTablePosition().getRow())
                ).setDose(event.getNewValue());
            }
        });

        TableColumn latte = new TableColumn("Latte");
        latte.setCellValueFactory(new PropertyValueFactory<Tabella, String>("latte"));
        latte.setCellFactory(TextFieldTableCell.forTableColumn());
        latte.setOnEditCommit( new EventHandler<TableColumn.CellEditEvent<Tabella, String>>() {
            public void handle ( TableColumn.CellEditEvent<Tabella, String> event) {
                ((Tabella) event.getTableView().getItems().get(
                        event.getTablePosition().getRow())
                ).setLatte(event.getNewValue());
            }
        });

        TableColumn acqua = new TableColumn("Acqua");
        acqua.setCellValueFactory(new PropertyValueFactory<Tabella, String>("acqua"));
        acqua.setCellFactory(TextFieldTableCell.forTableColumn());
        acqua.setOnEditCommit( new EventHandler<TableColumn.CellEditEvent<Tabella, String>>() {
            public void handle ( TableColumn.CellEditEvent<Tabella, String> event) {
                ((Tabella) event.getTableView().getItems().get(
                        event.getTablePosition().getRow())
                ).setAcqua(event.getNewValue());
            }
        });

        TableColumn vodka = new TableColumn("vodka");
        vodka.setCellValueFactory(new PropertyValueFactory<Tabella, String>("vodka"));
        vodka.setCellFactory(TextFieldTableCell.forTableColumn());
        vodka.setOnEditCommit( new EventHandler<TableColumn.CellEditEvent<Tabella, String>>() {
            public void handle ( TableColumn.CellEditEvent<Tabella, String> event) {
                ((Tabella) event.getTableView().getItems().get(
                        event.getTablePosition().getRow())
                ).setVodka(event.getNewValue());
            }
        });

        tableView.setItems(data);
        tableView.setEditable(false);
        tableView.setStyle("-fx-font: 16px Serif");

        tableView.getColumns().addAll(id, tipo, nome, costo, Q_max, temp, dose, latte, acqua, vodka);

        tableView.setEditable(true);

        vBox.getChildren().addAll(tableView);
        vBox.setFillWidth(true);
        VBox.setVgrow(tableView, Priority.ALWAYS); // DM: VBox con la V maiuscola?
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

    public VBox getvBox() {
        return vBox;
    }
}


