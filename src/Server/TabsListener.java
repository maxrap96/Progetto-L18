package Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TabsListener implements ActionListener {
    JPanel statsPanel;
    JTabbedPane jTabListener;

    public TabsListener(JPanel infoPanel) {
        this.statsPanel = infoPanel;
        jTabListener = new JTabbedPane();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        statsPanel.removeAll();
        createTabs();
        statsPanel.add(jTabListener);
        statsPanel.validate();
        statsPanel.repaint();
    }

    /**
     * Funzione che crea le schede delle statistiche
     */
    private void createTabs() {
        jTabListener.removeAll();

        JPanel panel = makeTextPanel("Statistiche relative alle monete:");
        jTabListener.addTab("Monete", panel);
        JComponent panel2 = makeTextPanel("Statistiche relative agli ingredienti:");
        jTabListener.addTab("Ingredienti", panel2);
        JComponent panel3 = makeTextPanel("Statistiche varie:");
        panel3.setPreferredSize(new Dimension(410, 50));
        jTabListener.addTab("Miscellanea", panel3);
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