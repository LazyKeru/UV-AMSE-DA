package com.lazykeru.tp2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class StartScreen extends Fragment {

    View.OnClickListener clickListener = (View.OnClickListener) new View.OnClickListener(){
        public void onClick(View view) {
            Button button = (Button) view.findViewById(R.id.start_button);
            button.setVisibility(View.INVISIBLE);
        }
    };

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ){
        View view = inflater.inflate(R.layout.screen_start, container, false);
        Button button = (Button) view.findViewById(R.id.start_button);
        button.setText("Start");
        button.setOnClickListener(this.clickListener);
        return view;

    }
}
