package com.rgs.siegeworld.GUI.Menus.PlayerInfo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.rgs.siegeworld.GUI.GUI;
import com.rgs.siegeworld.Game;
import com.rgs.siegeworld.Player.Stats.PlayerStats;
import com.rgs.siegeworld.SaveData;

public class PlayerInfo {

    public static Image backgroundImage;
    public static Texture iconsTexture;
    public static Image iconsImage;
    public static Image healthBar;
    public static Image manaBar;
    public static Image expBar;
    public static Label levelLabel;
    public static Label goldLabel;
    public static Label diamondLabel;

    public void load() {
        backgroundImage = new Image(Game.assets.manager.get("Menus/PlayerInfo/background.png", Texture.class));
        iconsTexture = new Texture(Game.assets.manager.get("Menus/PlayerInfo/icons.png", Texture.class).getTextureData());
        iconsTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        iconsImage = new Image(iconsTexture);
        healthBar = new Image(Game.assets.manager.get("Menus/PlayerInfo/health.png", Texture.class));
        manaBar = new Image(Game.assets.manager.get("Menus/PlayerInfo/mana.png", Texture.class));
        expBar = new Image(Game.assets.manager.get("Menus/PlayerInfo/exp.png", Texture.class));
    }

    public void create() {
        backgroundImage.setPosition(-Game.stageGUI.getWidth()/2, Game.stageGUI.getHeight()/2 - backgroundImage.getHeight());
        Game.stageGUI.addActor(backgroundImage);
        iconsImage.setPosition(-Game.width/2, Game.height/2 - iconsImage.getHeight());
        Game.stageGUI.addActor(iconsImage);
        healthBar.setPosition(-Game.stageGUI.getWidth()/2 + 150, Game.stageGUI.getHeight()/2 - 45);
        Game.stageGUI.addActor(healthBar);
        manaBar.setPosition(-Game.stageGUI.getWidth()/2 + 150, Game.stageGUI.getHeight()/2 - 90);
        Game.stageGUI.addActor(manaBar);
        expBar.setPosition(-Game.stageGUI.getWidth()/2 + 10, Game.stageGUI.getHeight()/2 - 115);
        Game.stageGUI.addActor(expBar);
        levelLabel = new Label(Integer.toString(PlayerStats.level) ,new Label.LabelStyle(GUI.large_white, GUI.large_white.getColor()));
        levelLabel.setPosition(-Game.stageGUI.getWidth()/2 + 75 - levelLabel.getWidth()/2, Game.stageGUI.getHeight()/2 - 95);
        Game.stageGUI.addActor(levelLabel);
        goldLabel = new Label(Integer.toString(SaveData.prefs.getInteger("gold")), new Label.LabelStyle(GUI.small_white, GUI.small_white.getColor()));
        goldLabel.setPosition(-Game.width/2 - goldLabel.getWidth() + 380, Game.height/2 - goldLabel.getHeight() - 122);
        Game.stageGUI.addActor(goldLabel);
        diamondLabel = new Label(Integer.toString(SaveData.prefs.getInteger("diamonds")), new Label.LabelStyle(GUI.small_white, GUI.small_white.getColor()));
        diamondLabel.setPosition(-Game.width/2 - diamondLabel.getWidth() + 380, Game.height/2 - diamondLabel.getHeight() - 182);
        Game.stageGUI.addActor(diamondLabel);
    }

    public void render() {

    }
}