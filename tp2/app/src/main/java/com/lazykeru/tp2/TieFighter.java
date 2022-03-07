package com.lazykeru.tp2;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;

import androidx.fragment.app.Fragment;

import java.util.concurrent.TimeUnit;


public class TieFighter{
    View TieFighter;
    View Explosion;
    PlayScreen parent;
    float delta_x, delta_y;
    Rect hitBox;
    boolean isMoving;
    boolean isAlive;

    public TieFighter(View TieFighter,View Explosion, PlayScreen parent){
        this.parent = parent;
        this.TieFighter = TieFighter;
        this.Explosion = Explosion;
        this.Explosion.setVisibility(View.INVISIBLE);
        // isAlive and isMoving False, as the game hasn't yet started
        setInactive();
        setSizeTieFighter(200);
        setHitBox();
    }



    Handler handlerForMovingTie = new Handler();
    Runnable movingTie = new Runnable() {
        @Override
        public void run() {
            handlerForMovingTie.postDelayed(this, 10);
            if((isAlive == true) && (isMoving == true)){
                System.out.println("Updating Tie Fighter Position");
                updateTieFighterPosition(); // Update TieFighter position
            }
        }
    };

    // Function called to reset the game and state of the fighter to the starting game states
    public void setInactive(){
        this.isAlive = false;
        this.isMoving = false;
        this.TieFighter.setVisibility(View.INVISIBLE);
        this.Explosion.setVisibility(View.INVISIBLE);
    }

    public void setActive(){
        this.isAlive = true;
        this.TieFighter.setVisibility(View.VISIBLE);
    }

    public void startGame(){
        TieFighter.setY(0 + (parent.getView().getHeight()/2) - (TieFighter.getHeight()/2));
        TieFighter.setX(0 + (parent.getView().getWidth()/2) - (TieFighter.getWidth()/2));
        setActive();
        // Could add more function to update the speed of the fighter, etc ...
    }

    public void setHitBox() {
        int [] posistion = new int[2];
        this.TieFighter.getLocationOnScreen(posistion);
        this.hitBox = new Rect(
                posistion[0],
                posistion[1],
                posistion[0] + this.TieFighter.getMeasuredWidth(),
                posistion[1] + this.TieFighter.getMeasuredHeight()
        );
    }

    public void setIsMoving(boolean bool) {
        this.isMoving = bool;
    }

    public void updateY(float delta_y) {
        float y = TieFighter.getY() + delta_y;
        if(
                (y < parent.getView().getHeight() - TieFighter.getHeight()/2)
                        && (y > 0 - TieFighter.getHeight()/2)
        ){
            TieFighter.setY(y);
        }
    }

    public void updateX(float delta_x) {
        float x = TieFighter.getX() + delta_x;
        if(
                (x < parent.getView().getWidth() - TieFighter.getWidth()/2)
                        && (x > 0 - TieFighter.getWidth()/2)
        ){
            TieFighter.setX(x);
        }
    }

    public void updateDelta(float delta_x, float delta_y){
        this.delta_x = delta_x;
        this.delta_y = delta_y;
    }

    public void updateTieFighterPosition(){
        updateX(this.delta_x);
        updateY(this.delta_y);
    }

    public void setSizeTieFighter(int size) {
        this.TieFighter.getLayoutParams().height = size;
        this.TieFighter.getLayoutParams().width = size;
    }

    public void explosion(){
        int[] location = new int[2];
        TieFighter.getLocationOnScreen(location);
        Explosion.setVisibility(View.VISIBLE);
        Explosion.setAlpha(1f); // Make it visible again
        Explosion.setScaleX(0f); // Put to size
        Explosion.setScaleY(0f); // Put to size
        Explosion.setY(
                (TieFighter.getY()+TieFighter.getHeight()/2) // Top of the Tie fighter + half it's height
                - (Explosion.getHeight()/2) // half the height of the explosion removed
                //Should center the explosion
        );
        //SAME AS ABOVE
        Explosion.setX(
                (TieFighter.getX()+TieFighter.getWidth()/2)
                        - (Explosion.getWidth()/2)
                //Should center the explosion to the TieFighter
        );
        Explosion.animate()
                .alpha(0f)
                .scaleY(10f)
                .scaleX(10f)
                .setDuration(1000);
        TieFighter.setVisibility(View.INVISIBLE);
    }

    public void tieCrashed(){
        if(isAlive == false){return;}
        this.isAlive = false;
        explosion();
        this.parent.Collision();
    }

    public void stopGame(){
        this.TieFighter.setVisibility(View.INVISIBLE);
        //this.Explosion.setVisibility(View.INVISIBLE);
    }
}
