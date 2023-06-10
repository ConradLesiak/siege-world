package com.rgs.siegeworld.Player.Controls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.rgs.siegeworld.Game;
import com.rgs.siegeworld.Player.Body.Body;
import com.rgs.siegeworld.Player.Skills.Attack;

public class AttackJoystick {

    private Texture background;
    private Texture knob;
    private float width;
    private float height;
    public static Touchpad touchpad;
    private Vector2 touchCenter;
    private double touchDistance = 0; // Distance from touchCenter to knob
    private Vector2 knobPercent = new Vector2();
    private float touchAngle = 0;

    public void load() {
        background = new Texture(Game.assets.manager.get("Controls/Joystick/background.png", Texture.class).getTextureData());
        background.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        width = background.getWidth() * 1.4f;
        height = background.getHeight() * 1.4f;
        knob = new Texture(Game.assets.manager.get("Controls/Joystick/knob.png", Texture.class).getTextureData());
        knob.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    public void create() {
        touchpad = new Touchpad(1, new Touchpad.TouchpadStyle(new TextureRegionDrawable(new TextureRegion(background)), new TextureRegionDrawable(new TextureRegion(knob))));
        touchpad.setBounds(Game.stageGUI.getWidth()/2 - width - 120, -Game.stageGUI.getHeight()/2 + 110, width, height);
        touchpad.setColor(1, 1, 1, .7f);
        touchCenter = new Vector2(touchpad.getWidth()/2, touchpad.getWidth()/2);
    }

    public void show() {
        Game.stageGUI.addActor(touchpad);
    }

    public void render() {
        knobPercent.set(touchpad.getKnobPercentX(), touchpad.getKnobPercentY());
        touchAngle = knobPercent.angle();       //Angle of center to knob 0-360
        touchDistance = Math.hypot(touchCenter.x-touchpad.getKnobX(), touchCenter.y-touchpad.getKnobY()); //Distance from center of touchpad to knob.

        if(touchDistance > 80) {
            Attack.attack = true;

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
}