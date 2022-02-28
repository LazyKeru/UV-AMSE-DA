package com.lazykeru.tp2;

import android.animation.ObjectAnimator;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.lazykeru.tp2.Asteroid;

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
        ImageView jOut = view.findViewById(R.id.jOut);
        ImageView jCent = view.findViewById(R.id.jCent);
        Joystick joystick = new Joystick(
                jOut,
                jCent,
                1000,
                1000
        );
        return view;

    }
}
