package com.lazykeru.amse_da;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.lazykeru.amse_da.SecondActivity;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState );
        /** Init Layout Layer **/
        LinearLayout R = new LinearLayout(this);
        R.setOrientation(LinearLayout.VERTICAL);
        /** Init a Button class **/
        Button b = new Button(this);
        /** Para of the button **/
        b.setText ("Send Request");
        EditText e = new EditText(this);
        b.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent start = new Intent(MainActivity.this, SecondActivity.class);
                start.putExtra("message", e.getText().toString());
                startActivity(start);
            }
        });
        R.addView(b);
        R.addView(e);
        setContentView(R);
    }
}

