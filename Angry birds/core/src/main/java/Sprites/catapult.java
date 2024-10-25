package Sprites;

import com.badlogic.gdx.graphics.Texture;
import rio.com.Main;

public class catapult {
    private Main game;
    private Texture catp;

    public catapult(Main game) {
        this.game = game;
        catp = new Texture("catp.png");
    }

    public void render(float delta){
        game.batch.begin();
        game.batch.draw(catp, 500, 203);
        game.batch.end();
    }
}
