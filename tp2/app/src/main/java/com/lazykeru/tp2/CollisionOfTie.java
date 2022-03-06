package com.lazykeru.tp2;

import android.graphics.Rect;
import android.os.Handler;

public class CollisionOfTie {
    TieFighter tieFighter;
    Asteroid asteroid;
    public CollisionOfTie(TieFighter tieFighter, Asteroid asteroid){
        setTieFighter(tieFighter);
        setAsteroid(asteroid);
    }

    Handler handlerForCollison = new Handler();
    Runnable collision = new Runnable() {
        @Override
        public void run() {
            handlerForCollison.postDelayed(this, 10);
            System.out.println("Updating the hitbox of the two entities");
            updateTheirHitBox();
            System.out.println("Checking for collision");
            if(areTheyInCollision() == true){
                System.out.println("Boom...");
            }
        }
    };

    public void updateTheirHitBox(){
        this.tieFighter.setHitBox();
        this.asteroid.setHitBox();
    }

    public boolean areTheyInCollision(){
        return(this.tieFighter.hitBox.intersect(this.asteroid.hitBox));
    }

    public void setAsteroid(Asteroid asteroid) {
        this.asteroid = asteroid;
    }

    public void setTieFighter(TieFighter tieFighter) {
        this.tieFighter = tieFighter;
    }
}
