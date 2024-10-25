package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import levels.MainLevel;
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
    public Texture resume_inac;

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
        resume_inac = new Texture("Play Inactive.png");
    }
    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        game.batch.begin();
        game.batch.draw(bg,0,0,game.WIDTH, game.HEIGHT);
        game.batch.draw(des,0,0,game.WIDTH,469);
        game.batch.draw(sel,(Main.WIDTH/2)-(sel.getWidth()/2),(3*Main.HEIGHT/4)+100);

        int x1 = (Main.WIDTH/2)-(two_inac.getWidth()/2);
        if (Gdx.input.getX()> x1 && Gdx.input.getX()< x1 +two_inac.getWidth() && game.HEIGHT - Gdx.input.getY()>Main.HEIGHT/2 && game.HEIGHT - Gdx.input.getY()<(Main.HEIGHT/2)+two_inac.getHeight()){
            game.batch.draw(two_ac,(Main.WIDTH/2)-(one_inac.getWidth()/2),(Main.HEIGHT/2));

            if (Gdx.input.isTouched()){
                this.dispose();
                game.setScreen(new MainLevel(game));
            }
        }else {
            game.batch.draw(two_inac, (Main.WIDTH / 2) - (one_inac.getWidth() / 2), (Main.HEIGHT / 2));
        }

        int x2 = (Main.WIDTH/2)-(two_inac.getWidth()/2)-500;
        if (Gdx.input.getX()> x2 && Gdx.input.getX()< x2 +two_inac.getWidth() && game.HEIGHT - Gdx.input.getY()>Main.HEIGHT/2 && game.HEIGHT - Gdx.input.getY()<(Main.HEIGHT/2)+two_inac.getHeight()){
            game.batch.draw(one_ac,x2,(Main.HEIGHT/2));
        }else {
            game.batch.draw(one_inac, x2, (Main.HEIGHT / 2));
        }

        int x3 = (Main.WIDTH/2)-(two_inac.getWidth()/2)+500;
        if (Gdx.input.getX()> x3 && Gdx.input.getX()< x3 +two_inac.getWidth() && game.HEIGHT - Gdx.input.getY()>Main.HEIGHT/2 && game.HEIGHT - Gdx.input.getY()<(Main.HEIGHT/2)+two_inac.getHeight()){
            game.batch.draw(three_ac, x3,(Main.HEIGHT/2));
        }else {
            game.batch.draw(three_inac, x3, (Main.HEIGHT / 2));
        }

        if (Gdx.input.getX()>30 && Gdx.input.getX()<resume_inac.getWidth()+30 && game.HEIGHT - Gdx.input.getY()>Main.HEIGHT-resume_inac.getHeight()-30 && game.HEIGHT - Gdx.input.getY()<Main.HEIGHT){
            game.batch.draw(resume_inac,30-12,Main.HEIGHT-resume_inac.getHeight()-30-12);
            if (Gdx.input.isTouched()){
                this.dispose();
                game.setScreen(new MainMenuScreen(game));
            }
        }else{
            game.batch.draw(resume_inac,30,Main.HEIGHT-resume_inac.getHeight()-30);
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
