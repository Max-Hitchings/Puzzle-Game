package entities;

import main.Game;
import utils.Constants;
import utils.LoadStuff;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Entity {
    float x, y;
    Game game;
    BufferedImage sprite;
    public HashMap<Constants.PlayerSprites, BufferedImage> sprites;

    public Entity(Game game, int x, int y) {
        sprites = new HashMap<>();
        loadSprites();
        sprite = sprites.get(Constants.PlayerSprites.NORMAL);
        this.game = game;
        this.x = x;
        this.y = y;
//        sprite = LoadStuff.Sprite(spriteAtlasPos);

    }

    private void loadSprites() {
        sprites.put(Constants.PlayerSprites.NORMAL, LoadStuff.Sprite(Constants.SpriteAtlas.PLAYER));
        sprites.put(Constants.PlayerSprites.CORRECT, LoadStuff.Sprite(Constants.SpriteAtlas.PLAYER_CORRECT));
    }


}
