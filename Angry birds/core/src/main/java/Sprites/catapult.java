package Sprites;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import rio.com.Main;

public class catapult implements InputProcessor {
    private Main game;
    private Texture catp;
    private ShapeRenderer shapeRenderer;
    private OrthographicCamera camera;

    // Fixed start points for the lines
    private Vector2 startPoint1;
    private Vector2 startPoint2;

    // End points that will move with the cursor
    private Vector2 endPoint1;
    private Vector2 endPoint2;

    private boolean isDragging1 = false;
    private boolean isDragging2 = false;

    // Pixels per meter scaling factor
    private static final float ppm = 100f; // Change this to match the ppm in your MainLevel class

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

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public boolean keyDown(int i) {
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button == Input.Buttons.LEFT) {
            // Begin dragging
            isDragging1 = true;
            isDragging2 = true;

            // Convert screen coordinates to world coordinates
            Vector3 worldCoords = camera.unproject(new Vector3(screenX, screenY, 0));
            Vector2 mousePos = new Vector2(worldCoords.x, worldCoords.y);

            // Update end points to follow the cursor
            endPoint1.set(mousePos);
            endPoint2.set(mousePos);
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Vector3 worldCoords = camera.unproject(new Vector3(screenX, screenY, 0));
        Vector2 mousePos = new Vector2(worldCoords.x, worldCoords.y);

        // Update only the end points to follow the cursor
        if (isDragging1) {
            endPoint1.set(mousePos);
        }
        if (isDragging2) {
            endPoint2.set(mousePos);
        }

        return true;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(float v, float v1) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (button == Input.Buttons.LEFT) {
            // Stop dragging
            isDragging1 = false;
            isDragging2 = false;
        }
        return true;
    }

    @Override
    public boolean touchCancelled(int i, int i1, int i2, int i3) {
        return false;
    }

    public void render(float delta) {
        game.batch.begin();
        game.batch.draw(catp, 500, 203);
        game.batch.end();

        camera.update();
        shapeRenderer.setProjectionMatrix(camera.combined);

        Gdx.gl.glLineWidth(10);
        shapeRenderer.setColor(Color.BLACK);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        // Draw the lines anchored at fixed start points
        if (isDragging1) {
            shapeRenderer.line(startPoint1.x, startPoint1.y, endPoint1.x, endPoint1.y);
        }
        if (isDragging2) {
            shapeRenderer.line(startPoint2.x, startPoint2.y, endPoint2.x, endPoint2.y);
        }

        shapeRenderer.end();
        Gdx.gl.glLineWidth(1);
    }
}
