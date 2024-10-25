package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import levels.MainLevel;
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
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();

        game.batch.draw(bg,0,0,game.WIDTH, game.HEIGHT);
        game.batch.draw(des,0,0,game.WIDTH,469);

        int x1 = (Main.WIDTH/2 - resume_ac.getWidth()/2) - 500;
        if (Gdx.input.getX()> x1 && Gdx.input.getX()<resume_inac.getWidth()+ x1 && game.HEIGHT - Gdx.input.getY()>Main.HEIGHT/2-resume_inac.getHeight()/2 && game.HEIGHT - Gdx.input.getY()<Main.HEIGHT/2+resume_inac.getHeight()/2){
            game.batch.draw(resume_ac, x1 -9,Main.HEIGHT/2-resume_inac.getHeight()/2-9,230,230);
            if (Gdx.input.isTouched()){
                this.dispose();
                game.setScreen(game.currentLevel);
            }
        }else{
            game.batch.draw(resume_inac, x1,Main.HEIGHT/2-resume_inac.getHeight()/2);
        }

        int x2 = (Main.WIDTH/2 - resume_ac.getWidth()/2);
        if (Gdx.input.getX()> x2 && Gdx.input.getX()<resume_inac.getWidth()+ x2 && game.HEIGHT - Gdx.input.getY()>Main.HEIGHT/2-resume_inac.getHeight()/2 && game.HEIGHT - Gdx.input.getY()<Main.HEIGHT/2+resume_inac.getHeight()/2){
            game.batch.draw(replay_ac, x2 -9,Main.HEIGHT/2-resume_inac.getHeight()/2-9,230,230);
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
            game.batch.draw(replay_inac, x2,Main.HEIGHT/2-resume_inac.getHeight()/2);
        }

        int x3 = (Main.WIDTH/2 - resume_ac.getWidth()/2) + 500;
        if (Gdx.input.getX()> x3 && Gdx.input.getX()<resume_inac.getWidth()+ x3 && game.HEIGHT - Gdx.input.getY()>Main.HEIGHT/2-resume_inac.getHeight()/2 && game.HEIGHT - Gdx.input.getY()<Main.HEIGHT/2+resume_inac.getHeight()/2){
            game.batch.draw(home_ac, x3 -9,Main.HEIGHT/2-resume_inac.getHeight()/2-9,230,230);
            if (Gdx.input.isTouched()){
                this.dispose();
                game.setScreen(new MainMenuScreen(game));
            }
        }else{
            game.batch.draw(home_inac, x3,Main.HEIGHT/2-resume_inac.getHeight()/2);
        }

        game.batch.draw(paused,Main.WIDTH/2-paused.getWidth()/4, Main.HEIGHT/2 + 200,512,236);

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

}
