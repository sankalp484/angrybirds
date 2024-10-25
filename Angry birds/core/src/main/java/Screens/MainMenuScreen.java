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

    public MainMenuScreen(Main game){
        this.game = game;
        background = new Texture("menu_bg.png");
        logo = new Texture("Logo.png");
        start_btn = new Texture("Start.png");
        exit_btn = new Texture("Exit.png");

    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.batch.begin();
        game.batch.draw(background,0,0,Main.WIDTH,Main.HEIGHT);
        game.batch.draw(logo,(Main.WIDTH/2)-(logo.getWidth()/2),(Main.HEIGHT/2));

        int x = (Main.WIDTH/2)-(start_btn.getWidth()/2);
        if (Gdx.input.getX()> x && Gdx.input.getX()< x +start_btn.getWidth() && game.HEIGHT - Gdx.input.getY()>Main.HEIGHT/4-75 && game.HEIGHT - Gdx.input.getY()<(Main.HEIGHT/4-75)+start_btn.getHeight()){
            game.batch.draw(start_btn, x -38,Main.HEIGHT/4-100,397,258);
            if (Gdx.input.isTouched()){
                this.dispose();
                game.currentLevel = new LevelScreen(game);
                game.setScreen(game.currentLevel);
            }
        }else{
            game.batch.draw(start_btn, x,Main.HEIGHT/4-75);
        }

        if (Gdx.input.getX()<exit_btn.getWidth() && game.HEIGHT - Gdx.input.getY()<exit_btn.getHeight()){
            game.batch.draw(exit_btn,0,0,135,135);
            if (Gdx.input.isTouched()){
                Gdx.app.exit();
            }
        }else {
            game.batch.draw(exit_btn,0,0);
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
