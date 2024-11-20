package levels;

import Sprites.birds.Bird;
import Sprites.birds.BlueBird;
import Sprites.birds.RedBird;
import Sprites.birds.YellowBird;
import Sprites.blocks.Glass;
import Sprites.blocks.Material;
import Sprites.blocks.Rock;
import Sprites.blocks.Wood;
import Sprites.pigs.BigPig;
import Sprites.pigs.MediumPig;
import Sprites.pigs.Pig;
import Sprites.pigs.SmallPig;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import rio.com.Main;

public class level_3 extends MainLevel {
    private Texture background;

    public level_3(Main game) {
        super(game);

        background = new Texture("level 3 bg.jpg");
        Array<Bird> birds = new Array<>();
        birds.add(new RedBird(this, 450, 225));
        birds.add(new YellowBird(this, 375, 230));
        birds.add(new BlueBird(this, 300, 220));

        Array<Wood> woodBlocks = new Array<>();
        Array<Rock> rockBlocks = new Array<>();
        Array<Pig> pigs = new Array<>();

        float groundY = 300;

        createStructure(woodBlocks, rockBlocks, pigs, 1000, groundY);

        setupLevel(birds, mergeMaterials(woodBlocks, rockBlocks), pigs);
    }

    private void createStructure(Array<Wood> woodBlocks, Array<Rock> rockBlocks, Array<Pig> pigs, float startX, float groundY) {
        // Base layer: Two vertical rock blocks
        rockBlocks.add(new Rock(this, startX, groundY, 90));
        rockBlocks.add(new Rock(this, startX + 200, groundY, 90));
        rockBlocks.add(new Rock(this, startX, groundY +205, 90));
        rockBlocks.add(new Rock(this, startX + 200, groundY + 205, 90));
        rockBlocks.add(new Rock(this, startX + 100, groundY + 204 +1 + 204, 0));
        // Add two pigs between the rock blocks
        pigs.add(new MediumPig(this, startX + 90, groundY ));
        pigs.add(new MediumPig(this, startX + 150, groundY ));


        // Middle layer: Horizontal wooden block
        rockBlocks.add(new Rock(this, startX , groundY + 204, 0));

        // Top layer: Rock block on top of the wooden block
        rockBlocks.add(new Rock(this, startX + 200, groundY + 204, 0));
        woodBlocks.add(new Wood(this, startX + 40, groundY + 100 + 204 + 1 + 204, 65));
        pigs.add(new BigPig(this, startX + 100 , groundY + 5 + 204 + 1 + 204));
        woodBlocks.add(new Wood(this, startX + 200 - 44 , groundY + 100 + 204 + 1 + 204, -65));
    }

    private Array<Material> mergeMaterials(Array<Wood> woodBlocks, Array<Rock> rockBlocks) {
        Array<Material> allBlocks = new Array<>();
        allBlocks.addAll(woodBlocks);
        allBlocks.addAll(rockBlocks);
        return allBlocks;
    }

    @Override
    public void render(float delta) {
        super.game.batch.begin();
        super.game.batch.draw(background, 0, 0, Main.WIDTH, Main.HEIGHT);
        super.game.batch.end();

        super.render(delta);
    }
}
