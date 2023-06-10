package com.rgs.siegeworld.GUI.Menus.ForgeMenu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.rgs.siegeworld.GUI.GUI;
import com.rgs.siegeworld.GUI.Menus.Inventory.Border;
import com.rgs.siegeworld.GUI.Menus.Inventory.Inventory;
import com.rgs.siegeworld.GUI.Menus.Inventory.Slots;
import com.rgs.siegeworld.Game;
import com.rgs.siegeworld.Items.Items;

public class ForgeMenu {

    public static Texture forgeTexture;
    public static Image forgeImage;
    public static boolean showForgeMenu = false;
    public static Label forgeLabel;
    public static CreateTab createTab = new CreateTab();
    public static UpgradeTab upgradeTab = new UpgradeTab();

    public void load() {
        forgeTexture = new Texture(Game.assets.manager.get("Menus/Forge/forge_icon.png", Texture.class).getTextureData());
        forgeTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        forgeImage = new Image(forgeTexture);
        forgeImage.setPosition(-forgeImage.getWidth()/2, -400);
        forgeImage.setColor(1, 1, 1, .7f);
        forgeImage.setVisible(false);
        forgeImage.addListener( new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                showMenu();
            }
        });
        forgeLabel = new Label("Forge", GUI.largeWhiteStyle);
        forgeLabel.setPosition(-forgeLabel.getWidth()/2, Inventory.backgroundImage.getY() + Inventory.backgroundImage.getHeight());
        createTab.load();
        upgradeTab.load();
    }

    public void create() {
        Game.stageGUI.addActor(forgeImage);
        createTab.create();
        upgradeTab.create();
    }

    public static void showMenu() {
        showForgeMenu = true;
        Game.stageGUI.addActor(Inventory.backgroundImage);
        Border.show();
        Border.borderMiddle.remove();
        Game.stageGUI.addActor(forgeLabel);
        Game.stageGUI.addActor(CreateTab.createTabLabel);
        if(CreateTab.showCreateTab) {
            CreateTab.show();
        }
        Game.stageGUI.addActor(UpgradeTab.upgradeTabLabel);
        if(UpgradeTab.showUpgradeTab) {
            UpgradeTab.show();
        }
    }

    public static void remove() {
        showForgeMenu = false;
        Inventory.backgroundImage.remove();
        Border.remove();
        forgeLabel.remove();
        CreateTab.createTabLabel.remove();
        UpgradeTab.upgradeTabLabel.remove();
        if(CreateTab.showCreateTab) {
            CreateTab.remove();
        } else if(UpgradeTab.showUpgradeTab) {
            UpgradeTab.remove();
        }
    }

    public void render() {

    }
}
