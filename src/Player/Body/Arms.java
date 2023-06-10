package com.rgs.siegeworld.Player.Body;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.rgs.siegeworld.Game;
import com.rgs.siegeworld.Player.Skills.Attack;
import com.rgs.siegeworld.SaveData;

import java.util.ArrayList;

import static com.rgs.siegeworld.Player.Player.playerPos;

public class Arms {

    public static ArrayList<Texture> armsSpriteSheets = new ArrayList<Texture>();
    public static ArrayList<Image[][]> armsImages = new ArrayList<Image[][]>();
    public static Image armsImage;

    public static ArrayList<Texture> handsSpriteSheets = new ArrayList<Texture>();
    public static ArrayList<Image[][]> handsImages = new ArrayList<Image[][]>();
    public static Image handsImage;

    private int col = 3;                //Number of columns in the SpriteSprites.
    private int row = 4;                //Number of rows in the SpriteSprites.
    private int spriteWidth;
    private  int spriteHeight;

    //----------------------------------------Load---------------------------------------------------//
    public void load() {
        //Arms
        armsSpriteSheets.add(Game.assets.manager.get("Player/Arms/arms_white.png", Texture.class));
        armsSpriteSheets.add(Game.assets.manager.get("Player/Arms/arms_tan.png", Texture.class));
        for(Texture e : armsSpriteSheets) {
            e.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
        spriteWidth = armsSpriteSheets.get(0).getWidth()/col;
        spriteHeight = armsSpriteSheets.get(0).getHeight()/row;
        for(int i = 0; i < armsSpriteSheets.size(); i++) {
            armsImages.add(i, new Image[col][row]);
            for(int c = 0; c < col; c++){
                for(int r = 0; r < row; r++){
                    armsImages.get(i)[c][r] = new Image(new TextureRegion(armsSpriteSheets.get(i), c * spriteWidth, r * spriteHeight, spriteWidth, spriteHeight));
                }
            }
        }
        armsImage = armsImages.get(0)[0][0];

        //Hands
        handsSpriteSheets.add(Game.assets.manager.get("Player/Arms/hands_white.png", Texture.class));
        handsSpriteSheets.add(Game.assets.manager.get("Player/Arms/hands_tan.png", Texture.class));
        for(Texture e : handsSpriteSheets) {
            e.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
        for(int i = 0; i < handsSpriteSheets.size(); i++) {
            handsImages.add(i, new Image[col][row]);
            for(int c = 0; c < col; c++){
                for(int r = 0; r < row; r++){
                    handsImages.get(i)[c][r] = new Image(new TextureRegion(handsSpriteSheets.get(i), c * spriteWidth, r * spriteHeight, spriteWidth, spriteHeight));
                }
            }
        }
        handsImage = handsImages.get(0)[0][0];
    }//-----------------------------------------------------------------------------------------------//

    //----------------------------------------Create---------------------------------------------------//
    public void create() {

    }//-------------------------------------------------------------------------------------------------------//

    public void show() {
        armsImage = new Image(armsImages.get(0)[0][0].getDrawable());
        Game.stage.addActor(armsImage);
        handsImage = new Image(handsImages.get(0)[0][0].getDrawable());
        Game.stage.addActor(handsImage);
    }

    public void render() {
        armsImage.setDrawable(armsImages.get(SaveData.prefs.getInteger("faceIndex"))[Attack.frameX][Body.frameY].getDrawable());
        armsImage.setPosition(playerPos.x - spriteWidth /2, playerPos.y);
        handsImage.setDrawable(handsImages.get(SaveData.prefs.getInteger("faceIndex"))[Attack.frameX][Body.frameY].getDrawable());
        handsImage.setPosition(playerPos.x - spriteWidth /2, playerPos.y);
    }
}
