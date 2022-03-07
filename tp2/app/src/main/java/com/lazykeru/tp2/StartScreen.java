package com.lazykeru.tp2;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class StartScreen extends Fragment{
    private Button startButton;
    private MainActivity mainActivity;

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ){
        View view = inflater.inflate(R.layout.screen_start, container, false);
        startButton = (Button) view.findViewById(R.id.start_button);
        startButton.setText("Start");
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame();
            }
        });
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

    public void startGame(){
        startButton.setVisibility(View.INVISIBLE);
        this.mainActivity.startGame();
    }

    public void endGame(){
        startButton.setVisibility(View.VISIBLE);
        startButton.setText("Game over Restart");
    }
}
