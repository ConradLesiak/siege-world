package com.rgs.siegeworld.Player.Body;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.rgs.siegeworld.Game;

import java.util.ArrayList;

import static com.rgs.siegeworld.Player.Player.playerPos;

public class Legs {

    public static ArrayList<Texture> legsSpriteSheets = new ArrayList<Texture>();
    public static ArrayList<Image[][]> legsImages = new ArrayList<Image[][]>();
    public static Image legsImage;
    private int col = 3;                //Number of columns in the legSprites
    private int row = 4;                //Number of rows in the legSprites
    private int spriteWidth;
    private  int spriteHeight;

    public void load() {
        legsSpriteSheets.add(Game.assets.manager.get("Player/Legs/starter_legs.png", Texture.class));
        for(Texture e : legsSpriteSheets) {
            e.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
        spriteWidth = legsSpriteSheets.get(0).getWidth()/col;
        spriteHeight = legsSpriteSheets.get(0).getHeight()/row;
        for(int i = 0; i < legsSpriteSheets.size(); i++) {
            legsImages.add(i, new Image[col][row]);
            for(int c = 0; c < col; c++){
                for(int r = 0; r < row; r++){
                    legsImages.get(i)[c][r] = new Image(new TextureRegion(legsSpriteSheets.get(i), c * spriteWidth, r * spriteHeight, spriteWidth, spriteHeight));
                }
            }
        }
        legsImage = legsImages.get(0)[0][0];
    }

    //----------------------------------------Create---------------------------------------------------//
    public void create() {

    }//-------------------------------------------------------------------------------------------------------//

    public void show() {
        legsImage = new Image(legsImages.get(0)[0][0].getDrawable());
        Game.stage.addActor(legsImage);
    }

    public void render() {
        legsImage.setDrawable(legsImages.get(0)[Body.frameX][Body.frameY].getDrawable());
        legsImage.setPosition(playerPos.x - spriteWidth /2, playerPos.y);
    }
}
