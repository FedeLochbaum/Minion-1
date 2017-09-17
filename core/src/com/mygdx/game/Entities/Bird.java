package com.mygdx.game.Entities;

import com.mygdx.game.Utils.States.BirdState;
import com.mygdx.game.Utils.Types.BirdType;
import com.mygdx.game.Utils.WorldEngine;

import static com.mygdx.game.Utils.States.BirdState.STATE_FLY;
import static com.mygdx.game.Utils.Types.BirdType.TYPE_LEFT;
import static com.mygdx.game.Utils.Types.BirdType.TYPE_RIGHT;

public class Bird extends MovementComponent {

    public static final float WIDTH = 1.8f;
    public static final float HEIGHT = 1.5f;
    public static final float VELOCITY = 3f;

    BirdState state;
    BirdType type;

    float stateTime = 0;

    public Bird(float x, float y, BirdType type){
        super(x, y, WIDTH, HEIGHT);
        this.type = type;
        this.state = STATE_FLY;
    }

    public void update(float delta){
        position.add(velocity.x * delta, velocity.y * delta);
        bounds.x = position.x - bounds.width/2;
        bounds.y = position.y - bounds.height/2;

        if(type == TYPE_LEFT) {
            velocity.x = -VELOCITY;
            if(position.x < -WIDTH) position.x = WorldEngine.WIDTH+WIDTH/2;
        }

        if (type == TYPE_RIGHT) {
            velocity.x = VELOCITY;
            if(position.x > WorldEngine.WIDTH+WIDTH/2) position.x = -WIDTH;
        }

        stateTime += delta;
    }
}
