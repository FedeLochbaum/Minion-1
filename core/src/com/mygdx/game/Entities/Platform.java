package com.mygdx.game.Entities;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Utils.Types.PlatformType;
import com.mygdx.game.Utils.WorldEngine;

import static com.mygdx.game.Utils.Types.PlatformType.TYPE_MOVING_X;
import static com.mygdx.game.Utils.Types.PlatformType.TYPE_MOVING_Y;

public class Platform extends MovementComponent {

    public static final float WIDTH = 3.16f;
    public static final float HEIGHT = 0.67f;
    public static final float VELOCITY = 2.5f;

    PlatformType type;
    float stateTime = 0;

    private Vector2 origin;
    private boolean flip = false;

    public Platform(float x, float y, PlatformType type) {
        super(x, y, WIDTH, HEIGHT);
        this.type = type;
        this.origin = new Vector2(this.position);
    }

    public void update(float delta){
        bounds.x = position.x - bounds.width/2;
        bounds.y = position.y - bounds.height/2;

        if(type == TYPE_MOVING_X) {
            if(position.x < WorldEngine.WIDTH)
                velocity.x = VELOCITY;
            else if(position.x > WorldEngine.WIDTH+(WIDTH/2)) position.x = -WIDTH+1;
        }

        if(type == TYPE_MOVING_Y) {
            if(!flip) velocity.y = VELOCITY;
            else velocity.y = (-1)*VELOCITY;
            if(position.y >= origin.y && !flip) flip = true;
            else if(position.y <= origin.y-5) flip = false;
        }

        position.add(velocity.x * delta, velocity.y * delta);
        stateTime += delta;
    }
}
