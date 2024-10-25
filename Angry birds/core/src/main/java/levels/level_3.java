package levels;

import com.badlogic.gdx.graphics.Texture;
import rio.com.Main;

public class level_3 extends MainLevel{
    public Texture background;
    public level_3(Main game) {
        super(game);
        background = new Texture("level 3 bg.jpg");
    }

    @Override
    public void render(float delta) {
        super.game.batch.begin();
        super.game.batch.draw(background,0,0,Main.WIDTH,Main.HEIGHT);
        super.game.batch.end();

        super.render(delta);
    }
}
