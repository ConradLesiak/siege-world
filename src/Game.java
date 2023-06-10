package com.rgs.siegeworld;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.rgs.siegeworld.GUI.GUI;
import com.rgs.siegeworld.Player.Player;
import com.rgs.siegeworld.World.World;

public class Game implements ApplicationListener {

    public static Stage stage;
    public static Stage stageGUI;
    public static float width;
    public static float height;
    public static Assets assets = new Assets();
    public static SplashScreen splashScreen = new SplashScreen();
    public static SaveData saveData = new SaveData();
    public static World world = new World();
    public static Player player = new Player();
    public static GUI gui = new GUI();

    @Override
	public void create () {
        stage = new Stage(new FillViewport(1920, 1440), new SpriteBatch());
        stageGUI = new Stage(new ExtendViewport(1920, 1440), new SpriteBatch());
        stageGUI.getViewport().getCamera().position.set(0, 0, 1);
        Gdx.input.setInputProcessor(stageGUI);
        width = stageGUI.getWidth();
        height = stageGUI.getHeight();
        splashScreen.create();
    }

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stageGUI.getViewport().apply();
        stageGUI.draw();
        splashScreen.render();
        if(SaveData.assetsCreated && SaveData.prefs.getBoolean("loggedIn", false)) {
            stage.getViewport().apply();
            world.render();

            stage.draw();
            player.render();

            stage.getBatch().begin();
            world.renderWalls();
            stage.getBatch().end();

            stageGUI.getViewport().apply();
            stageGUI.draw();
            gui.render();
        }
    }

	@Override
	public void resize(int width, int height) {
        stage.getViewport().update(width, height);
        stageGUI.getViewport().update(width, height);
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose () {
        stage.dispose();
        stageGUI.dispose();
	}
}
