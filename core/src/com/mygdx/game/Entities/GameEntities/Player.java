package com.mygdx.game.Entities.GameEntities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.Utils.States.PlayerState;
import com.mygdx.game.World.WorldEngine;

import static com.mygdx.game.Utils.States.PlayerState.STATE_DEAD;
import static com.mygdx.game.Utils.States.PlayerState.STATE_FALL;
import static com.mygdx.game.Utils.States.PlayerState.STATE_INGROUND;
import static com.mygdx.game.Utils.States.PlayerState.STATE_JUMP;

public class Player extends com.mygdx.game.Entities.LogicEntities.MovementComponent {

    public static final float JUMP_VELOCITY = 11f;
    public static final float WIDTH = 2.4f;
    public static final float HEIGHT = 3f;

    PlayerState state;
    float stateTime = 0;

    public Player(float x, float y){
        super(x,y,WIDTH,HEIGHT);
        state = STATE_INGROUND;
        stateTime = 0;
    }

    public void update(float delta) {
        velocity.add(WorldEngine.gravity.x * delta, WorldEngine.gravity.y * delta);
        position.add(velocity.x * delta, velocity.y * delta);

        bounds.x = position.x - bounds.width/2;
        bounds.y = position.y - bounds.height/2;
        if(state == STATE_DEAD) return;
        float accel = 0;
        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT)) accel = WIDTH+(WIDTH/2);
        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT)) accel = -(WIDTH+(WIDTH/2));
        velocity.x = accel;
        if(state == STATE_INGROUND) jump();

        if(velocity.y < 0 && state != STATE_INGROUND){
            if(state != STATE_FALL) state = STATE_FALL;
        }
        if(position.y <= 0){
            position.y = 0f;
            if(state != STATE_DEAD){
                state = STATE_INGROUND;
            }
        }
        if(position.x+WIDTH < 0) position.x = WorldEngine.WIDTH;
        if(position.x > WorldEngine.WIDTH) position.x = -WIDTH;
        stateTime += delta;
    }

    private void jump() {
        velocity.y = JUMP_VELOCITY;
        state = STATE_JUMP;
        stateTime = 0;
    }

    public void dead(){
        velocity.set(0,0);
        state = STATE_DEAD;
        stateTime = 0;
    }

    public void hitPlatform(Platform p) {
        velocity.y = 0;
        position.y = p.position.y+0.3f;
        state = STATE_INGROUND;
        stateTime = 0;
    }

    public void hitBird() {
        velocity.y = JUMP_VELOCITY*1.5f;
        state = STATE_JUMP;
        stateTime = 0;
    }

    public PlayerState getState() {
        return state;
    }

    public void setState(PlayerState state) {
        this.state = state;
    }

}
