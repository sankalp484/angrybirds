package Sprites.birds;

import com.badlogic.gdx.graphics.Texture;
import levels.MainLevel;

public class BlueBird extends Bird {
    public BlueBird(MainLevel level, float xpos, float ypos) {
        super(level, xpos, ypos);
    }

    @Override
    protected void initializeTexture() {
        texture = new Texture("blue bird.png");
        textureWidth = 33;
        textureHeight = 32;
        rad = 13;
    }
}
