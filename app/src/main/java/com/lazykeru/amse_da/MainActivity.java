package com.lazykeru.amse_da;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.widget.TextView;

public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        /** Showing our text **/
        setContentView(t);
    }
}
