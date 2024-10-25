package levels;

import com.badlogic.gdx.graphics.Texture;
import rio.com.Main;

public class level_2 extends MainLevel{
    public Texture background;
    public level_2(Main game) {
        super(game);
        background = new Texture("level 2 bg.jpg");
    }

    @Override
    public void render(float delta) {
        super.game.batch.begin();
        super.game.batch.draw(background,0,0);
        super.game.batch.end();

        super.render(delta);
    }
}
