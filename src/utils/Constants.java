package utils;

import java.awt.*;

public class Constants {
    public static class SpriteAtlas {
        public static final Point PLAYER = new Point(0, 1);
        public static final Point WALL = new Point(1, 0);
    }
    public static class MapBlocks {
        public static final char EMPTY = '_';
        public static final char WALL = '#';
        public static final char PLAYER_SPAWN = 'A';


        public static int GetBlockType(char character) {
            switch (character) {
                case WALL:
                    return 2;
                case PLAYER_SPAWN:
                    return 1;
                case EMPTY:
                default:
                    return 0;
            }
        }
    }
}
