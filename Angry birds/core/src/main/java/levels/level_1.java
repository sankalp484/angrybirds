package levels;

import Sprites.birds.Bird;
import Sprites.birds.RedBird;
import Sprites.birds.YellowBird;
import Sprites.birds.BlueBird;
import Sprites.blocks.Glass;
import Sprites.catapult;
import Sprites.pigs.SmallPig;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import rio.com.Main;

public class level_1 extends MainLevel implements Screen {
    public Texture background;
    public RedBird rb;
    public YellowBird yb;
    public BlueBird bb;
    private Array<Glass> glassBlocks; // Array for all glass blocks
    private Array<SmallPig> pigs;    // Array for all pigs
    private Array<Bird> birds;       // Array for all birds
    private catapult catp;

    public level_1(Main game) {
        super(game);

        // Load background and birds
        background = new Texture("level 1 bg.png");
        rb = new RedBird(this, 450, 500);
        yb = new YellowBird(this, 375, 500);
        bb = new BlueBird(this, 300, 500);

        // Initialize arrays
        glassBlocks = new Array<>();
        pigs = new Array<>();
        birds = new Array<>();
        birds.add(rb);
        birds.add(yb);
        birds.add(bb);
        float groundY = 300; // Adjust this based on your ground level
        createGlassSetup(900, 300); // First setup// Second setup
        createGlassSetup2(900 +188 +188 + 12 + 20 , 300);
        // Initialize catapult
        catp = new catapult(game, camera);

        // Input handling for dragging and releasing birds
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                Vector3 worldCoordinates = camera.unproject(new Vector3(screenX, screenY, 0));
                Vector2 touchPos = new Vector2(worldCoordinates.x, worldCoordinates.y);

                for (Bird bird : birds) {
                    bird.startDrag(touchPos);
                }
                return true;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                Vector3 worldCoordinates = camera.unproject(new Vector3(screenX, screenY, 0));
                Vector2 touchPos = new Vector2(worldCoordinates.x, worldCoordinates.y);

                for (Bird bird : birds) {
                    bird.drag(touchPos);
                }
                return true;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                for (Bird bird : birds) {
                    bird.release();
                }
                return true;
            }
        });
    }

    // Helper method to create a 2x2 glass cube and place a pig inside
    private void createGlassSetup(float startX, float groundY) {
        // Add first pair of vertical glass blocks
        Glass glass1 = new Glass(this, startX, groundY, 90); // First vertical block
        glassBlocks.add(glass1);

        Glass glass2 = new Glass(this, startX + 200, groundY, 90); // Second vertical block
        glassBlocks.add(glass2);

        // Place the first SmallPig between the first two glass blocks
        SmallPig pig1 = new SmallPig(this, startX + 102, groundY + 20); // Centered between blocks 1 and 2
        pigs.add(pig1);



        // Add a horizontal glass block on top of the structure, resting on the vertical blocks
        float horizontalY = groundY + 204;  // Set height of the horizontal glass block
        Glass horizontalGlass = new Glass(this, startX , horizontalY, 0); // Horizontal block (positioned above the first two vertical blocks)
        glassBlocks.add(horizontalGlass);


        Glass horizontalGlass2 = new Glass(this, startX+200 , horizontalY, 0); // Horizontal block (positioned above the first two vertical blocks)
        glassBlocks.add(horizontalGlass2);


    }
    private void createGlassSetup2(float startX, float groundY) {
        // Add first pair of vertical glass blocks
        Glass glass1 = new Glass(this, startX, groundY, 90); // First vertical block
        glassBlocks.add(glass1);

        Glass glass2 = new Glass(this, startX + 200, groundY, 90); // Second vertical block
        glassBlocks.add(glass2);

        // Place the first SmallPig between the first two glass blocks
        SmallPig pig1 = new SmallPig(this, startX - 104 , groundY + 20); // Centered between blocks 1 and 2
        pigs.add(pig1);
        SmallPig pig2 = new SmallPig(this, startX + 102 , groundY + 20); // Centered between blocks 1 and 2
        pigs.add(pig2);



        // Add a horizontal glass block on top of the structure, resting on the vertical blocks
        float horizontalY = groundY + 204;  // Set height of the horizontal glass block
        Glass horizontalGlass = new Glass(this, startX , horizontalY, 0); // Horizontal block (positioned above the first two vertical blocks)
        glassBlocks.add(horizontalGlass);


        Glass horizontalGlass2 = new Glass(this, startX+200 , horizontalY, 0); // Horizontal block (positioned above the first two vertical blocks)
        glassBlocks.add(horizontalGlass2);


    }





    @Override
    public void render(float delta) {
        // Render background
        super.game.batch.begin();
        super.game.batch.draw(background, 0, 205);
        super.game.batch.end();

        // Render birds, catapult, pigs, and glass blocks
        super.render(delta);

        Bird draggingBird = null;
        for (Bird bird : birds) {
            if (bird.isDragging) {
                draggingBird = bird;
                break;
            }
        }
        catp.render(delta, draggingBird);

        // Render each bird
        for (Bird bird : birds) {
            bird.render(delta);
        }

        // Render each glass block
        for (Glass glass : glassBlocks) {
            glass.render(delta);
        }

        // Render each pig
        for (SmallPig pig : pigs) {
            pig.render(delta);
        }
    }
}
