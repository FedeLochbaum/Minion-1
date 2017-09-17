package com.mygdx.game.Entities;

public class Coin extends TransformComponent {

    public static final float WIDTH = 1.3f;
    public static final float HEIGHT = 1.4f;

    public static final int POINTS = 10;

    float stateTime;

    public Coin(float x, float y){
        super(x, y, WIDTH, HEIGHT);
    }
    public void update(float delta){
        stateTime += delta;
    }
}
