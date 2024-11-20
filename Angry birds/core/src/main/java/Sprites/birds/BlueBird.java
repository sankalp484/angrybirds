package Sprites.birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import levels.MainLevel;

public class BlueBird extends Bird {
    private boolean hasSplit = false;
    public BlueBird(MainLevel level, float xpos, float ypos) {
        super(level, xpos, ypos);
    }

    @Override
    protected void initializeTexture() {
        texture = new Texture("blue bird.png");
        textureWidth = 33;
        textureHeight = 32;
        rad = 13;
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        // Check for user input to activate the splitting power
        if (!hasSplit && hasLaunched && Gdx.input.justTouched()) {
            splitIntoThree();
            hasSplit = true; // Prevent multiple activations
        }
    }

    private void splitIntoThree() {
        Vector2 velocity = body.getLinearVelocity();
        Vector2 position = body.getPosition();

        // Create two additional birds
        BlueBird bird1 = createSplitBird(position.x, position.y - 0.8f, velocity);
        BlueBird bird2 = createSplitBird(position.x, position.y + 0.8f, velocity);

        // Safely schedule adding new birds to the game
        level.scheduleTask(() -> {
            level.birds.add(bird1);
            level.birds.add(bird2);
        });
    }

    private BlueBird createSplitBird(float x, float y, Vector2 velocity) {
        BlueBird bird = new BlueBird(level, x * MainLevel.ppm, y * MainLevel.ppm);
        bird.body.setType(BodyDef.BodyType.DynamicBody);
        bird.body.setTransform(x, y, 0);
        bird.body.setLinearVelocity(velocity);
        bird.hasLaunched = true; // Mark the bird as launched
        return bird;
    }
}
