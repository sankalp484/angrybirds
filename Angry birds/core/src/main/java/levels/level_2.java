package levels;

import Sprites.blocks.Wood;  // Corrected to use Wood instead of Wooden
import Sprites.pigs.SmallPig;
import com.badlogic.gdx.graphics.Texture;
import rio.com.Main;
import com.badlogic.gdx.utils.Array;

public class level_2 extends MainLevel {
    public Texture background;
    private Array<Wood> woodBlocks;  // Corrected to use Wood instead of Wooden
    private Array<SmallPig> pigs;

    public level_2(Main game) {
        super(game);

        // Load background
        background = new Texture("level 2 bg.jpg");

        // Initialize the arrays for wood blocks and pigs
        woodBlocks = new Array<>();  // Corrected to use Wood instead of Wooden
        pigs = new Array<>();

        // Create setups (modify these methods to use Wood blocks)
        createWoodSetup(900, 300); // Adjust this based on your layout
        createWoodSetup2(900 + 188 + 188 + 12 + 20, 300); // Another setup with Wood blocks
    }

    // Helper method to create wood blocks and place pigs
    private void createWoodSetup(float startX, float groundY) {
        // Add first pair of vertical wood blocks
        Wood wood1 = new Wood(this, startX, groundY, 90);  // First vertical block
        woodBlocks.add(wood1);

        Wood wood2 = new Wood(this, startX + 200, groundY, 90);  // Second vertical block
        woodBlocks.add(wood2);
        Wood wood3 = new Wood(this, startX - 102 , groundY, 90);  // Second vertical block
        woodBlocks.add(wood3);

        Wood wood4 = new Wood(this, startX - 102 , groundY +206, 90);  // Second vertical block
        woodBlocks.add(wood4);
        

        // Place the first SmallPig between the first two wood blocks
        SmallPig pig1 = new SmallPig(this, startX + 102, groundY + 20);  // Centered between blocks 1 and 2
        pigs.add(pig1);

        // Add a horizontal wood block on top of the structure, resting on the vertical blocks
        float horizontalY = groundY + 204;  // Set height of the horizontal wood block
        Wood horizontalWood = new Wood(this, startX, horizontalY, 0);  // Horizontal block
        woodBlocks.add(horizontalWood);

        Wood horizontalWood2 = new Wood(this, startX + 200, horizontalY, 0);  // Another horizontal block
        woodBlocks.add(horizontalWood2);
    }

    private void createWoodSetup2(float startX, float groundY) {
        // Add first pair of vertical wood blocks
        Wood wood1 = new Wood(this, startX, groundY, 90);  // First vertical block
        woodBlocks.add(wood1);

        Wood wood2 = new Wood(this, startX + 200, groundY, 90);  // Second vertical block
        woodBlocks.add(wood2);

        // Place two SmallPigs between the first two wood blocks
        SmallPig pig1 = new SmallPig(this, startX - 104, groundY + 20);  // Centered between blocks 1 and 2
        pigs.add(pig1);

        SmallPig pig2 = new SmallPig(this, startX + 102, groundY + 20);  // Centered between blocks 1 and 2
        pigs.add(pig2);

        // Add a horizontal wood block on top of the structure, resting on the vertical blocks
        float horizontalY = groundY + 204;  // Set height of the horizontal wood block
        Wood horizontalWood = new Wood(this, startX, horizontalY, 0);  // Horizontal block
        woodBlocks.add(horizontalWood);

        Wood horizontalWood2 = new Wood(this, startX + 200, horizontalY, 0);  // Another horizontal block
        woodBlocks.add(horizontalWood2);
    }

    @Override
    public void render(float delta) {
        super.game.batch.begin();
        super.game.batch.draw(background, 0, 0);  // Draw level 2 background
        super.game.batch.end();

        // Render all objects
        super.render(delta);

        // Render each wood block
        for (Wood wood : woodBlocks) {
            wood.render(delta);
        }

        // Render each pig
        for (SmallPig pig : pigs) {
            pig.render(delta);
        }
    }
}
