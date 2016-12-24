package com.drewfow94.alienblastergame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.drewfow94.AnimatedSprite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew on 2016-12-23.
 */
public class ShotManager {


    private static final int SHOT_Y_OFFSET = 0;
    private static final int SHOT_SPEED = 300;
    public static final float MINIMUM_TIME_BETWEEN_SHOTS = .5f;
    private final Texture shotTexture;
    private List<AnimatedSprite> shots = new ArrayList<AnimatedSprite>();
    private float timeSinceLastShot = 0;

    public ShotManager(Texture shotTexture) {
        this.shotTexture = shotTexture;
    }

    public void firePlayerShot(int shipCenterXLocation)
    {
      if(canFireShot()) {
          Sprite newShot = new Sprite(shotTexture);
          AnimatedSprite newShotAnimated = new AnimatedSprite(newShot);
          newShotAnimated.setPosition(shipCenterXLocation,  SHOT_Y_OFFSET);
          newShotAnimated.setVelocity(new Vector2(0, SHOT_SPEED));
          shots.add(newShotAnimated);
          timeSinceLastShot = 0f;
      }

    }

    private boolean canFireShot() {
        return timeSinceLastShot > MINIMUM_TIME_BETWEEN_SHOTS;
    }

    public void update() {
        for(AnimatedSprite shot : shots){
            shot.move();
        }
        timeSinceLastShot += Gdx.graphics.getDeltaTime();
    }

    public void draw(SpriteBatch batch) {
        for(AnimatedSprite shot : shots){
            shot.draw(batch);
        }
    }
}
