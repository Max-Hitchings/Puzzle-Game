package utils;

import java.awt.Point;

public class Constants {
    public static final String[] LEVEL_FILES = {
            "level_tutorial.json",
            "level_1.json",
            "level_2.json"
    };
    public enum PlayerSprites {
            NORMAL,
            CORRECT
    }

    public static class Grid {
        public enum TYPES {
            PLAYER,
            WALL,
            EMPTY,
            EMPTY_PLAYER,
            FINISH_TILE,
            PLAYER_CORRECT
        }
    }
    public static class SpriteAtlas {
        public static final Point ATLAS_DIMENSIONS = new Point(2,3);
        public static final Point EMPTY = new Point(0, 0);

        public static final Point PLAYER = new Point(0, 1);
        public static final Point WALL = new Point(1, 0);
        public static final Point EMPTY_PLAYER = new Point(1, 1);
        public static final Point FINISH_TILE = new Point(0, 2);
        public static final Point PLAYER_CORRECT = new Point(1, 2);
        public static Point GetAtlasPos(Grid.TYPES block) {
            return switch (block) {
                case WALL -> WALL;
                case PLAYER -> PLAYER;
                case EMPTY_PLAYER -> EMPTY_PLAYER;
                case FINISH_TILE -> FINISH_TILE;
                case PLAYER_CORRECT -> PLAYER_CORRECT;
                case EMPTY, default -> EMPTY;
            };
        }
    }
    public static class MapBlocks {
        public static final char EMPTY = '_';
        public static final char WALL = '#';
        public static final char PLAYER_SPAWN = 'A';
        public static final char EMPTY_PLAYER = '2';
        public static final char FINISH_TILE = 'F';


        public static Grid.TYPES GetBlockType(char character) {
            return switch (character) {
                case WALL -> Grid.TYPES.WALL;
                case PLAYER_SPAWN -> Grid.TYPES.PLAYER;
                case EMPTY_PLAYER -> Grid.TYPES.EMPTY_PLAYER;
                case FINISH_TILE -> Grid.TYPES.FINISH_TILE;
                case EMPTY, default -> Grid.TYPES.EMPTY;
            };
        }
    }
}
