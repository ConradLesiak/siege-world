package com.rgs.siegeworld.GUI.Menus.ForgeMenu;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.rgs.siegeworld.GUI.GUI;
import com.rgs.siegeworld.Game;

public class CreateTab {

    public static boolean showCreateTab = true;
    public static Label createTabLabel;
    public static Label createButton;

    public void load() {
        createTabLabel = new Label("Create", GUI.smallWhiteStyle);
        createTabLabel.setPosition(-270 - createTabLabel.getWidth()/2, 360);
        createTabLabel.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                if(!showCreateTab) {
                    UpgradeTab.remove();
                    show();
                }
            }
        });
        createButton = new Label("Create", GUI.smallWhiteStyle);
        createButton.setPosition(-createButton.getWidth()/2, -600);
    }

    public void create() {

    }

    public static void show() {
        createTabLabel.setStyle(GUI.smallWhiteStyle);
        UpgradeTab.upgradeTabLabel.setStyle(GUI.smallGrayStyle);
        Game.stageGUI.addActor(createButton);
        UpgradeTab.showUpgradeTab = false;
        showCreateTab = true;
    }

    public static void remove() {
        createButton.remove();
    }

    public void render() {

    }
}
