package Server;

import javax.swing.*;

public class Toolbar extends JMenuBar {
    JMenu menuFile = new JMenu("File");

    public Toolbar() {
        setMenuFile();
        this.add(menuFile); // Questa è solo una demo per avere un riscontro visivo, bisognerebbe creare i
                            // singoli JMenu in base alle necessità.
        this.add(new JMenu("Edit"));
        this.add(new JSeparator(SwingConstants.HORIZONTAL));
        this.add(new JMenu("Help"));
    }

    // Per approfondimenti:
    // https://docs.oracle.com/javase/tutorial/uiswing/components/menu.html
    private void setMenuFile() {
        JMenuItem menuMachine = new JMenuItem("Open Menu");
        JMenuItem statsMachine = new JMenuItem("Open Stats");
        menuFile.add(menuMachine);
        menuFile.add(statsMachine);
    }
}
