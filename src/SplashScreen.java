package com.rgs.siegeworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class SplashScreen {

    public static Image backgroundImage;
    FreeTypeFontGenerator fontGenerator;
    FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;
    public static BitmapFont xlarge_white;
    public static BitmapFont large_white;
    public static BitmapFont medium_white;
    public static Label siegeWorldLabel;
    public static Label radStudiosLabel;
    public static Image loadingBar;
    public static Label startGameLabel;
    public static float alpha = 0;
    public static long startTime = 0;
    public static long elapsedTime = 0;
    public static boolean showSplash = true;

    public void create() {
        backgroundImage = new Image(new Texture(Gdx.files.internal("SplashScreen/background.png")));
        backgroundImage.setBounds(-Game.width/2, -Game.height/2, Game.width, Game.height);
        Game.stageGUI.addActor(backgroundImage);

        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/arialbd.ttf"));
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = 110; // large_white size in pixels
        fontParameter.color = Color.WHITE;
        fontParameter.borderWidth = 4;
        fontParameter.borderColor = Color.BLACK;
        fontParameter.minFilter = Texture.TextureFilter.Linear;
        fontParameter.magFilter = Texture.TextureFilter.Linear;
        xlarge_white = fontGenerator.generateFont(fontParameter);
        fontParameter.size = 80;
        large_white = fontGenerator.generateFont(fontParameter);
        fontParameter.size = 60;
        medium_white = fontGenerator.generateFont(fontParameter);
        fontGenerator.dispose();

        siegeWorldLabel = new Label("Siege World", new Label.LabelStyle(xlarge_white, xlarge_white.getColor()));
        siegeWorldLabel.setPosition(-siegeWorldLabel.getWidth()/2, 400);
        Game.stageGUI.addActor(siegeWorldLabel);

        radStudiosLabel = new Label("Rad Game Studios", new Label.LabelStyle(large_white, large_white.getColor()));
        radStudiosLabel.setPosition(-radStudiosLabel.getWidth()/2, 100);
        //Game.stageGUI.addActor(radStudiosLabel);

        startGameLabel = new Label("Start Game", new Label.LabelStyle(medium_white, medium_white.getColor()));
        startGameLabel.setPosition(-startGameLabel.getWidth()/2, -150);
        startGameLabel.setColor(1, 1, 1, 0);
        Game.stageGUI.addActor(startGameLabel);

        loadingBar = new Image(new Texture(Gdx.files.internal("SplashScreen/loading_bar.png")));
        loadingBar.setBounds(-300, -550, 0, 20);
        Game.stageGUI.addActor(loadingBar);
        Game.assets.load();
    }

    public void render() {
        if(showSplash) {
            if (!Game.assets.manager.update()) { //Load Assets into game.
                loadingBar.setWidth(360 * Game.assets.manager.getProgress());
            } else if(!Assets.loaded) {
                Game.world.create();
                Game.player.load();
                Game.gui.load();
                startTime = System.currentTimeMillis();
                Assets.loaded = true;
            } else {
                loadingBar.setWidth(600);
                elapsedTime = System.currentTimeMillis() - startTime;
                if(elapsedTime < 2000) {
                    alpha += .01f;
                } else if(elapsedTime < 4000) {
                    alpha -= .01f;
                }
                if(elapsedTime > 4000) {
                    alpha = 0;
                    startTime = System.currentTimeMillis();
                    elapsedTime = 0;
                }
                startGameLabel.setColor(1, 1, 1, alpha);
                if (Gdx.input.justTouched()) {
                    remove();
                    Game.saveData.create();
                }
            }
        }
    }

    public static void remove() {
        backgroundImage.remove();
        siegeWorldLabel.remove();
        radStudiosLabel.remove();
        startGameLabel.remove();
        loadingBar.remove();
        showSplash = false;
    }
}
