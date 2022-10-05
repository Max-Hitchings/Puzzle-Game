package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.JPanel;
import java.awt.*;
import java.util.Random;

public class GamePanel extends JPanel {
    int PANEL_SIZE_X = 1280;
    int PANEL_SIZE_Y = 800;
    private MouseInputs mouseInputs = new MouseInputs(this);
    private float xDelta = 0, yDelta = 0;
    private float xDir = .5f, yDir = .5f;
    private Color recColour = new Color(23, 67, 200);
    private Random random = new Random();

    public GamePanel() {
        setPanelSize();

        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void setPanelSize() {
        // 32px tiles so 40 wide and 25 high
        Dimension size = new Dimension(PANEL_SIZE_X, PANEL_SIZE_Y);
        setMinimumSize(size);
        setMaximumSize(size);
        setPreferredSize(size);
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
        if (xDelta >= PANEL_SIZE_X || xDelta <= 0) {
            xDir *= -1;
            recColour = getRndColour();
        }
        if (yDelta >= PANEL_SIZE_Y || yDelta <= 0) {
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
