package Sprites;



import Sprites.birds.Bird;
import Sprites.catapult;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import levels.MainLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rio.com.Main;
import static org.junit.jupiter.api.Assertions.*;

public class CatapultTest {

    private catapult catapult;
    private Main game;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;
    private Bird bird; // Real Bird object

    @BeforeEach
    public void setup() {
        // Initialize real instances instead of mocks
        game = new Main();  // You might need to create a simple instance or mock minimal behavior here
        camera = new OrthographicCamera();
        shapeRenderer = new ShapeRenderer();

        // Initialize the catapult object
        catapult = new catapult(game, camera);
        MainLevel level = new MainLevel(game);
        // Initialize a Bird object
        Bird bird = new Bird(level, 3, 3) {
            @Override
            protected void initializeTexture() {

            }
        };  // Create a bird at position (3, 3)
        bird.body.setTransform(new Vector2(3, 3), 0);
        // Set position of the bird directly

    }

    @Test
    public void testCatapultInitialization() {
        // Check if the texture is initialized correctly
        assertNotNull(catapult, "Catapult should not be null");

        // Verify the initial start and end points of the catapult lines
        assertEquals(515 / 100f, catapult.startPoint1.x, "Start point 1 X coordinate should be correct");
        assertEquals(375 / 100f, catapult.startPoint1.y, "Start point 1 Y coordinate should be correct");

        assertEquals(555 / 100f, catapult.startPoint2.x, "Start point 2 X coordinate should be correct");
        assertEquals(375 / 100f, catapult.startPoint2.y, "Start point 2 Y coordinate should be correct");
    }

    @Test
    public void testRenderWithDraggingBird() {
        // Call the render method
        catapult.render(0.016f, bird); // Passing a delta time of 0.016 seconds (similar to frame time)

        // Verify the anchor points of the catapult when the bird is being dragged
        Vector2 birdPosition = bird.body.getPosition();
        assertEquals(3, birdPosition.x, "Bird's X position should be 3");
        assertEquals(3, birdPosition.y, "Bird's Y position should be 3");

        // Check if the shape renderer's line method is invoked (this is indirect, we can't easily test it directly without Mockito)
        // Here we simply check that the render method was called as expected, this could be extended for a more graphical test
    }

    @Test
    public void testAnchorPointsWhenBirdIsDragged() {

        // Initial positions of anchor points before dragging the bird
        Vector2 initialAnchor1 = catapult.startPoint1;
        Vector2 initialAnchor2 = catapult.startPoint2;

        // Simulate dragging the bird and updating its position
        catapult.render(0.016f, bird); // This would update the anchor points based on the bird's position

        // After dragging, anchor points should match the bird's position
        assertEquals(3, initialAnchor1.x, "Anchor 1 X should match the bird's X position");
        assertEquals(3, initialAnchor1.y, "Anchor 1 Y should match the bird's Y position");

        assertEquals(3, initialAnchor2.x, "Anchor 2 X should match the bird's X position");
        assertEquals(3, initialAnchor2.y, "Anchor 2 Y should match the bird's Y position");
    }
}
