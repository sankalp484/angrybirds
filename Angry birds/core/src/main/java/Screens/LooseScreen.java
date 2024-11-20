package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import levels.level_1;
import levels.level_2;
import levels.level_3;
import rio.com.Main;

public class LooseScreen implements Screen {
    public Texture bg;
    public Main game;
    public Texture replay_inac;
    public Texture replay_ac;
    public Texture home_inac;
    public Texture home_ac;
    public Texture loose;

    public LooseScreen(Main game) {
        this.game = game;
        bg = new Texture("win bg.png");
        replay_inac= new Texture("Replay Inactive.png");
        replay_ac = new Texture("Replay active.png");
        home_inac = new Texture("Home inactive.png");
        home_ac = new Texture("Home active.png");
        loose = new Texture("loose.png");
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();

        game.batch.draw(bg,0,0);

        int x2 = (Main.WIDTH/2)-(loose.getWidth()/2) + 200;
        if (Gdx.input.getX()> x2 && Gdx.input.getX()<replay_inac.getWidth()+ x2 && game.HEIGHT - Gdx.input.getY()>Main.HEIGHT/2-replay_inac.getHeight()&& game.HEIGHT - Gdx.input.getY()<Main.HEIGHT/2){
            game.batch.draw(replay_ac, x2 -9,Main.HEIGHT/2-replay_inac.getHeight()-9,230,230);
            if (Gdx.input.isTouched()){
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
        }else{
            game.batch.draw(replay_inac, x2,Main.HEIGHT/2-replay_inac.getHeight());
        }
        int x3 = (Main.WIDTH/2)-(loose.getWidth()/2) + 600;
        if (Gdx.input.getX()> x3 && Gdx.input.getX()<replay_inac.getWidth()+ x3 && game.HEIGHT - Gdx.input.getY()>Main.HEIGHT/2-replay_inac.getHeight() && game.HEIGHT - Gdx.input.getY()<Main.HEIGHT/2){
            game.batch.draw(home_ac, x3 -9,Main.HEIGHT/2-replay_inac.getHeight()-9,230,230);
            if (Gdx.input.isTouched()){
                this.dispose();
                game.setScreen(new MainMenuScreen(game));
            }
        }else{
            game.batch.draw(home_inac, x3,Main.HEIGHT/2-replay_inac.getHeight());
        }

        game.batch.draw(loose,(Main.WIDTH/2)-(loose.getWidth()/2),(Main.HEIGHT/2));

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
