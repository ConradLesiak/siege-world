package com.rgs.siegeworld.Player.Stats;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.rgs.siegeworld.Game;
import com.rgs.siegeworld.Items.Items;

import java.util.ArrayList;

public class PlayerStats {

    FreeTypeFontGenerator fontGenerator;
    FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;
    public static BitmapFont small_white;
    public static BitmapFont small_green;
    public static BitmapFont small_red;
    public static ArrayList<Label> statsLabels = new ArrayList<Label>();
    public static ArrayList<Label> differenceLabels = new ArrayList<Label>();

    public static int level = 1;
    public static int maxHealth = 100;
    public static int maxMana = 100;
    public static int damage = 10;
    public static int defense = 0;
    public static int melee = 0;
    public static int magic = 0;
    public static int range = 0;

    public void load() {

    }

    public void create() {
        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/arialbd.ttf"));
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = 50; // large_white size in pixels
        fontParameter.color = Color.WHITE;
        fontParameter.borderWidth = 2;
        fontParameter.borderColor = Color.BLACK;
        fontParameter.minFilter = Texture.TextureFilter.Linear;
        fontParameter.magFilter = Texture.TextureFilter.Linear;
        small_white = fontGenerator.generateFont(fontParameter);
        fontParameter.color = Color.GREEN;
        small_green = fontGenerator.generateFont(fontParameter);
        fontParameter.color = Color.RED;
        small_red = fontGenerator.generateFont(fontParameter);
        fontGenerator.dispose();
    }

    public static void updateStats() {  //Update stats by resetting to default and then adding equip stats.
        damage = 10;
        defense = 0;
        melee = 0;
        magic = 0;
        range = 0;
        for(int i = 0; i < Items.equippedItems.length; i++) {
            damage += Items.equippedItems[i].getDamage();
            defense += Items.equippedItems[i].getDefense();
            melee += Items.equippedItems[i].getMelee();
            magic += Items.equippedItems[i].getMagic();
            range += Items.equippedItems[i].getRange();
        }
        removeStats();
        showStats();
    }

    public static void showStats() {    //Show Player Stats
        removeStats();
        removeDifference();
        //Name
        statsLabels.add(new Label("Player Stats", new Label.LabelStyle(small_white, small_white.getColor())));
        statsLabels.get(0).setPosition(-statsLabels.get(0).getWidth() / 2, 370);
        Game.stageGUI.addActor(statsLabels.get(0));
        //Damage
        statsLabels.add(new Label(Integer.toString(damage), new Label.LabelStyle(small_white, small_white.getColor())));
        statsLabels.get(1).setPosition(-statsLabels.get(1).getWidth() / 2, 300);
        Game.stageGUI.addActor(statsLabels.get(1));
        //Defense
        statsLabels.add(new Label(Integer.toString(defense), new Label.LabelStyle(small_white, small_white.getColor())));
        statsLabels.get(2).setPosition(-statsLabels.get(2).getWidth() / 2, 230);
        Game.stageGUI.addActor(statsLabels.get(2));
        //Melee
        statsLabels.add(new Label(Integer.toString(melee), new Label.LabelStyle(small_white, small_white.getColor())));
        statsLabels.get(3).setPosition(-statsLabels.get(3).getWidth() / 2, 160);
        Game.stageGUI.addActor(statsLabels.get(3));
        //Magic
        statsLabels.add(new Label(Integer.toString(magic), new Label.LabelStyle(small_white, small_white.getColor())));
        statsLabels.get(4).setPosition(-statsLabels.get(4).getWidth() / 2, 90);
        Game.stageGUI.addActor(statsLabels.get(4));
        //Range
        statsLabels.add(new Label(Integer.toString(range), new Label.LabelStyle(small_white, small_white.getColor())));
        statsLabels.get(5).setPosition(-statsLabels.get(5).getWidth() / 2, 20);
        Game.stageGUI.addActor(statsLabels.get(5));
    }

    public static void removeStats() {  //Remove Player stats
        for(int i = 0; i < statsLabels.size(); i++) {
            statsLabels.get(i).remove();
        }
        statsLabels.clear();
    }

    public static void showDifference() {   //Shows difference between equipped item and selected item.
        removeDifference();
        for(int i = 0; i < Items.equippedItems.length; i++) {
            String type = "";
            if(i == 0)
                type = "weapon";
            if(i == 1)
                type = "helmet";
            if(i == 2)
                type = "shield";
            if(i == 3)
                type = "gloves";
            if(i == 4)
                type = "chest";
            if(i == 5)
                type = "cape";
            if(i == 6)
                type = "belt";
            if(i == 7)
                type = "legs";
            if(i == 8)
                type = "boots";

            int damageDif = 0;
            int defenseDif = 0;
            int meleeDif = 0;
            int magicDif = 0;
            int rangeDif = 0;
            if(Items.equippedItems[i].getType() != null && type.equalsIgnoreCase(Items.getSelected().getType())) {     //When an item already equipped
                damageDif = Items.getSelected().getDamage() - Items.equippedItems[i].getDamage();
                defenseDif = Items.getSelected().getDefense() - Items.equippedItems[i].getDefense();
                meleeDif = Items.getSelected().getMelee() - Items.equippedItems[i].getMelee();
                magicDif = Items.getSelected().getMagic() - Items.equippedItems[i].getMagic();
                rangeDif = Items.getSelected().getRange() - Items.equippedItems[i].getRange();
            } else if(type.equalsIgnoreCase(Items.getSelected().getType())) {      //When no item is equipped
                damageDif = Items.getSelected().getDamage();
                defenseDif = Items.getSelected().getDefense();
                meleeDif = Items.getSelected().getMelee();
                magicDif = Items.getSelected().getMagic();
                rangeDif = Items.getSelected().getRange();
            }
            if(damageDif >= 0) {    //Show Damage Green
                differenceLabels.add(new Label("+" + Integer.toString(damageDif), new Label.LabelStyle(small_green, small_green.getColor())));
                differenceLabels.get(0).setPosition(50, 300);
                Game.stageGUI.addActor(differenceLabels.get(0));
            } else {    //Show Damage Red
                differenceLabels.add(new Label(Integer.toString(damageDif), new Label.LabelStyle(small_red, small_red.getColor())));
                differenceLabels.get(0).setPosition(50, 300);
                Game.stageGUI.addActor(differenceLabels.get(0));
            }
            if(defenseDif >= 0) {    //Show Defense Green
                differenceLabels.add(new Label("+" + Integer.toString(defenseDif), new Label.LabelStyle(small_green, small_green.getColor())));
                differenceLabels.get(1).setPosition(50, 230);
                Game.stageGUI.addActor(differenceLabels.get(1));
            } else {    //Show Defense Red
                differenceLabels.add(new Label(Integer.toString(defenseDif), new Label.LabelStyle(small_red, small_red.getColor())));
                differenceLabels.get(1).setPosition(50, 230);
                Game.stageGUI.addActor(differenceLabels.get(1));
            }
            if(meleeDif >= 0) {    //Show Melee Green
                differenceLabels.add(new Label("+" + Integer.toString(meleeDif), new Label.LabelStyle(small_green, small_green.getColor())));
                differenceLabels.get(2).setPosition(50, 160);
                Game.stageGUI.addActor(differenceLabels.get(2));
            } else {    //Show Melee Red
                differenceLabels.add(new Label(Integer.toString(meleeDif), new Label.LabelStyle(small_red, small_red.getColor())));
                differenceLabels.get(2).setPosition(50, 160);
                Game.stageGUI.addActor(differenceLabels.get(2));
            }
            if(magicDif >= 0) {    //Show Defense Green
                differenceLabels.add(new Label("+" + Integer.toString(magicDif), new Label.LabelStyle(small_green, small_green.getColor())));
                differenceLabels.get(3).setPosition(50, 90);
                Game.stageGUI.addActor(differenceLabels.get(3));
            } else {    //Show Defense Red
                differenceLabels.add(new Label(Integer.toString(magicDif), new Label.LabelStyle(small_red, small_red.getColor())));
                differenceLabels.get(3).setPosition(50, 90);
                Game.stageGUI.addActor(differenceLabels.get(3));
            }
            if(rangeDif >= 0) {    //Show Range Green
                differenceLabels.add(new Label("+" + Integer.toString(rangeDif), new Label.LabelStyle(small_green, small_green.getColor())));
                differenceLabels.get(4).setPosition(50, 20);
                Game.stageGUI.addActor(differenceLabels.get(4));
            } else {    //Show Range Red
                differenceLabels.add(new Label(Integer.toString(rangeDif), new Label.LabelStyle(small_red, small_red.getColor())));
                differenceLabels.get(4).setPosition(50, 20);
                Game.stageGUI.addActor(differenceLabels.get(4));
            }
        }
    }

    public static void removeDifference() {
        for(int i = 0; i < differenceLabels.size(); i++) {
            differenceLabels.get(i).remove();
        }
        differenceLabels.clear();
    }
}