package Sprites.pigs;

import com.badlogic.gdx.graphics.Texture;
import levels.MainLevel;

public class SmallPig extends Pig {
    public SmallPig(MainLevel level, float posx, float posy) {
        super(level, posx, posy);
    }

    @Override
    protected void initializeTexture() {
        texture = new Texture("small pig.png");
        textureWidth = 50;  // Width for SmallPig
        textureHeight = 48; // Height for SmallPig
        rad = 24;
    }

    @Override
    protected void initializeHitPoints() {
        hitPoints = 1; // SmallPig requires 1 hit
    }
}
