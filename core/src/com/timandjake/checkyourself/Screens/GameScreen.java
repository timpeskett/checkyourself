package com.timandjake.checkyourself.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.GL20;
import com.timandjake.checkyourself.Main;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.timandjake.checkyourself.model.Board;

public class GameScreen implements Screen {

	private Main game;
	private OrthographicCamera gameCam;
	private float gameTimer;
	private GlyphLayout layout;
	private Board board;

	public GameScreen(Main game) {

		this.game = game;
		gameTimer = 0;
		board = new Board(8, Board.PieceDirection.DOWN);
		layout = new GlyphLayout();
		gameCam = new OrthographicCamera();
		gameCam.setToOrtho(false, Main.WIDTH, Main.HEIGHT);

	}

	@Override
	public void show() {}

	@Override
	public void render(float delta) {

		update(delta);

		Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameCam.update();
        game.batch.setProjectionMatrix(gameCam.combined);

        //Drawing of the checkerboard
        game.shapeRenderer.begin(ShapeType.Filled);
        game.shapeRenderer.setColor(0, 0, 0, 1);
        for(int ii = 0; ii < 8; ii++) {
        	for(int jj = 0; jj < 8; jj++) {
        		game.shapeRenderer.rect(Main.WIDTH - Main.HEIGHT + (jj * Main.HEIGHT / 8), ii * Main.HEIGHT / 8, Main.HEIGHT / 8, Main.HEIGHT / 8);
        		if(game.shapeRenderer.getCurrentType() == ShapeType.Filled && jj != 7) {
        			game.shapeRenderer.set(ShapeType.Line);
        		} else if(game.shapeRenderer.getCurrentType() == ShapeType.Line && jj != 7) {
        			game.shapeRenderer.set(ShapeType.Filled);
        		}

        	}
        }
        game.shapeRenderer.end();

        //graphic & text drawing
        game.batch.begin();
        game.font.draw(game.batch, layout, (Main.WIDTH - Main.HEIGHT - layout.width) / 2, (Main.HEIGHT + layout.height) / 8);
        game.batch.end();
        
	}

	public void update(float delta) {
		gameTimer += delta;
		game.font.setColor(0, 0, 0, 1);
		layout.setText(game.font, String.format("%04d", (int)gameTimer));
	}

	@Override
	public void resize(int width, int height) {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void hide() {}

	@Override
	public void dispose() {}

}