package com.rgs.siegeworld.GUI.Menus.Settings;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.rgs.siegeworld.GUI.GUI;
import com.rgs.siegeworld.Game;

public class Settings {

    public static Texture settingsTexture;
    public static Image settingsImage;
    public static boolean settingsOpen = false;
    public static Image backgroundImage;
    public static Label settingsLabel;

    public void load() {
        settingsTexture = new Texture(Game.assets.manager.get("Menus/Settings/settings_icon.png", Texture.class).getTextureData());
        settingsTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        settingsImage = new Image(settingsTexture);
        backgroundImage = new Image(new Texture(Game.assets.manager.get("Menus/Settings/background.png", Texture.class).getTextureData()));
    }

    public void create() {
        settingsImage.setBounds(Game.stageGUI.getWidth()/2 - settingsImage.getWidth() - 40, Game.stageGUI.getHeight()/2 - settingsImage.getHeight() - 40, settingsImage.getWidth(), settingsImage.getHeight());
        settingsImage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(!settingsOpen) {
                    show();
                } else {
                    remove();
                }
            };
        });
        Game.stageGUI.addActor(settingsImage);
        backgroundImage.setBounds(-Game.stageGUI.getWidth()/2, -Game.stageGUI.getHeight()/2, Game.stageGUI.getWidth(), Game.stageGUI.getHeight());
        backgroundImage.setColor(0, 0, 0, .8f);
        settingsLabel = new Label("Settings", new Label.LabelStyle(GUI.large_white, GUI.large_white.getColor()));
        settingsLabel.setPosition(-settingsLabel.getWidth()/2, Game.stageGUI.getHeight()/2 - 200);
    }

    public static void show() {
        settingsOpen = true;
        Game.stageGUI.addActor(backgroundImage);
        Game.stageGUI.addActor(settingsLabel);
        settingsImage.toFront();
    }

    public static void remove() {
        settingsOpen = false;
        Game.stageGUI.addActor(settingsImage);
        backgroundImage.remove();
        settingsLabel.remove();
    }

    public void render() {

    }
}