package Server;

import javax.swing.*;

public class Toolbar extends JMenuBar {

    public Toolbar() {
        this.add(new JMenu("File")); // Questa è solo una demo per avere un riscontro visivo, bisognerebbe creare i
                                        // singoli JMenu in base alle necessità.
        this.add(new JMenu("Edit"));
        this.add(new JSeparator(SwingConstants.HORIZONTAL));
        this.add(new JMenu("Help"));
    }
}
