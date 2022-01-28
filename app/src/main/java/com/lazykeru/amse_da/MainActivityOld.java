package com.lazykeru.amse_da;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivityOld extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** Init Layout Layer **/
        LinearLayout l1 = new LinearLayout(this);
        l1.setOrientation(LinearLayout.VERTICAL);

        /** Init the TextView class **/
        TextView t1 = new TextView(this);
        /** Writting the content **/
        t1.setText("Hello World !");
        /** Setting the size and color of our text **/
        t1.setTextSize((float) 50.);
        t1.setTextColor(Color.BLUE);
        /** We are setting the position of the text **/
        t1.setX(100);
        t1.setY(140);
        /** Adding our text to the Layout **/
        l1.addView(t1);

        /** Init the TextView class **/
        TextView t2 = new TextView(this);
        /** Writting the content **/
        t2.setText("Damn boy !");
        /** Setting the size and color of our text **/
        t2.setTextSize((float) 40.);
        t2.setTextColor(Color.BLUE);
        /** We are setting the position of the text **/
        t2.setX(150);
        t2.setY(100);
        /** Adding our text to the Layout **/
        l1.addView(t2);

        /** Show Layout **/
        setContentView(l1);
    }
}
