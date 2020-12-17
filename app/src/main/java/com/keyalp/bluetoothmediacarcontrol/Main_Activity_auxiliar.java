package com.keyalp.bluetoothmediacarcontrol;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;


public class Main_Activity_auxiliar extends AppCompatActivity {

    // Get the default adapter
    private BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    private int REQUEST_ENABLE_BT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkBluetoothConnection();
    }

    public void onClick(View view) throws IOException {
        Intent mIntent = new Intent(this,MusiController.class);
        AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

        switch (view.getId()){
            case R.id.btnConnect:
                if(checkBluetoothConnection())
                    if(openConnection(audioManager))
                        popUpText("Connection Already ON!");
                    else
                        popUpText("Opening Connection");
                break;
            case R.id.btnDisconnect:
                if(closeConnection(audioManager))
                    popUpText("Connection Already OFF!");
                else
                    popUpText("Closing Connection");
                break;
            case R.id.btnMController:
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
        //Check if the connection is NOT active.
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

    //This method shows a pop up indicating to the user if wants to activate the bluetooth module
    private boolean checkBluetoothConnection(){
        if (!bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            return false;
        }else
            return true;
    }



}

