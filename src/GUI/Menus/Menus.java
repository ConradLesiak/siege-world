package com.rgs.siegeworld.GUI.Menus;

import com.badlogic.gdx.math.Rectangle;
import com.rgs.siegeworld.GUI.Menus.Equipment.Equipment;
import com.rgs.siegeworld.GUI.Menus.ForgeMenu.ForgeMenu;
import com.rgs.siegeworld.GUI.Menus.Inventory.Inventory;
import com.rgs.siegeworld.GUI.Menus.Settings.Settings;
import com.rgs.siegeworld.GUI.Menus.PlayerInfo.PlayerInfo;
import com.rgs.siegeworld.GUI.Menus.SkillsMenu.SkillsMenu;

public class Menus {

    Settings settings = new Settings();
    PlayerInfo playerInfo = new PlayerInfo();
    Inventory inventory = new Inventory();
    Equipment equipment = new Equipment();
    SkillsMenu skillsMenu = new SkillsMenu();
    ForgeMenu forgeMenu = new ForgeMenu();
    public static Rectangle menuBounds;

    public void load() {
        settings.load();
        playerInfo.load();
        inventory.load();
        equipment.load();
        skillsMenu.load();
        forgeMenu.load();
    }

    public void create() {
        settings.create();
        playerInfo.create();
        inventory.create();
        equipment.create();
        skillsMenu.create();
        forgeMenu.create();

        menuBounds = new Rectangle(Inventory.backgroundBounds);
    }

    public void show() {

    }

    public void render() {
        settings.render();
        playerInfo.render();
        skillsMenu.render();
        forgeMenu.render();
    }
}