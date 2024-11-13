package levels;

import Screens.*;
import Sprites.catapult;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import rio.com.Main;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class MainLevel implements Screen {
    public static final float ppm = 100f; // Pixels per meter
    public World world;
    private Box2DDebugRenderer b2dr;
    public Main game;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;
    public Texture menu_inac;
    public Texture menu_ac;
    public Texture win;
    public Texture loose;
    public Texture ground;
    private catapult catp;
    private float win_width = 229;
    private float win_height = 200;
    private float loose_width = 477;
    private float loose_height = 200;

    public MainLevel(Main game) {
        this.game = game;
        menu_inac = new Texture("Menu Inactive.png");
        menu_ac = new Texture("Menu active.png");
        win = new Texture("win button.png");
        loose = new Texture("loose button.png");
        ground = new Texture("ground.png");
        camera = new OrthographicCamera(Gdx.graphics.getWidth() / ppm, Gdx.graphics.getHeight() / ppm);
        catp = new catapult(game,camera);

        // Initialize Box2D world
        world = new World(new Vector2(0, -9.81f), true);
        b2dr = new Box2DDebugRenderer();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0); // Center the camera
        camera.update(); // Update the camera's matrix

        shapeRenderer = new ShapeRenderer();

        // Create ground and circle
        createGround();
    }

    private void createGround() {
        BodyDef gbDef = new BodyDef();
        gbDef.position.set(0, 0); // Ground at the bottom left corner

        Body gBody = world.createBody(gbDef);

        PolygonShape gShape = new PolygonShape();
        gShape.setAsBox(1920 / ppm, 205 / ppm); // Width and height in Box2D units

        gBody.createFixture(gShape, 1.0f);
        gShape.dispose(); // Dispose of shape after use
    }



    @Override
    public void show() {
        // Not used in this example
    }

    @Override
    public void render(float delta) {
        world.step(1 / 60f, 6, 2); // Step the world forward

        // Update the camera before rendering
        camera.update();

        // Render the Box2D world for debugging
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
        catp.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width / ppm, height / ppm); // Adjust camera to new viewport
    }

    @Override
    public void pause() {
        // Not used in this example
    }

    @Override
    public void resume() {
        // Not used in this example
    }

    @Override
    public void hide() {
        // Not used in this example
    }

    @Override
    public void dispose() {
        world.dispose(); // Clean up the world
        b2dr.dispose();  // Clean up debug renderer
        shapeRenderer.dispose(); // Clean up shape renderer
    }
}
