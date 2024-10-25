package Sprites.blocks;

import com.badlogic.gdx.graphics.Texture;
import levels.MainLevel;

public class Rock extends Material {
    public Rock(MainLevel level, float posX, float posY, float rotation) {
        super(level, posX, posY, rotation);
    }

    @Override
    protected void initializeTexture() {
        texture = new Texture("rock.png");
        width = 204;  // Width for Rock material
        height = 20; // Height for Rock material (square)
    }
}
