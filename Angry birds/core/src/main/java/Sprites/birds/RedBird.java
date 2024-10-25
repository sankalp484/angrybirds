package Sprites.birds;

import com.badlogic.gdx.graphics.Texture;
import levels.MainLevel;

public class RedBird extends Bird {
    public RedBird(MainLevel level, float xpos, float ypos) {
        super(level, xpos, ypos);
    }

    @Override
    protected void initializeTexture() {
        texture = new Texture("red bird.png");
        textureWidth = 47;
        textureHeight = 47;
        rad = 20;
    }
}
