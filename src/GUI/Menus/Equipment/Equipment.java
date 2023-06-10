package com.rgs.siegeworld.GUI.Menus.Equipment;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.rgs.siegeworld.GUI.GUI;
import com.rgs.siegeworld.GUI.Menus.ForgeMenu.ForgeMenu;
import com.rgs.siegeworld.GUI.Menus.Inventory.Border;
import com.rgs.siegeworld.GUI.Menus.Inventory.Inventory;
import com.rgs.siegeworld.GUI.Menus.Inventory.Slots;
import com.rgs.siegeworld.GUI.Menus.Settings.Settings;
import com.rgs.siegeworld.GUI.Menus.SkillsMenu.SkillsMenu;
import com.rgs.siegeworld.Game;
import com.rgs.siegeworld.Items.Items;
import com.rgs.siegeworld.Player.Stats.PlayerStats;

import java.util.ArrayList;

public class Equipment {

    public static Texture equipTexture;
    public static Image equipImage;
    public static boolean showEquips = false;
    public static ArrayList<ArrayList<Image>> equipSlots = new ArrayList<ArrayList<Image>>();
    public static ArrayList<Image> extraSlots = new ArrayList<Image>(); //Extra slot images for equip index 9-12
    public static Label swordLabel;
    public static Label helmetLabel;
    public static Label shieldLabel;
    public static Label glovesLabel;
    public static Label chestLabel;
    public static Label beltLabel;
    public static Label bootsLabel;
    public static Label legsLabel;
    public static Label capeLabel;
    public static Label potion1Label;
    public static Label potion2Label;
    public static Label amuletLabel;
    public static Label ringLabel;
    public static Label equipLabel;
    public static EquipDisplay equipDisplay = new EquipDisplay();

    public void load() {
        equipTexture = new Texture(Game.assets.manager.get("Menus/Equipment/equip_icon.png", Texture.class).getTextureData());
        equipTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        equipImage = new Image(equipTexture);
        equipDisplay.load();
    }

    public void create() {
        equipImage.setPosition(Inventory.inventoryImage.getX() - equipImage.getWidth()*1.2f, Settings.settingsImage.getY());
        equipImage.addListener( new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                if(Inventory.showInventory) {
                    if(Items.getSelected() != null) {
                        Items.getSelected().deselect();
                        EquipDisplay.removeStats();
                    }
                    Inventory.removeInventory();
                    showEquips();
                    PlayerStats.showStats();
                } else if(SkillsMenu.showSkills) {
                    if(Items.getSelected() != null)
                        Items.getSelected().deselect();
                    SkillsMenu.removeSkills();
                    showEquips();
                    PlayerStats.showStats();
                } else if(ForgeMenu.showForgeMenu) {
                    ForgeMenu.remove();
                    showEquips();
                    PlayerStats.showStats();
                } else if(!showEquips) {
                    if(Items.getSelected() != null)
                        Items.getSelected().deselect();
                    showEquips();
                    PlayerStats.showStats();
                } else {
                    if(Items.getSelected() != null)
                        Items.getSelected().deselect();
                    removeEquips();
                }
            }
        });
        Game.stageGUI.addActor(equipImage);
        equipSlots = (ArrayList<ArrayList<Image>>) Slots.slotImages.clone();
        equipSlots.remove(0);
        equipSlots.remove(3);
        equipSlots.remove(3);
        extraSlots.add(new Image(Slots.slotTexture));
        extraSlots.add(new Image(Slots.slotTexture));
        extraSlots.add(new Image(Slots.slotTexture));
        extraSlots.add(new Image(Slots.slotTexture));
        extraSlots.get(0).setPosition(Inventory.backgroundImage.getX() + 50, Inventory.backgroundImage.getY() + 100 + Slots.slotImage.getHeight());
        extraSlots.get(1).setPosition(Inventory.backgroundImage.getX() + 50, Inventory.backgroundImage.getY() + 100);
        extraSlots.get(2).setPosition(Inventory.backgroundImage.getX() - 50 + Inventory.backgroundImage.getWidth() - Slots.slotImage.getWidth(), Inventory.backgroundImage.getY() + 100 + Slots.slotImage.getHeight());
        extraSlots.get(3).setPosition(Inventory.backgroundImage.getX() - 50 + Inventory.backgroundImage.getWidth() - Slots.slotImage.getWidth(), Inventory.backgroundImage.getY() + 100);

        swordLabel = new Label("Sword", new Label.LabelStyle(GUI.tiny_gray, GUI.tiny_gray.getColor()));
        helmetLabel = new Label("Helmet", new Label.LabelStyle(GUI.tiny_gray, GUI.tiny_gray.getColor()));
        shieldLabel = new Label("Shield", new Label.LabelStyle(GUI.tiny_gray, GUI.tiny_gray.getColor()));
        glovesLabel = new Label("Gloves", new Label.LabelStyle(GUI.tiny_gray, GUI.tiny_gray.getColor()));
        chestLabel = new Label("Chest", new Label.LabelStyle(GUI.tiny_gray, GUI.tiny_gray.getColor()));
        beltLabel = new Label("Belt", new Label.LabelStyle(GUI.tiny_gray, GUI.tiny_gray.getColor()));
        bootsLabel = new Label("Boots", new Label.LabelStyle(GUI.tiny_gray, GUI.tiny_gray.getColor()));
        legsLabel = new Label("Legs", new Label.LabelStyle(GUI.tiny_gray, GUI.tiny_gray.getColor()));
        capeLabel = new Label("Cape", new Label.LabelStyle(GUI.tiny_gray, GUI.tiny_gray.getColor()));
        potion1Label = new Label("Potion", new Label.LabelStyle(GUI.tiny_gray, GUI.tiny_gray.getColor()));
        potion2Label = new Label("Potion", new Label.LabelStyle(GUI.tiny_gray, GUI.tiny_gray.getColor()));
        amuletLabel = new Label("Amulet", new Label.LabelStyle(GUI.tiny_gray, GUI.tiny_gray.getColor()));
        ringLabel = new Label("Ring", new Label.LabelStyle(GUI.tiny_gray, GUI.tiny_gray.getColor()));

        equipLabel = new Label("Equipment", new Label.LabelStyle(GUI.large_white, GUI.large_white.getColor()));
        equipLabel.setPosition(-equipLabel.getWidth()/2, Inventory.backgroundImage.getY()+Inventory.backgroundImage.getHeight());
        equipDisplay.create();
    }

    public static void showEquips() {
        showEquips = true;
        Game.stageGUI.addActor(Inventory.backgroundImage);
        Border.show();
        equipDisplay.show();
        Game.stageGUI.addActor(equipLabel);
        for(int row = 0; row < equipSlots.size(); row++) {    //Fills equipSlots in rows starting at bottom left corner
            for(int col = 0; col < equipSlots.get(row).size(); col++) {
                Game.stageGUI.addActor(equipSlots.get(row).get(col));
                equipSlots.get(row).get(col).moveBy(Slots.slotImage.getWidth()/2, 0);
            }
        }
        Game.stageGUI.addActor(extraSlots.get(0));
        Game.stageGUI.addActor(extraSlots.get(1));
        Game.stageGUI.addActor(extraSlots.get(2));
        Game.stageGUI.addActor(extraSlots.get(3));
        //Show Items
        Vector2 topLeftPos = new Vector2(equipSlots.get(0).get(2).getX(), equipSlots.get(0).get(2).getY());
        int col = 0;
        int row = 0;
        for(int index = 0; index < 9; index++) {
            Items.showEquippedItems(index, topLeftPos.x + col*200, topLeftPos.y - row*200);
            col++;
            if(col > 2) {
                row++;
                col = 0;
            }
        }
        Items.showEquippedItems(9, extraSlots.get(0).getX(), extraSlots.get(0).getY());
        Items.showEquippedItems(10, extraSlots.get(1).getX(), extraSlots.get(1).getY());
        Items.showEquippedItems(11, extraSlots.get(2).getX(), extraSlots.get(2).getY());
        Items.showEquippedItems(12, extraSlots.get(3).getX(), extraSlots.get(3).getY());
        Vector2 startPoint = new Vector2(Inventory.backgroundImage.getX() + 300, Inventory.backgroundImage.getY() + 600);
        swordLabel.setPosition(startPoint.x + 10, startPoint.y - swordLabel.getHeight() - 4);
        helmetLabel.setPosition(swordLabel.getX() + 200, swordLabel.getY());
        shieldLabel.setPosition(swordLabel.getX() + 400, swordLabel.getY());
        glovesLabel.setPosition(swordLabel.getX(), swordLabel.getY() - 200);
        chestLabel.setPosition(glovesLabel.getX() + 200, glovesLabel.getY());
        beltLabel.setPosition(glovesLabel.getX() + 400, glovesLabel.getY());
        bootsLabel.setPosition(glovesLabel.getX(), glovesLabel.getY() - 200);
        legsLabel.setPosition(bootsLabel.getX() + 200, bootsLabel.getY());
        capeLabel.setPosition(bootsLabel.getX() + 400, bootsLabel.getY());
        potion1Label.setPosition(Inventory.backgroundImage.getX() + 50 + 10, Inventory.backgroundImage.getY() + 500 - potion1Label.getHeight() - 4);
        potion2Label.setPosition(potion1Label.getX(), potion1Label.getY() - 200);
        amuletLabel.setPosition(potion1Label.getX() + 900, potion1Label.getY());
        ringLabel.setPosition(amuletLabel.getX(), potion2Label.getY());
        Game.stageGUI.addActor(swordLabel);
        Game.stageGUI.addActor(helmetLabel);
        Game.stageGUI.addActor(shieldLabel);
        Game.stageGUI.addActor(glovesLabel);
        Game.stageGUI.addActor(chestLabel);
        Game.stageGUI.addActor(beltLabel);
        Game.stageGUI.addActor(bootsLabel);
        Game.stageGUI.addActor(legsLabel);
        Game.stageGUI.addActor(capeLabel);
        Game.stageGUI.addActor(potion1Label);
        Game.stageGUI.addActor(potion2Label);
        Game.stageGUI.addActor(amuletLabel);
        Game.stageGUI.addActor(ringLabel);
    }

    public static void removeEquips() {
        showEquips = false;
        Inventory.backgroundImage.remove();
        Border.remove();
        equipDisplay.remove();
        equipLabel.remove();
        for(int row = 0; row < equipSlots.size(); row++) {    //Fills equipSlots in rows starting at bottem left corner;
            for(int col = 0; col < equipSlots.get(row).size(); col++) {
                equipSlots.get(row).get(col).moveBy(-Slots.slotImage.getWidth()/2, 0);
                equipSlots.get(row).get(col).remove();
            }
        }
        for(Image e : extraSlots) {
            e.remove();
        }
        swordLabel.remove();
        helmetLabel.remove();
        shieldLabel.remove();
        glovesLabel.remove();
        chestLabel.remove();
        beltLabel.remove();
        bootsLabel.remove();
        legsLabel.remove();
        capeLabel.remove();
        potion1Label.remove();
        potion2Label.remove();
        amuletLabel.remove();
        ringLabel.remove();
        Items.removeEquippedItems();
        PlayerStats.removeStats();
    }
}