package com.lazykeru.amse_da;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

class ZoneAnimation extends View {
    Paint paint;
    int balleX, balleY;

    public ZoneAnimation(Context context){
        super(context);
        paint = new Paint();
        paint. setAntiAlias (true);
        balleX = 50;
        balleY = 50;
    }

    protected void onDraw(Canvas canvas) {
        try {
            Thread.sleep(10); // a entourer d’un try ... catch: proposition automatique par eclipse
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        canvas.drawCircle (balleX, balleY,10, paint );
        balleX++;
        balleY++;
        invalidate ();
    }
}


public class Animation extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** Init Layout Layer **/
        LinearLayout l1 = new LinearLayout(this);
        l1.setOrientation(LinearLayout.VERTICAL);
        /** New Zone animation **/
        ZoneAnimation z = new ZoneAnimation(this);
        l1.addView(z);
        setContentView(l1);
    }
}