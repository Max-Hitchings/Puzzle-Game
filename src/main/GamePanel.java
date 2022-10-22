package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel {
    float SF = 0.6f;
    float PANEL_SIZE_X = 1280 * SF;
    float PANEL_SIZE_Y = 800 * SF;
    private MouseInputs mouseInputs = new MouseInputs(this);
    private int xDelta = 0, yDelta = 0;
    private Random random;
    private ArrayList<MyRect> rects = new ArrayList<MyRect>();
    private ArrayList<Circle> circles = new ArrayList<>();
    public GamePanel() {
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        random = new Random();
    }

    private void setPanelSize() {
        // 32px tiles so 40 wide and 25 high
        Dimension size = new Dimension((int) PANEL_SIZE_X, (int) PANEL_SIZE_Y);
        setMinimumSize(size);
        setMaximumSize(size);
        setPreferredSize(size);
    }

    public void changeXDelta(int value) {
        this.xDelta += value;
//        System.out.println(this.xDelta);

//        repaint();
    }
    public void changeYDelta(int value) {
        this.yDelta += value;
        System.out.println(this.yDelta);
//        repaint();
    }

    public void setRectPos(int x, int y) {
        this.xDelta = x;
        this.yDelta = y;
//        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.DARK_GRAY);
        g.fillRect(xDelta, yDelta, 200, 60);
        g.drawRect(100, 200, 200, 60);

        for (int i = 0; i <= rects.size()-1; i++){
            rects.get(i).updateRect();
            rects.get(i).draw(g);
        }
        for (int i = 0; i <= circles.size()-1; i++){
            circles.get(i).updateCircle();
            circles.get(i).draw(g);
        }

    }

    public void spawnRect(int x, int y) {
        rects.add(new MyRect(x, y));
        System.out.println(rects.size());
    }

    public void spawnCirlce(int x, int y, int r){
        circles.add(new Circle(x, y, r));
        System.out.println(circles.size());
    }

    public class Circle {
        int x, y, r;
        int xVelocity = 2;
        int yVelocity = 2;
        Color colour;

        public Circle(int x, int y, int r) {
            this.r = r;
            colour = getRandColour();
        }

        public void updateCircle() {
            if ((x + r) > PANEL_SIZE_X || x < 0) {
                xVelocity *= -1;
                colour = getRandColour();
            }
            if ((y + r) > PANEL_SIZE_Y || y < 0) {
                yVelocity *= -1;
                colour = getRandColour();
            }
        }

        public void draw(Graphics g) {
            g.setColor(colour);
            g.drawOval(x, y, r, r);
        }
    }

    public class MyRect {
        int x, y, w, h;
        int xVelocity = 3;
        int yVelocity = 3;
        Color colour;

        public MyRect(int x, int y) {
            this.x = x;
            this.y = y;
            w = random.nextInt(75);
            h = random.nextInt(50);
//            w = h = 50;
            colour = getRandColour();
        }

        public void updateRect() {
            if ((x + w) > PANEL_SIZE_X || x < 0) {
                xVelocity *= -1;
                colour = getRandColour();
            }
            if ((y + h) > PANEL_SIZE_Y || y < 0) {
                yVelocity *= -1;
                colour = getRandColour();
            }

            x += xVelocity;
            y += yVelocity;
        }

        public void draw(Graphics g) {
            g.setColor(colour);
            g.fillRect(x, y, w, h);
        }
    }

    private Color getRandColour() {
        int r, g, b;
        r = random.nextInt(255);
        g = random.nextInt(255);
        b = random.nextInt(255);
        return new Color(r, g, b);
    }
}
