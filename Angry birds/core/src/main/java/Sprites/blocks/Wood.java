package Sprites.blocks;

import com.badlogic.gdx.graphics.Texture;
import levels.MainLevel;

public class Wood extends Material {
    public Wood(MainLevel level, float posX, float posY, float rotation) {
        super(level, posX, posY, rotation);
    }

    @Override
    protected void initializeTexture() {
        texture = new Texture("wood.png");
        width = 204;  // Width for Wood material
        height = 20; // Height for Wood material
    }
}
