package GUI_FX_Server;

import PersonalExceptions.FileNotReadable;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static Server.FileServer.fileMenuServer;

public class MenuTable extends TableView {

    public MenuTable(Stage stage) {

        TableView<menu> tableView = new TableView<>();

        final ObservableList<menu> data =
                FXCollections.observableArrayList(
                        new menu("Jacob", "Smith", "jacob.smith@example.com"),
                        new menu("Isabella", "Johnson", "isabella.johnson@example.com"),
                        new menu("Ethan", "Williams", "ethan.williams@example.com"),
                        new menu("Emma", "Jones", "emma.jones@example.com"),
                        new menu("Michael", "Brown", "michael.brown@example.com")
                );

        TableColumn id = new TableColumn("ID");
        id.setCellValueFactory( new PropertyValueFactory<menu, String>("id"));
        TableColumn tipo = new TableColumn("Tipo");
        tipo.setCellValueFactory(new PropertyValueFactory<menu,String>("tipo"));
        TableColumn nome = new TableColumn("Nome");
        nome.setCellValueFactory(new PropertyValueFactory<menu,String>("nome"));
        TableColumn costo = new TableColumn("Costo");
        TableColumn Q_max = new TableColumn("Q_max");
        TableColumn temp = new TableColumn("Temp");
        TableColumn dose = new TableColumn("Dose");
        TableColumn latte = new TableColumn("Latte");
        TableColumn acqua = new TableColumn("Acqua");
        TableColumn vodka = new TableColumn("vodka");
        tableView.setItems(data);
        tableView.getColumns().addAll(id,tipo,nome,costo,Q_max,temp,dose,latte,acqua,vodka);

        tableView.setEditable(true);

        Group root = new Group();

        GridPane mainPanel = new GridPane();

        Toolbar1 toolbar1 = new Toolbar1(stage);
        MenuBar1 menuBar1 = new MenuBar1(stage);
        VBox vBox = new VBox();
        vBox.getChildren().addAll(toolbar1,menuBar1, tableView);
        vBox.setFillWidth(true);

        stage.setTitle("MenuTable");

        Scene scene = new Scene(root, 800, 550, Color.AQUAMARINE);

        mainPanel.addRow(0, vBox);
        mainPanel.prefHeightProperty().bind(scene.heightProperty());
        mainPanel.prefWidthProperty().bind(scene.widthProperty());
        root.getChildren().addAll(mainPanel);
        stage.setScene(scene);
        stage.show();


    }

    public static class menu {

        private final SimpleStringProperty id;
        private final SimpleStringProperty tipo;
        private final SimpleStringProperty nome;

        private menu(String ID, String Tipo, String Nome) {
            this.id = new SimpleStringProperty(ID);
            this.tipo = new SimpleStringProperty(Tipo);
            this.nome = new SimpleStringProperty(Nome);
        }

        public String getId() {
            return id.get();
        }

        public void setId(String ID) {
            id.set(ID);
        }

        public String getTipo() {
            return tipo.get();
        }

        public void setTipo(String Tipo) {
            tipo.set(Tipo);
        }

        public String getNome() {
            return nome.get();
        }

        public void setNome(String Nome) {
            nome.set(Nome);
        }
    }
}

