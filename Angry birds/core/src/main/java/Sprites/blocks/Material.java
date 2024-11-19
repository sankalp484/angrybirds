package Sprites.blocks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import levels.MainLevel;

public abstract class Material {
    protected MainLevel level;
    protected Texture texture;
    protected Body body;
    protected float width;    // Material width
    protected float height;   // Material height
    protected BodyDef mDef;

    public Material(MainLevel level, float posX, float posY, float rotation) {
        this.level = level;
        initializeTexture();
        createRectangle(posX, posY, rotation);
    }

    // Abstract method for subclasses to specify their texture and size
    protected abstract void initializeTexture();

    // Create Box2D body with rectangle shape, using position and rotation
    private void createRectangle(float posX, float posY, float rotation) {
        mDef = new BodyDef();
        mDef.type = BodyDef.BodyType.DynamicBody;  // Typically, materials are static
        mDef.position.set(posX / MainLevel.ppm, posY / MainLevel.ppm);  // Set the initial position
        mDef.angle = rotation * MathUtils.degreesToRadians; // Set rotation in radians

        body = level.world.createBody(mDef);

        PolygonShape rectangle = new PolygonShape();
        rectangle.setAsBox(width / 2 / MainLevel.ppm, height / 2 / MainLevel.ppm); // Set width and height in Box2D units

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = rectangle;
        fixtureDef.density = 1.0f;      // Standard density
        fixtureDef.friction = 1.0f;

        body.createFixture(fixtureDef);
        rectangle.dispose();
    }

    // Render the material texture at the body's position
    public void render(float delta) {
        level.game.batch.begin();

        Vector2 position = body.getPosition();
        float angle = body.getAngle() * MathUtils.radiansToDegrees;

        level.game.batch.draw(
            texture,
            position.x * level.ppm - width / 2,
            position.y * level.ppm - height / 2,
            width / 2, height / 2,
            width, height,
            1, 1,
            angle,
            0, 0,
            texture.getWidth(), texture.getHeight(),
            false, false
        );

        level.game.batch.end();
    }

    public void makeDynamic() {
        body.setType(BodyDef.BodyType.DynamicBody);
    }
}
