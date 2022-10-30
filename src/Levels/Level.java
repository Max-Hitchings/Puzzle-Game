package Levels;

import utils.Constants.Grid;

public class Level {
    public Grid.TYPES[][] layout;

    public Level(Grid.TYPES[][] layout) {
        this.layout = layout;
    }
}
