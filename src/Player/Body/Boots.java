package com.rgs.siegeworld.Player.Body;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.rgs.siegeworld.Game;

import java.util.ArrayList;

import static com.rgs.siegeworld.Player.Player.playerPos;

public class Boots {

    public static ArrayList<Texture> bootsSpriteSheets = new ArrayList<Texture>();
    public static ArrayList<Image[][]> bootsImages = new ArrayList<Image[][]>();
    public static Image bootsImage;
    private int col = 3;                //Number of columns in the bootsSprites
    private int row = 4;                //Number of rows in the bootsSprites
    private int spriteWidth;
    private  int spriteHeight;

    public void load() {
        bootsSpriteSheets.add(Game.assets.manager.get("Player/Boots/starter_boots.png", Texture.class));
        for(Texture e : bootsSpriteSheets) {
            e.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
        spriteWidth = bootsSpriteSheets.get(0).getWidth()/col;
        spriteHeight = bootsSpriteSheets.get(0).getHeight()/row;
        for(int i = 0; i < bootsSpriteSheets.size(); i++) {
            bootsImages.add(i, new Image[col][row]);
            for(int c = 0; c < col; c++){
                for(int r = 0; r < row; r++){
                    bootsImages.get(i)[c][r] = new Image(new TextureRegion(bootsSpriteSheets.get(i), c * spriteWidth, r * spriteHeight, spriteWidth, spriteHeight));
                }
            }
        }
        bootsImage = bootsImages.get(0)[0][0];
    }

    //----------------------------------------Create---------------------------------------------------//
    public void create() {

    }//-------------------------------------------------------------------------------------------------------//

    public void show() {
        bootsImage = new Image(bootsImages.get(0)[0][0].getDrawable());
        Game.stage.addActor(bootsImage);
    }

    public void render() {
        bootsImage.setDrawable(bootsImages.get(0)[Body.frameX][Body.frameY].getDrawable());
        bootsImage.setPosition(playerPos.x - spriteWidth /2, playerPos.y);
    }
}
