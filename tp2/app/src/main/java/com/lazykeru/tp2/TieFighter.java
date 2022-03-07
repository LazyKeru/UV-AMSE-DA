package com.lazykeru.tp2;

import android.graphics.Rect;
import android.os.Handler;
import android.view.View;

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
        setSizeExplosion(150);
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
        TieFighter.setY(TieFighter.getY() + delta_y);
    }

    public void updateX(float delta_x) {
        TieFighter.setX(TieFighter.getX() + delta_x);
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

    public void setSizeExplosion(int size) {
        this.Explosion.getLayoutParams().height = size;
        this.Explosion.getLayoutParams().width = size;
    }

    public void explosion(){
        int[] location = new int[2];
        this.TieFighter.getLocationOnScreen(location);
        this.Explosion.setY(location[1]);
        this.Explosion.setX(location[0]);
        this.Explosion.setVisibility(View.VISIBLE);
        this.TieFighter.setVisibility(View.INVISIBLE);
    }

    public void tieCrashed(){
        this.isAlive = false;
        explosion();
        this.parent.Collision();
    }

    public void stopGame(){
        this.TieFighter.setVisibility(View.INVISIBLE);
        this.Explosion.setVisibility(View.INVISIBLE);
    }
}
