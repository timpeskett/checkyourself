package com.timandjake.checkyourself.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.GL20;
import com.timandjake.checkyourself.Main;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.timandjake.checkyourself.model.Board;
import com.timandjake.checkyourself.model.Piece;
import com.timandjake.checkyourself.model.BoardCoord;
import com.badlogic.gdx.graphics.Texture;
import java.util.Iterator;
import com.badlogic.gdx.math.Vector3;

public class GameScreen implements Screen {

	private Main game;
	private OrthographicCamera gameCam;
	private float gameTimer;
	private GlyphLayout layout;
	private Board board;

	private Vector3 touchPos;

	private boolean isWhiteTurn;

	private Texture whitePiece;
	private Texture redPiece;
	private Texture potentialPiece;

	private BoardCoord firstClick;

	public GameScreen(Main game) {

		this.game = game;
		gameTimer = 0;
		isWhiteTurn = false;
		board = new Board(8, Board.PieceDirection.DOWN);
		layout = new GlyphLayout();
		gameCam = new OrthographicCamera();
		gameCam.setToOrtho(false, Main.WIDTH, Main.HEIGHT);
		touchPos = new Vector3();

		whitePiece = game.manager.get("white_piece.png");
		redPiece = game.manager.get("red_piece.png");
		potentialPiece = game.manager.get("potential_piece.png");

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
        for(int ii = 0; ii < Main.BOARD_SIZE; ii++) {
        	for(int jj = 0; jj < 8; jj++) {
        		game.shapeRenderer.rect(Main.WIDTH - Main.HEIGHT + (jj * Main.HEIGHT / Main.BOARD_SIZE), ii * Main.HEIGHT / Main.BOARD_SIZE, Main.HEIGHT / Main.BOARD_SIZE, Main.HEIGHT / Main.BOARD_SIZE);
        		if(game.shapeRenderer.getCurrentType() == ShapeType.Filled && jj != Main.BOARD_SIZE - 1) {
        			game.shapeRenderer.set(ShapeType.Line);
        		} else if(game.shapeRenderer.getCurrentType() == ShapeType.Line && jj != Main.BOARD_SIZE - 1) {
        			game.shapeRenderer.set(ShapeType.Filled);
        		}

        	}
        }
        game.shapeRenderer.end();

        //piece & text drawing
        game.batch.begin();
        game.font.draw(game.batch, layout, (Main.WIDTH - Main.HEIGHT - layout.width) / 2, (Main.HEIGHT + layout.height) / Main.BOARD_SIZE);
        for( Piece thisPiece : board) {
        	if(thisPiece.isWhite()) {
        		game.batch.draw(whitePiece, Main.WIDTH - Main.HEIGHT + (thisPiece.getX() - 1) * Main.HEIGHT / Main.BOARD_SIZE, (thisPiece.getY() - 1) * Main.HEIGHT / Main.BOARD_SIZE);
        	} else {
        		game.batch.draw(redPiece, Main.WIDTH - Main.HEIGHT + (thisPiece.getX() - 1) * Main.HEIGHT / Main.BOARD_SIZE, (thisPiece.getY() - 1) * Main.HEIGHT / Main.BOARD_SIZE);
        	}
        }
        game.batch.end();
        
	}

	private void update(float delta) {
		handleInput(delta);

		gameTimer += delta;
		game.font.setColor(0, 0, 0, 1);
		layout.setText(game.font, String.format("%04d", (int)gameTimer));
	}

	private void handleInput(float delta) {
		if(Gdx.input.justTouched()) {
			touchPos.set( Gdx.input.getX(), Gdx.input.getY(), 0);
			gameCam.unproject(touchPos);
			if(touchPos.x >= Main.WIDTH - Main.HEIGHT) {
				BoardCoord thisCoord = calcBoardCoord( touchPos);
				if( firstClick == null) {
					firstClick = thisCoord;
				} else {
					try {
						System.out.println(firstClick);
						System.out.println(thisCoord);
						board.movePiece( board.getPiece(firstClick), thisCoord);
					} catch(Exception e) {
						System.out.println("no move");
					}
					firstClick = null;
				}
			}
		}
	}

	/* Given a vector3 mouse coordinate, will return a board coordinate */
	private BoardCoord calcBoardCoord(Vector3 v) {
		int x, y;
		x = (int)(v.x - (Main.WIDTH - Main.HEIGHT)) / (Main.HEIGHT / Main.BOARD_SIZE) + 1;
		y = (int)(v.y / (Main.HEIGHT / 8)) + 1;
		
		return new BoardCoord( Main.BOARD_SIZE, x, y);
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