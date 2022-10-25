package main;

import infoDisplay.Display;

public class Game implements Runnable{
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;

    private final int FPS_SET = 144;
    private final int TPS_SET = 200;
    public Game() {
        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        startGameLoop();
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
        gamePanel.updateGame();
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
}
