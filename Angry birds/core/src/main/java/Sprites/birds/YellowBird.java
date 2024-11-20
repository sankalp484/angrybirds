package Sprites.birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import levels.MainLevel;

// YellowBird subclass with equilateral triangle shape
public class YellowBird extends Bird {
    private boolean hasPowerActivated = false;

    public YellowBird(MainLevel level,float xpos,float ypos) {
        super(level, xpos, ypos);
    }

    @Override
    protected void initializeTexture() {
        texture = new Texture("yellow bird.png");
        textureHeight = 56;
        textureWidth = 60;
    }

    @Override
    protected void createCircle() {
        // Use a triangle shape instead
        createTriangle();
    }

    // Method to create an equilateral triangle-shaped body with 54-pixel side length
    private void createTriangle() {
        cDef = new BodyDef();
        cDef.type = BodyDef.BodyType.StaticBody;
        cDef.position.set(xpos / MainLevel.ppm, ypos / MainLevel.ppm);

        body = level.world.createBody(cDef);

        // Side length and height calculations in meters
        float sideLength = 54 / MainLevel.ppm;
        float height = (float) (Math.sqrt(3) / 2 * sideLength); // Height of equilateral triangle

        // Define vertices for the equilateral triangle
        PolygonShape triangleShape = new PolygonShape();
        float[] vertices = {
            0, height / 2,                  // Top vertex
            -sideLength / 2, -height / 2,   // Bottom left vertex
            sideLength / 2, -height / 2     // Bottom right vertex
        };
        triangleShape.set(vertices);

        body.createFixture(triangleShape, 1.0f);
        triangleShape.dispose();
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        // Check for user input to activate the power
        if (!hasPowerActivated && hasLaunched && Gdx.input.justTouched()) {
            body.setLinearVelocity(body.getLinearVelocity().scl(3)); // Double the velocity
            hasPowerActivated = true; // Prevent multiple activations
        }
    }
}
