package com.timandjake.checkyourself.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.timandjake.checkyourself.Main;
import com.badlogic.gdx.graphics.Color;

public class MenuScreen implements Screen {

	private Main game;
	private OrthographicCamera menuCam;
	private GlyphLayout layout;

	private float fadeTimer;

	private Texture titleScreen;

	public MenuScreen(Main game) {

		this.game = game;
		menuCam = new OrthographicCamera();
		layout = new GlyphLayout();
		menuCam.setToOrtho(false, Main.WIDTH, Main.HEIGHT);
		fadeTimer = 0;
		titleScreen = game.manager.get("title_screen.png");
	}

	@Override
	public void show() {}

	@Override
	public void render(float delta) {
		update(delta);

		Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        fadeTimer = fadeTimer >= 1 ? 0 : fadeTimer + delta;

        game.font.setColor(0,0,0, 1 * fadeTimer);
        layout.setText(game.font, "CLICK TO BEGIN");

        menuCam.update();
        game.batch.setProjectionMatrix(menuCam.combined);

        game.batch.begin();
        game.batch.draw(titleScreen, (Main.WIDTH / 2) - (titleScreen.getWidth() / 2), (Main.HEIGHT / 2) - (titleScreen.getHeight() / 2));
        game.font.draw(game.batch, layout, (Main.WIDTH - layout.width) / 2, (Main.HEIGHT + layout.height) / 4);
        game.batch.end();

        if(Gdx.input.isTouched()) {
        	game.setScreen(new GameScreen(game));
        	dispose();
        }
	}

	public void update(float delta) {}

	@Override
	public void resize(int width, int height) {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void hide() {}

	@Override
	public void dispose() {
		titleScreen.dispose();
	}

}