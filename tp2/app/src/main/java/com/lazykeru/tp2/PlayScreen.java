package com.lazykeru.tp2;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.lazykeru.tp2.Asteroid;
import com.lazykeru.tp2.TieFighter;

import androidx.fragment.app.Fragment;

public class PlayScreen extends Fragment {

    private Joystick joystick;
    private TieFighter tieFighter;
    private MainActivity mainActivity;

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ){
        View view = inflater.inflate(R.layout.screen_play, container, false);
        /** Asteroid1 init START **/
        ImageView asteroid1 = view.findViewById(R.id.ast1);
        //ObjectAnimator animation = ObjectAnimator.ofFloat(asteroid1, "translationX", 100f);
        //animation.setDuration(2000);
        //animation.start();
        Asteroid ast1 = new Asteroid(asteroid1);
        ast1.startAnimation();
        /** Asteroid2 init START **/
        /** TieFighter init START **/
        ImageView tFight_alive = view.findViewById(R.id.tieFighter);
        ImageView tFight_dead = view.findViewById(R.id.explosion);
        tieFighter = new TieFighter(
                tFight_alive,
                tFight_dead,
                (this)
        );
        /** TieFighter init END **/
        /** Joystick init START **/
        ImageView jOut = view.findViewById(R.id.jOut); //Joystick image outside
        ImageView jCent = view.findViewById(R.id.jCent); // Joystick image center
        joystick = new Joystick(
                jOut,
                jCent,
                tieFighter
        );
        /** Joystick init END **/
        /** Collision init START **/
        CollisionOfTie ast1Tie = new CollisionOfTie(tieFighter, ast1);
        ast1Tie.collision.run();
        /** Collision init END **/
        return view;
    }

    // Called when a fragment is first attached to its context.
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof MainActivity) {
            this.mainActivity = (MainActivity) context;
        }
    }


    // Function called from main activity, will be used to be called from the child StartScreen fragment
    public void StartGame(){
        this.joystick.startGame();
        this.tieFighter.startGame();
    }

    public  void EndGame(){
        this.mainActivity.endGame();
    }

    // Function called from TieFighter to warn us of a collision
    public void Collision(){
        this.joystick.stopGame();
        this.tieFighter.stopGame();
        EndGame();
    }
}
