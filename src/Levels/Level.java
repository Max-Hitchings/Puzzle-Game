package Levels;

import utils.Constants.Grid;

import java.awt.*;

public class Level {
//    Currently unused
    public Point dimensions;
    public Point playerSpawn;
    public Grid.TYPES[][] layout;

    public Level(Grid.TYPES[][] layout, Point playerSpawn, Point dimensions) {
        this.layout = layout;
        this.playerSpawn = playerSpawn;
        this.dimensions = dimensions;
    }
}
