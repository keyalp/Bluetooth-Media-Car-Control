package com.keyalp.bluetoothmediacarcontrol;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MusiController extends AppCompatActivity {

    private AudioManager myaud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller);
        myaud = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
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
        if(myaud.isMusicActive()) {
            Toast.makeText(this, "Si s'esta reproduint", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this, "No s'esta reprodruint", Toast.LENGTH_LONG).show();
        }
    }

    private void previousFunc(){
        Toast.makeText(this, "PREVIOUS SONG TET", Toast.LENGTH_LONG).show();
    }

    private void nextFunc(){
        Toast.makeText(this, "NEXT SONG TET", Toast.LENGTH_LONG).show();
    }



    @Override
    public void onBackPressed() {
        finish();
    }
}
