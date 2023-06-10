package com.rgs.siegeworld.Player.Body;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.rgs.siegeworld.Game;
import com.rgs.siegeworld.Player.Player;

public class Aura {//TODO REMAKE CLASS BEFORE USING

    private Texture auraTexture;
    private Image auraImage;

    public void create() {
        auraTexture = new Texture(Gdx.files.internal("Player/Aura/shadow.png"));
        auraTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        auraImage = new Image(auraTexture);
        auraImage.setPosition(Player.playerPos.x - auraTexture.getWidth()/2, Player.playerPos.y - auraTexture.getHeight()/2);
        Game.stage.addActor(auraImage);
    }

    public void render() {
        auraImage.setPosition(Player.playerPos.x - auraTexture.getWidth()/2, Player.playerPos.y - auraTexture.getHeight() * .4f);
    }

    public void update() {

    }
}
