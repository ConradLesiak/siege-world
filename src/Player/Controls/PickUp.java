package com.rgs.siegeworld.Player.Controls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.rgs.siegeworld.GUI.Menus.Inventory.Inventory;
import com.rgs.siegeworld.Game;
import com.rgs.siegeworld.Items.Items;
import com.rgs.siegeworld.Player.Player;
import com.rgs.siegeworld.Player.Stats.PlayerStats;

public class PickUp {

    public static Texture handTexture;
    public static Image handImage;
    public static Texture fistTexture;
    public static Image fistImage;
    public static boolean handClosed = false;
    public static long startTime;

    public void load() {
        handTexture = Game.assets.manager.get("Controls/hand_open.png", Texture.class);
        handTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        handImage = new Image(handTexture);
        fistTexture = Game.assets.manager.get("Controls/hand_closed.png", Texture.class);
        fistTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        fistImage = new Image(fistTexture);
    }

    public void create() {
        handImage.setPosition(Game.width/2 - handImage.getWidth() - 100, Game.height/2 - 450);
        handImage.setColor(1, 1, 1, .7f);
        handImage.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                handImage.remove();
                Game.stageGUI.addActor(fistImage);
                startTime = System.currentTimeMillis();
                handClosed = true;
            }
        });
        fistImage.setPosition(handImage.getX(), handImage.getY());
        fistImage.setColor(1, 1, 1, .7f);
    }

    public void show() {
        Game.stageGUI.addActor(handImage);
    }

    public void render() {
        //Only draw hand when standing over a dropped item
        handImage.setVisible(false);
        for (int i = 0; i < Items.droppedItems.size(); i++) {
            if (Intersector.overlaps(Player.pickUpRadius, Items.droppedItems.get(i).getBounds())) {
                handImage.setVisible(true);
            }
        }
        //Close hand for grabbing animation
        if(handClosed) {
            long time = System.currentTimeMillis() - startTime;
            if(time > 200) {
                pickUp();
                fistImage.remove();
                Game.stageGUI.addActor(handImage);
                handClosed = false;
            }
        }
        //Keyboard input
        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            pickUp();
        }
        //Render all dropped items behind player
        for(int i = 0; i < Items.droppedItems.size(); i++) {
            Items.droppedItems.get(i).getImage().toBack();
        }
    }

    public void pickUp() {
        for (int i = 0; i < Items.droppedItems.size(); i++) {
            if (Intersector.overlaps(Player.pickUpRadius, Items.droppedItems.get(i).getBounds())) {
                Items.droppedItems.get(i).remove();
                Items.droppedItems.get(i).getImage().setSize(Items.droppedItems.get(i).getImage().getWidth()/0.7f, Items.droppedItems.get(i).getImage().getHeight()/0.7f);
                Items.addItem(Items.droppedItems.get(i));
                Items.droppedItems.remove(i);
                if(Inventory.showInventory) {
                    Inventory.removeInventory();
                    Inventory.showInventory();
                    PlayerStats.showStats();
                }
                break;
            }
        }
    }
}
