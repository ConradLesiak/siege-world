package com.rgs.siegeworld.Player.Body;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.rgs.siegeworld.Game;
import com.rgs.siegeworld.Player.Skills.Attack;

import java.util.ArrayList;

import static com.rgs.siegeworld.Player.Player.playerPos;

public class Chest {

    public static ArrayList<Texture> chestSpriteSheets = new ArrayList<Texture>();
    public static ArrayList<Image[][]> chestImages = new ArrayList<Image[][]>();
    public static Image chestImage;
    private int col = 3;                //Number of columns in the chestSprites
    private int row = 4;                //Number of rows in the chestSprites
    private int spriteWidth;
    private  int spriteHeight;

    public void load() {
        chestSpriteSheets.add(Game.assets.manager.get("Player/Chest/starter_chest.png", Texture.class));
        for(Texture e : chestSpriteSheets) {
            e.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
        spriteWidth = chestSpriteSheets.get(0).getWidth()/col;
        spriteHeight = chestSpriteSheets.get(0).getHeight()/row;
        for(int i = 0; i < chestSpriteSheets.size(); i++) {
            chestImages.add(i, new Image[col][row]);
            for(int c = 0; c < col; c++){
                for(int r = 0; r < row; r++){
                    chestImages.get(i)[c][r] = new Image(new TextureRegion(chestSpriteSheets.get(i), c * spriteWidth, r * spriteHeight, spriteWidth, spriteHeight));
                }
            }
        }
        chestImage = chestImages.get(0)[0][0];
    }

    //----------------------------------------Create---------------------------------------------------//
    public void create() {

    }//-------------------------------------------------------------------------------------------------------//

    public void show() {
        chestImage = new Image(chestImages.get(0)[0][0].getDrawable());
        Game.stage.addActor(chestImage);
    }

    public void render() {
        chestImage.setDrawable(chestImages.get(0)[Attack.frameX][Body.frameY].getDrawable());
        chestImage.setPosition(playerPos.x - spriteWidth /2, playerPos.y);
    }
}
