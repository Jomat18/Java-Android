package com.tutsplus.matt.bluetoothscanner;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by User on 6/3/2015.
 */
public class ConnectThread extends Thread{

    private BluetoothSocket bTSocket;
    private BluetoothDevice bTDevice;
    private UUID DEFAULT_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");


    public ConnectThread(BluetoothDevice bTDevice, UUID UUID) {
        BluetoothSocket tmp = null;
        this.bTDevice = bTDevice;

        try {
            tmp = this.bTDevice.createRfcommSocketToServiceRecord(UUID);
            //tmp = bTDevice.createRfcommSocketToServiceRecord(this.bTDevice.getUuids()[0].getUuid());
        }
        catch (IOException e) {
            Log.d("CONNECTTHREAD", "Could not start listening for RFCOMM");
        }
        bTSocket = tmp;
    }

    public boolean connect(BluetoothDevice bTDevice, UUID mUUID) {
        this.bTDevice = bTDevice;
        BluetoothSocket temp = null;

        Log.i("CONNECTTHREAD","Device Name: " + bTDevice.getName());
        Log.i("CONNECTTHREAD", "Device UUID: " + bTDevice.getUuids()[0].getUuid());

        try {
            temp = bTDevice.createRfcommSocketToServiceRecord(mUUID);
            //temp = bTDevice.createRfcommSocketToServiceRecord(bTDevice.getUuids()[0].getUuid());
        } catch (IOException e) {
            Log.d("CONNECTTHREAD","Could not create RFCOMM socket:" + e.toString());
            return false;
        }

        //BTAdapter.cancelDiscovery();
        bTSocket = temp;

        try {
            bTSocket.connect();
        } catch(IOException e) {
            Log.d("CONNECTTHREAD","Could not connect: " + e.toString());
            try {
                bTSocket.close();
            } catch(IOException close) {
                Log.d("CONNECTTHREAD", "Could not close connection:" + e.toString());
                return false;
            }
        }

        return true;
    }

    public boolean cancel() {
        try {
            bTSocket.close();
        } catch(IOException e) {
            return false;
        }
        return true;
    }

}
