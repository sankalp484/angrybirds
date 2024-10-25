package Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import rio.com.Main;

public class LoadingScreen implements Screen {
    private ShapeRenderer shapeRenderer;
    private BitmapFont font;
    private float progress;
    private float loadDuration;
    private float elapsedTime;
    private Texture bg;

    private Main game;
    private static final int BAR_WIDTH = 600;
    private static final int BAR_HEIGHT = 40;

    public LoadingScreen(Main game) {
        this.game = game;
        shapeRenderer = new ShapeRenderer();
        bg = new Texture("load.png");

        // Initialize a larger font
        font = new BitmapFont();
        font.getData().setScale(2f);
        font.setColor(Color.ROYAL);

        progress = 0f;
        loadDuration = 5f;
        elapsedTime = 0f;
    }

    @Override
    public void show() {
        progress = 0f;
        elapsedTime = 0f;
    }

    @Override
    public void render(float delta) {
        // Clear the screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Update elapsed time and calculate progress
        elapsedTime += delta;
        progress = Math.min(elapsedTime / loadDuration, 1f);

        // Calculate the center position for the progress bar
        float barX = (Gdx.graphics.getWidth() - BAR_WIDTH) / 2;
        float barY = 100; // Position bar at the bottom center of the screen

        game.batch.begin();
        game.batch.draw(bg,0,0);
        game.batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // Draw background bar
        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.rect(barX, barY, BAR_WIDTH, BAR_HEIGHT);

        // Draw the filled progress portion of the bar
        shapeRenderer.setColor(Color.ORANGE);
        shapeRenderer.rect(barX, barY, BAR_WIDTH * progress, BAR_HEIGHT);

        shapeRenderer.end();

        game.batch.begin();
        font.draw(game.batch, "Loading: " + (int) (progress * 100) + "%", Gdx.graphics.getWidth() / 2 - 60, barY + BAR_HEIGHT -5);
        game.batch.end();

        // Switch to the main screen after loading is complete
        if (progress >= 1f) {
            game.setScreen(new MainMenuScreen(game));
        }
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        font.dispose();
    }
}

