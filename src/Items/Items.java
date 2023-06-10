package com.rgs.siegeworld.Items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.rgs.siegeworld.GUI.Menus.Equipment.EquipDisplay;
import com.rgs.siegeworld.GUI.Menus.ForgeMenu.ForgeMenu;
import com.rgs.siegeworld.GUI.Menus.ForgeMenu.UpgradeTab;
import com.rgs.siegeworld.GUI.Menus.Inventory.Inventory;
import com.rgs.siegeworld.GUI.Menus.Inventory.ItemDisplay;
import com.rgs.siegeworld.GUI.Menus.Inventory.Slots;
import com.rgs.siegeworld.Game;
import com.rgs.siegeworld.Items.EquippedItems.Weapon;
import com.rgs.siegeworld.Items.Weapons.Staffs.ShortStaff;
import com.rgs.siegeworld.Items.Weapons.Swords.ShortSword;
import com.rgs.siegeworld.Player.Player;
import com.rgs.siegeworld.Player.Stats.PlayerStats;
import com.rgs.siegeworld.SaveData;

import java.util.ArrayList;

public class Items {

    public static Item[] items = new Item[18];
    public static Item[] equippedItems = new Item[13];
    public static ArrayList<Item> droppedItems = new ArrayList<Item>();

    public static Texture selectTexture;
    public static Image selectImage;

    public static ShortSword shortSword = new ShortSword(true);
    public static ShortStaff shortStaff = new ShortStaff(true);

    //Load assets
    public static void load() {
        selectTexture = new Texture(Game.assets.manager.get("Items/select.png", Texture.class).getTextureData());
        selectTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        selectImage = new Image(selectTexture);
        shortSword.load();
        shortStaff.load();
    }

    //Create static assets.
    public static void create() {
        selectImage.addListener( new InputListener() {
            Vector2 startPos;
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                startPos = new Vector2(getSelected().getImage().getX(), getSelected().getImage().getY());
                return true;
            }
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                if(Inventory.showInventory) {
                    getSelected().setPosition(event.getStageX() - getSelected().getImage().getWidth() / 2, event.getStageY());
                    getSelected().getImage().toFront();
                }
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if(Inventory.showInventory) {
                    Vector2 topLeftPos = new Vector2(Inventory.backgroundImage.getX(), Inventory.backgroundImage.getY() + Slots.slotImage.getHeight() * 2);
                    int col = 0;
                    int row = 0;
                    int selectedIndex = 0;
                    for (int index = 0; index < 18; index++) {
                        if (getSelected() == items[index]) {
                            selectedIndex = index;
                        }
                    }
                    for (int index = 0; index < 18; index++) {
                        if (getSelected().getBounds().contains(topLeftPos.x + col * 200 + selectImage.getWidth() / 2, topLeftPos.y - row * 200 + selectImage.getHeight() / 2)) {
                            Item temp = getSelected();
                            items[selectedIndex] = items[index];
                            items[index] = temp;
                            Inventory.removeInventory();
                            Inventory.showInventory();
                        }
                        col++;
                        if (col > 5) {
                            row++;
                            col = 0;
                        }
                    }
                    if (!new Rectangle(Inventory.backgroundImage.getX(), Inventory.backgroundImage.getY(), selectImage.getWidth() * 6, selectImage.getHeight() * 3).contains(getSelected().getImage().getX() + 100, getSelected().getImage().getY() + 100)) {
                        getSelected().setPosition(startPos.x, startPos.y);
                    }
                }
                getSelected().deselect();
                if(EquipDisplay.showDisplay) {
                    EquipDisplay.removeStats();
                } else if(ItemDisplay.showDisplay) {
                    ItemDisplay.removeStats();
                }
                if(!UpgradeTab.showUpgradeTab) {
                    PlayerStats.showStats();
                }
            }
        });
        if(SaveData.prefs.getBoolean("loggedIn")) { //Load previous inventory
            items = SaveData.json.fromJson(items.getClass(), SaveData.prefs.getString("inventoryItems"));
            equippedItems = SaveData.json.fromJson(equippedItems.getClass(), SaveData.prefs.getString("equippedItems"));
        }
        for(int i = 0; i < items.length; i++) {     //Fills initial inventory with blank items
            if(items[i] == null) {
                items[i] = new Item();
            }
        }
        for(int i = 0; i < equippedItems.length; i++) { //Fills initial equips with blank items
            if(equippedItems[i] == null) {
                equippedItems[i] = new Item();
            } else {    //Fills equips with saved equippedItems if already loggedIn
                if(equippedItems[i].getType() != null) {
                    if(equippedItems[i].getType().equals("weapon")) {
                        Weapon.addWeapon(equippedItems[i]);
                    }
                }
            }
        }
        //Test Items for Inventory
        if(!SaveData.prefs.getBoolean("loggedIn")) {
            addItem(new ShortSword());
            addItem(new ShortSword());
            addItem(new ShortSword());
            addItem(new ShortStaff());
            addItem(new ShortStaff());
            addItem(new ShortStaff());
        }
    }

    public void render() {
        //Save Inventory and Equipment Items
        SaveData.prefs.putString("inventoryItems", SaveData.json.prettyPrint(items));
        SaveData.prefs.putString("equippedItems", SaveData.json.prettyPrint(equippedItems));
        SaveData.prefs.flush();
        //Render all Dropped Items behind Player
        for(int i = 0; i < Items.droppedItems.size(); i++) {
            Items.droppedItems.get(i).getImage().toBack();
        }
    }

    //Add Item to Inventory
    public static void addItem(Item item) {
        for(int i = 0; i < items.length; i++) {
            if(items[i].getType() == null) {
                items[i] = item;
                return;
            }
        }
    }

    /*  Searches through Inventory and Equipment and returns the selected item.
    Return null if no item is selected.*/
    public static Item getSelected() {
        for(int i = 0; i < items.length; i++) {
            if(items[i].isSelected()) {
                return items[i];
            }
        }
        for(int i = 0; i < equippedItems.length; i++) {
            if(equippedItems[i].isSelected()) {
                return equippedItems[i];
            }
        }
        return null;
    }

    //Show items in Inventory menu.
    public static void showItems(int index, float xPos, float yPos) {
        items[index].getImage().setPosition(xPos, yPos);
        items[index].show();
    }

    //Remove items from Inventory menu display.
    public static void removeItems() {
        for(int i = 0; i < items.length; i++) {
            items[i].remove();
        }
        selectImage.remove();
    }

    //Show items in Equipment menu.
    public static void showEquippedItems(int index, float xPos, float yPos) {
        equippedItems[index].getImage().setPosition(xPos, yPos);
        equippedItems[index].show();
    }

    //Remove items in Equipment menu.
    public static void removeEquippedItems() {
        for(int i = 0; i < equippedItems.length; i++) {
            equippedItems[i].remove();
        }
        selectImage.remove();
    }

    //Move item from inventory to equipment.
    public static void equipItem(Item item) {
        for(int i = 0; i < items.length; i++) {
            if(item != null && item.isSelected() && items[i].equals(item) ) {
                int e = 0;
                if(item.getType().equals("weapon"))
                    e = 0;
                if(item.getType().equals("helmet"))
                    e = 1;
                if(item.getType().equals("shield"))
                    e = 2;
                if(item.getType().equals("gloves"))
                    e = 3;
                if(item.getType().equals("chest"))
                    e = 4;
                if(item.getType().equals("belt"))
                    e = 5;
                if(item.getType().equals("boots"))
                    e = 6;
                if(item.getType().equals("legs"))
                    e = 7;
                if(item.getType().equals("cape"))
                    e = 8;
                if(item.getType().equals("potion1"))
                    e = 9;
                if(item.getType().equals("potion2"))
                    e = 10;
                if(item.getType().equals("amulet"))
                    e = 11;
                if(item.getType().equals("ring"))
                    e = 12;
                if(equippedItems[e].getType() != null) {
                    items[i].remove();
                    Item temp = equippedItems[e];
                    equippedItems[e].deselect();
                    equippedItems[e] = item;
                    items[i] = temp;
                    items[i].getImage().setPosition(equippedItems[e].getImage().getX(), equippedItems[e].getImage().getY());
                    items[i].show();
                    items[i].deselect();
                    if(item.getType().equals("weapon")) {
                        Weapon.removeWeapon();
                        Weapon.addWeapon(equippedItems[e]);
                    }
                    System.out.println("switch equip");
                } else {
                    equippedItems[e] = item;
                    item.deselect();
                    item.remove();
                    items[i] = new Item();
                    System.out.println("add equip");
                    if(equippedItems[e].getType().equals("weapon")) {
                        Weapon.addWeapon(equippedItems[e]);
                    }
                }
            }
        }
    }

    //Move item from equipment to inventory.
    public static void unEquipItem(Item item) {
        for(int i = 0; i < items.length; i++) {
            if(items[i].getType() == null && item.isSelected()) {
                items[i] = item;
                item.deselect();
                item.remove();
                if(item.getType().equals("weapon")) {
                    Weapon.removeWeapon();
                    equippedItems[0] = new Item();
                }
                return;
            }
        }
        System.out.println("remove equip");
    }

    public static void dropItem(Item item) {
        for(int i = 0; i < items.length; i++) {
            if(items[i] == item) {
                items[i] = new Item();
            }
        }
        item.deselect();
        item.getImage().setSize(item.getImage().getWidth()*0.7f, item.getImage().getHeight()*0.7f);
        item.getImage().setPosition(Player.playerPos.x - item.getImage().getWidth()/2, Player.playerPos.y - item.getImage().getHeight()/2);
        Game.stage.addActor(item.getImage());
        droppedItems.add(0, item);
    }
}