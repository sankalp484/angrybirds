package Sprites.blocks;

import com.badlogic.gdx.graphics.Texture;
import levels.MainLevel;

public class Glass extends Material {
    public Glass(MainLevel level, float posX, float posY, float rotation) {
        super(level, posX, posY, rotation);
    }

    @Override
    protected void initializeTexture() {
        texture = new Texture("glass.png");
        width = 204;  // Width for Glass material
        height = 20; // Height for Glass material
    }
}
