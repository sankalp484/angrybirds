package levels;

import Screens.LooseScreen;
import Screens.PauseMenu;
import Screens.WinScreen;
import Sprites.birds.Bird;
import Sprites.blocks.Material;
import Sprites.catapult;
import Sprites.pigs.Pig;
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
    public Texture ground;

    public Array<Material> blocks; // Array for all glass blocks
    public Array<Pig> pigs;    // Array for all pigs
    public Array<Bird> birds;       // Array for all birds
    private catapult catp;

    public Array<Body> bodiesToDestroy;
    private boolean start_collision = false;

    private int currentBirdIndex = 0;
    private static final float launchX = 535; // In pixels
    private static final float launchY = 375; // In pixels

    public MainLevel(Main game) {
        this.game = game;

        // Load shared textures
        menu_inac = new Texture("Menu Inactive.png");
        menu_ac = new Texture("Menu active.png");
        ground = new Texture("ground.png");

        bodiesToDestroy = new Array<>();

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
        setupContactListener();
    }

    private void createGround() {
        BodyDef gbDef = new BodyDef();
        gbDef.position.set(0, 0);

        Body gBody = world.createBody(gbDef);

        PolygonShape gShape = new PolygonShape();
        gShape.setAsBox(1920 / ppm, 205 / ppm);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = gShape;
        fixtureDef.density = 1.0f;      // Standard density
        fixtureDef.friction = 5.0f;

        gBody.createFixture(fixtureDef);
        gShape.dispose();

        //Right Boundary
        BodyDef rightBoundDef = new BodyDef();
        rightBoundDef.position.set(1950/ppm,0);

        Body rBound = world.createBody(rightBoundDef);

        PolygonShape rbShape = new PolygonShape();
        rbShape.setAsBox(20/ppm,1080/ppm);

        rBound.createFixture(rbShape,1.0f);
        rbShape.dispose();
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
                start_collision = true;
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

    private void setupContactListener() {
        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Object a = contact.getFixtureA().getBody().getUserData();
                Object b = contact.getFixtureB().getBody().getUserData();

                if (a instanceof Material && start_collision) ((Material) a).handleCollision();
                if (b instanceof Material && start_collision) ((Material) b).handleCollision();
                if (a instanceof Pig && start_collision) ((Pig) a).handleCollision();
                if (b instanceof Pig && start_collision) ((Pig) b).handleCollision();
            }

            @Override public void endContact(Contact contact) {}
            @Override public void preSolve(Contact contact, Manifold oldManifold) {}
            @Override public void postSolve(Contact contact, ContactImpulse impulse) {}
        });
    }

    public void setupLevel(Array<Bird> birds, Array<Material> glassBlocks, Array<Pig> pigs) {
        this.birds = birds;
        this.blocks = glassBlocks;
        this.pigs = pigs;
    }

    @Override
    public void render(float delta) {
        world.step(1 / 100f, 8, 2); // Step the physics world
        for (Body body : bodiesToDestroy) {
            if (body != null) {
                world.destroyBody(body);
            }
        }
        bodiesToDestroy.clear();
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

        if (currentBirdIndex < birds.size) {
            Bird currentBird = birds.get(currentBirdIndex);
            if (!currentBird.hasLaunched && !currentBird.isDragging) {
                currentBird.body.setTransform(launchX / ppm, launchY / ppm, 0); // Place at launch position
            } else if (currentBird.isReadyForRemoval()) {
                // Destroy the bird and advance to the next one
                bodiesToDestroy.add(currentBird.body);
                birds.removeIndex(currentBirdIndex);
            }
        }

        if (pigs.size == 0) {
            game.setScreen(new WinScreen(game));
        }

        // Check lose condition
        if (birds.size == 0 && pigs.size > 0) {
            game.setScreen(new LooseScreen(game));
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
