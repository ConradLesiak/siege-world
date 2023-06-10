package com.rgs.siegeworld.Player.Controls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.particles.ResourceData;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.rgs.siegeworld.Game;
import com.rgs.siegeworld.Player.Body.Body;
import com.rgs.siegeworld.Player.Player;
import com.rgs.siegeworld.SaveData;

public class MoveJoystick {

    private Texture background;
    private Texture knob;
    private int width;
    private int height;
    public static Touchpad touchpad;
    private Vector2 touchCenter;
    private double touchDistance = 0; // Distance from touchCenter to knob
    private Vector2 knobPercent = new Vector2();
    private float touchAngle = 0;

    public void load() {
        background = Game.assets.manager.get("Controls/Joystick/background.png", Texture.class);
        background.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        width = background.getWidth();
        height = background.getHeight();
        knob = Game.assets.manager.get("Controls/Joystick/knob.png", Texture.class);
        knob.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    public void create() {
        touchpad = new Touchpad(1, new Touchpad.TouchpadStyle(new TextureRegionDrawable(new TextureRegion(background)), new TextureRegionDrawable(new TextureRegion(knob))));
        touchpad.setBounds(-com.rgs.siegeworld.Game.stageGUI.getWidth()/2 + 120, -com.rgs.siegeworld.Game.stageGUI.getHeight()/2 + 110, width*1.4f, height*1.4f);
        touchpad.setColor(1, 1, 1, .7f);
        touchCenter = new Vector2(touchpad.getWidth()/2, touchpad.getWidth()/2);
    }

    public void show() {
        Game.stageGUI.addActor(touchpad);
    }

    public void render() {
        //---------------------------//KEYBOARD MOVEMENT//---------------------------//
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            Player.moveY = -Player.moveSpeed;
            //Player.playerPos.set(Player.playerPos.x, Player.playerPos.y + Player.moveY);
            Body.frameY = 0;
        } else if(Gdx.input.isKeyPressed(Input.Keys.W)){
            Player.moveY = Player.moveSpeed;
            //Player.playerPos.set(Player.playerPos.x, Player.playerPos.y + Player.moveY);
            Body.frameY = 1;
        } else {
            Player.moveY = 0;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            Player.moveX = Player.moveSpeed;
            //Player.playerPos.set(Player.playerPos.x + Player.moveX, Player.playerPos.y);
            Body.frameY = 2;
        } else if(Gdx.input.isKeyPressed(Input.Keys.A)){
            Player.moveX = -Player.moveSpeed;
            //Player.playerPos.set(Player.playerPos.x + Player.moveX, Player.playerPos.y);
            Body.frameY = 3;
        } else {
            Player.moveX = 0;
        }
        //---------------------------------------------------------------------------------//

        //-------------------------------//TOUCHPAD MOVEMENT//-----------------------------//
        touchDistance = Math.hypot(touchCenter.x-touchpad.getKnobX(), touchCenter.y-touchpad.getKnobY()); //Distance from center of touchpad to knob.

        //Player Movement
        if(touchpad.isTouched()){ // TODO Add minimum speed (Correctly)
            Player.moveX = Player.moveSpeed * touchpad.getKnobPercentX();
            Player.moveY = Player.moveSpeed * touchpad.getKnobPercentY();
        }

        //Change direction
        knobPercent.set(touchpad.getKnobPercentX(), touchpad.getKnobPercentY());
        touchAngle = knobPercent.angle();
        if(touchAngle > 225 && touchAngle < 315) {  //DOWN
            Body.frameY = 0;
        }
        if(touchAngle > 45 && touchAngle < 135) {  //UP
            Body.frameY = 1;
        }
        if(touchAngle > 315 || touchAngle < 45 && touchAngle != 0) {  //RIGHT
            Body.frameY = 2;
        }
        if(touchAngle > 135 && touchAngle < 225) {  //LEFT
            Body.frameY = 3;
        }
        //--------------------------------------------------------------------------------//
    }
}