package InterfacciaDistributore;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

/* Questa classe definisce un pulsante di forma rettangolare con bordi smussati */

public class RoundRectButton extends JButton {
    public RoundRectButton(String label) {
        super(label);
        setBackground(Color.lightGray);
        setFocusable(false);

        Dimension size = getPreferredSize();
        size.width = size.height = Math.max(size.width, size.height);
        setPreferredSize(size);

        setContentAreaFilled(false);
    }

    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(Color.gray);
        } else {
            g.setColor(getBackground());
        }
        g.fillRoundRect(0, 0, getSize().width - 1, getSize().height - 1, 40, 40);

        super.paintComponent(g);
    }

    protected void paintBorder(Graphics g) {
        g.setColor(Color.darkGray);
        g.drawRoundRect(0, 0, getSize().width - 1, getSize().height - 1, 40, 40);
    }

    Shape shape;

    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
        }
        return shape.contains(x, y);
    }
}
