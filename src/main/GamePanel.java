package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel {
    private MouseInputs mouseInputs = new MouseInputs(this);
    private int xDelta = 0, yDelta = 0;

    public GamePanel() {
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    public void changeXDelta(int value) {
        this.xDelta += value;
//        System.out.println(this.xDelta);

        repaint();
    }
    public void changeYDelta(int value) {
        this.yDelta += value;
        System.out.println(this.yDelta);
        repaint();
    }

    public void setRectPos(int x, int y) {
        this.xDelta = x;
        this.yDelta = y;
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.DARK_GRAY);
        g.fillRect(xDelta, yDelta, 200, 60);
        g.drawRect(100, 200, 200, 60);
    }
}
