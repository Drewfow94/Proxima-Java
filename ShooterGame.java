package com.drewfow94.alienblastergame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.drewfow94.AnimatedSprite;

/* Andrew Smith(Drewfow94) */
public class ShooterGame extends ApplicationAdapter {


	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 580;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture background;
	private Sprite spaceshipSprite;
    private Texture spaceshipTexture;
	private float elapsedTime = 0;
    private AnimatedSprite animatedShip;
	private ShotManager shotManager;
	
	@Override
	public void create () {


		camera = new OrthographicCamera();
		camera.setToOrtho(false, SCREEN_WIDTH,  SCREEN_HEIGHT);

		batch = new SpriteBatch();

		background = new Texture(Gdx.files.internal("data\\Global_Warming.jpg"));
        spaceshipTexture = new Texture(Gdx.files.internal("data\\playercruiser_spritesheet.png"));
        spaceshipSprite = new Sprite(spaceshipTexture);
		animatedShip = new AnimatedSprite(spaceshipSprite);
		animatedShip.setPosition(800 / 2, 0);

		// Initialize the Shot Manager and make texture
		Texture shotTexture = new Texture(Gdx.files.internal("data\\shot_spritesheet.png"));
		shotManager = new ShotManager(shotTexture);



	}

	public void dispose () {
		batch.dispose();

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(background, 0, 0);
		animatedShip.draw(batch);
		shotManager.draw(batch);
		batch.end();

		handleInput();

		animatedShip.move();
		shotManager.update();

	}

	private void handleInput() {
		if(Gdx.input.isTouched())
		{
			Vector3 touchPosition = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPosition);


			//int yTouch = Gdx.input.getY();
			// This is to check if the player has touched the screen
			if(touchPosition.x > animatedShip.getX()){
		        animatedShip.moveRight();
			}
			else if(touchPosition.x < animatedShip.getX())
			{
				animatedShip.moveLeft();
			}
			else{
				animatedShip.stopMovement();
			}

			shotManager.firePlayerShot(animatedShip.getX());



		}

	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}


	}
