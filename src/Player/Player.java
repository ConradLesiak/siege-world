package com.rgs.siegeworld.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.rgs.siegeworld.Game;
import com.rgs.siegeworld.Items.EquippedItems.EquippedItems;
import com.rgs.siegeworld.Items.EquippedItems.Weapon;
import com.rgs.siegeworld.Items.Items;
import com.rgs.siegeworld.Player.Body.Arms;
import com.rgs.siegeworld.Player.Body.Belt;
import com.rgs.siegeworld.Player.Body.Body;
import com.rgs.siegeworld.Player.Body.Chest;
import com.rgs.siegeworld.Player.Body.Legs;
import com.rgs.siegeworld.Player.Controls.Controls;
import com.rgs.siegeworld.Player.Skills.Attack;
import com.rgs.siegeworld.Player.Stats.PlayerStats;
import com.rgs.siegeworld.SaveData;

public class Player {

    public static Vector2 playerPos; //Very Bottom Middle Of Player
    public static float moveX;
    public static float moveY;
    public static int moveSpeed = 300;
    public static Rectangle collisionBounds;
    public static Circle pickUpRadius;
    public static PlayerStats playerStats = new PlayerStats();
    public static Body body = new Body();
    public static Items items = new Items();
    public static EquippedItems equippedItems = new EquippedItems();
    public static Controls controls = new Controls();
    public static Attack attack = new Attack();

    public void load() {
        playerStats.load();
        body.load();
        items.load();
        controls.load();
    }

    public void create() {  //Called after all game assets are loaded. Must be called before any other player methods.
        playerPos = new Vector2(SaveData.prefs.getFloat("playerX", 60 * 300), SaveData.prefs.getFloat("playerY", 55 * 300));
        collisionBounds = new Rectangle(playerPos.x - 84, playerPos.y-30, 168, 60);
        pickUpRadius = new Circle(playerPos.x, playerPos.y + 30, 60);
        playerStats.create();
        body.create();
        equippedItems.create();
        items.create();
        controls.create();
        attack.create();
    }

    public static void show() {     //Draws player on the world stage.
        body.show();
        controls.show();
    }

    public void render() {  //Called in the game loop to continuously update player once the player create method has been called.
        body.render();
        equippedItems.render();
        items.render();
        controls.render();
        attack.render();

        collisionBounds.set(playerPos.x - collisionBounds.getWidth()/2, playerPos.y-collisionBounds.getHeight()/2,
                collisionBounds.getWidth(), collisionBounds.getHeight());
        pickUpRadius.set(playerPos.x, playerPos.y + pickUpRadius.radius/2, pickUpRadius.radius);
        if(Body.frameY == 1) {
            Legs.legsImage.toFront();
            Arms.armsImage.toFront();
            Chest.chestImage.toFront();
            Belt.beltImage.toFront();
        } else {
            Chest.chestImage.toFront();
            Belt.beltImage.toFront();
            Arms.armsImage.toFront();
            Weapon.weaponImage.toFront();
            Arms.handsImage.toFront();
        }

        //Move Player
        playerPos.x += (float)moveX * Gdx.graphics.getDeltaTime();
        playerPos.y += (float)moveY * Gdx.graphics.getDeltaTime();
        playerPos.set(Math.round(playerPos.x), Math.round(playerPos.y));

        //Move Camera
        Game.stage.getViewport().getCamera().position.set(playerPos.x , playerPos.y + 100, 1);

        //Save World Position
        SaveData.prefs.putFloat("playerX", playerPos.x);
        SaveData.prefs.putFloat("playerY", playerPos.y);
        SaveData.prefs.flush();
    }
}