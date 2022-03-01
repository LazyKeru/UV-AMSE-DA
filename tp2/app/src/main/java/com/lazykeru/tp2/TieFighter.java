package com.lazykeru.tp2;

import android.view.View;


public class TieFighter {
    View TieFighter;
    float x, y;
    public TieFighter(View TieFighter){
        this.TieFighter = TieFighter;
        setSize(200);
    }

    public void updateY(float delta_y) {
        this.y =+ delta_y;
        TieFighter.setY(this.y);
    }

    public void updateX(float delta_x) {
        this.x =+ delta_x;
        TieFighter.setX(this.x);
    }

    public void updateTieFighterPosition(float delta_x, float delta_y){
        updateX(delta_x);
        updateY(delta_y);
    }

    public void setSize(int size) {
        this.TieFighter.getLayoutParams().height = size;
        this.TieFighter.getLayoutParams().width = size;
    }
}
