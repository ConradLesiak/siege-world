package com.rgs.siegeworld.Items.EquippedItems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.rgs.siegeworld.Game;
import com.rgs.siegeworld.Items.Item;
import com.rgs.siegeworld.Player.Body.Body;
import com.rgs.siegeworld.Player.Skills.Attack;

import static com.rgs.siegeworld.Player.Player.playerPos;

public class Weapon {

    private Texture weaponSprites;
    private int col = 3;
    private int row = 4;
    private int spriteWidth;
    private  int spriteHeight;
    public static Image[][] weaponImages;
    public static Image weaponImage;
    public static Texture iconTexture;
    public static Image iconImage;

    //----------------------------------------Create-----------------------------------------------------//
    public void create() {
        weaponSprites = new Texture(Game.assets.manager.get("Items/Weapons/Swords/ShortSword/short_sword.png", Texture.class).getTextureData()); //Save current weapon here
        weaponSprites.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        //Width/spriteHeight of Image[*][*]
        spriteWidth = weaponSprites.getWidth()/col;
        spriteHeight = weaponSprites.getHeight()/row;

        //Fill array with array[0][0] being top left corner. And [col][row] being bottom right corner.
        weaponImages = new Image[col][row];
        for(int i = 0; i < col; i++){
            for(int j = 0; j < row; j++){
                weaponImages[i][j] = new Image(new TextureRegion(weaponSprites, i * spriteWidth, j * spriteHeight, spriteWidth, spriteHeight));
            }
        }
        weaponImage = new Image(weaponImages[0][Body.frameY].getDrawable());
        //Game.stage.addActor(weaponImage);

        iconTexture = new Texture(Game.assets.manager.get("Items/Weapons/Swords/ShortSword/short_sword_icon.png", Texture.class).getTextureData());
        iconTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        iconImage = new Image(iconTexture);
    }//-------------------------------------------------------------------------------------------------------//

    public static void addWeapon(Item item) {
        weaponImages = item.getImages();
        weaponImage = new Image(weaponImages[0][Body.frameY].getDrawable());
        Game.stage.addActor(weaponImage);
    }

    public static void removeWeapon() {
        weaponImage.remove();
    }

    public void render() {
        weaponImage.setDrawable(weaponImages[Attack.frameX][Body.frameY].getDrawable());
        weaponImage.setPosition(playerPos.x - spriteWidth /2, playerPos.y);
    }
}