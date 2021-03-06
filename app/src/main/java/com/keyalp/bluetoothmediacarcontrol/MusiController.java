package com.keyalp.bluetoothmediacarcontrol;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class MusiController extends AppCompatActivity {

    private AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller);

        audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);   //This Object is utilized for manage the music controls//

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);   //Flag used to keep the screen on.
        setScreenBrightness(0); //Set screen brightness to the minimum and save battery

    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_universal:
                universalAction();
                break;
        }
    }

    private void universalAction() {
        long eventtime = SystemClock.uptimeMillis();
        //Play if Paused
        KeyEvent downEvent = new KeyEvent(eventtime, eventtime, KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE, 0);
        audioManager.dispatchMediaKeyEvent(downEvent);
        //Pause If Playing
        KeyEvent upEvent = new KeyEvent(eventtime, eventtime, KeyEvent.ACTION_UP, KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE, 0);
        audioManager.dispatchMediaKeyEvent(upEvent);

    }


    //Method used to set a custom screen brightness (0 min / 1 max)
    public void setScreenBrightness(float brightness){
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.screenBrightness = brightness;
        getWindow().setAttributes(lp);
    }

}
