package com.rgs.siegeworld.Items.Weapons.Swords;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.rgs.siegeworld.GUI.Menus.Equipment.EquipDisplay;
import com.rgs.siegeworld.GUI.Menus.Inventory.ItemDisplay;
import com.rgs.siegeworld.Game;
import com.rgs.siegeworld.Items.Item;
import com.rgs.siegeworld.Items.Items;
import com.rgs.siegeworld.Player.Body.Body;
import com.rgs.siegeworld.Player.Skills.Attack;
import com.rgs.siegeworld.Player.Stats.PlayerStats;
import com.rgs.siegeworld.SaveData;

import static com.rgs.siegeworld.Player.Player.playerPos;

public class ShortSword extends Item implements Json.Serializable {

    public static Texture swordSprites;
    public static int col = 3;
    public static int row = 4;
    public static int spriteWidth;
    public static  int spriteHeight;
    public static Image[][] swordImages;
    public static Image swordImage;
    public static Texture iconTexture;
    private Image iconImage;
    private Vector2 iconPos;
    private Rectangle iconBounds = new Rectangle();
    public boolean selected = false;
    public String itemType = "weapon";
    public String itemName = "Short Sword";
    public int itemLevel = 3;
    public int itemDamage = 10;
    public int itemDefense = 0;
    public int itemMelee = 3;
    public int itemMagic = 0;
    public int itemRange = 0;


    //----------------------------------------Load-Method----------------------------------------------//
    public static void load() {
        swordSprites = new Texture(Game.assets.manager.get("Items/Weapons/Swords/ShortSword/short_sword.png", Texture.class).getTextureData());
        swordSprites.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        //Width/spriteHeight of Image[*][*]
        spriteWidth = swordSprites.getWidth()/col;
        spriteHeight = swordSprites.getHeight()/row;

        //Fill array with array[0][0] being top left corner. And [col][row] being bottom right corner.
        swordImages = new Image[col][row];
        for(int i = 0; i < col; i++){
            for(int j = 0; j < row; j++){
                swordImages[i][j] = new Image(new TextureRegion(swordSprites, i * spriteWidth, j * spriteHeight, spriteWidth, spriteHeight));
            }
        }
        swordImage = new Image(swordImages[0][Body.frameY].getDrawable());

        iconTexture = new Texture(Game.assets.manager.get("Items/Weapons/Swords/ShortSword/short_sword_icon.png", Texture.class).getTextureData());
        iconTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }//-------------------------------------------------------------------------------------------------------//

    public ShortSword(boolean isStatic) {   //Create Static Instances
        System.out.println("Static ShortSword class created");  //Static assets are created only once.
    }
    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

    public ShortSword() {    //Creates item to be put in arraylist of class's  and to be rendered in current screen.
        this.iconPos = new Vector2(0, 0);
        this.iconImage = new Image(iconTexture);
        this.iconImage.setPosition(iconPos.x, iconPos.y);
        this.iconBounds.set(iconImage.getX(), iconImage.getY(), iconImage.getWidth(), iconImage.getHeight());
        this.iconImage.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(Items.getSelected()!= null) {
                    Items.getSelected().deselect();
                }
                if(!selected) {
                    Items.selectImage.setPosition(iconImage.getX(), iconImage.getY());
                    Game.stageGUI.addActor(Items.selectImage);
                    select();
                    PlayerStats.removeStats();
                    if(ItemDisplay.showDisplay)
                        ItemDisplay.showStats();
                    if(EquipDisplay.showDisplay)
                        EquipDisplay.showStats();

                }
            };
        });
        System.out.println("ShortSword Created");
    }

    public Image getImage() {
        return iconImage;
    }

    public void select() {
        selected = true;
    }

    public boolean isSelected() {
        return selected;
    }

    public void deselect() {
        selected = false;
        Items.selectImage.remove();
    }

    public void show() {
        Game.stageGUI.addActor(this.iconImage);
    }

    public void remove() {
        this.iconImage.remove();
    }

    public void setPosition(float x, float y) {
        iconImage.setPosition(x, y);
    }

    public Rectangle getBounds() {
        this.iconBounds.set(iconImage.getX(), iconImage.getY(), iconImage.getWidth(), iconImage.getHeight());
        return iconBounds;
    }

    public Image[][] getImages() {
        return swordImages;
    }

    public String getType() {
        return itemType;
    }

    public String getName() {
        return itemName;
    }

    public int getLevel() {
        return itemLevel;
    }

    public int getDamage() {
        return itemDamage;
    }

    public int getDefense() {
        return itemDefense;
    }

    public int getMelee() {
        return itemMelee;
    }

    public int getMagic() {
        return itemMagic;
    }

    public int getRange() {
        return itemRange;
    }

    public void write(Json json) {
        json.writeValue("itemType", itemType);
        json.writeValue("itemName", itemName);
        json.writeValue("itemLevel", itemLevel);
        json.writeValue("itemDamage", itemDamage);
        json.writeValue("itemDefense", itemDefense);
        json.writeValue("itemMelee", itemMelee);
        json.writeValue("itemMagic", itemMagic);
        json.writeValue("itemRange", itemRange);
    }

    public void read(Json json, JsonValue jsonData) {
        json.readFields(this, jsonData);
    }
}