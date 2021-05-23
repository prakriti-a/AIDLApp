package com.prakriti.aidlapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class MultiplicationService extends Service {

    public MultiplicationService() {}

    @Override
    public IBinder onBind(Intent intent) {
        // returns the communication channel to the service
        //        throw new UnsupportedOperationException("Not yet implemented");
        return myBinder;
    }

    // myBinder init
    AIDLMultiplyInterface.Stub myBinder = new AIDLMultiplyInterface.Stub() {
        @Override
        public long multipleTwoNumbers(long first, long second) throws RemoteException {
            // handle exception
            return first * second;
        }
    };
}