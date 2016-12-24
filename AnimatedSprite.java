package com.drewfow94;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.drewfow94.alienblastergame.ShooterGame;

public class AnimatedSprite  {

    // Constant columns and rows for the sprite sheet
    private static final int FRAME_COLS = 2, FRAME_ROWS = 2;
    public static final int SHIP_SPEED = 300;
    public static final int SPEED_ZERO= 0;

    // Objects used
    private Animation<TextureRegion> shipAnimation;
    private Texture texture;
    private Sprite sprite;
    private TextureRegion currentFrame;
    private Vector2 velocity = new Vector2();
    private TextureRegion[] shipFrames;

    // Variable to track time
    float stateTime;

    //@Override
    public AnimatedSprite(Sprite sprite) {

        this.sprite = sprite;
        // Load the sprite sheet as a texture
        texture = sprite.getTexture();

        // Use the split method to create a 2D array of TextureRegions.
        TextureRegion[][] tmp = TextureRegion.split(texture,
                (int)getSpriteWidth(), texture.getHeight() / FRAME_ROWS
                );

        /* Place the regions into a 1D array in the correct order, first the top left,
        *  going across first. The animation constructor requires a 1D array.
        *
        */
        shipFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++){
            for (int j = 0; j < FRAME_COLS; j++){
                shipFrames[index++] = tmp[i][j];
            }
        }

        // Initialize the Animation with the frame interval and array of frames
        shipAnimation = new Animation<TextureRegion>(0.1f, shipFrames);

        // Makes an instance of SpriteBatch for drawing and resetting animation
        //spriteBatch = new SpriteBatch();
        stateTime = 0f;

    }

    public void draw(SpriteBatch spriteBatch){
        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = shipAnimation.getKeyFrame(stateTime, true);

        spriteBatch.draw(currentFrame, sprite.getX(), sprite.getY());

    }

    public void setPosition(float x, float y)
    {
        sprite.setPosition(x - getSpriteCenterOffset(), y);
    }

    public float getSpriteCenterOffset()
    {
        return getSpriteWidth() / 2;
    }

    private float getSpriteWidth() {
        return sprite.getWidth() / FRAME_COLS;
    }


    public void moveRight()
    {
        velocity = new Vector2(SHIP_SPEED, 0);
    }

    public void moveLeft()
    {
        velocity = new Vector2(-SHIP_SPEED, 0);
    }

   /* public void stopMovement()
    {
        velocity = new Vector2((, 0))
    }
   */
    public int getX() {
       return (int) (sprite.getX() + getSpriteCenterOffset());

    }

    public void move() {
        int xMovement = (int)(velocity.x * Gdx.graphics.getDeltaTime());
        int yMovement = (int)(velocity.y * Gdx.graphics.getDeltaTime());
        sprite.setPosition(sprite.getX() + xMovement, sprite.getY() + yMovement);

        if(sprite.getX() < 0)
        {
            sprite.setX(0);
            velocity.x = 0;
        }

        if(sprite.getX() + getSpriteWidth() > ShooterGame.SCREEN_WIDTH){
            sprite.setX(ShooterGame.SCREEN_WIDTH - getSpriteWidth());
            velocity.x = 0;
        }


    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public void stopMovement() {
        velocity = new Vector2(SPEED_ZERO, 0);
    }
}