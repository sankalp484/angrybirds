package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import levels.level_1;
import levels.level_2;
import levels.level_3;
import rio.com.Main;

public class WinScreen implements Screen {
    private Texture bg;
    private Main game;
    private Texture replay_inac;
    private Texture replay_ac;
    private Texture home_inac;
    private Texture home_ac;
    private Texture win;
    private Texture next_ac;
    private Texture next_inac;

    public WinScreen(Main game) {
        this.game = game;
        bg = new Texture("win bg.png");
        replay_inac= new Texture("Replay Inactive.png");
        replay_ac = new Texture("Replay active.png");
        home_inac = new Texture("Home inactive.png");
        home_ac = new Texture("Home active.png");
        win = new Texture("win.png");
        next_ac = new Texture("next ac.png");
        next_inac = new Texture("next inac.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();

        game.batch.draw(bg,0,0);

        int x2 = (Main.WIDTH/2)-(win.getWidth()/2) + 200;
        if (Gdx.input.getX()> x2 && Gdx.input.getX()<replay_inac.getWidth()+ x2 && game.HEIGHT - Gdx.input.getY()>Main.HEIGHT/2-replay_inac.getHeight()&& game.HEIGHT - Gdx.input.getY()<Main.HEIGHT/2){
            game.batch.draw(replay_ac, x2 -9,Main.HEIGHT/2-replay_inac.getHeight()-9,230,230);
            if (Gdx.input.isTouched()){
                this.dispose();
                switch (game.checkLevel) {
                    case 1:
                        game.setScreen(new level_1(game));
                        break;

                    case 2:
                        game.setScreen(new level_2(game));
                        break;

                    case 3:
                        game.setScreen(new level_3(game));
                        break;
                }
            }
        }else{
            game.batch.draw(replay_inac, x2,Main.HEIGHT/2-replay_inac.getHeight());
        }
        int x3 = (Main.WIDTH/2)-(win.getWidth()/2) + 600;
        if (Gdx.input.getX()> x3 && Gdx.input.getX()<replay_inac.getWidth()+ x3 && game.HEIGHT - Gdx.input.getY()>Main.HEIGHT/2-replay_inac.getHeight() && game.HEIGHT - Gdx.input.getY()<Main.HEIGHT/2){
            game.batch.draw(home_ac, x3 -9,Main.HEIGHT/2-replay_inac.getHeight()-9,230,230);
            if (Gdx.input.isTouched()){
                this.dispose();
                game.setScreen(new MainMenuScreen(game));
            }
        }else{
            game.batch.draw(home_inac, x3,Main.HEIGHT/2-replay_inac.getHeight());
        }

        if (Gdx.input.getX()> (game.WIDTH/2 - next_ac.getWidth()/2) && Gdx.input.getX()<(game.WIDTH/2 + next_ac.getWidth()/2) && game.HEIGHT - Gdx.input.getY()>(game.HEIGHT/4 - next_ac.getHeight()) && game.HEIGHT - Gdx.input.getY()<(game.HEIGHT/4)){
            game.batch.draw(next_ac, (game.WIDTH/2 - next_ac.getWidth()/2),(game.HEIGHT/4 - next_ac.getHeight()));
            if (Gdx.input.isTouched()){
                this.dispose();
                switch (game.checkLevel) {
                    case 1:
                        game.setScreen(new level_2(game));
                        game.checkLevel = 2;
                        break;

                    case 2:
                        game.setScreen(new level_3(game));
                        game.checkLevel = 3;
                        break;

                    case 3:
                        game.setScreen(new level_1(game));
                        game.checkLevel = 1;
                        break;
                }
            }
        }else{
            game.batch.draw(next_inac, (game.WIDTH/2 - next_ac.getWidth()/2),(game.HEIGHT/4 - next_ac.getHeight()));
        }

        game.batch.draw(win,(Main.WIDTH/2)-(win.getWidth()/2),(Main.HEIGHT/2));

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
