package com.keyalp.bluetoothmediacarcontrol;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothHeadset;
import android.content.Context;
import android.content.Intent;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class Main_Activity extends AppCompatActivity {

    BluetoothHeadset bluetoothHeadset;
    // Get the default adapter
    BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    TextView consola;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //consola = (TextView) findViewById((R.id.consola));

    }

    public void onClick(View view) throws IOException {
        Intent mIntent = new Intent(this,MusiController.class);
        AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

        switch (view.getId()){
            case R.id.btnconnect:
                if(openConnection(audioManager))
                    popUpText("Connection Already ON!");
                else
                    popUpText("Opening Connection");
                break;
            case R.id.btnstopconnect:
                if(closeConnection(audioManager))
                    popUpText("Connection Already OFF!");
                else
                    popUpText("Closing Connection");
                break;
            case R.id.btnmcontroller:
                startActivity(mIntent);
                break;
        }
    }


    //Method that opens the bluetooth connection
    private boolean openConnection(AudioManager audioManager){
        //Check if the connection it is already active
        if(audioManager.isBluetoothScoOn())
            return true;
        //Start Bluetooth SCO
        audioManager.startBluetoothSco();       //Start SCO environment
        audioManager.setBluetoothScoOn(true);   //Set SCO flag to true
        return false;
    }

    //Method that closes the bluetooth connection
    private boolean closeConnection(AudioManager audioManager){
        //Check if the connection it NOT active.
        if(!audioManager.isBluetoothScoOn()) {
            return true;
        }else {
            //Stop Bluetooth SCO
            audioManager.setBluetoothScoOn(false);  //Set SCO flag to false
            audioManager.stopBluetoothSco();        //Stop SCO environment
            return false;
        }
    }

    //Method that shows a floating message to the user
    private void popUpText(String text){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

}

