package com.mygdx.game.Animations;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Utils.Assets;

public class BirdAnimation {

    TextureRegion[] flyFrames;
    TextureRegion dieFrames;
    Animation flyAnimation;

    public BirdAnimation(){
        flyFrames = new TextureRegion[2];
        Texture birdAsset = Assets.getBird();
        flyFrames[0] = new TextureRegion(birdAsset, 0, 0, 57, 45);
        flyFrames[1] = new TextureRegion(birdAsset, 57, 0, 65, 45);
        dieFrames = new TextureRegion(birdAsset, 122, 0, 57, 45);
        flyAnimation = new Animation(0.3f, flyFrames);
    }

    public TextureRegion[] getFlyFrames() {
        return flyFrames;
    }

    public void setFlyFrames(TextureRegion[] flyFrames) {
        this.flyFrames = flyFrames;
    }

    public TextureRegion getDieFrames() {
        return dieFrames;
    }

    public void setDieFrames(TextureRegion dieFrames) {
        this.dieFrames = dieFrames;
    }

    public Animation getFlyAnimation() {
        return flyAnimation;
    }

    public void setFlyAnimation(Animation flyAnimation) {
        this.flyAnimation = flyAnimation;
    }
}
