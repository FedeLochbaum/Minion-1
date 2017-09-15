package com.mygdx.game.Screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Minion;

import static com.mygdx.game.Utils.Constants.HEIGHT;
import static com.mygdx.game.Utils.Constants.WIDTH;

public class GameScreen extends ScreenAdapter {

    private Minion game;
    private OrthographicCamera camera;
    private Vector3 touchPoint;

    public GameScreen(Minion gameM) {
        game = gameM;

        camera = new OrthographicCamera(WIDTH,HEIGHT);
        camera.position.set(WIDTH/2,HEIGHT/2,0);


    }
}
