package levels;

import Screens.LevelScreen;
import Screens.PauseMenu;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import rio.com.Main;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class MainLevel implements Screen {
    public static final float ppm = 100f;
    private World world;
    private Box2DDebugRenderer b2dr;
    private Main game;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;
    public Texture menu_inac;
    public Texture menu_ac;

    public MainLevel(Main game) {
        this.game = game;
        menu_inac = new Texture("Menu Inactive.png");
        menu_ac = new Texture("Menu active.png");
        world = new World(new Vector2(0, -9.81f), true);
        b2dr = new Box2DDebugRenderer();
        camera = new OrthographicCamera(Gdx.graphics.getWidth() / ppm, Gdx.graphics.getHeight() / ppm);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
        shapeRenderer = new ShapeRenderer();
        createGround();
        createCircle();
    }

    private void createGround() {
        BodyDef gbDef = new BodyDef();
        gbDef.position.set(0, 0);
        Body gBody = world.createBody(gbDef);
        PolygonShape gShape = new PolygonShape();
        gShape.setAsBox(1920 / ppm, 200 / ppm);
        gBody.createFixture(gShape, 1.0f);
        gShape.dispose();
    }

    private void createCircle() {
        BodyDef cDef = new BodyDef();
        cDef.type = BodyDef.BodyType.DynamicBody;
        cDef.position.set(500 / ppm, 500 / ppm);
        Body cBody = world.createBody(cDef);
        CircleShape cShape = new CircleShape();
        cShape.setRadius(25 / ppm);
        cBody.createFixture(cShape, 1.0f);
        cShape.dispose();
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        world.step(1 / 60f, 6, 2);
        camera.update();
        b2dr.render(world, camera.combined);
        game.batch.begin();
        if (Gdx.input.getX() > 30 && Gdx.input.getX() < menu_inac.getWidth() + 30 && game.HEIGHT - Gdx.input.getY() > Main.HEIGHT - menu_ac.getHeight() - 30 && game.HEIGHT - Gdx.input.getY() < Main.HEIGHT) {
            game.batch.draw(menu_ac, 30 - 12, Main.HEIGHT - menu_inac.getHeight() - 30 - 12, 130, 128);
            if (Gdx.input.isTouched()) {
                this.dispose();
                game.setScreen(new PauseMenu(game, this));
            }
        } else {
            game.batch.draw(menu_inac, 30, Main.HEIGHT - menu_inac.getHeight() - 30);
        }
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width / ppm, height / ppm);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        world.dispose();
        b2dr.dispose();
        shapeRenderer.dispose();
    }
}
