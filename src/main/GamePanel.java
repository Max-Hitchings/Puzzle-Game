package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GamePanel extends JPanel {

    public GamePanel() {
        addKeyListener(new KeyboardInputs());
        addMouseListener(new MouseInputs());
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.drawRect(100, 100, 200, 60);
        g.fillRect(100, 200, 200, 60);
    }
}
