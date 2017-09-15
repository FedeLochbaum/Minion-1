package com.mygdx.game.Utils;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Actors.Player;

import java.util.Random;

public class WorldUtil {

    public static final Vector2 gravity = new Vector2(0, -9.8f);
    public static final float SCALE = 30;

    public static final float WIDTH = 16;
    public static final float HEIGHT = 26 * 10;

    public final Player player;

//    public final List<Platform> platforms;
//    public final List<Bird> birds;
//    public final List<Coin> coins;
//    public final Random rand;

    public int score = 0;
    public float heightCam;


    public WorldUtil(){
//        rand = new Random();
//        platforms = new ArrayList<Platform>();
//        birds = new ArrayList<Bird>();
//        coins = new ArrayList<Coin>();
        player = new Player(10, 0);
//        generateLevel();
        heightCam = 0;
    }
    public void update(float delta){
//        updateLame(delta);
//        updatePlatforms(delta);
//        updateBirds(delta);
//        updateCoins(delta);
//        checkCollisions();
    }

}
