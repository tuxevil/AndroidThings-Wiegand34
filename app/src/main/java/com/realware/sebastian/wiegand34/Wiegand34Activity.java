package com.realware.sebastian.wiegand34;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.things.pio.Gpio;
import com.google.android.things.pio.GpioCallback;
import com.google.android.things.pio.PeripheralManagerService;

import java.io.IOException;

/**
 * Created by Sebastian on 16/12/2016.
 */

public class Wiegand34Activity extends Activity {
    private static final String TAG = "Wiegand34Activity";
    /*private static final String GPIO_PIN_D0_Name = "BCM23";
    //private static final String GPIO_PIN_D1_Name = "BCM24";


    private Gpio mWiegand34GpioD0;
    //private Gpio mWiegand34GpioD1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PeripheralManagerService service = new PeripheralManagerService();
        try {
            // Step 1. Create GPIO connection.
            mWiegand34GpioD0 = service.openGpio(GPIO_PIN_D0_Name);
            //mWiegand34GpioD1 = service.openGpio(GPIO_PIN_D1_Name);
            // Step 2. Configure as an input.
            mWiegand34GpioD0.setDirection(Gpio.DIRECTION_IN);
            //mWiegand34GpioD1.setDirection(Gpio.DIRECTION_IN);
            // Step 3. Enable edge trigger events.
            mWiegand34GpioD0.setEdgeTriggerType(Gpio.EDGE_RISING);
            //mWiegand34GpioD1.setEdgeTriggerType(Gpio.EDGE_RISING);
            // Step 4. Register an event callback.
            mWiegand34GpioD0.registerGpioCallback(mDataPulse);
            //mWiegand34GpioD1.registerGpioCallback(mDataPulse);
        } catch (IOException e) {
            Log.e(TAG, "Error on PeripheralIO API", e);
        }
    }

    // Step 4. Register an event callback.
    private GpioCallback mDataPulse = new GpioCallback() {
        @Override
        public boolean onGpioEdge(Gpio gpio) {
            Log.i(TAG, "GPIO changed, Card Readed");
            ((TextView)findViewById(R.id.textView1)).setText("Leido");

            // Step 5. Return true to keep callback active.
            return true;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Step 6. Close the resource
        //if (mWiegand34GpioD0 != null && mWiegand34GpioD1 != null) {
        if (mWiegand34GpioD0 != null) {
            mWiegand34GpioD0.unregisterGpioCallback(mDataPulse);
            //mWiegand34GpioD1.unregisterGpioCallback(mDataPulse);
            try {
                mWiegand34GpioD0.close();
                //mWiegand34GpioD1.close();
            } catch (IOException e) {
                Log.e(TAG, "Error on PeripheralIO API", e);
            }
        }
    }*/
}