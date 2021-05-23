package com.prakriti.aidlapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
// CLIENT
    // use Remote Service

    private ServiceConnection serviceConnection; // interface
    private AIDLMultiplyInterface myInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText edtFirstNumber = findViewById(R.id.edtFirstNumber);
        EditText edtSecondNumber = findViewById(R.id.edtSecondNumber);
        TextView txtResult = findViewById(R.id.txtResult);

        // bind client Main Activity w/ service MultiplicationService
        Intent multiplyIntent = new Intent(this, MultiplicationService.class);

        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                // init interface object
                myInterface = AIDLMultiplyInterface.Stub.asInterface(service); // connects Service to this interface
            }
            @Override
            public void onServiceDisconnected(ComponentName name) {}
        };
        // pass to bindService
        bindService(multiplyIntent, serviceConnection, Context.BIND_AUTO_CREATE);

        // button
        findViewById(R.id.btnMultiply).setOnClickListener(view -> {
            long firstNum = Integer.parseInt(edtFirstNumber.getText().toString());
            long secondNum = Integer.parseInt(edtSecondNumber.getText().toString());
            try {
                long product = myInterface.multipleTwoNumbers(firstNum, secondNum); // throws RemoteException (in Service class)
                txtResult.setText(product + "");
            }
            catch (RemoteException e) {
                e.printStackTrace();
            }
        });
    }

}