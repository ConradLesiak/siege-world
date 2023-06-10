package com.rgs.siegeworld.Player.Body;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.rgs.siegeworld.Player.Controls.Controls;
import com.rgs.siegeworld.Player.Controls.MoveJoystick;
import com.rgs.siegeworld.SaveData;

public class Body {

    private long time = System.currentTimeMillis();
    public static long startTime = 0;
    public static boolean isWalking = false;
    public static int frameX = 0;
    public static int frameY = 0;
    public static int frameLength = 300;

    //Aura aura = new Aura();
    Head head = new Head();
    //Helmet helmet = new Helmet();
    Arms arms = new Arms();
    Legs legs = new Legs();
    Boots boots = new Boots();
    Chest chest = new Chest();
    Belt belt = new Belt();
    //Weapon weapon = new Weapon();

    public void load() {
        head.load();
        arms.load();
        legs.load();
        boots.load();
        chest.load();
        belt.load();
    }

    public void create() {
        arms.create();
        head.create();
        legs.create();
        boots.create();
        chest.create();
        belt.create();
    }

    public void show() {
        arms.show();
        head.show();
        legs.show();
        boots.show();
        chest.show();
        belt.show();
    }

    public void render() {
        arms.render();
        head.render();
        legs.render();
        boots.render();
        chest.render();
        belt.render();

        //Animation for keyboard controls.
        if(Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.A)){
            isWalking = true;
        }else if(MoveJoystick.touchpad.isTouched()){ //Animation for touchpad controls.
            isWalking = true;
        }else{
            frameX = 0;       //Reset legs sprite when not walking.
            isWalking = false;
        }

        //Animation by changing spritesheet column.
        time = System.currentTimeMillis() - startTime;
        if(Body.isWalking && time > frameLength) {
            if(frameX < 2 ){
                frameX++;
            }else {
                frameX = 1;
            }
            startTime = System.currentTimeMillis();
        }
    }
}
