package levels;

import Sprites.birds.Bird;
import Sprites.birds.RedBird;
import Sprites.birds.YellowBird;
import Sprites.birds.BlueBird;
import Sprites.blocks.Glass;
import Sprites.blocks.Rock;
import Sprites.blocks.Wood;
import Sprites.catapult;
import Sprites.pigs.BigPig;
import Sprites.pigs.MediumPig;
import Sprites.pigs.SmallPig;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
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
    private Array<Bird> birds;
    private catapult catp;

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
        birds = new Array<>();
        birds.add(rb);
        birds.add(yb);
        birds.add(bb);
        catp = new catapult(game,camera);

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                Vector3 worldCoordinates = camera.unproject(new Vector3(screenX, screenY, 0));
                Vector2 touchPos = new Vector2(worldCoordinates.x, worldCoordinates.y);

                for (Bird bird : birds) {
                    bird.startDrag(touchPos);
                }
                return true;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                Vector3 worldCoordinates = camera.unproject(new Vector3(screenX, screenY, 0));
                Vector2 touchPos = new Vector2(worldCoordinates.x, worldCoordinates.y);

                for (Bird bird : birds) {
                    bird.drag(touchPos);
                }
                return true;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                for (Bird bird : birds) {
                    bird.release();
                }
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {

        super.game.batch.begin();
        super.game.batch.draw(background,0,205);
        super.game.batch.end();

        super.render(delta);

        Bird draggingBird = null;
        for (Bird bird : birds) {
            if (bird.isDragging) {
                draggingBird = bird;
                break;
            }
        }
        catp.render(delta,draggingBird);

        for (Bird bird : birds) {
            bird.render(delta);
        }
        sp.render(delta);
        mp.render(delta);
        bp.render(delta);
        wood.render(delta);
        rock.render(delta);
        glass.render(delta);
    }
}
