package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import levels.level_1;
import levels.level_2;
import levels.level_3;
import rio.com.Main;

public class LevelScreen implements Screen {
    public Texture bg;
    public Texture des;
    public Texture sel;
    public Texture one_inac;
    public Texture one_ac;
    public Texture two_inac;
    public Texture two_ac;
    public Texture three_inac;
    public Texture three_ac;
    public Texture back;

    public Main game;

    public LevelScreen(Main game){
        this.game = game;

        bg = new Texture("Lvl_bg.png");
        des = new Texture("Lvl_des.png");
        sel = new Texture("Lvl_select.png");
        one_inac = new Texture("One inactive.png");
        one_ac = new Texture("One active.png");
        two_ac = new Texture("Two active.png");
        two_inac = new Texture("Two inactive.png");
        three_ac = new Texture("Three active.png");
        three_inac = new Texture("Three inactive.png");
        back = new Texture("back.png");
    }

    private boolean isButtonPressed(int buttonX, int buttonY, int buttonWidth, int buttonHeight) {
        return Gdx.input.getX() > buttonX && Gdx.input.getX() < buttonX + buttonWidth &&
            game.HEIGHT - Gdx.input.getY() > buttonY && game.HEIGHT - Gdx.input.getY() < buttonY + buttonHeight;
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        game.batch.begin();
        game.batch.draw(bg, 0, 0, game.WIDTH, game.HEIGHT);
        game.batch.draw(des, 0, 0, game.WIDTH, 469);
        game.batch.draw(sel, (Main.WIDTH / 2) - (sel.getWidth() / 2), (3 * Main.HEIGHT / 4) + 100);

        // Dynamically calculate button positions based on screen width
        int buttonSpacing = 500;
        int buttonY = Main.HEIGHT / 2;
        int x1 = (Main.WIDTH / 2) - (one_ac.getWidth()/2) - buttonSpacing;
        int x2 = (Main.WIDTH / 2) - (one_ac.getWidth()/2);
        int x3 = (Main.WIDTH / 2) - (one_ac.getWidth()/2) + buttonSpacing;

        // Handle level 1 button
        if (isButtonPressed(x1, buttonY, one_inac.getWidth(), one_inac.getHeight())) {
            game.batch.draw(one_ac, x1, buttonY);
            if (Gdx.input.isTouched()) {
                this.dispose();
                game.currentLevel = new level_1(game);
                game.checkLevel = 1;
                game.setScreen(game.currentLevel);
            }
        } else {
            game.batch.draw(one_inac, x1, buttonY);
        }

        // Handle level 2 button
        if (isButtonPressed(x2, buttonY, two_inac.getWidth(), two_inac.getHeight())) {
            game.batch.draw(two_ac, x2, buttonY);
            if (Gdx.input.isTouched()) {
                this.dispose();
                game.currentLevel = new level_2(game);
                game.checkLevel = 2;
                game.setScreen(game.currentLevel);
            }
        } else {
            game.batch.draw(two_inac, x2, buttonY);
        }

        // Handle level 3 button
        if (isButtonPressed(x3, buttonY, three_inac.getWidth(), three_inac.getHeight())) {
            game.batch.draw(three_ac, x3, buttonY);
            if (Gdx.input.isTouched()) {
                this.dispose();
                game.currentLevel = new level_3(game);
                game.checkLevel = 3;
                game.setScreen(game.currentLevel);
            }
        } else {
            game.batch.draw(three_inac, x3, buttonY);
        }

        // Handle resume button (return to main menu)
        if (isButtonPressed(30, Main.HEIGHT - back.getHeight() - 30, back.getWidth(), back.getHeight())) {
            game.batch.draw(back, 30 - 12, Main.HEIGHT - back.getHeight() - 30 - 12, back.getWidth() + 24, back.getHeight()+24);
            if (Gdx.input.isTouched()) {
                this.dispose();
                game.currentLevel = new MainMenuScreen(game);
                game.setScreen(game.currentLevel);
            }
        } else {
            game.batch.draw(back, 30, Main.HEIGHT - back.getHeight() - 30);
        }

        game.batch.end();
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
    public void dispose() {}
}
