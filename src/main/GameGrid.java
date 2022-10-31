package main;

import Levels.Level;
import utils.Constants.Grid;

import static main.Game.TILE_SIZE;

public class GameGrid {
    private Grid.TYPES[][] grid;

    public GameGrid(int tile_w, int tile_h) {
        grid = new Grid.TYPES[tile_h][tile_w];
//        Level level = getLevel1();
    }

    public void loadLevel(Level level) {
//        Initialise grid
        grid = level.layout;
    }
    public boolean isOkayToMove(int x, int y) {
        x = x / TILE_SIZE;
        y = y / TILE_SIZE;
//        System.out.println(grid[y][x]);
        if (grid[y][x] != Grid.TYPES.WALL && grid[y][x] != Grid.TYPES.EMPTY_PLAYER){
            return true;
        } else {
            return false;
        }
    }
}
