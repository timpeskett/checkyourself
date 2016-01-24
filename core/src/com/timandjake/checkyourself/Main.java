package com.timandjake.checkyourself;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.Game;
import com.timandjake.checkyourself.Screens.MenuScreen;
import com.badlogic.gdx.assets.AssetManager;

public class Main extends Game {
	public SpriteBatch batch;
	public AssetManager manager;
	public BitmapFont font;

	public static final int WIDTH = 800;
	public static final int HEIGHT = 480;


	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();

		manager = new AssetManager();
		manager.load("title_screen.png", Texture.class);
		manager.finishLoading();

		setScreen(new MenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose() {
		batch.dispose();
		manager.dispose();
	}
}
