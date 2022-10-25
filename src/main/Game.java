package main;

import entities.Player;
import infoDisplay.Display;

import java.awt.*;

public class Game implements Runnable{
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;

    private final int FPS_SET = 144;
    private final int TPS_SET = 200;
    private Player player;
    public Game() {
        initClasses();

        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();

        startGameLoop();
    }

    private void initClasses() {
        player = new Player(20, 20);
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
        player.update();
    }
    public void render(Graphics g) {
        player.render(g);
    }
    @Override
    public void run() {

        double frameTime = 1000000000.0 / FPS_SET;
        double timePerTick = 1000000000.0 / TPS_SET;

        long previousTime = System.nanoTime();
        int updates = 0;

        int frames = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaLag = 0;
        double deltaFrames = 0;

        long currentTime;

        while (true) {
            currentTime = System.nanoTime();

            deltaLag += (currentTime - previousTime) / timePerTick;
            deltaFrames += (currentTime - previousTime) / frameTime;
            previousTime = currentTime;

            if (deltaLag >= 1) {
                update();
                updates++;
                deltaLag--;
            }

            if (deltaFrames >= 1) {
                gamePanel.repaint();
                frames++;
                deltaFrames--;
            }

//            if (now - lastFrame >= frameTime) {
//                gamePanel.repaint();
//                lastFrame = now;
//                frames++;
//            }


            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                gamePanel.update_fps(frames);
                gamePanel.update_tps(updates);
                System.out.println("FPS " + frames + " | TPS: " + updates);
                frames = 0;
                updates = 0;
            }

        }

    }
    public Player getPlayer() {
        return player;
    }
}
