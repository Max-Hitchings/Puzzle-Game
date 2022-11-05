package main;

import Levels.Level;
import utils.Constants.Grid;

import java.awt.*;
import java.util.ArrayList;

import static main.Game.TILE_SIZE;

public class GameGrid {
    private Grid.TYPES[][] grid;
    private ArrayList<Point> finishTiles = new ArrayList<>();

    public GameGrid(int tile_w, int tile_h) {
        grid = new Grid.TYPES[tile_h][tile_w];
//        Level level = getLevel1();
    }

    public Grid.TYPES[][] getGrid() {return grid;};

    public void loadLevel(Level level) {
//        Initialise grid
//        grid = level.layout.clone();
        grid = level.layout;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == Grid.TYPES.FINISH_TILE) {
                    finishTiles.add(new Point(j, i));
                }
            }
        }
    }
    public boolean isOkayToMove(int x, int y) {
        x = x / TILE_SIZE;
        y = y / TILE_SIZE;
        if (grid[y][x] != Grid.TYPES.WALL && grid[y][x] != Grid.TYPES.EMPTY_PLAYER){
            return true;
        } else {
            return false;
        }
    }
    public boolean checkWin(int x, int y) {
        x = x / TILE_SIZE;
        y = y / TILE_SIZE;
        return grid[y][x] == Grid.TYPES.FINISH_TILE;
    }
    public Point checkForNewSubPlayers(int x, int y, Point delta) {
        x = x / TILE_SIZE;
        y = y / TILE_SIZE;
        if (grid[y+1][x] == Grid.TYPES.EMPTY_PLAYER){
            grid[y+1][x] = Grid.TYPES.EMPTY;
            return new Point(delta.x, 1 + delta.y);
        } if (grid[y-1][x] == Grid.TYPES.EMPTY_PLAYER){
            grid[y-1][x] = Grid.TYPES.EMPTY;
            return new Point(delta.x, -1 + delta.y);
        } if (grid[y][x+1] == Grid.TYPES.EMPTY_PLAYER){
            grid[y][x+1] = Grid.TYPES.EMPTY;
            return new Point(1 + delta.x, delta.y);
        } if (grid[y][x-1] == Grid.TYPES.EMPTY_PLAYER){
            grid[y][x-1] = Grid.TYPES.EMPTY;
            return new Point(-1 + delta.x, delta.y);
        }
        return null;
    }
}
