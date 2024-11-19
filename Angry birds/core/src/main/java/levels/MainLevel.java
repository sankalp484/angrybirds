package levels;

import Screens.LooseScreen;
import Screens.PauseMenu;
import Screens.WinScreen;
import Sprites.birds.Bird;
import Sprites.blocks.Glass;
import Sprites.blocks.Material;
import Sprites.catapult;
import Sprites.pigs.Pig;
import Sprites.pigs.SmallPig;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import rio.com.Main;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class MainLevel implements Screen {
    public static final float ppm = 100f; // Pixels per meter
    public World world;
    private Box2DDebugRenderer b2dr;
    public Main game;
    public OrthographicCamera camera;
    public ShapeRenderer shapeRenderer;

    public Texture menu_inac;
    public Texture menu_ac;
    public Texture win;
    public Texture loose;
    public Texture ground;

    private Array<Material> blocks; // Array for all glass blocks
    private Array<Pig> pigs;    // Array for all pigs
    private Array<Bird> birds;       // Array for all birds
    private catapult catp;

    private float win_width = 229;
    private float win_height = 200;
    private float loose_width = 477;
    private float loose_height = 200;

    public MainLevel(Main game) {
        this.game = game;

        // Load shared textures
        menu_inac = new Texture("Menu Inactive.png");
        menu_ac = new Texture("Menu active.png");
        win = new Texture("win button.png");
        loose = new Texture("loose button.png");
        ground = new Texture("ground.png");

        // Initialize camera, world, and debug renderer
        camera = new OrthographicCamera(Gdx.graphics.getWidth() / ppm, Gdx.graphics.getHeight() / ppm);
        world = new World(new Vector2(0, -9.81f), true);
        b2dr = new Box2DDebugRenderer();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        shapeRenderer = new ShapeRenderer();

        // Create catapult and ground
        catp = new catapult(game, camera);
        createGround();

        // Initialize entity arrays
        blocks = new Array<>();
        pigs = new Array<>();
        birds = new Array<>();

        setupInputHandling();
    }

    private void createGround() {
        BodyDef gbDef = new BodyDef();
        gbDef.position.set(0, 0);

        Body gBody = world.createBody(gbDef);

        PolygonShape gShape = new PolygonShape();
        gShape.setAsBox(1920 / ppm, 205 / ppm); // Width and height in Box2D units

        gBody.createFixture(gShape, 1.0f);
        gShape.dispose();
    }

    private void setupInputHandling() {
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

    public void setupLevel(Array<Bird> birds, Array<Material> glassBlocks, Array<Pig> pigs) {
        this.birds = birds;
        this.blocks = glassBlocks;
        this.pigs = pigs;
    }

    @Override
    public void render(float delta) {
        world.step(1 / 60f, 6, 2); // Step the physics world
        camera.update();
        b2dr.render(world, camera.combined);

        game.batch.begin();
        if (Gdx.input.getX()>30 && Gdx.input.getX()<menu_inac.getWidth()+30 && game.HEIGHT - Gdx.input.getY()>Main.HEIGHT-menu_ac.getHeight()-30 && game.HEIGHT - Gdx.input.getY()<Main.HEIGHT){
            game.batch.draw(menu_ac,30-12,Main.HEIGHT-menu_inac.getHeight()-30-12,130,128);
            if (Gdx.input.isTouched()){
                game.setScreen(new PauseMenu(game));
            }
        }else{
            game.batch.draw(menu_inac,30,Main.HEIGHT-menu_inac.getHeight()-30);
        }

        if (Gdx.input.getX()>game.WIDTH-(win_width+loose_width) && Gdx.input.getX()<game.WIDTH && game.HEIGHT - Gdx.input.getY()>game.HEIGHT-win_height && game.HEIGHT - Gdx.input.getY()<game.HEIGHT){
            game.batch.draw(win, game.WIDTH-(win_width+loose_width), game.HEIGHT-win_height, win_width,win_height);
            if (Gdx.input.isTouched()){
                game.setScreen(new WinScreen(game));
            }
        }else{
            game.batch.draw(win, game.WIDTH-(win_width+loose_width), game.HEIGHT-win_height, win_width,win_height);
        }

        if (Gdx.input.getX()>game.WIDTH-(loose_width) && Gdx.input.getX()<game.WIDTH && game.HEIGHT - Gdx.input.getY()>game.HEIGHT-win_height && game.HEIGHT - Gdx.input.getY()<game.HEIGHT){
            game.batch.draw(loose, game.WIDTH-(loose_width), game.HEIGHT-win_height, loose_width,loose_height);
            if (Gdx.input.isTouched()){
                game.setScreen(new LooseScreen(game));
            }
        }else{
            game.batch.draw(loose, game.WIDTH-(loose_width), game.HEIGHT-win_height, loose_width,loose_height);
        }
        game.batch.draw(ground,0,0);
        game.batch.end();

        // Render entities
        Bird draggingBird = null;
        for (Bird bird : birds) {
            if (bird.isDragging) {
                draggingBird = bird;
                break;
            }
        }
        catp.render(delta, draggingBird);

        for (Bird bird : birds) {
            bird.render(delta);
        }
        for (Material block : blocks) {
            block.render(delta);
        }
        for (Pig pig : pigs) {
            pig.render(delta);
        }
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width / ppm, height / ppm);
    }

    @Override
    public void dispose() {
        world.dispose();
        b2dr.dispose();
        shapeRenderer.dispose();
    }

    // Remaining methods left as is
    @Override public void show() {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
}
