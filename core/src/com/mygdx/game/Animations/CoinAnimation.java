package com.mygdx.game.Animations;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Utils.Assets;

public class CoinAnimation {

    TextureRegion[] coinFrames;
    Animation coinAnimation;
    public CoinAnimation(){
        coinFrames = new TextureRegion[4];
        Texture coinAsset = Assets.getCoin();
        coinFrames[0] = new TextureRegion(coinAsset, 0, 0, 40, 43);
        coinFrames[1] = new TextureRegion(coinAsset, 0, 43, 40, 43);
        coinFrames[2] = new TextureRegion(coinAsset, 0, 87, 40, 43);
        coinFrames[3] = new TextureRegion(coinAsset, 0, 132, 40, 43);
        coinAnimation = new Animation(0.2f, coinFrames);
    }
}
