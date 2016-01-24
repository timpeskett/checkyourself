package com.timandjake.checkyourself;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.Game;
import com.timandjake.checkyourself.Screens.MenuScreen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Main extends Game {
	public SpriteBatch batch;
	public AssetManager manager;
	public BitmapFont font;
	public ShapeRenderer shapeRenderer;

	public static final int WIDTH = 800;
	public static final int HEIGHT = 480;
	public static final int BOARD_SIZE = 8;


	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setAutoShapeType(true);

		manager = new AssetManager();
		manager.load("title_screen.png", Texture.class);
		manager.load("white_piece.png", Texture.class);
		manager.load("red_piece.png", Texture.class);
		manager.load("potential_piece.png", Texture.class);
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
		shapeRenderer.dispose();
	}
}
