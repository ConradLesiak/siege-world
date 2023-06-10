package com.rgs.siegeworld.GUI.Menus.Inventory;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.rgs.siegeworld.GUI.GUI;
import com.rgs.siegeworld.GUI.Menus.Equipment.Equipment;
import com.rgs.siegeworld.GUI.Menus.ForgeMenu.ForgeMenu;
import com.rgs.siegeworld.GUI.Menus.Settings.Settings;
import com.rgs.siegeworld.GUI.Menus.SkillsMenu.SkillsMenu;
import com.rgs.siegeworld.Game;
import com.rgs.siegeworld.Items.Items;
import com.rgs.siegeworld.Player.Stats.PlayerStats;
import com.rgs.siegeworld.SaveData;

public class Inventory {

    public static Texture inventoryTexture;
    public static Image inventoryImage;
    public static boolean showInventory = false;
    public static Image backgroundImage;
    public static Rectangle backgroundBounds;
    public static Border border = new Border();
    public static Label inventoryLabel;
    public static Slots slots = new Slots();
    public static ItemDisplay itemDisplay = new ItemDisplay();

    public void load() {
        inventoryTexture = new Texture(Game.assets.manager.get("Menus/Inventory/inventory.png", Texture.class).getTextureData());
        inventoryTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        inventoryImage = new Image(inventoryTexture);
        backgroundImage = new Image(new Texture(Game.assets.manager.get("Menus/Inventory/background.png", Texture.class).getTextureData()));
        backgroundImage.setBounds(-600, -640, 1200, 1070);
        border.load();
        slots.load();
        itemDisplay.load();
    }

    public void create() {
        inventoryImage.setPosition(Settings.settingsImage.getX() - inventoryImage.getWidth()*1.2f, Settings.settingsImage.getY());
        inventoryImage.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(Equipment.showEquips) {
                    if(Items.getSelected() != null) {
                        Items.getSelected().deselect();
                        ItemDisplay.removeStats();
                    }
                    Equipment.removeEquips();
                    showInventory();
                    PlayerStats.showStats();
                } else if(SkillsMenu.showSkills) {
                    if(Items.getSelected() != null)
                        Items.getSelected().deselect();
                    SkillsMenu.removeSkills();
                    showInventory();
                    PlayerStats.showStats();
                } else if(ForgeMenu.showForgeMenu) {
                    ForgeMenu.remove();
                    showInventory();
                    PlayerStats.showStats();
                } else if(!showInventory) {
                    if(Items.getSelected() != null)
                        Items.getSelected().deselect();
                    showInventory();
                    PlayerStats.showStats();
                } else {
                    if(Items.getSelected() != null)
                        Items.getSelected().deselect();
                    removeInventory();
                }
            };
        });
        Game.stageGUI.addActor(inventoryImage);
        backgroundBounds = new Rectangle(backgroundImage.getX(), backgroundImage.getY(), backgroundImage.getWidth(), backgroundImage.getHeight());
        border.create();
        slots.create();
        itemDisplay.create();
        inventoryLabel = new Label("Inventory", new Label.LabelStyle(GUI.large_white, GUI.large_white.getColor()));
        inventoryLabel.setPosition(-inventoryLabel.getWidth()/2, Inventory.backgroundImage.getY()+Inventory.backgroundImage.getHeight());
    }

    public static void showInventory() {   //Shows Inventory on screen
        showInventory = true;
        Game.stageGUI.addActor(backgroundImage);
        border.show();
        slots.show();
        itemDisplay.show();
        Game.stageGUI.addActor(inventoryLabel);

        //Show Items
        Vector2 topLeftPos = new Vector2(backgroundImage.getX(), backgroundImage.getY() + Slots.slotImage.getHeight()*2);
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
    }

    public static void removeInventory() {     //Removes Inventory screen
        showInventory = false;
        backgroundImage.remove();
        border.remove();
        slots.remove();
        itemDisplay.remove();
        inventoryLabel.remove();
        Items.removeItems();
        PlayerStats.removeStats();
        PlayerStats.removeDifference();
    }
}