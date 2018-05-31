package GUI_FX_Server;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;

public class DrinkChart extends BarChart {
    private ObservableList<String> data;
    private ObservableList<String> menu;

    public DrinkChart(Axis xAxis, Axis yAxis, ObservableList<String> data, ObservableList<String> menu) {
        super(xAxis, yAxis);
        this.data = data;
        this.menu = menu;
    }

    public BorderPane initChart() {
        BorderPane mainTabPane = new BorderPane();

        if (data == null)
            return mainTabPane;

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> drinksChart = new BarChart<>(xAxis, yAxis);

        drinksChart.setTitle("Quantità rimanenti bevande");
        drinksChart.setLegendVisible(false);

        xAxis.setLabel("Bevanda");
        yAxis.setLabel("Quantità");

        XYChart.Series series1 = new XYChart.Series();

        ArrayList<String> drinkName = new ArrayList<>();
        ArrayList<Double> drinkQty = new ArrayList<>();

        analyzeData(drinkName, drinkQty, data, menu);

        for (int j = 0; j < drinkName.size(); j++) {
            series1.getData().add(new XYChart.Data(drinkName.get(j), drinkQty.get(j)));
        }

        drinksChart.getData().add(series1);
        mainTabPane.setCenter(drinksChart);

        return mainTabPane;
    }

    private void analyzeData(ArrayList<String> drinkName, ArrayList<Double> drinkQty,
                             ObservableList<String> data, ObservableList<String> menu) {

        for (int rows = 0; rows < menu.size(); rows++) {
            if (menu.get(rows).startsWith("0")) {
                String[] splitMenu = menu.get(rows).split("\t");

                drinkName.add(splitMenu[2]);
            }
        }

        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).startsWith("0")) {
                String[] splitData = data.get(i).split("\t");

                drinkQty.add(Double.parseDouble(splitData[1]));
            }
        }
    }
}


//package GUI_FX_Server;
//
//import javafx.collections.ObservableList;
//import javafx.scene.Node;
//import javafx.scene.chart.*;
//import javafx.scene.layout.BorderPane;
//
//import java.util.ArrayList;
//
//public class DrinkChart extends BarChart {
//
//    private ObservableList<String> data;
//    private ObservableList<String> menu;
//
//    public DrinkChart(Axis xAxis, Axis yAxis, ObservableList<String> data, ObservableList<String> menu) {
//        super(xAxis, yAxis);
//        this.data = data;
//        this.menu = menu;
//    }
//
//    public BorderPane initChart() {
//        BorderPane mainTabPane = new BorderPane();
//
//        if(data == null || menu == null)
//            return mainTabPane;
//
//        final CategoryAxis xAxis = new CategoryAxis();
//        final NumberAxis yAxis = new NumberAxis();
//
//        final BarChart<String,Number> drinksChart = new BarChart<>(xAxis, yAxis);
//        drinksChart.setTitle("Quantità rimanenti bevande");
//        drinksChart.setLegendVisible(false);
//
//        xAxis.setLabel("Bevanda");
//        yAxis.setLabel("Quantità");
//
//        XYChart.Series series1 = new XYChart.Series();
//
//        ArrayList<String> drinkName = new ArrayList<>();
//        ArrayList<Double> drinkQty = new ArrayList<>();
//
//        analyzeData(drinkName, drinkQty, data, menu);
//
////        for (int rows = 0; rows < menu.size(); rows++) {
////            if (!menu.get(rows).startsWith("*") && !menu.isEmpty()) {
////                String[] splitMenu = menu.get(rows).split("\t");
////
////                drinkName.add(splitMenu[2]);
////
////                numDrinks++;    // Num drinks
////            }
////        }
////
////        for (int i = 0; i < data.size(); i++) {
////            if (data.get(i).startsWith("0") && !data.isEmpty()) {
////                String[] splitData = data.get(i).split("\t");
////
////                drinkQty.add(Double.parseDouble(splitData[1]));
////            }
////        }
////
//        for (int j = 0; j < drinkName.size(); j++) {
//            series1.getData().add(new XYChart.Data(drinkName.get(j), drinkQty.get(j)));
//        }
//
//        // Ottenimento dati
////        for(int rows = 0; rows < menu.size(); rows++) {
////            for (int i = 0; i < numDrinks; i++) {
////                if (menu.get(rows).startsWith("0")) {
////                    String[] splitData = data.get(rows).split("\t");
////                    String[] splitMenu = menu.get(rows).split("\t");
////
////                    //TODO sostituire ID con nome bevanda
////                    series1.getData().add(new XYChart.Data(splitMenu[2], Double.parseDouble(splitData[1])));
////                }
////            }
////        }
//
//        drinksChart.getData().add(series1);
//
//        mainTabPane.setCenter(drinksChart);
//
//        return mainTabPane;
//    }
//
//    private void analyzeData(ArrayList<String> drinkName, ArrayList<Double> drinkQty,
//                             ObservableList<String> data, ObservableList<String> menu) {
//
//            for (int rows = 0; rows < menu.size(); rows++) {
//                if (menu.get(rows).startsWith("0")) {
//                    String[] splitMenu = menu.get(rows).split("\t");
//
//                    drinkName.add(splitMenu[2]);
//                }
//            }
//
//            for (int i = 0; i < data.size(); i++) {
//                if (data.get(i).startsWith("0")) {
//                    String[] splitData = data.get(i).split("\t");
//
//                    drinkQty.add(Double.parseDouble(splitData[1]));
//                }
//            }
//    }
//}
