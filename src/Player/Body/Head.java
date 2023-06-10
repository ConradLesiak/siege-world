package com.rgs.siegeworld.Player.Body;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.rgs.siegeworld.Game;
import com.rgs.siegeworld.Player.Player;
import com.rgs.siegeworld.SaveData;

import java.util.ArrayList;

import static com.rgs.siegeworld.Player.Player.playerPos;

public class Head {

    public static ArrayList<Texture> faceSpriteSheets = new ArrayList<Texture>();
    public static ArrayList<Image[][]> faceImages = new ArrayList<Image[][]>();
    public static Image faceImage;

    public static ArrayList<Texture> eyesSpriteSheets = new ArrayList<Texture>();
    public static ArrayList<Image[][]> eyesImages = new ArrayList<Image[][]>();
    public static Image eyesImage;

    public static ArrayList<Texture> hairSpriteSheets = new ArrayList<Texture>();
    public static ArrayList<Image[][]> hairImages = new ArrayList<Image[][]>();
    public static Image hairImage;

    private int col = 1;                //Number of columns in the SpriteSprites.
    private int row = 4;                //Number of rows in the SpriteSprites.
    private int spriteWidth;
    private  int spriteHeight;

    public void load() {
        //Face
        faceSpriteSheets.add(Game.assets.manager.get("Player/Head/face1_white.png", Texture.class));
        faceSpriteSheets.add(Game.assets.manager.get("Player/Head/face1_tan.png", Texture.class));
        for(Texture e : faceSpriteSheets) {
            e.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
        spriteWidth = faceSpriteSheets.get(0).getWidth()/col;
        spriteHeight = faceSpriteSheets.get(0).getHeight()/row;
        for(int i = 0; i < faceSpriteSheets.size(); i++) {
            faceImages.add(i, new Image[col][row]);
            for(int c = 0; c < col; c++){
                for(int r = 0; r < row; r++){
                    faceImages.get(i)[c][r] = new Image(new TextureRegion(faceSpriteSheets.get(i), c * spriteWidth, r * spriteHeight, spriteWidth, spriteHeight));
                }
            }
        }
        faceImage = faceImages.get(0)[0][0];

        //Eyes
        eyesSpriteSheets.add(Game.assets.manager.get("Player/Head/eyes_blue.png", Texture.class));
        eyesSpriteSheets.add(Game.assets.manager.get("Player/Head/eyes_brown.png", Texture.class));
        eyesSpriteSheets.add(Game.assets.manager.get("Player/Head/eyes_green.png", Texture.class));
        for(Texture e : eyesSpriteSheets) {
            e.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
        for(int i = 0; i < eyesSpriteSheets.size(); i++) {
            eyesImages.add(i, new Image[col][row]);
            for(int c = 0; c < col; c++){
                for(int r = 0; r < row; r++){
                    eyesImages.get(i)[c][r] = new Image(new TextureRegion(eyesSpriteSheets.get(i), c * spriteWidth, r * spriteHeight, spriteWidth, spriteHeight));
                }
            }
        }
        eyesImage = eyesImages.get(0)[0][0];

        //Hair
        hairSpriteSheets.add(Game.assets.manager.get("Player/Head/hair1_brown.png", Texture.class));
        hairSpriteSheets.add(Game.assets.manager.get("Player/Head/hair1_black.png", Texture.class));
        hairSpriteSheets.add(Game.assets.manager.get("Player/Head/hair1_light_brown.png", Texture.class));
        hairSpriteSheets.add(Game.assets.manager.get("Player/Head/hair1_blonde.png", Texture.class));
        for(Texture e : hairSpriteSheets) {
            e.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
        for(int i = 0; i < hairSpriteSheets.size(); i++) {
            hairImages.add(i, new Image[col][row]);
            for(int c = 0; c < col; c++){
                for(int r = 0; r < row; r++){
                    hairImages.get(i)[c][r] = new Image(new TextureRegion(hairSpriteSheets.get(i), c * spriteWidth, r * spriteHeight, spriteWidth, spriteHeight));
                }
            }
        }
        hairImage = hairImages.get(0)[0][0];
    }

    //----------------------------------------Create---------------------------------------------------//
    public void create() {

    }//-------------------------------------------------------------------------------------------------------//

    public void show() {
        faceImage = new Image(faceImages.get(SaveData.prefs.getInteger("faceIndex"))[0][0].getDrawable());
        Game.stage.addActor(faceImage);
        eyesImage = new Image(eyesImages.get(SaveData.prefs.getInteger("eyesIndex"))[0][0].getDrawable());
        Game.stage.addActor(eyesImage);
        hairImage = new Image(hairImages.get(SaveData.prefs.getInteger("hairIndex"))[0][0].getDrawable());
        Game.stage.addActor(hairImage);
    }

    public void render() {
        faceImage.setDrawable(faceImages.get(SaveData.prefs.getInteger("faceIndex"))[0][Body.frameY].getDrawable());
        faceImage.setPosition(playerPos.x - spriteWidth /2, playerPos.y);
        eyesImage.setDrawable(eyesImages.get(SaveData.prefs.getInteger("eyesIndex"))[0][Body.frameY].getDrawable());
        eyesImage.setPosition(playerPos.x - spriteWidth /2, playerPos.y);
        hairImage.setDrawable(hairImages.get(SaveData.prefs.getInteger("hairIndex"))[0][Body.frameY].getDrawable());
        hairImage.setPosition(playerPos.x - spriteWidth /2, playerPos.y);
    }
}
