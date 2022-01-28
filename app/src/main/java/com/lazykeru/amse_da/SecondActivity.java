package com.lazykeru.amse_da;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lazykeru.amse_da.R;

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState );
        /** Init Layout Layer **/
        LinearLayout l = new LinearLayout(this);
        l.setOrientation(LinearLayout.VERTICAL);
        /** Init the TextView class **/
        TextView t = new TextView(this);
        /** Writting the content **/
        t.setText("Hello World !");
        /** Setting the size and color of our text **/
        t.setTextSize((float) 50.);
        t.setTextColor(Color.BLUE);
        /** We are setting the position of the text **/
        t.setX(100);
        t.setY(140);

        Intent i = getIntent();
        String m = i.getStringExtra ("message");
        t.setText (m+"");

        /** Adding our text to the Layout **/
        l.addView(t);
        setContentView(l);
    }
}