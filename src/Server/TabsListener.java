package Server;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TabsListener implements ActionListener {
    JPanel statsPanel;
    JTabbedPane jTabs;
    BarChart coinsChart;

    public TabsListener(JPanel infoPanel) {
        this.statsPanel = infoPanel;
        jTabs = new JTabbedPane();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        statsPanel.removeAll();
        createTabs();
        statsPanel.add(jTabs);
        statsPanel.validate();
        statsPanel.repaint();
    }

    /**
     * Funzione che crea le schede delle statistiche
     */
    private void createTabs() {
        jTabs.removeAll();

        JPanel panel = makeTextPanel("Statistiche relative alle monete:");
        jTabs.addTab("Monete", panel);

        //createFirstTab();

        JComponent panel2 = makeTextPanel("Statistiche relative agli ingredienti:");
        jTabs.addTab("Ingredienti", panel2);
        JComponent panel3 = makeTextPanel("Statistiche varie:");
        panel3.setPreferredSize(new Dimension(410, 50));
        jTabs.addTab("Miscellanea", panel3);
    }

    // MJ: può essere tolta in futuro e sostituita con grafici delle statistiche.

    private JPanel makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }


    // MJ: work in progress su tabs con javaFX...

    /*private void createFirstTab() {
        JFXPanel panel = makeTextPanelNew("Statistiche relative alle monete:");
        jTabs.addTab("Monete", panel);
    }

    private JFXPanel makeTextPanelNew(String text) {
        JFXPanel panel = new JFXPanel();
        panel.setPreferredSize(new Dimension(500, 800));
        panel.setLayout(new GridLayout(1, 1));
        Label label = new Label();

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                initFX(panel);
            }
        });

        JPanel jp = new JPanel();
        jp.setLayout(new BorderLayout());
        jp.add(panel, BorderLayout.CENTER);

        return panel;
    }

    private static void initFX(JFXPanel fxPanel) {
        // This method is invoked on JavaFX thread
        Group root = new Group();
        Scene scene = new Scene(root, Color.PLUM);

        fxPanel.setScene(scene);
    }*/
}