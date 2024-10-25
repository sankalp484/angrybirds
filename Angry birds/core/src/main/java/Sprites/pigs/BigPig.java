package Sprites.pigs;

import com.badlogic.gdx.graphics.Texture;
import levels.MainLevel;

public class BigPig extends Pig {
    public BigPig(MainLevel level, float posx, float posy) {
        super(level, posx, posy);
    }

    @Override
    protected void initializeTexture() {
        texture = new Texture("big pig.png");
        textureWidth = 100;  // Width for BigPig
        textureHeight = 99; // Height for BigPig
        rad = 45;
    }
}
