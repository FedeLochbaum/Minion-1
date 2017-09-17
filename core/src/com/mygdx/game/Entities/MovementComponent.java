package com.mygdx.game.Entities;

import com.badlogic.gdx.math.Vector2;

public class MovementComponent extends TransformComponent {
    public final Vector2 velocity;
    public final Vector2 accel;

    public MovementComponent(float x, float y, float w, float h){
        super(x,y,w,h);
        velocity = new Vector2();
        accel = new Vector2();
    }
}
