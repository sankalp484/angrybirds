package Sprites.pigs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import levels.MainLevel;

public abstract class Pig {
    protected MainLevel level;
    protected Texture texture;
    protected Body body;
    protected float textureWidth;  // Pig texture width
    protected float textureHeight; // Pig texture height
    protected BodyDef pDef;
    protected float posx;
    protected float posy;
    protected float rad;

    public Pig(MainLevel level, float posx, float posy) {
        this.level = level;
        this.posx = posx;
        this.posy = posy;
        initializeTexture();
        createCircle();
    }

    // Abstract method for subclasses to specify their texture and size
    protected abstract void initializeTexture();

    // Create Box2D body with circle shape
    private void createCircle() {
        pDef = new BodyDef();
        pDef.type = BodyDef.BodyType.DynamicBody;
        pDef.position.set(posx / MainLevel.ppm, posy / MainLevel.ppm);  // Initial position

        body = level.world.createBody(pDef);

        CircleShape cShape = new CircleShape();
        cShape.setRadius(rad / MainLevel.ppm); // Set radius based on pig size

        body.createFixture(cShape, 1.0f);
        cShape.dispose();
    }

    // Render the pig texture at the body's position and rotation
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
