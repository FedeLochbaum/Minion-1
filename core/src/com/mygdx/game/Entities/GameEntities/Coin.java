package com.mygdx.game.Entities.GameEntities;

public class Coin extends com.mygdx.game.Entities.LogicEntities.TransformComponent {

    public static final float WIDTH = 1.3f;
    public static final float HEIGHT = 1.4f;

    public static final int POINTS = 10;

    private float stateTime;

    public Coin(float x, float y){
        super(x, y, WIDTH, HEIGHT);
    }
    public void update(float delta){
        stateTime += delta;
    }

    public float getStateTime() {
        return stateTime;
    }

    public void setStateTime(float stateTime) {
        this.stateTime = stateTime;
    }
}
