package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import levels.level_1;
import levels.level_2;
import levels.level_3;
import rio.com.Main;

public class PauseMenu implements Screen {
    public Texture resume_inac;
    public Texture resume_ac;
    public Texture replay_inac;
    public Texture replay_ac;
    public Texture home_inac;
    public Texture home_ac;
    public Texture bg;
    public Texture des;
    public Texture paused;
    public Main game;
    public Texture save;

    public PauseMenu(Main game){
        this.game = game;
        initTextures();
    }

    private void initTextures() {
        resume_inac = new Texture("Play Inactive.png");
        resume_ac = new Texture("Play active.png");
        replay_inac= new Texture("Replay Inactive.png");
        replay_ac = new Texture("Replay active.png");
        home_inac = new Texture("Home inactive.png");
        home_ac = new Texture("Home active.png");
        bg = new Texture("Lvl_bg.png");
        des = new Texture("Lvl_des.png");
        paused = new Texture("paused.png");
        save = new Texture("savedgames.png");
    }

    @Override
    public void show() {

    }



    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();

        // Draw background and decorative elements
        game.batch.draw(bg, 0, 0, game.WIDTH, game.HEIGHT);
        game.batch.draw(des, 0, 0, game.WIDTH, 469);

        // Resume Button
        int x1 = (Main.WIDTH / 2 - resume_ac.getWidth() / 2) - 500;
        if (Gdx.input.getX() > x1 && Gdx.input.getX() < resume_inac.getWidth() + x1 &&
            game.HEIGHT - Gdx.input.getY() > Main.HEIGHT / 2 - resume_inac.getHeight() / 2 &&
            game.HEIGHT - Gdx.input.getY() < Main.HEIGHT / 2 + resume_inac.getHeight() / 2) {
            game.batch.draw(resume_ac, x1 - 9, Main.HEIGHT / 2 - resume_inac.getHeight() / 2 - 9, 230, 230);
            if (Gdx.input.isTouched()) {
                this.dispose();
                game.setScreen(game.currentLevel);
            }
        } else {
            game.batch.draw(resume_inac, x1, Main.HEIGHT / 2 - resume_inac.getHeight() / 2);
        }

        // Replay Button
        int x2 = (Main.WIDTH / 2 - resume_ac.getWidth() / 2);
        if (Gdx.input.getX() > x2 && Gdx.input.getX() < resume_inac.getWidth() + x2 &&
            game.HEIGHT - Gdx.input.getY() > Main.HEIGHT / 2 - resume_inac.getHeight() / 2 &&
            game.HEIGHT - Gdx.input.getY() < Main.HEIGHT / 2 + resume_inac.getHeight() / 2) {
            game.batch.draw(replay_ac, x2 - 9, Main.HEIGHT / 2 - resume_inac.getHeight() / 2 - 9, 230, 230);
            if (Gdx.input.isTouched()) {
                this.dispose();
                switch (game.checkLevel) {
                    case 1:
                        level_1 level1 = new level_1(game);
                        game.currentLevel = level1;
                        game.checkLevel = 1;
                        game.setScreen(level1);
                        break;
                    case 2:
                        level_2 level2 = new level_2(game);
                        game.currentLevel = level2;
                        game.checkLevel = 2;
                        game.setScreen(level2);
                        break;
                    case 3:
                        level_3 level3 = new level_3(game);
                        game.currentLevel = level3;
                        game.checkLevel = 3;
                        game.setScreen(level3);
                        break;
                }
            }
        } else {
            game.batch.draw(replay_inac, x2, Main.HEIGHT / 2 - resume_inac.getHeight() / 2);
        }

        // Home Button
        int x3 = (Main.WIDTH / 2 - resume_ac.getWidth() / 2) + 500;
        if (Gdx.input.getX() > x3 && Gdx.input.getX() < resume_inac.getWidth() + x3 &&
            game.HEIGHT - Gdx.input.getY() > Main.HEIGHT / 2 - resume_inac.getHeight() / 2 &&
            game.HEIGHT - Gdx.input.getY() < Main.HEIGHT / 2 + resume_inac.getHeight() / 2) {
            game.batch.draw(home_ac, x3 - 9, Main.HEIGHT / 2 - resume_inac.getHeight() / 2 - 9, 230, 230);
            if (Gdx.input.isTouched()) {
                this.dispose();
                game.setScreen(new MainMenuScreen(game));
            }
        } else {
            game.batch.draw(home_inac, x3, Main.HEIGHT / 2 - resume_inac.getHeight() / 2);
        }

        // Save Game Button - Smaller and placed in the bottom right corner
        int saveButtonWidth = 100;
        int saveButtonHeight = 100;
        int x4 = Main.WIDTH - saveButtonWidth - 20; // 20px margin from the right edge
        int y4 = 20; // 20px margin from the bottom edge
        if (Gdx.input.getX() > x4 && Gdx.input.getX() < x4 + saveButtonWidth &&
            game.HEIGHT - Gdx.input.getY() > y4 && game.HEIGHT - Gdx.input.getY() < y4 + saveButtonHeight) {
            game.batch.draw(save, x4 - 9, y4 - 9, saveButtonWidth + 18, saveButtonHeight + 18); // Highlight the button
            if (Gdx.input.isTouched()) {
                // Save the game state
                saveGame();
            }
        } else {
            game.batch.draw(save, x4, y4, saveButtonWidth, saveButtonHeight); // Normal button appearance
        }

        // Draw "Paused" Text
        game.batch.draw(paused, Main.WIDTH / 2 - paused.getWidth() / 4, Main.HEIGHT / 2 + 200, 512, 236);

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
        resume_inac.dispose();
        resume_ac.dispose();
        replay_inac.dispose();
        replay_ac.dispose();
        home_inac.dispose();
        home_ac.dispose();
        bg.dispose();
        des.dispose();
        paused.dispose();
    }
    private void saveGame() {
        // Logic to save the game state
        System.out.println("Game Saved!"); // Placeholder for saving logic
        // You can implement saving the game state to a file or in-memory object
    }

}
