package com.rgs.siegeworld.Player.Controls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.rgs.siegeworld.Game;

public class Zoom {

    public static float percent = 0;
    public static Texture background;
    public static Image backgroundImage;
    public static Texture slider;
    public static Image sliderImage;

    public void load() {
        background = new Texture(Game.assets.manager.get("Controls/Zoom/background.png", Texture.class).getTextureData());
        background.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        backgroundImage = new Image(background);
        slider = new Texture(Game.assets.manager.get("Controls/Zoom/slider.png", Texture.class).getTextureData());
        slider.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        sliderImage = new Image(slider);
    }

    public void create() {
        backgroundImage.setPosition(-com.rgs.siegeworld.Game.stageGUI.getWidth()/2 + 100, -50);
        Game.stageGUI.addActor(backgroundImage);
        sliderImage.setPosition(backgroundImage.getX() + backgroundImage.getWidth()/2 - sliderImage.getWidth()/2, backgroundImage.getImageY() + (backgroundImage.getHeight()*percent/100) - sliderImage.getHeight()/2);
        Game.stage.getViewport().setWorldSize((int)(1920 * (1 + (percent)/100) ), (int)(1440 * (1 + (percent)/100) ));
        sliderImage.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                sliderImage.setPosition(sliderImage.getX(), sliderImage.getY() - sliderImage.getHeight()/2 + y);
                if(percent > 1 && percent < 99){
                    Game.stage.getViewport().setWorldSize((int)(1920 * (1 + (percent)/100) ), (int)(1440 * (1 + (percent)/100) ));
                }
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            }
        });
    }

    public void show() {
        Game.stageGUI.addActor(sliderImage);
    }

    public void render() {
        percent = ( (sliderImage.getY() + sliderImage.getHeight()/4 - backgroundImage.getY()) / (backgroundImage.getY() + backgroundImage.getHeight()) ) * 100;

        //Block slider inside of background.
        if(sliderImage.getY() + sliderImage.getHeight()/2 < backgroundImage.getY() ) { //Bottom Border
            sliderImage.setPosition(sliderImage.getX(), backgroundImage.getY() - sliderImage.getHeight()/2 + 1);
        }//
        if(sliderImage.getY() + sliderImage.getHeight()/2 > backgroundImage.getY() + backgroundImage.getHeight()) { //Top Border
            sliderImage.setPosition(sliderImage.getX(), backgroundImage.getY() + backgroundImage.getHeight() - sliderImage.getHeight()/2 - 1);
        }

        //Zoom with arrow up/down.
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            if(Game.stage.getViewport().getWorldHeight() <= Game.stageGUI.getHeight()*2) {
                Game.stage.getViewport().setWorldSize((int)(Game.stage.getViewport().getWorldWidth()*1.1), (int)(Game.stage.getViewport().getWorldHeight()*1.1));
            }
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            if(Game.stage.getViewport().getWorldHeight() >= Game.stageGUI.getHeight()) {
                Game.stage.getViewport().setWorldSize((int)(Game.stage.getViewport().getWorldWidth()*.9), (int)(Game.stage.getViewport().getWorldHeight()*.9));
            }
        }
    }
}