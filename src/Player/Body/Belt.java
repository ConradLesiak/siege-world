package com.rgs.siegeworld.Player.Body;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.rgs.siegeworld.Game;

import java.util.ArrayList;

import static com.rgs.siegeworld.Player.Player.playerPos;

public class Belt {

    public static ArrayList<Texture> beltSpriteSheets = new ArrayList<Texture>();
    public static ArrayList<Image[][]> beltImages = new ArrayList<Image[][]>();
    public static Image beltImage;
    private int col = 3;                //Number of columns in the beltSprites
    private int row = 4;                //Number of rows in the beltSprites
    private int spriteWidth;
    private  int spriteHeight;

    public void load() {
        beltSpriteSheets.add(Game.assets.manager.get("Player/Belt/starter_belt.png", Texture.class));
        for(Texture e : beltSpriteSheets) {
            e.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
        spriteWidth = beltSpriteSheets.get(0).getWidth()/col;
        spriteHeight = beltSpriteSheets.get(0).getHeight()/row;
        for(int i = 0; i < beltSpriteSheets.size(); i++) {
            beltImages.add(i, new Image[col][row]);
            for(int c = 0; c < col; c++){
                for(int r = 0; r < row; r++){
                    beltImages.get(i)[c][r] = new Image(new TextureRegion(beltSpriteSheets.get(i), c * spriteWidth, r * spriteHeight, spriteWidth, spriteHeight));
                }
            }
        }
        beltImage = beltImages.get(0)[0][0];
    }

    //----------------------------------------Create---------------------------------------------------//
    public void create() {

    }//-------------------------------------------------------------------------------------------------------//

    public void show() {
        beltImage = new Image(beltImages.get(0)[0][0].getDrawable());
        Game.stage.addActor(beltImage);
    }

    public void render() {
        beltImage.setDrawable(beltImages.get(0)[Body.frameX][Body.frameY].getDrawable());
        beltImage.setPosition(playerPos.x - spriteWidth /2, playerPos.y);
    }
}