package com.keyalp.bluetoothmediacarcontrol;

import android.bluetooth.BluetoothAdapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;


public class Main_Activity extends AppCompatActivity {

    // Get the default adapter
    private BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    private TextView status_text;
    private int REQUEST_ENABLE_BT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkBluetoothConnection();
        status_text = (TextView) findViewById((R.id.status_text));
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

    //Method that opens the SCO bluetooth connection
    // return false if connection already on
    // return true if connection is established
    private boolean openConnection(AudioManager audioManager){
        //Check if the connection it is already active
        if(!audioManager.isBluetoothScoOn()) {
            //Start Bluetooth SCO
            audioManager.startBluetoothSco();       //Start SCO environment
            audioManager.setBluetoothScoOn(true);   //Set SCO flag to true
        //Check if the connection it's really established
        }else if(audioManager.isBluetoothScoOn()){
            setStatus(true);
            return true;
        }
        return false;
    }

    //Method that closes the SCO bluetooth connection
    // returns true if connection is already closed
    // returns false if connection is closed successfully
    private boolean closeConnection(AudioManager audioManager){
        if(audioManager.isBluetoothScoOn()) {
            //Stop Bluetooth SCO
            audioManager.setBluetoothScoOn(false);  //Set SCO flag to false
            audioManager.stopBluetoothSco();        //Stop SCO environment
        // Check if the connection it's successfully closed
        }else if (!audioManager.isBluetoothScoOn()) {
            setStatus(false);
            return false;
        }
        return true;
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

    void setStatus(Boolean status){
        if (status) {
            status_text.setTextColor(Color.GREEN);
            status_text.setText("Status: CONNECTED");
        }else {
            status_text.setTextColor(Color.RED);
            status_text.setText("Status: DISCONNECTED");
        }
    }



}

