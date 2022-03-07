package com.lazykeru.tp2;

import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.graphics.Rect;
import android.view.animation.Animation;
import android.widget.ImageView;


public class Asteroid {
    int size;
    int duration;
    Path path = new Path();
    ObjectAnimator asteroidAnimation;
    ImageView asteroid;
    Rect hitBox;

    /** CONSTRUCTOR START **/

    // For custom path following Asteroid
    public Asteroid(ImageView asteroid, int size, Path path, int duration){
        // Default asteroid view
        this.asteroid = asteroid;
        // default asteroid path
        setPath(path);
        // custom Path
        setAsteroidAnimation(
            ObjectAnimator.ofFloat(
                    asteroid,
                    "translationX", // x Property Name
                    "translationY", // y Property Name
                    this.path// Path the loop will follow
            )
        );
        // Default asteroid size
        setSize(size);
        // Default asteroid size
        setDuration(duration);
        //setAsteroidAnimation(ObjectAnimator.ofFloat(this.asteroid, "translationX", 100f));
    }

    // For straight line following Asteroid
    public Asteroid(ImageView asteroid, int x, int y, int size, float translationX, int duration){
        // Default asteroid view
        this.asteroid = asteroid;
        setSize(size);
        asteroid.setX(x);
        asteroid.setY(y);
        // default asteroid path
        setPath(path);
        // straight asteroid animation
        setAsteroidAnimation(
                ObjectAnimator.ofFloat(
                        asteroid,
                        "translationX", // x Property Name
                        translationX
                )
        );
        setDuration(duration);
    }

    /** CONSTRUCTOR END **/

    /** SETTER START **/

    public void setHitBox() {
        int [] posistion = new int[2];
        this.asteroid.getLocationOnScreen(posistion);
        this.hitBox = new Rect(
                posistion[0],
                posistion[1],
                posistion[0] + this.asteroid.getMeasuredWidth(),
                posistion[1] + this.asteroid.getMeasuredHeight()
        );
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

    public void moveStaticAsteroid(float x, float y) {
        // set the new cords
        this.asteroid.setX(x);
        // set the new cords
        this.asteroid.setY(y);
    }

    /** METHODS END **/
}