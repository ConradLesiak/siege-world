package com.rgs.siegeworld.Items.Weapons.Staffs;

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

import static com.rgs.siegeworld.Player.Player.playerPos;

public class ShortStaff extends Item implements Json.Serializable {

    public static Texture staffSprites;
    public static int col = 3;
    public static int row = 4;
    public static int spriteWidth;
    public static  int spriteHeight;
    public static Image[][] staffImages;
    public static Image staffImage;
    public static Texture iconTexture;
    private Image iconImage;
    private Vector2 iconPos;
    private Rectangle iconBounds = new Rectangle();
    public boolean selected = false;
    public String itemType = "weapon";
    public String itemName = "Short Staff";
    public int itemLevel = 1;
    public int itemDamage = 10;
    public int itemDefense = 0;
    public int itemMelee = 0;
    public int itemMagic = 3;
    public int itemRange = 0;


    //----------------------------------------Load-Method----------------------------------------------//
    public static void load() {
        staffSprites = new Texture(Game.assets.manager.get("Items/Weapons/Staffs/ShortStaff/short_staff.png", Texture.class).getTextureData());
        staffSprites.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        //Width/spriteHeight of Image[*][*]
        spriteWidth = staffSprites.getWidth()/col;
        spriteHeight = staffSprites.getHeight()/row;

        //Fill array with array[0][0] being top left corner. And [col][row] being bottom right corner.
        staffImages = new Image[col][row];
        for(int i = 0; i < col; i++){
            for(int j = 0; j < row; j++){
                staffImages[i][j] = new Image(new TextureRegion(staffSprites, i * spriteWidth, j * spriteHeight, spriteWidth, spriteHeight));
            }
        }
        staffImage = new Image(staffImages[0][Body.frameY].getDrawable());

        iconTexture = new Texture(Game.assets.manager.get("Items/Weapons/Staffs/ShortStaff/short_staff_icon.png", Texture.class).getTextureData());
        iconTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }//-------------------------------------------------------------------------------------------------------//

    public ShortStaff(boolean isStatic) {   //Create Static Instances
        System.out.println("Static ShortStaff class created");  //Static assets are created only once.
    }
    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

    public ShortStaff() {    //Creates item to be put in arraylist of class's  and to be rendered in current screen.
        this.iconPos = new Vector2(0, 0);
        this.iconImage = new Image(iconTexture);
        this.iconImage.setPosition(iconPos.x, iconPos.y);
        this.iconBounds.set(iconImage.getX(), iconImage.getY(), iconImage.getWidth(), iconImage.getHeight());
        this.iconImage.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(Items.getSelected() != null) {
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
        System.out.println("ShortStaff Created");
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
        return staffImages;
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