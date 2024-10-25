package levels;

import Sprites.birds.RedBird;
import Sprites.birds.YellowBird;
import Sprites.birds.BlueBird;
import Sprites.blocks.Glass;
import Sprites.blocks.Rock;
import Sprites.blocks.Wood;
import Sprites.pigs.BigPig;
import Sprites.pigs.MediumPig;
import Sprites.pigs.SmallPig;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import rio.com.Main;

public class level_1 extends MainLevel implements Screen {
    public Texture background;
    public RedBird rb;
    public YellowBird yb;
    public BlueBird bb;
    public SmallPig sp;
    public BigPig bp;
    public MediumPig mp;
    public Wood wood;
    public Glass glass;
    public Rock rock;

    public level_1(Main game) {
        super(game);
        background = new Texture("level 1 bg.png");
        rb = new RedBird(this, 450,500);
        yb = new YellowBird(this, 375,500);
        bb = new BlueBird(this, 300,500);
        sp = new SmallPig(this, 920, 500);
        mp = new MediumPig(this, 1000, 500);
        bp = new BigPig(this, 1100, 500);
        wood = new Wood(this, 920,800,30);
        glass = new Glass(this, 1000, 700, 110);
        rock = new Rock(this, 1100, 600,0);
    }

    @Override
    public void render(float delta) {

        super.game.batch.begin();
        super.game.batch.draw(background,0,205);
        super.game.batch.end();

        super.render(delta);

        rb.render(delta);
        yb.render(delta);
        bb.render(delta);
        sp.render(delta);
        mp.render(delta);
        bp.render(delta);
        wood.render(delta);
        rock.render(delta);
        glass.render(delta);
    }
}
