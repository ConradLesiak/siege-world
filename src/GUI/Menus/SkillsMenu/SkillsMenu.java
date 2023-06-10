package com.rgs.siegeworld.GUI.Menus.SkillsMenu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.rgs.siegeworld.GUI.GUI;
import com.rgs.siegeworld.GUI.Menus.Equipment.Equipment;
import com.rgs.siegeworld.GUI.Menus.ForgeMenu.ForgeMenu;
import com.rgs.siegeworld.GUI.Menus.Inventory.Border;
import com.rgs.siegeworld.GUI.Menus.Inventory.Inventory;
import com.rgs.siegeworld.GUI.Menus.Settings.Settings;
import com.rgs.siegeworld.Game;
import com.rgs.siegeworld.Player.Stats.PlayerStats;

public class SkillsMenu {

    public static Texture skillsTexture;
    public static Image skillsImage;
    public static boolean showSkills = false;
    public static Label skillsLabel;
    public static Label meleeLabel;
    public static Label magicLabel;
    public static Label rangeLabel;

    public void load() {
        skillsTexture = new Texture(Game.assets.manager.get("Menus/Skills/skills_icon.png", Texture.class).getTextureData());
        skillsTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        skillsImage = new Image(skillsTexture);
    }

    public void create() {
        skillsImage.setPosition(Equipment.equipImage.getX() - skillsImage.getWidth() * 1.2f, Settings.settingsImage.getY());
        skillsImage.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (Inventory.showInventory) {
                    Inventory.removeInventory();
                    PlayerStats.removeStats();
                    PlayerStats.removeDifference();
                    showSkills();
                } else if (Equipment.showEquips) {
                    Equipment.removeEquips();
                    PlayerStats.removeStats();
                    showSkills();
                } else if (ForgeMenu.showForgeMenu) {
                    ForgeMenu.remove();
                    showSkills();
                } else if (!showSkills) {
                    showSkills();
                    PlayerStats.removeStats();
                } else {
                    removeSkills();
                }
            }
        });
        Game.stageGUI.addActor(skillsImage);
        skillsLabel = new Label("Skills", new Label.LabelStyle(GUI.large_white, GUI.large_white.getColor()));
        skillsLabel.setPosition(-skillsLabel.getWidth() / 2, Inventory.backgroundImage.getY() + Inventory.backgroundImage.getHeight());
        meleeLabel = new Label("Melee", new Label.LabelStyle(GUI.small_white, GUI.small_white.getColor()));
        meleeLabel.setPosition(-Inventory.backgroundImage.getWidth() / 3 - meleeLabel.getWidth() / 2, 360);
        magicLabel = new Label("Magic", new Label.LabelStyle(GUI.small_white, GUI.small_white.getColor()));
        magicLabel.setPosition(-meleeLabel.getWidth() / 2, 360);
        rangeLabel = new Label("Range", new Label.LabelStyle(GUI.small_white, GUI.small_white.getColor()));
        rangeLabel.setPosition(Inventory.backgroundImage.getWidth() / 3 - rangeLabel.getWidth() / 2, 360);
    }

    public void render() {

    }

    public static void showSkills() {
        showSkills = true;
        Game.stageGUI.addActor(Inventory.backgroundImage);
        Border.show();
        Border.borderMiddle.remove();
        Game.stageGUI.addActor(skillsLabel);
        Game.stageGUI.addActor(meleeLabel);
        Game.stageGUI.addActor(magicLabel);
        Game.stageGUI.addActor(rangeLabel);
    }

    public static void removeSkills() {
        showSkills = false;
        Inventory.backgroundImage.remove();
        Border.remove();
        skillsLabel.remove();
        meleeLabel.remove();
        magicLabel.remove();
        rangeLabel.remove();
    }
}
