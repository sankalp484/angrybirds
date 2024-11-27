//package Sprites.birds;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import com.badlogic.gdx.math.Vector2;
//import com.badlogic.gdx.utils.Array;
//
//class TrajectoryTest {
//
//    private Bird bird;
//    private Array<Vector2> expectedTrajectoryPoints;
//
//    @BeforeEach
//    void setUp() {
//        // Initialize a concrete Bird subclass (replace `RedBird` with your actual subclass)
//        bird = new RedBird(null, 0, 10); // Replace `RedBird` with the appropriate subclass of Bird
//
//        // Manually set initial position and velocity
//        bird.setPosition(new Vector2(0, 10));
//        bird.setVelocity(new Vector2(5, 10));
//
//        // Initialize expected trajectory points
//        expectedTrajectoryPoints = new Array<>();
//    }
//
//    @Test
//    void testCalculateTrajectory() {
//        // Simulate drag release at a specific position
//        float endX = 5f;
//        float endY = 10f;
//        bird.calculateTrajectory(endX, endY);
//
//        // Calculate expected trajectory points
//        float timeStep = 0.1f;
//        Vector2 startPos = bird.getPosition();
//        Vector2 initialVelocity = bird.getVelocity();
//        float gravity = -9.8f; // Standard gravity
//
//        for (int i = 0; i < 30; i++) {
//            float time = i * timeStep;
//            Vector2 expectedPoint = new Vector2(
//                startPos.x + initialVelocity.x * time,
//                startPos.y + initialVelocity.y * time + 0.5f * gravity * time * time
//            );
//            expectedTrajectoryPoints.add(expectedPoint);
//        }
//
//        // Retrieve the actual trajectory points from the bird
//        Array<Vector2> actualTrajectoryPoints = bird.getTrajectoryPoints();
//        assertEquals(expectedTrajectoryPoints.size, actualTrajectoryPoints.size, "Number of trajectory points mismatch");
//
//        for (int i = 0; i < expectedTrajectoryPoints.size; i++) {
//            assertEquals(expectedTrajectoryPoints.get(i).x, actualTrajectoryPoints.get(i).x, 0.01f, "X-coordinate mismatch at index " + i);
//            assertEquals(expectedTrajectoryPoints.get(i).y, actualTrajectoryPoints.get(i).y, 0.01f, "Y-coordinate mismatch at index " + i);
//        }
//    }
//}
