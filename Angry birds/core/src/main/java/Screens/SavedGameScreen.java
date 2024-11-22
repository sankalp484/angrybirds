package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import rio.com.Main;

public class SavedGameScreen implements Screen {
    private final Main game;
    private final Texture background;
    private final BitmapFont font;
    private final Texture backButton;
    private final Texture Button;

    public SavedGameScreen(Main game) {
        this.game = game;
        background = new Texture("menu_bg.png"); // Using the same background
        backButton = new Texture("back.png"); // Assuming a "back" button texture exists
        Button = new Texture("Play Inactive.png");
        font = new BitmapFont(); // Default font, you can load a custom one if needed
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        game.batch.begin();

        // Draw the background
        game.batch.draw(background, 0, 0, Main.WIDTH, Main.HEIGHT);

        // Draw the "Saved Games" title
        font.getData().setScale(3f); // Adjust font size
        font.draw(game.batch, "Saved Games", Main.WIDTH / 2f - 100, Main.HEIGHT - 100);

        // Saved games entries and buttons
        int startY = Main.HEIGHT - 200; // Starting Y position for the first saved game entry
        int entrySpacing = 300;         // Increased spacing between each entry
        int buttonWidth = Button.getWidth();
        int buttonHeight = Button.getHeight();

        for (int i = 1; i <= 3; i++) {
            // Calculate positions dynamically
            float entryX = Main.WIDTH / 2f - 100; // X position for the saved game text
            float buttonX = entryX + 250;         // X position for the Play button
            float entryY = startY - (i - 1) * entrySpacing; // Y position for this saved game entry

            // Draw saved game text
            font.getData().setScale(2f); // Adjust font size
            font.draw(game.batch, i + ". Save Game " + i, entryX, entryY);

            // Draw the Play button beside the saved game text
            game.batch.draw(Button, buttonX, entryY - buttonHeight / 2, buttonWidth, buttonHeight);
        }

        // Draw the back button
        int backBtnX = 10; // Position near the top-left
        int backBtnY = Main.HEIGHT - backButton.getHeight() - 10;
        game.batch.draw(backButton, backBtnX, backBtnY); // Regular button

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
        font.dispose();
    }
}
