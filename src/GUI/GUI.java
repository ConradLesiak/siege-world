package com.rgs.siegeworld.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.rgs.siegeworld.GUI.Menus.Menus;
import com.rgs.siegeworld.Game;
import com.rgs.siegeworld.World.World;

import java.util.ArrayList;

public class GUI {

    FreeTypeFontGenerator fontGenerator;
    FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;
    public static BitmapFont large_white;
    public static Label.LabelStyle largeWhiteStyle;
    public static BitmapFont small_white;
    public static Label.LabelStyle smallWhiteStyle;
    public static BitmapFont small_gray;
    public static Label.LabelStyle smallGrayStyle;
    public static BitmapFont tiny_gray;
    public static BitmapFont grayItem; //Item lvl 1
    public static BitmapFont blueItem; //Item lvl 2
    public static BitmapFont yellowItem; //Item lvl 3
    public static BitmapFont whiteItem; //Item lvl 4
    public static ArrayList<BitmapFont> itemFonts = new ArrayList<BitmapFont>();
    public static ArrayList<Label.LabelStyle> itemFontStyles = new ArrayList<Label.LabelStyle>();

    Label showCoords;
    Menus menus = new Menus();

    public void load() {
        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/arialbd.ttf"));
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = 80; // large_white size in pixels
        fontParameter.color = Color.WHITE;
        fontParameter.borderWidth = 4;
        fontParameter.borderColor = Color.BLACK;
        fontParameter.minFilter = Texture.TextureFilter.Linear;
        fontParameter.magFilter = Texture.TextureFilter.Linear;
        large_white = fontGenerator.generateFont(fontParameter);
        largeWhiteStyle = new Label.LabelStyle(large_white, large_white.getColor());
        fontParameter.size = 50;
        fontParameter.borderWidth = 2;
        small_white = fontGenerator.generateFont(fontParameter);
        smallWhiteStyle = new Label.LabelStyle(small_white, small_white.getColor());
        fontParameter.color = Color.GRAY;
        small_gray = fontGenerator.generateFont(fontParameter);
        smallGrayStyle = new Label.LabelStyle(small_gray, small_gray.getColor());
        fontParameter.color = Color.LIGHT_GRAY;
        fontParameter.size = 40;
        fontParameter.borderColor = Color.BLACK;
        fontParameter.borderWidth = 1;
        fontParameter.shadowColor = Color.BLACK;
        fontParameter.shadowOffsetX = -3;
        fontParameter.shadowOffsetY = -3;
        tiny_gray = fontGenerator.generateFont(fontParameter);
        fontParameter.size = 50;
        fontParameter.shadowOffsetX = -3;
        fontParameter.shadowOffsetY = 3;
        fontParameter.color = new Color(180/255f, 180/255f, 190/255f, 1f);
        grayItem = fontGenerator.generateFont(fontParameter);
        itemFonts.add(grayItem);
        itemFontStyles.add(new Label.LabelStyle(grayItem, grayItem.getColor()));
        fontParameter.color = new Color(102/255f, 102/255f, 255/255f, 1f);
        blueItem = fontGenerator.generateFont(fontParameter);
        itemFonts.add(blueItem);
        itemFontStyles.add(new Label.LabelStyle(blueItem, blueItem.getColor()));
        fontParameter.color = new Color(255/255f, 220/255f, 77/255f, 1f);
        yellowItem = fontGenerator.generateFont(fontParameter);
        itemFonts.add(yellowItem);
        itemFontStyles.add(new Label.LabelStyle(yellowItem, yellowItem.getColor()));
        fontParameter.color = Color.WHITE;
        fontParameter.shadowColor = Color.CYAN;
        whiteItem = fontGenerator.generateFont(fontParameter);
        itemFonts.add(whiteItem);
        itemFontStyles.add(new Label.LabelStyle(whiteItem, whiteItem.getColor()));
        //TODO ** Add Level 4 Item Font **
        fontGenerator.dispose();
        menus.load();
    }

    public void create() {
        showCoords = new Label(World.tilePosX + ", " + World.tilePosY, new Label.LabelStyle(large_white, large_white.getColor()));
        showCoords.setPosition(-showCoords.getWidth()/2, -Game.stageGUI.getHeight()/2 + showCoords.getHeight()/2);
        Game.stageGUI.addActor(showCoords);

        menus.create();
    }

    public void show() {

    }

    public void render() {
        showCoords.setText(World.tilePosX + ", " + World.tilePosY);
        showCoords.setPosition(-showCoords.getWidth()/2, -Game.stageGUI.getHeight()/2 + showCoords.getHeight()/2);

        menus.render();
    }
}