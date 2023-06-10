package com.rgs.siegeworld.GUI.Menus.Inventory;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.rgs.siegeworld.Game;

import java.util.ArrayList;

public class Slots {

    public static Texture slotTexture;
    public static Image slotImage;
    public static ArrayList<ArrayList<Image>> slotImages = new ArrayList<ArrayList<Image>>();

    public void load() {
        slotTexture = new Texture(Game.assets.manager.get("Menus/Inventory/slot.png", Texture.class).getTextureData());
        slotTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        slotImage = new Image(slotTexture);
        Vector2 startPoint = new Vector2(-Inventory.backgroundImage.getWidth()/2, -Inventory.backgroundImage.getHeight()/2);
        for(int row = 0; row < 6; row++) {    //Fills slotImages in rows starting at bottem left corner;
            slotImages.add(new ArrayList<Image>());
            for(int col = 0; col < 3; col++) {
                slotImage = new Image(slotTexture);
                slotImage.setPosition(-Inventory.backgroundImage.getWidth()/2 + row * slotImage.getWidth(), Inventory.backgroundImage.getY() + col * slotImage.getHeight());
                slotImages.get(row).add(slotImage);
            }
        }
    }

    public void create() {

    }

    public static void show() {
        for(int row = 0; row < slotImages.size(); row++) {    //Fills slotImages in rows starting at bottom left corner;
            for(int col = 0; col < slotImages.get(row).size(); col++) {
                Game.stageGUI.addActor(slotImages.get(row).get(col));
            }
        }
    }

    public static void remove() {
        for(int row = 0; row < slotImages.size(); row++) {    //Fills slotImages in rows starting at bottom left corner;
            for(int col = 0; col < slotImages.get(row).size(); col++) {
                slotImages.get(row).get(col).remove();
            }
        }
    }
}