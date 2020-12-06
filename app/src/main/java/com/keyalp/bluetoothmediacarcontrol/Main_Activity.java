package com.keyalp.bluetoothmediacarcontrol;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
public class Main_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view){
        Intent mIntent = new Intent(this,MusiController.class);

        switch (view.getId()){
            case R.id.btnconnect:
                bluetoothConnect(true);
                break;
            case R.id.btnstopconnect:
                bluetoothConnect(false);
                break;
            case R.id.btnmcontroller:
                startActivity(mIntent);
                break;
        }
    }

    /*public void onBackPressed() {
        setContentView(R.layout.bluetooth_manager);
    }*/
    
    private void bluetoothConnect(Boolean con){
    }


}

