package Server;

//import javafx.application.Platform;
//import javafx.embed.swing.JFXPanel;
//import javafx.scene.Group;
//import javafx.scene.Scene;
//import javafx.scene.control.Label;
//import javafx.scene.paint.Color;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

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
        jTabs.addTab("Coins", panel);
        jTabs.setMnemonicAt(0, KeyEvent.VK_C);
        JComponent panel2 = makeTextPanel("Statistiche relative agli ingredienti:");
        jTabs.addTab("Ingredients", panel2);
        jTabs.setMnemonicAt(1, KeyEvent.VK_I);
        JComponent panel3 = makeTextPanel("Statistiche varie:");
        panel3.setPreferredSize(new Dimension(410, 50));
        jTabs.addTab("Miscellaneous", panel3);
        jTabs.setMnemonicAt(2, KeyEvent.VK_M);
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
}