package com.rgs.siegeworld.GUI.Menus.ForgeMenu;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.rgs.siegeworld.GUI.GUI;
import com.rgs.siegeworld.GUI.Menus.Inventory.Border;
import com.rgs.siegeworld.GUI.Menus.Inventory.Inventory;
import com.rgs.siegeworld.GUI.Menus.Inventory.Slots;
import com.rgs.siegeworld.Game;
import com.rgs.siegeworld.Items.Items;

import java.util.ArrayList;

public class UpgradeTab {

    public static boolean showUpgradeTab = false;
    public static Label upgradeTabLabel;
    public static Label upgradeButton;
    public static Label damageLabel;
    public static Label defenseLabel;
    public static Label meleeLabel;
    public static Label magicLabel;
    public static Label rangeLabel;
    public static ArrayList<Label> statsLabels = new ArrayList<Label>();

    public void load() {
        upgradeTabLabel = new Label("Upgrade", GUI.smallWhiteStyle);
        upgradeTabLabel.setPosition(270 - upgradeTabLabel.getWidth()/2, 360);
        upgradeTabLabel.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                if(!showUpgradeTab) {
                    CreateTab.remove();
                    show();
                }
            }
        });
        upgradeButton = new Label("Upgrade", GUI.smallWhiteStyle);
        upgradeButton.setPosition(Inventory.backgroundImage.getX() + Inventory.backgroundImage.getWidth() - upgradeButton.getWidth() - 20, 60);
    }

    public void create() {
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
        upgradeTabLabel.setStyle(GUI.smallWhiteStyle);
        CreateTab.createTabLabel.setStyle(GUI.smallGrayStyle);
        Game.stageGUI.addActor(upgradeButton);
        Game.stageGUI.addActor(Border.borderMiddle);
        Slots.show();
        //Show Items
        Vector2 topLeftPos = new Vector2(Inventory.backgroundImage.getX(), Inventory.backgroundImage.getY() + Slots.slotImage.getHeight()*2);
        int col = 0;
        int row = 0;
        for(int index = 0; index < 18; index++) {
            Items.showItems(index, topLeftPos.x + col*200, topLeftPos.y - row*200);
            col++;
            if(col > 5) {
                row++;
                col = 0;
            }
        }
        Game.stageGUI.addActor(damageLabel);
        Game.stageGUI.addActor(defenseLabel);
        Game.stageGUI.addActor(meleeLabel);
        Game.stageGUI.addActor(magicLabel);
        Game.stageGUI.addActor(rangeLabel);
        CreateTab.showCreateTab = false;
        showUpgradeTab = true;
    }

    public static void remove() {
        upgradeButton.remove();
        Border.borderMiddle.remove();
        Slots.remove();
        Items.removeItems();
        damageLabel.remove();
        defenseLabel.remove();
        meleeLabel.remove();
        magicLabel.remove();
        rangeLabel.remove();
    }

    public void render() {

    }
}
