package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import rio.com.Main;

public class SavedGameScreen implements Screen {
    private final Main game;
    private final transient Texture background;
    private final transient Texture backButton;
    private final transient Texture savedGames;
    private final transient Texture saveGame1;
    private final transient Texture saveGame2;
    private final transient Texture saveGame3;

    public SavedGameScreen(Main game) {
        this.game = game;
        background = new Texture("menu_bg.png"); // Using the same background
        backButton = new Texture("back.png");
        savedGames = new Texture("Saved Games.png");
        saveGame1 = new Texture("Save game 1.png");
        saveGame2 = new Texture("Save game 2.png");
        saveGame3 = new Texture("Save game 3.png");

    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        game.batch.begin();

        // Draw the background
        game.batch.draw(background, 0, 0, Main.WIDTH, Main.HEIGHT);

        game.batch.draw(savedGames, Main.WIDTH / 2f - 3*savedGames.getWidth()/8f, Main.HEIGHT - 500,3*savedGames.getWidth()/4f,3*savedGames.getHeight()/4f);

        // Saved games entries and buttons
        int startY = Main.HEIGHT - 500; // Starting Y position for the first saved game entry
        int entrySpacing = 100;         // Increased spacing between each entry

        for (int i = 1; i <= 3; i++) {
            Texture saveGameTexture = null;

            // Select the appropriate texture
            if (i == 1) {
                saveGameTexture = saveGame1;
            } else if (i == 2) {
                saveGameTexture = saveGame2;
            } else if (i == 3) {
                saveGameTexture = saveGame3;
            }

            if (saveGameTexture != null) {
                // Calculate position dynamically
                float textureX = Main.WIDTH / 2f - saveGameTexture.getWidth() / 2f;
                float textureY = startY - (i - 1) * entrySpacing - saveGameTexture.getHeight();

                // Check if the mouse is hovering over the texture
                boolean isHovering = Gdx.input.getX() > textureX && Gdx.input.getX() < textureX + saveGameTexture.getWidth() &&
                    Main.HEIGHT - Gdx.input.getY() > textureY &&
                    Main.HEIGHT - Gdx.input.getY() < textureY + saveGameTexture.getHeight();

                if (isHovering) {
                    // Highlight the button by slightly enlarging it
                    game.batch.draw(saveGameTexture, textureX - 5, textureY - 5,
                        saveGameTexture.getWidth() + 10, saveGameTexture.getHeight() + 10);

                    // Handle click
                    if (Gdx.input.isTouched()) {
                        System.out.println("Save Game " + i + " clicked!"); // Replace with your desired action
                        loadGame(i);
                    }
                } else {
                    // Regular button
                    game.batch.draw(saveGameTexture, textureX, textureY);
                }
            }
        }

        // Draw the back button
        int backBtnX = 10; // Position near the top-left
        int backBtnY = Main.HEIGHT - backButton.getHeight() - 10;

        boolean isHoveringBackButton = Gdx.input.getX() > backBtnX && Gdx.input.getX() < backBtnX + backButton.getWidth() &&
            Main.HEIGHT - Gdx.input.getY() > backBtnY &&
            Main.HEIGHT - Gdx.input.getY() < backBtnY + backButton.getHeight();

        if (isHoveringBackButton) {
            // Highlighted back button when hovered
            game.batch.draw(backButton, backBtnX - 5, backBtnY - 5, backButton.getWidth() + 10, backButton.getHeight() + 10);

            // Check for a click
            if (Gdx.input.isTouched()) {
                this.dispose();
                game.setScreen(new MainMenuScreen(game)); // Navigate to Main Menu
            }
        } else {
            // Regular back button
            game.batch.draw(backButton, backBtnX, backBtnY);
        }

        game.batch.end();
    }

    private void loadGame(int i) {
        game.setScreen(game.currentLevel);
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
    public void dispose() {
        background.dispose();
        backButton.dispose();
    }
}
