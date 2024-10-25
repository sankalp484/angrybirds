package Sprites.birds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import levels.MainLevel;

public abstract class Bird {
    protected MainLevel level;
    protected Texture texture;
    protected Body body;
    protected float textureWidth;  // Default texture width
    protected float textureHeight; // Default texture height
    protected BodyDef cDef;
    protected float xpos;
    protected float ypos;
    protected float rad;

    public Bird(MainLevel level, float xpos, float ypos) {
        this.level = level;
        this.xpos = xpos;
        this.ypos = ypos;
        initializeTexture();
        createCircle();
    }

    // Abstract method for subclasses to specify their texture
    protected abstract void initializeTexture();

    // Create Box2D body with circle shape
    protected void createCircle() {
        cDef = new BodyDef();
        cDef.type = BodyDef.BodyType.DynamicBody;
        cDef.position.set(xpos / MainLevel.ppm, ypos / MainLevel.ppm);

        body = level.world.createBody(cDef);

        CircleShape cShape = new CircleShape();
        cShape.setRadius(rad / MainLevel.ppm);

        body.createFixture(cShape, 1.0f);
        cShape.dispose();
    }

    // Render the bird texture at the body's position and rotation
    public void render(float delta) {
        level.game.batch.begin();

        Vector2 position = body.getPosition();
        float angle = body.getAngle() * MathUtils.radiansToDegrees;

        level.game.batch.draw(
            texture,
            position.x * level.ppm - textureWidth / 2,
            position.y * level.ppm - textureHeight / 2,
            textureWidth / 2, textureHeight / 2,
            textureWidth, textureHeight,
            1, 1,
            angle,
            0, 0,
            texture.getWidth(), texture.getHeight(),
            false, false
        );

        level.game.batch.end();
    }
}

