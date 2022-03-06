package com.lazykeru.tp2;

import android.graphics.Rect;
import android.os.Handler;
import android.view.View;


public class TieFighter{
    View TieFighter;
    float x, y, delta_x, delta_y;
    Rect hitBox;

    public TieFighter(View TieFighter){
        this.TieFighter = TieFighter;
        setSize(200);
        setHitBox();
    }



    Handler handlerForMovingTie = new Handler();
    Runnable movingTie = new Runnable() {
        @Override
        public void run() {
            handlerForMovingTie.postDelayed(this, 10);
            System.out.println("Updating Tie Fighter Position");
            updateTieFighterPosition(); // Update TieFighter position
        }
    };

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


    /** First try using a HitBox : didn't work :(**/
    /**
    public void setHitBox() {
        this.hitBox = new Rect(
                this.TieFighter.getLeft(),
                this.TieFighter.getTop(),
                this.TieFighter.getRight(),
                this.TieFighter.getBottom()
        );
    }
     **/

    public void updateY(float delta_y) {
        this.y =+ delta_y;
        TieFighter.setY(this.y);
    }

    public void updateX(float delta_x) {
        this.x =+ delta_x;
        TieFighter.setX(this.x);
    }

    public void updateDelta(float delta_x, float delta_y){
        this.delta_x = delta_x;
        this.delta_y = delta_y;
    }

    public void updateTieFighterPosition(){
        updateX(this.delta_x);
        updateY(this.delta_y);
    }

    public void setSize(int size) {
        this.TieFighter.getLayoutParams().height = size;
        this.TieFighter.getLayoutParams().width = size;
    }
}
