package com.keyalp.bluetoothmediacarcontrol;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MusiController extends AppCompatActivity {

    private AudioManager myaud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller);

        myaud = (AudioManager)getSystemService(Context.AUDIO_SERVICE);          //Objecte utilitzat per controlar la música.
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);   //Flag que Evita que s'apagui la pantalla.
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btnplay_pause:
                playPause();
                break;
            case R.id.btnprevious:
                previousFunc();
                break;
            case R.id.btnnext:
                nextFunc();
                break;
        }
    }

    private void playPause() {
        long eventtime = SystemClock.uptimeMillis();
        KeyEvent downEvent = new KeyEvent(eventtime, eventtime, KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE, 0);
        myaud.dispatchMediaKeyEvent(downEvent);
        KeyEvent upEvent = new KeyEvent(eventtime, eventtime, KeyEvent.ACTION_UP, KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE, 0);
        myaud.dispatchMediaKeyEvent(upEvent);

    }

    private void previousFunc(){
        long eventtime = SystemClock.uptimeMillis();
        KeyEvent downEvent = new KeyEvent(eventtime, eventtime, KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_MEDIA_PREVIOUS, 0);
        myaud.dispatchMediaKeyEvent(downEvent);
    }

    private void nextFunc(){
        long eventtime = SystemClock.uptimeMillis();
        KeyEvent downEvent = new KeyEvent(eventtime, eventtime, KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_MEDIA_NEXT, 0);
        myaud.dispatchMediaKeyEvent(downEvent);

    }



    @Override
    public void onBackPressed() {
        finish();
    }
}
