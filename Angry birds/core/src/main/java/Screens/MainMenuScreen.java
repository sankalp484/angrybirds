package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import rio.com.Main;
public class MainMenuScreen implements Screen {
    public Texture background;
    public Texture logo;
    public Texture start_btn;
    public Texture exit_btn;
    public Main game;
    public Texture saved_btn;

    public MainMenuScreen(Main game) {
        this.game = game;
        background = new Texture("menu_bg.png");
        logo = new Texture("Logo.png");
        start_btn = new Texture("Start.png");
        exit_btn = new Texture("Exit.png");
        saved_btn = new Texture("savedgames.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.batch.begin();
        game.batch.draw(background, 0, 0, Main.WIDTH, Main.HEIGHT);
        game.batch.draw(logo, (Main.WIDTH / 2) - (logo.getWidth() / 2), (Main.HEIGHT / 2));

        // Play button
        int playButtonWidth = start_btn.getWidth();
        int playButtonHeight = start_btn.getHeight();
        int playX = (Main.WIDTH / 2) - (playButtonWidth / 2);

        if (Gdx.input.getX() > playX && Gdx.input.getX() < playX + playButtonWidth &&
            game.HEIGHT - Gdx.input.getY() > Main.HEIGHT / 4 - 75 &&
            game.HEIGHT - Gdx.input.getY() < (Main.HEIGHT / 4 - 75) + playButtonHeight) {
            game.batch.draw(start_btn, playX - 10, Main.HEIGHT / 4 - 85, playButtonWidth + 20, playButtonHeight + 20); // Hover effect
            if (Gdx.input.isTouched()) {
                this.dispose();
                game.currentLevel = new LevelScreen(game);
                game.setScreen(game.currentLevel);
            }
        } else {
            game.batch.draw(start_btn, playX, Main.HEIGHT / 4 - 75, playButtonWidth, playButtonHeight);
        }

        // Exit button
        int exitButtonWidth = 135;
        int exitButtonHeight = 135;

        if (Gdx.input.getX() < exitButtonWidth && game.HEIGHT - Gdx.input.getY() < exitButtonHeight) {
            game.batch.draw(exit_btn, 0, 0, exitButtonWidth, exitButtonHeight);
            if (Gdx.input.isTouched()) {
                Gdx.app.exit();
            }
        } else {
            game.batch.draw(exit_btn, 0, 0, exitButtonWidth, exitButtonHeight);
        }

        // Saved Games button
        int savedButtonX = Main.WIDTH - exitButtonWidth - 20; // Positioned on the top-right
        int savedButtonY = 20;

        if (Gdx.input.getX() > savedButtonX && Gdx.input.getX() < savedButtonX + exitButtonWidth &&
            game.HEIGHT - Gdx.input.getY() > savedButtonY &&
            game.HEIGHT - Gdx.input.getY() < savedButtonY + exitButtonHeight) {
            game.batch.draw(saved_btn, savedButtonX - 5, savedButtonY - 5, exitButtonWidth + 10, exitButtonHeight + 10); // Hover effect
            if (Gdx.input.isTouched()) {
                this.dispose();
                game.setScreen(new SavedGameScreen(game)); // Navigate to Saved Games Screen
            }
        } else {
            game.batch.draw(saved_btn, savedButtonX, savedButtonY, exitButtonWidth, exitButtonHeight);
        }

        game.batch.end();
    }


    @Override
    public void resize(int width, int height) {

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

    }
}
