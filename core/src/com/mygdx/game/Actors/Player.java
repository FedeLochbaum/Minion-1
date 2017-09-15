package com.mygdx.game.Actors;

import com.mygdx.game.Utils.States.PlayerState;

public class Player {

    PlayerState state;

    public Player(float x, float y) {
    }

    public PlayerState getState() {
        return state;
    }

    public void setState(PlayerState state) {
        this.state = state;
    }

}
