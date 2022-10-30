package utils;

import main.GameGrid;

import java.awt.*;

public class Constants {
    public static class Grid {
        public enum TYPES {
            PLAYER,
            WALL,
            EMPTY,
            EMPTY_PLAYER
        }
    }
    public static class SpriteAtlas {
        public static final Point PLAYER = new Point(0, 1);
        public static final Point WALL = new Point(1, 0);
        public static final Point EMPTY_PLAYER = new Point(1, 1);
    }
    public static class MapBlocks {
        public static final char EMPTY = '_';
        public static final char WALL = '#';
        public static final char PLAYER_SPAWN = 'A';
        public static final char EMPTY_PLAYER = '2';


        public static Grid.TYPES GetBlockType(char character) {
            switch (character) {
                case WALL:
                    return Grid.TYPES.WALL;
                case PLAYER_SPAWN:
                    return Grid.TYPES.PLAYER;
                case EMPTY_PLAYER:
                    return Grid.TYPES.EMPTY_PLAYER;
                case EMPTY:
                default:
                    return Grid.TYPES.EMPTY;
            }
        }
        public static int BlockToSprite(Grid.TYPES type) {
            switch (type) {
                case WALL:
                    return 2;
                case PLAYER:
                    return 1;
                case EMPTY_PLAYER:
                    return 3;
                case EMPTY:
                default:
                    return 0;
            }
        }
    }
}
