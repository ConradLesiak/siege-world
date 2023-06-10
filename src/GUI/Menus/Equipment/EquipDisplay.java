package com.rgs.siegeworld.GUI.Menus.Equipment;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.rgs.siegeworld.GUI.GUI;
import com.rgs.siegeworld.GUI.Menus.Inventory.Inventory;
import com.rgs.siegeworld.Game;
import com.rgs.siegeworld.Items.Items;
import com.rgs.siegeworld.Player.Stats.PlayerStats;

import java.util.ArrayList;

public class EquipDisplay {

    public static boolean showDisplay = false;
    public static Label removeLabel;
    public static Label damageLabel;
    public static Label defenseLabel;
    public static Label meleeLabel;
    public static Label magicLabel;
    public static Label rangeLabel;
    public static ArrayList<Label> statsLabels = new ArrayList<Label>();

    public void load() {

    }

    public void create() {
        removeLabel = new Label("Remove", GUI.smallWhiteStyle);
        removeLabel.setPosition(Inventory.backgroundImage.getX() + Inventory.backgroundImage.getWidth() - removeLabel.getWidth(), 200);
        removeLabel.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(Items.getSelected() != null) {
                    EquipDisplay.removeStats();
                    PlayerStats.showStats();
                    Items.unEquipItem(Items.getSelected());
                    PlayerStats.updateStats();
                }
            };
        });
        damageLabel = new Label("Damage:", GUI.smallWhiteStyle);
        damageLabel.setPosition(Inventory.backgroundImage.getX(), 300);
        defenseLabel = new Label("Defense:", GUI.smallWhiteStyle);
        defenseLabel.setPosition(Inventory.backgroundImage.getX(), 230);
        meleeLabel= new Label("Melee:", GUI.smallWhiteStyle);
        meleeLabel.setPosition(Inventory.backgroundImage.getX(), 160);
        magicLabel= new Label("Magic:", GUI.smallWhiteStyle);
        magicLabel.setPosition(Inventory.backgroundImage.getX(), 90);
        rangeLabel= new Label("Range:", GUI.smallWhiteStyle);
        rangeLabel.setPosition(Inventory.backgroundImage.getX(), 20);
    }

    public static void show() {
        Game.stageGUI.addActor(damageLabel);
        Game.stageGUI.addActor(defenseLabel);
        Game.stageGUI.addActor(removeLabel);
        Game.stageGUI.addActor(meleeLabel);
        Game.stageGUI.addActor(magicLabel);
        Game.stageGUI.addActor(rangeLabel);
        showDisplay = true;
    }

    public static void remove() {
        removeStats();
        damageLabel.remove();
        defenseLabel.remove();
        removeLabel.remove();
        meleeLabel.remove();
        magicLabel.remove();
        rangeLabel.remove();
        showDisplay = false;
    }

    public static void showStats() {
        System.out.println("showMenu stats...");
        removeStats();
        if(Items.getSelected() != null) {
            //Name
            statsLabels.add(new Label(Items.getSelected().getName(), GUI.smallWhiteStyle));
            statsLabels.get(0).setPosition(-statsLabels.get(0).getWidth() / 2, 370);
            Game.stageGUI.addActor(statsLabels.get(0));
            //Damage
            statsLabels.add(new Label(Integer.toString(Items.getSelected().getDamage()), GUI.smallWhiteStyle));
            statsLabels.get(1).setPosition(-statsLabels.get(1).getWidth() / 2, 300);
            Game.stageGUI.addActor(statsLabels.get(1));
            //Defense
            statsLabels.add(new Label(Integer.toString(Items.getSelected().getDefense()), GUI.smallWhiteStyle));
            statsLabels.get(2).setPosition(-statsLabels.get(2).getWidth() / 2, 230);
            Game.stageGUI.addActor(statsLabels.get(2));
            //Melee
            statsLabels.add(new Label(Integer.toString(Items.getSelected().getMelee()), GUI.smallWhiteStyle));
            statsLabels.get(3).setPosition(-statsLabels.get(3).getWidth() / 2, 160);
            Game.stageGUI.addActor(statsLabels.get(3));
            //Magic
            statsLabels.add(new Label(Integer.toString(Items.getSelected().getMagic()), GUI.smallWhiteStyle));
            statsLabels.get(4).setPosition(-statsLabels.get(4).getWidth() / 2, 90);
            Game.stageGUI.addActor(statsLabels.get(4));
            //Range
            statsLabels.add(new Label(Integer.toString(Items.getSelected().getRange()), GUI.smallWhiteStyle));
            statsLabels.get(5).setPosition(-statsLabels.get(5).getWidth() / 2, 20);
            Game.stageGUI.addActor(statsLabels.get(5));
        }
    }

    public static void removeStats() {
        System.out.println("remove stats");
        for(int i = 0; i < statsLabels.size(); i++) {
            statsLabels.get(i).remove();
        }
        statsLabels.clear();
    }
}