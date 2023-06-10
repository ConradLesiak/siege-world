package com.rgs.siegeworld.Items;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Item {    //Superclass of all Items in the game.

    public Item() {

    }

    public static void create() {

    }

    public Image getImage() {
        return new Image();
    }

    public void select() {

    }

    public boolean isSelected() {   //Return true if item is selected.
        return false;
    }

    public void deselect() {

    }

    public void show() {

    }

    public void remove() {

    }

    public void setPosition(float x, float y) {

    }

    public Rectangle getBounds() {
        return new Rectangle();
    }

    public Image[][] getImages() {
        return new Image[0][];
    }

    public String getType() {
        return null;
    }

    public String getName() {
        return "";
    }

    public int getLevel() {
        return 0;
    }

    public int getDamage() {
        return 0;
    }

    public int getDefense() {
        return 0;
    }

    public int getMelee() {
        return 0;
    }

    public int getMagic() {
        return 0;
    }

    public int getRange() {
        return 0;
    }
}