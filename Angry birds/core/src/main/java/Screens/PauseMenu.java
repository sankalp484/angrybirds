package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import levels.MainLevel;
import rio.com.Main;

public class PauseMenu implements Screen {
    public Texture resume_inac;
    public Main game;
    public MainLevel level;

    public PauseMenu(Main game, MainLevel level){
        this.game = game;
        this.level = level;

        resume_inac = new Texture("Play Inactive.png");
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.batch.begin();
        if (Gdx.input.getX()>30 && Gdx.input.getX()<resume_inac.getWidth()+30 && game.HEIGHT - Gdx.input.getY()>Main.HEIGHT-resume_inac.getHeight()-30 && game.HEIGHT - Gdx.input.getY()<Main.HEIGHT){
            game.batch.draw(resume_inac,30-12,Main.HEIGHT-resume_inac.getHeight()-30-12);
            if (Gdx.input.isTouched()){
                this.dispose();
                game.setScreen(level);
            }
        }else{
            game.batch.draw(resume_inac,30,Main.HEIGHT-resume_inac.getHeight()-30);
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
