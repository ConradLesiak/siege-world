package com.rgs.siegeworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Json;
import com.rgs.siegeworld.Player.Player;

public class SaveData {

    public static Preferences prefs;
    public static Json json = new Json();

    public static boolean assetsCreated = false;

    //Unused notation for savadata stored inside preferences
    public static boolean loggedIn = false;
    public static String name;
    public static int hairIndex;
    public static int eyesIndex;
    public static int faceIndex;
    public static int playerX;
    public static int playerY;
    public static String inventoryItems;
    public static String equippedItems;
    public static int gold;
    public static int diamonds;

    CreateCharacter createCharacter = new CreateCharacter();

    public void create() {  //Called at end of SplashScreen
        prefs = Gdx.app.getPreferences("siege_world_save");
        Game.player.create();
        Game.gui.create();
        assetsCreated = true;
        if(prefs.getBoolean("loggedIn", false)) {
            System.out.println("Start Game!");
            Player.show();
        } else {
            createCharacter.create();
        }
    }
}
