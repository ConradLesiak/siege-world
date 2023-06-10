package com.rgs.siegeworld.GUI.Menus.Inventory;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.rgs.siegeworld.GUI.Menus.Equipment.Equipment;
import com.rgs.siegeworld.GUI.Menus.ForgeMenu.ForgeMenu;
import com.rgs.siegeworld.GUI.Menus.SkillsMenu.SkillsMenu;
import com.rgs.siegeworld.Game;

public class Border {

    public static Texture borderTexture;
    public static Image borderTop;
    public static Image borderBottom;
    public static Image borderLeft;
    public static Image borderRight;
    public static Image borderMiddle;
    public static Texture exitTexture;
    public static Image exitImage;

    public void load() {
        borderTexture = new Texture(Game.assets.manager.get("Menus/Inventory/border.png", Texture.class).getTextureData());
        borderTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        borderTop = new Image(borderTexture);
        exitTexture = new Texture(Game.assets.manager.get("Menus/Inventory/exit.png", Texture.class).getTextureData());
        exitTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        exitImage = new Image(exitTexture);
        borderBottom = new Image(borderTexture);
        borderLeft = new Image(borderTexture);
        borderRight = new Image(borderTexture);
        borderMiddle = new Image(borderTexture);
    }

    public void create() {
        borderTop.setBounds(-Inventory.backgroundImage.getWidth()/2 - borderTop.getHeight(), Inventory.backgroundImage.getY() + Inventory.backgroundImage.getHeight(), Inventory.backgroundImage.getWidth() + borderTop.getHeight()*2, borderTop.getHeight());

        borderBottom.setBounds(-Inventory.backgroundImage.getWidth()/2 - borderTop.getHeight(), Inventory.backgroundImage.getY() - borderBottom.getHeight()+1, Inventory.backgroundImage.getWidth() + borderTop.getHeight()*2, borderTop.getHeight());
        borderBottom.setOrigin(borderBottom.getWidth()/2,borderBottom.getHeight()/2);
        borderBottom.rotateBy(180);

        borderLeft.setBounds(-Inventory.backgroundImage.getWidth()/2, Inventory.backgroundImage.getY(), Inventory.backgroundImage.getHeight() + borderBottom.getHeight(), borderBottom.getHeight() );
        borderLeft.rotateBy(90);

        borderRight.setBounds(Inventory.backgroundImage.getWidth()/2, Inventory.backgroundImage.getY() + Inventory.backgroundImage.getHeight(), Inventory.backgroundImage.getHeight() + borderBottom.getHeight() - 1, borderBottom.getHeight());
        borderRight.rotateBy(270);

        borderMiddle.setBounds(-Inventory.backgroundImage.getWidth()/2 - borderTop.getHeight(), Inventory.backgroundImage.getY() + 600, Inventory.backgroundImage.getWidth() + borderTop.getHeight()*2, borderTop.getHeight());

        exitImage.setPosition(Inventory.backgroundImage.getWidth()/2 - exitImage.getWidth()/2, Inventory.backgroundImage.getY() + Inventory.backgroundImage.getHeight() - exitImage.getHeight()/2);
        exitImage.addListener( new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                if(Inventory.showInventory)
                    Inventory.removeInventory();
                if(Equipment.showEquips)
                    Equipment.removeEquips();
                if(SkillsMenu.showSkills)
                    SkillsMenu.removeSkills();
                if(ForgeMenu.showForgeMenu)
                    ForgeMenu.remove();
            }
        });
    }

    public static void show() {
        Game.stageGUI.addActor(borderMiddle);
        Game.stageGUI.addActor(borderLeft);
        Game.stageGUI.addActor(borderRight);
        Game.stageGUI.addActor(borderTop);
        Game.stageGUI.addActor(borderBottom);
        Game.stageGUI.addActor(exitImage);
    }

    public static void remove() {
        borderMiddle.remove();
        borderLeft.remove();
        borderRight.remove();
        borderTop.remove();
        borderBottom.remove();
        exitImage.remove();
    }
}