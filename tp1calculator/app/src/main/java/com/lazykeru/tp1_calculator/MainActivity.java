package com.lazykeru.tp1_calculator;

import static java.lang.Integer.parseInt;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;
import com.lazykeru.tp1_calculator.databinding.ActivityMainBinding;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static int nButtons = 16;
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Init Calculator
        Calculator _cal = new Calculator();
        // Init Screen
        TextView screen = (TextView) findViewById(R.id.screen);
        screen.setTextSize(20);
        screen.setText("Enter a simple operation");
        // Finding all the buttons and assigning awesome things
        // [ b0, b1, b2, b3, b4, b5, b6, b7, b8, b9 ] numbers 0 to 9
        // [ b10, b11, b12, b13 ] operators /, *, -, +
        // [ b14 ] the coma ,
        // [ b15 ] equal sign =
        Button[] calculator = new Button[16];
        for (int i = 0; i < 16; i++) {
            int finalI = i;
            String temp_id = "b" + String.valueOf(i);
            int id = getResources().getIdentifier(temp_id, "id", getPackageName());
            calculator[i] = (Button)  findViewById(id);
            // Associating each Buttons with an event listener
            if(i<0) Log.e("Main: ", "id of the button is smaller than 0");
                // [ b0, b1, b2, b3, b4, b5, b6, b7, b8, b9 ] numbers 0 to 9
            else if(i<10)
                calculator[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        screen.setText(_cal.valClick(finalI));
                    }
                });
            // [ b10, b11, b12, b13 ] operators /, *, -, +
            else if(i < 14)
                calculator[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        screen.setText(_cal.opClick(finalI));
                    }
                });
            // [ b14 ] the coma ,
            else if(i==14)
                calculator[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        screen.setText(_cal.comaClick());
                    }
                });
            // [ b15 ] equal sign =
            else if(i==15)
                calculator[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        screen.setText(_cal.resClick());
                        _cal.reset();
                    }
                });
            // i out of bound
            else if(i>15){
                Log.e("Main: ", "id of the button is greater than 15");
            }
        }
        // Reset button
        Button reset = (Button) findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                screen.setText("Enter a simple operation");
                _cal.reset();
            }
        });

    }
}

class Calculator{
    // val1 op val2 = res
    String val1 = "";
    String val2 = "";
    String op = "";
    String res = "";

    // [ b0, b1, b2, b3, b4, b5, b6, b7, b8, b9 ] numbers 0 to 9
    String valClick(int i){
        Log.i("Calculator: ","value " + String.valueOf(i) + " was clicked");
        if(this.op.isEmpty() == true) return this.val1 = this.val1 + String.valueOf(i);
        if(this.op.isEmpty() != true) return this.val2 = this.val2 + String.valueOf(i);
        // reset before returning error:
        this.reset();
        return "Unknown Error";
    }

    // [ b10, b11, b12, b13 ] operators /, *, -, +
    String opClick(int i){
        Log.i("Calculator: ","operator " + String.valueOf(i) + " was clicked");
        String e = new String();
        if (this.op.isEmpty() != true) e = "Error: we already selected an operator";
        else if (this.val1.isEmpty() == true) e = "Error: tried to use an operator with no value before";
        else if (this.val2.isEmpty() != true) e = "Error: tried to add multiple value";
        else if (this.val1.isEmpty() != true && this.val2.isEmpty() == true){
            this.op = String.valueOf(i);
            if(parseInt(this.op) == 10) return "/";
            if(parseInt(this.op) == 11) return "*";
            if(parseInt(this.op) == 12) return "-";
            if(parseInt(this.op) == 13) return "+";
        }
        // reset before returning error:
        this.reset();
        return e;
    }

    // [ b14 ] the coma ,
    String comaClick(){
        Log.i("Calculator: ","value , was clicked");
        if(this.op.isEmpty() == true && this.val1.isEmpty() == true) return this.val1 = this.val1 + "0.";
        if(this.op.isEmpty() == true && this.val1.isEmpty() != true) return this.val1 = this.val1 + ".";
        if(this.op.isEmpty() != true && this.val2.isEmpty() == true) return this.val2 = this.val2 + "0.";
        if(this.op.isEmpty() != true && this.val2.isEmpty() != true) return this.val2 = this.val2 + ".";
        // reset before returning error:
        this.reset();
        return "Unknown Error";
    }

    // [ b15 ] equal sign =, rappel 10-13 represents the value that our operator can take
    String resClick(){
        Log.i("Calculator: ","Calculating");
        if( this.val1.isEmpty() != true && this.val1.isEmpty() != true && this.op.isEmpty() != true && this.res.isEmpty() == true){
            if(parseInt(this.op) == 10) return this.res = String.valueOf(Double.parseDouble(this.val1) / Double.parseDouble(this.val2));
            if(parseInt(this.op) == 11) return this.res = String.valueOf(Double.parseDouble(this.val1) * Double.parseDouble(this.val2));
            if(parseInt(this.op) == 12) return this.res = String.valueOf(Double.parseDouble(this.val1) - Double.parseDouble(this.val2));
            if(parseInt(this.op) == 13) return this.res = String.valueOf(Double.parseDouble(this.val1) + Double.parseDouble(this.val2));
            // reset before returning error:
            this.reset();
            return "Error: no right op";
        }
        // reset before returning error:
        this.reset();
        return "Unknown Error";
    }

    // Reset value
    void reset(){
        this.val1 = "";
        this.val2 = "";
        this.op = "";
        this.res = "";
    }
}