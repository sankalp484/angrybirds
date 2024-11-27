package Sprites;

import Sprites.birds.Bird;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import rio.com.Main;

public class catapult{
    private Main game;
    private Texture catp;
    private ShapeRenderer shapeRenderer;
    private OrthographicCamera camera;

    // Fixed start points for the lines
    Vector2 startPoint1;
    Vector2 startPoint2;

    // End points that will move with the cursor
    private Vector2 endPoint1;
    private Vector2 endPoint2;

    private boolean isDragging1 = false;
    private boolean isDragging2 = false;

    // Pixels per meter scaling factor
    private static final float ppm = 100f;

    public catapult(Main game, OrthographicCamera cam) {
        this.game = game;
        catp = new Texture("catp.png");
        this.camera = cam;
        shapeRenderer = new ShapeRenderer();

        // Convert start points from pixels to world units
        startPoint1 = new Vector2(515 / ppm, 375 / ppm); // Left point
        endPoint1 = new Vector2(startPoint1);            // Start at same position

        startPoint2 = new Vector2(555 / ppm, 375 / ppm); // Right point
        endPoint2 = new Vector2(startPoint2);
    }

    public void render(float delta, Bird draggingBird) {
        game.batch.begin();
        game.batch.draw(catp, 500, 203); // Draw the catapult texture
        game.batch.end();

        // Render the catapult lines
        shapeRenderer.setProjectionMatrix(camera.combined);
        Gdx.gl.glLineWidth(10);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        // If dragging a bird, anchor the lines to its position
        Vector2 anchor1 = draggingBird != null ? draggingBird.body.getPosition() : startPoint1;
        Vector2 anchor2 = draggingBird != null ? draggingBird.body.getPosition() : startPoint2;
        shapeRenderer.line(startPoint1.x, startPoint1.y, anchor1.x, anchor1.y);
        shapeRenderer.line(startPoint2.x, startPoint2.y, anchor2.x, anchor2.y);
        shapeRenderer.end();
        Gdx.gl.glLineWidth(1);
    }
}
