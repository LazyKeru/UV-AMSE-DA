package com.lazykeru.tp2;

import android.animation.ObjectAnimator;
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
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ){
        View view = inflater.inflate(R.layout.screen_play, container, false);
        ImageView asteroid1 = view.findViewById(R.id.ast1);
        //ObjectAnimator animation = ObjectAnimator.ofFloat(asteroid1, "translationX", 100f);
        //animation.setDuration(2000);
        //animation.start();
        Asteroid ast1 = new Asteroid(asteroid1);
        ast1.startAnimation();
        /** TieFighter init **/
        ImageView tFight_alive = view.findViewById(R.id.tieFighter);
        ImageView tFight_dead = view.findViewById(R.id.explosion);
        TieFighter tieFighter = new TieFighter(
                tFight_alive,
                tFight_dead
        );
        //To Do
        ImageView jOut = view.findViewById(R.id.jOut); //Joystick image outside
        ImageView jCent = view.findViewById(R.id.jCent); // Joystick image center
        Joystick joystick = new Joystick(
                jOut,
                jCent,
                tieFighter
        );
        CollisionOfTie ast1Tie = new CollisionOfTie(tieFighter, ast1);
        ast1Tie.collision.run();
        return view;

    }
}
