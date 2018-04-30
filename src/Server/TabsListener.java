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
        createTabs();
        statsPanel.add(jTabListener);
        statsPanel.validate();
    }

    private void createTabs() {
        //statsPanel.add(makeTextPanel("Test"));

        JPanel panel = makeTextPanel("Stats 1");
        jTabListener.addTab("Tab 1", panel);
        JComponent panel2 = makeTextPanel("Stats 2");
        jTabListener.addTab("Tab 2", panel2);
        JComponent panel3 = makeTextPanel("Stats 3");
        panel3.setPreferredSize(new Dimension(410, 50));
        jTabListener.addTab("Tab 3", panel3);
    }

    private JPanel makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }
}