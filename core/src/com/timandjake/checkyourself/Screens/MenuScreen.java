package com.timandjake.checkyourself.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.graphics.GL20;
import com.timandjake.checkyourself.Main;

public class MenuScreen implements Screen {

	private Main game;
	private OrthographicCamera menuCam;
	private Viewport menuPort;

	public MenuScreen(Main game) {

		this.game = game;
		menuCam = new OrthographicCamera();
		menuPort = new FitViewport(480, 800);

		menuCam.position.set(menuPort.getWorldWidth() / 2, menuPort.getWorldHeight() / 2, 0);

	}

	@Override
	public void show() {}

	@Override
	public void render(float delta) {
		update(delta);

		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	public void update(float delta) {}

	@Override
	public void resize(int width, int height) {
		menuPort.update(width, height);
	}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void hide() {}

	@Override
	public void dispose() {}

}