package levels;

import Sprites.birds.Bird;
import Sprites.birds.RedBird;
import Sprites.birds.YellowBird;
import Sprites.birds.BlueBird;
import Sprites.blocks.Glass;
import Sprites.blocks.Material;
import Sprites.pigs.Pig;
import Sprites.pigs.SmallPig;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import rio.com.Main;

public class level_1 extends MainLevel {
    private Texture background;
    public level_1(Main game) {
        super(game);

        background = new Texture("level 1 bg.png");

        Array<Bird> birds = new Array<>();
        birds.add(new RedBird(this, 450, 500));
        birds.add(new YellowBird(this, 375, 500));
        birds.add(new BlueBird(this, 300, 500));

        Array<Material> glassBlocks = new Array<>();
        Array<Pig> pigs = new Array<>();

        float groundY = 300;
        createGlassSetup(glassBlocks, pigs, 900, groundY);
        createGlassSetup(glassBlocks, pigs, 1300, groundY);

        setupLevel(birds, glassBlocks, pigs);
    }

    private void createGlassSetup(Array<Material> glassBlocks, Array<Pig> pigs, float startX, float groundY) {
        glassBlocks.add(new Glass(this, startX, groundY, 90));
        glassBlocks.add(new Glass(this, startX + 200, groundY, 90));
        pigs.add(new SmallPig(this, startX + 102, groundY + 20));
        glassBlocks.add(new Glass(this, startX, groundY + 204, 0));
        glassBlocks.add(new Glass(this, startX + 200, groundY + 204, 0));

    }


    @Override
    public void render(float delta) {
        super.game.batch.begin();
        super.game.batch.draw(background,0,0,Main.WIDTH,Main.HEIGHT);
        super.game.batch.end();

        super.render(delta);
    }
}
