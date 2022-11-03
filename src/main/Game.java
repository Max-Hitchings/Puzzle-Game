package main;

import Levels.LevelManager;
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
    private LevelManager levelManager;
    private GameGrid gameGrid;
    private Display infoDisplay;
    int x = 10;

    public final static int TILE_NORMAL_SIZE = 32;
    public final static float SCALE = 2.5f;
    public final static int TILES_IN_WIDTH = 16;
    public final static int TILES_IN_HEIGHT = 9;
    public final static int TILE_SIZE = (int) (TILE_NORMAL_SIZE * SCALE);
    public final static int GAME_WIDTH = TILE_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILE_SIZE * TILES_IN_HEIGHT;

    public Game() {
        initClasses();

        gamePanel = new GamePanel(this, GAME_WIDTH, GAME_HEIGHT);
        gamePanel.setBackground(new Color(63, 75, 48));
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();

        startGameLoop();
    }

    private void initClasses() {
        gameGrid = new GameGrid(TILES_IN_WIDTH, TILES_IN_HEIGHT);
        player = new Player(this, 1, 1);
        levelManager = new LevelManager(this);
        infoDisplay = new Display(GAME_WIDTH - 60, 15);
    }

    public GameGrid getGameGrid() {
        return gameGrid;
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
        levelManager.update();
//        player.update();
    }
    public void render(Graphics g) {
        levelManager.render(g);
        player.render(g);
        infoDisplay.draw_display(g);
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
                infoDisplay.updateFPS(frames);
                infoDisplay.updateTPS(updates);
//                System.out.println("FPS " + frames + " | TPS: " + updates);
                frames = 0;
                updates = 0;
            }

        }

    }
    public Player getPlayer() {
        return player;
    }
}
