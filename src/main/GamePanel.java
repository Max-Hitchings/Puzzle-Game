package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel {
    private MouseInputs mouseInputs = new MouseInputs(this);
    private int xDelta = 0, yDelta = 0;
    private Random random;
    private ArrayList<MyRect> rects = new ArrayList<MyRect>();
    public GamePanel() {
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        random = new Random();
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

    }

    public void spawnRect(int x, int y) {
        rects.add(new MyRect(x, y));
        System.out.println(rects.size());
    }

    public class MyRect {
        int x, y, w, h;
        int xDir = 5;
        int yDir = 5;
        Color colour;

        public MyRect(int x, int y) {
            this.x = x;
            this.y = y;
            w = random.nextInt(75);
            h = random.nextInt(50);
            colour = randColour();
        }

        public void updateRect() {
            if ((x + w) > 400 || x < 0) {
                xDir *= -1;
                colour = randColour();
            }
            if ((y + h) > 400 || y < 0) {
                yDir *= -1;
                colour = randColour();
            }

            x += xDir;
            y += yDir;
        }

        public void draw(Graphics g) {
            g.setColor(colour);
            g.fillRect(x, y, w, h);
        }
    }

    private Color randColour() {
        int r, g, b;
        r = random.nextInt(255);
        g = random.nextInt(255);
        b = random.nextInt(255);
        return new Color(r, g, b);
    }
}
