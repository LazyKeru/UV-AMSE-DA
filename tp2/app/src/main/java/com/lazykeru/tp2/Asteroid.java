package com.lazykeru.tp2;

import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.view.animation.Animation;
import android.widget.ImageView;


public class Asteroid {
    float x;
    float y;
    int size;
    int duration;
    Path path = new Path();
    ObjectAnimator asteroidAnimation;
    ImageView asteroid;

    /** CONSTRUCTOR START **/

    public Asteroid(ImageView asteroid){
        // Default asteroid view
        this.asteroid = asteroid;
        // default asteroid path
        path.arcTo(
                0f,
                0f,
                1000f,
                1000f,
                0f,
                -359f,
                true
        );
        // Default asteroid animation
        setAsteroidAnimation(
            ObjectAnimator.ofFloat(
                    asteroid,
                    "translationX", // x Property Name
                    "translationY", // y Property Name
                    this.path // Path the loop will follow
            )
        );
        // Default asteroid size
        setSize(250);
        // Default asteroid size
        setDuration(10000);
        // Default Cord X and Y
        setX(1000f);
        setY(1000f);
        //setAsteroidAnimation(ObjectAnimator.ofFloat(this.asteroid, "translationX", 100f));
    }

    /** CONSTRUCTOR END **/

    /** SETTER START **/

    public void setX(float x) {
        this.x = x;
        // set the new cords
        this.asteroid.setX(x);
    }

    public void setY(float y) {
        this.y = y;
        // set the new cords
        this.asteroid.setY(y);
    }

    public void setSize(int size) {
        this.size = size;
        this.asteroid.getLayoutParams().height = size;
        this.asteroid.getLayoutParams().width = size;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public void setAsteroidAnimation(ObjectAnimator asteroidAnimation) {
        this.asteroidAnimation = asteroidAnimation;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    /** SETTER END **/

    /** METHODS START **/

    public void startAnimation(){
        this.asteroidAnimation.setDuration(this.duration);
        this.asteroidAnimation.setRepeatCount(Animation.INFINITE);
        this.asteroidAnimation.start();
    }

    /** METHODS END **/
}