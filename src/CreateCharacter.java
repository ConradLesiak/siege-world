package com.rgs.siegeworld;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.rgs.siegeworld.Player.Body.Arms;
import com.rgs.siegeworld.Player.Body.Belt;
import com.rgs.siegeworld.Player.Body.Boots;
import com.rgs.siegeworld.Player.Body.Chest;
import com.rgs.siegeworld.Player.Body.Head;
import com.rgs.siegeworld.Player.Body.Legs;
import com.rgs.siegeworld.Player.Player;

public class CreateCharacter {

    public static Image backgroundImage;
    public static Texture arrowTexture;
    public static Label createCharacterLabel;
    public static Label nameLabel;

    public static Label faceLabel;
    public static Image faceImage;
    public static Image faceLeftArrow;
    public static Image faceRightArrow;
    public static int faceIndex = 0;

    public static Label eyesLabel;
    public static Image eyesImage;
    public static Image eyesLeftArrow;
    public static Image eyesRightArrow;
    public static int eyesIndex = 0;

    public static Label hairLabel;
    public static Image hairImage;
    public static Image hairLeftArrow;
    public static Image hairRightArrow;
    public static int hairIndex = 0;

    public static Image armsImage;
    public static Image legsImage;
    public static Image chestImage;
    public static Image beltImage;
    public static Image bootsImage;

    public static Label okayLabel;

    public void create() {
        backgroundImage = new Image(Game.assets.manager.get("Menus/CreateCharacter/background.png", Texture.class));
        backgroundImage.setBounds(-Game.width/2, -Game.height/2, Game.width, Game.height);
        Game.stageGUI.addActor(backgroundImage);
        arrowTexture = Game.assets.manager.get("Menus/CreateCharacter/arrow.png", Texture.class);
        arrowTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        createCharacterLabel = new Label("Create Character", new Label.LabelStyle(SplashScreen.large_white, SplashScreen.large_white.getColor()));
        createCharacterLabel.setPosition(-createCharacterLabel.getWidth()/2, 550);
        Game.stageGUI.addActor(createCharacterLabel);
        //--------------------------------------------------------------//
        nameLabel = new Label("Name", new Label.LabelStyle(SplashScreen.medium_white, SplashScreen.medium_white.getColor()));
        nameLabel.setPosition(-600 - nameLabel.getWidth()/2, 360);
        Game.stageGUI.addActor(nameLabel);
        //--------------------------------------------------------------//
        faceLabel = new Label("Face", new Label.LabelStyle(SplashScreen.medium_white, SplashScreen.medium_white.getColor()));
        faceLabel.setPosition(-450 - faceLabel.getWidth()/2, -280);
        Game.stageGUI.addActor(faceLabel);
        faceImage = new Image(Head.faceImages.get(0)[0][0].getDrawable());
        faceImage.setSize(faceImage.getWidth()*2, faceImage.getHeight()*2);
        faceImage.setPosition(300 - faceImage.getWidth()/2, -200);
        Game.stageGUI.addActor(faceImage);
        faceLeftArrow = new Image(arrowTexture);
        faceLeftArrow.setPosition(-800, -300);
        faceLeftArrow.setOrigin(arrowTexture.getWidth()/2, arrowTexture.getHeight()/2);
        faceLeftArrow.rotateBy(180);
        faceLeftArrow.addListener(new ClickListener() {
            public void	clicked(InputEvent event, float x, float y) {
                faceIndex--;
                if(faceIndex < 0)
                    faceIndex = Head.faceImages.size()-1;
                faceImage.setDrawable(Head.faceImages.get(faceIndex)[0][0].getDrawable());
                armsImage.setDrawable(Arms.armsImages.get(faceIndex)[0][0].getDrawable());
            }
        });
        Game.stageGUI.addActor(faceLeftArrow);
        faceRightArrow = new Image(arrowTexture);
        faceRightArrow.setPosition(-200, -300);
        faceRightArrow.addListener(new ClickListener() {
            public void	clicked(InputEvent event, float x, float y) {
                faceIndex++;
                if(faceIndex > Head.faceImages.size()-1)
                    faceIndex = 0;
                faceImage.setDrawable(Head.faceImages.get(faceIndex)[0][0].getDrawable());
                armsImage.setDrawable(Arms.armsImages.get(faceIndex)[0][0].getDrawable());
            }
        });
        Game.stageGUI.addActor(faceRightArrow);
        //--------------------------------------------------------------//
        eyesLabel = new Label("Eyes", new Label.LabelStyle(SplashScreen.medium_white, SplashScreen.medium_white.getColor()));
        eyesLabel.setPosition(-450 - eyesLabel.getWidth()/2, -80);
        Game.stageGUI.addActor(eyesLabel);
        eyesImage = new Image(Head.eyesImages.get(0)[0][0].getDrawable());
        eyesImage.setSize(eyesImage.getWidth()*2, eyesImage.getHeight()*2);
        eyesImage.setPosition(300 - eyesImage.getWidth()/2, -200);
        Game.stageGUI.addActor(eyesImage);
        eyesLeftArrow = new Image(arrowTexture);
        eyesLeftArrow.setPosition(-800, -100);
        eyesLeftArrow.setOrigin(arrowTexture.getWidth()/2, arrowTexture.getHeight()/2);
        eyesLeftArrow.rotateBy(180);
        eyesLeftArrow.addListener(new ClickListener() {
            public void	clicked(InputEvent event, float x, float y) {
                eyesIndex--;
                if(eyesIndex < 0)
                    eyesIndex = Head.eyesImages.size()-1;
                eyesImage.setDrawable(Head.eyesImages.get(eyesIndex)[0][0].getDrawable());
            }
        });
        Game.stageGUI.addActor(eyesLeftArrow);
        eyesRightArrow = new Image(arrowTexture);
        eyesRightArrow.setPosition(-200, -100);
        eyesRightArrow.addListener(new ClickListener() {
            public void	clicked(InputEvent event, float x, float y) {
                eyesIndex++;
                if(eyesIndex > Head.eyesImages.size()-1)
                    eyesIndex = 0;
                eyesImage.setDrawable(Head.eyesImages.get(eyesIndex)[0][0].getDrawable());
            }
        });
        Game.stageGUI.addActor(eyesRightArrow);
        //--------------------------------------------------------------//
        hairLabel = new Label("Hair", new Label.LabelStyle(SplashScreen.medium_white, SplashScreen.medium_white.getColor()));
        hairLabel.setPosition(-450 - hairLabel.getWidth()/2, 120);
        Game.stageGUI.addActor(hairLabel);
        hairImage = new Image(Head.hairImages.get(0)[0][0].getDrawable());
        hairImage.setSize(hairImage.getWidth()*2, hairImage.getHeight()*2);
        hairImage.setPosition(300 - hairImage.getWidth()/2, -200);
        Game.stageGUI.addActor(hairImage);
        hairLeftArrow = new Image(arrowTexture);
        hairLeftArrow.setPosition(-800, 100);
        hairLeftArrow.setOrigin(arrowTexture.getWidth()/2, arrowTexture.getHeight()/2);
        hairLeftArrow.rotateBy(180);
        hairLeftArrow.addListener(new ClickListener() {
            public void	clicked(InputEvent event, float x, float y) {
                hairIndex--;
                if(hairIndex < 0)
                    hairIndex = Head.hairImages.size()-1;
                hairImage.setDrawable(Head.hairImages.get(hairIndex)[0][0].getDrawable());
            }
        });
        Game.stageGUI.addActor(hairLeftArrow);
        hairRightArrow = new Image(arrowTexture);
        hairRightArrow.setPosition(-200, 100);
        hairRightArrow.addListener(new ClickListener() {
            public void	clicked(InputEvent event, float x, float y) {
                hairIndex++;
                if(hairIndex > Head.hairImages.size()-1)
                    hairIndex = 0;
                hairImage.setDrawable(Head.hairImages.get(hairIndex)[0][0].getDrawable());
            }
        });
        Game.stageGUI.addActor(hairRightArrow);
        //--------------------------------------------------------------//
        armsImage = new Image(Arms.armsImages.get(0)[0][0].getDrawable());
        armsImage.setSize(armsImage.getWidth()*2, armsImage.getHeight()*2);
        armsImage.setPosition(300 - armsImage.getWidth()/2, -200);
        Game.stageGUI.addActor(armsImage);
        legsImage = new Image(Legs.legsImages.get(0)[0][0].getDrawable());
        legsImage.setSize(legsImage.getWidth()*2, legsImage.getHeight()*2);
        legsImage.setPosition(300 - legsImage.getWidth()/2, -200);
        Game.stageGUI.addActor(legsImage);
        chestImage = new Image(Chest.chestImages.get(0)[0][0].getDrawable());
        chestImage.setSize(chestImage.getWidth()*2, chestImage.getHeight()*2);
        chestImage.setPosition(300 - chestImage.getWidth()/2, -200);
        Game.stageGUI.addActor(chestImage);
        beltImage = new Image(Belt.beltImages.get(0)[0][0].getDrawable());
        beltImage.setSize(beltImage.getWidth()*2, beltImage.getHeight()*2);
        beltImage.setPosition(300 - beltImage.getWidth()/2, -200);
        Game.stageGUI.addActor(beltImage);
        bootsImage = new Image(Boots.bootsImages.get(0)[0][0].getDrawable());
        bootsImage.setSize(bootsImage.getWidth()*2, bootsImage.getHeight()*2);
        bootsImage.setPosition(300 - bootsImage.getWidth()/2, -200);
        Game.stageGUI.addActor(bootsImage);
        //--------------------------------------------------------------//
        okayLabel  = new Label("Okay", new Label.LabelStyle(SplashScreen.medium_white, SplashScreen.medium_white.getColor()));
        okayLabel.setPosition(-okayLabel.getWidth()/2, -600);
        okayLabel.addListener(new ClickListener() {
            public void	clicked(InputEvent event, float x, float y) {
                SaveData.prefs.putInteger("hairIndex", hairIndex);
                SaveData.prefs.putInteger("eyesIndex", eyesIndex);
                SaveData.prefs.putInteger("faceIndex", faceIndex);
                SaveData.prefs.putBoolean("loggedIn",true);
                SaveData.prefs.flush();
                remove();
                Player.show();
            }
        });
        Game.stageGUI.addActor(okayLabel);
    }

    public void remove() {
        backgroundImage.remove();
        createCharacterLabel.remove();
        nameLabel.remove();
        faceLabel.remove();
        faceImage.remove();
        faceLeftArrow.remove();
        faceRightArrow.remove();
        eyesLabel.remove();
        eyesImage.remove();
        eyesLeftArrow.remove();
        eyesRightArrow.remove();
        hairLabel.remove();
        hairImage.remove();
        hairLeftArrow.remove();
        hairRightArrow.remove();
        armsImage.remove();
        legsImage.remove();
        chestImage.remove();
        beltImage.remove();
        bootsImage.remove();
        okayLabel.remove();
    }
}
