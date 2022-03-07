package com.lazykeru.tp2;


import android.view.MotionEvent;
import android.view.View;

public class Joystick {
    View padOutline; // will not move (fixed)
    View padCenter; // will move
    float origin_x; // Origin position y of pad center
    float origin_y; // Origin position x of pad center
    float fx; // Position y of finger
    float fy; // Position x of finger
    float delta_fx; // starting delta y of finger
    float delta_fy; // starting delta x of finger
    TieFighter tieFighter;
    boolean active;

    public Joystick(View padOutline, View padCenter,TieFighter tieFighter) {
        this.padCenter = padCenter;
        this.padOutline = padOutline;
        // Origin
        this.padCenter.setOnTouchListener((v, e) -> {
                return onTouch(v, e);
            }
        );
        this.tieFighter = tieFighter;
        this.tieFighter.movingTie.run(); // Starts the runnable to move the TieFighter
        this.setInactive(); // The game has not yet started
    }

    public void setInactive(){
        this.padCenter.setVisibility(View.INVISIBLE);
        this.padOutline.setVisibility(View.INVISIBLE);
        this.active = false;
    }

    public void setActive(){
        this.padCenter.setVisibility(View.VISIBLE);
        this.padOutline.setVisibility(View.VISIBLE);
        this.active = true;
    }

    public void startGame(){
        this.setActive();
        // this.tieFighter.movingTie.run(); // Starts the runnable to move the TieFighter will be herre once we stop is in stop game
        // Could add more functions to call when starting the game
    }

    public void stopGame(){
        this.setInactive();
        // pad didn't reset when the joystick dissapeared : so the ACTION_CANCEL doesn't work in this scenario
        this.setPadDefaultPosition(); //reset pad to default position
        this.tieFighter.setIsMoving(false); //set movement to false
        // this.tieFighter.movingTie.stop(); // need to add a function that could stop the runnable !
        // Could add more functions to call when starting the game
    }

    public  void setPadDefaultPosition(){
        this.padCenter.setX(this.origin_x);
        this.padCenter.setY(this.origin_y);
    }

    public  void initPadDefaultPosition(){
        this.origin_x = this.padCenter.getX();
        this.origin_y = this.padCenter.getY();
    }

    public void setPadPosition(float x, float y) {
        float[] XBorders = new float[] {
                this.padOutline.getX() - (float)(0.15 * this.padOutline.getWidth()),
                this.padOutline.getX()+ this.padOutline.getWidth() - this.padCenter.getWidth()+(float)(0.15 * this.padOutline.getWidth())};
        float[] YBorders = new float[] {
                this.padOutline.getY() - (float)(0.15 * this.padOutline.getHeight()),
                this.padOutline.getY()+ this.padOutline.getHeight()- this.padCenter.getHeight()+(float)(0.15 * this.padOutline.getHeight())};
        if((x < XBorders[1]) && (XBorders[0] < x)){
            this.padCenter.setX(x);
        }
        if((y < YBorders[1]) && (YBorders[0] < y)){
            this.padCenter.setY(y);
        }
        return;
    }

    public void updateLatestFingerPosition(MotionEvent e){
        this.fx = e.getRawX();
        this.fy = e.getRawY();
    }

    // Called after the first updateLatestFingerPosition
    public void setDeltaOfFingerPosition(){
        this.delta_fx = this.padCenter.getX() - this.fx;
        this.delta_fy = this.padCenter.getY() - this.fy;
    }

    public boolean onTouch(View v, MotionEvent e){
        if(this.active==false){return false;}
        switch (e.getAction()){
            case MotionEvent.ACTION_DOWN:
                initPadDefaultPosition(); // Not the cleanest way to get the initial X and Y
                updateLatestFingerPosition(e);
                setDeltaOfFingerPosition();
                break;
            case MotionEvent.ACTION_MOVE:
                updateLatestFingerPosition(e);
                setPadPosition(
                        this.fx + this.delta_fx,
                        this.fy + this.delta_fy
                );
                tieFighter.updateDelta(
                        (this.padCenter.getX() - this.origin_x) / 5,
                        (this.padCenter.getY() - this.origin_y) / 5
                );
                this.tieFighter.setIsMoving(
                        true
                ); // the ship should move
                break;
            case MotionEvent.ACTION_CANCEL: // finger exits the screen surface
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                setPadDefaultPosition();
                this.tieFighter.setIsMoving(
                        false
                ); // the ship shouldn't move
                break;
            default:
                return false;

        }
        return true; // safe guard
    }
}
