package rio.com;

import Screens.LoadingScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    public static final int WIDTH = 1920;
    public static final int HEIGHT = 1080;
    public SpriteBatch batch;
    public Screen currentLevel;
    public int checkLevel;

    @Override
    public void create() {
        batch = new SpriteBatch();
        currentLevel = new LoadingScreen(this);
        this.setScreen(currentLevel);
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
