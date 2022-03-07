package com.lazykeru.tp2;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    String _title = "Star Wars TP2";
    boolean isOver = true;
    /**
     * Tells us the state of the game
     * true : Game is ready to start
     * false : Game is live
     **/
    private SensorManager mSensorManager;
    private Sensor accelerometer;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Tool bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(_title);
        // on recupere l accelerometre a partir du SensorManager
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        // on associe l ecouteur d’evenements au SensorManager
        accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float gammaX = event.values[0], gammaY = event.values[1], gammaZ = event.values[2];
        System.out.println("Valeurs accelerometre:" + gammaX + "," + gammaY + "," + gammaZ);
        PlayScreen _playScreen
                = (PlayScreen) this.getSupportFragmentManager()
                .findFragmentById(R.id.play_screen);
        _playScreen.updateAccelerometer(gammaX, gammaY, gammaZ);
    }

    // Function so StartScreen fragment can tell the PlayScreen fragment that we are playing
    public void startGame(){
        isOver = false;
        PlayScreen _playScreen
                = (PlayScreen) this.getSupportFragmentManager()
                .findFragmentById(R.id.play_screen);
        _playScreen.StartGame();
    }

    // Function so PlayScreen fragment can tell the StartScreen fragment that we are dead
    public void endGame(){
        isOver = true;
        StartScreen _startScreen
                = (StartScreen) this.getSupportFragmentManager()
                .findFragmentById(R.id.start_screen);
        _startScreen.endGame();
    }
}