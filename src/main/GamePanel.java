package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.JPanel;
import java.awt.*;
import java.util.Random;

public class GamePanel extends JPanel {
    private MouseInputs mouseInputs = new MouseInputs(this);
    private float xDelta = 0, yDelta = 0;
    private float xDir = .5f, yDir = .5f;
    private Color recColour = new Color(23, 67, 200);
    private Random random = new Random();

    public GamePanel() {
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    public void changeXDelta(int value) {
        this.xDelta += value;
//        System.out.println(this.xDelta);

    }
    public void changeYDelta(int value) {
        this.yDelta += value;
        System.out.println(this.yDelta);
    }

    public void setRectPos(int x, int y) {
        this.xDelta = x;
        this.yDelta = y;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        updateRec();
        
        g.setColor(recColour);
        g.fillRect((int) xDelta,(int) yDelta, 200, 60);
        g.drawRect(100, 200, 200, 60);


    }

    private void updateRec() {
        xDelta += xDir;
        yDelta += yDir;
        if (xDelta >= 400 || xDelta <= 0) {
            xDir *= -1;
            recColour = getRndColour();
        }
        if (yDelta >= 400 || yDelta <= 0) {
            yDir *= -1;
            recColour = getRndColour();
        }
    }

    private Color getRndColour() {
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);

        return new Color(r, g, b);
    }
}
