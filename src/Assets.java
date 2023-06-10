package com.rgs.siegeworld;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class Assets {

    public AssetManager manager = new AssetManager();
    public static boolean loaded = false;

    public void load() {
        //Player
        manager.load("Player/Head/face1_white.png", Texture.class);
        manager.load("Player/Head/face1_tan.png", Texture.class);
        manager.load("Player/Head/eyes_blue.png", Texture.class);
        manager.load("Player/Head/eyes_brown.png", Texture.class);
        manager.load("Player/Head/eyes_green.png", Texture.class);
        manager.load("Player/Head/hair1_brown.png", Texture.class);
        manager.load("Player/Head/hair1_black.png", Texture.class);
        manager.load("Player/Head/hair1_light_brown.png", Texture.class);
        manager.load("Player/Head/hair1_blonde.png", Texture.class);
        manager.load("Player/Arms/arms_white.png", Texture.class);
        manager.load("Player/Arms/arms_tan.png", Texture.class);
        manager.load("Player/Arms/hands_white.png", Texture.class);
        manager.load("Player/Arms/hands_tan.png", Texture.class);
        manager.load("Player/Aura/shadow.png", Texture.class);
        manager.load("Player/Belt/starter_belt.png", Texture.class);
        manager.load("Player/Boots/starter_boots.png", Texture.class);
        manager.load("Player/Chest/starter_chest.png", Texture.class);
        manager.load("Player/Legs/starter_legs.png", Texture.class);

        //Controls
        manager.load("Controls/Joystick/background.png", Texture.class);
        manager.load("Controls/Joystick/knob.png", Texture.class);
        manager.load("Controls/Zoom/background.png", Texture.class);
        manager.load("Controls/Zoom/slider.png", Texture.class);
        manager.load("Controls/hand_open.png", Texture.class);
        manager.load("Controls/hand_closed.png", Texture.class);

        //Menus
        manager.load("Menus/Settings/background.png", Texture.class);
        manager.load("Menus/Settings/settings_icon.png", Texture.class);
        manager.load("Menus/CreateCharacter/background.png", Texture.class);
        manager.load("Menus/CreateCharacter/arrow.png", Texture.class);
        manager.load("Menus/Equipment/equip_icon.png", Texture.class);
        manager.load("Menus/Inventory/background.png", Texture.class);
        manager.load("Menus/Inventory/border.png", Texture.class);
        manager.load("Menus/Inventory/exit.png", Texture.class);
        manager.load("Menus/Inventory/inventory.png", Texture.class);
        manager.load("Menus/Inventory/slot.png", Texture.class);
        manager.load("Menus/PlayerInfo/background.png", Texture.class);
        manager.load("Menus/PlayerInfo/icons.png", Texture.class);
        manager.load("Menus/PlayerInfo/exp.png", Texture.class);
        manager.load("Menus/PlayerInfo/health.png", Texture.class);
        manager.load("Menus/PlayerInfo/health.png", Texture.class);
        manager.load("Menus/PlayerInfo/mana.png", Texture.class);
        manager.load("Menus/Skills/skills_icon.png", Texture.class);
        manager.load("Menus/Forge/forge_icon.png", Texture.class);

        //Items
        manager.load("Items/select.png", Texture.class);
        manager.load("Items/Weapons/Staffs/ShortStaff/short_staff.png", Texture.class);
        manager.load("Items/Weapons/Staffs/ShortStaff/short_staff_icon.png", Texture.class);
        manager.load("Items/Weapons/Swords/ShortSword/short_sword.png", Texture.class);
        manager.load("Items/Weapons/Swords/ShortSword/short_sword_icon.png", Texture.class);
    }

    public void dispose() {
        manager.dispose();
    }
}
