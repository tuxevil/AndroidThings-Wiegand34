package com.realware.sebastian.wiegand34;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.things.pio.Gpio;
import com.google.android.things.pio.GpioCallback;
import com.google.android.things.pio.PeripheralManagerService;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    private static final String GPIO_PIN_D0_Name = "BCM23";
    private static final String GPIO_PIN_D1_Name = "BCM24";

    private Gpio mWiegand34GpioD0;
    private Gpio mWiegand34GpioD1;
    private String stream = "";
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PeripheralManagerService service = new PeripheralManagerService();
        Log.d(TAG, "Available GPIO: " + service.getGpioList());

        try {
            // Step 1. Create GPIO connection.
            mWiegand34GpioD0 = service.openGpio(GPIO_PIN_D0_Name);
            mWiegand34GpioD1 = service.openGpio(GPIO_PIN_D1_Name);
            // Step 2. Configure as an input.
            mWiegand34GpioD0.setDirection(Gpio.DIRECTION_IN);
            mWiegand34GpioD1.setDirection(Gpio.DIRECTION_IN);
            // Step 3. Enable edge trigger events.
            mWiegand34GpioD0.setEdgeTriggerType(Gpio.EDGE_FALLING);
            mWiegand34GpioD1.setEdgeTriggerType(Gpio.EDGE_FALLING);

            //Testing what this do
            //mWiegand34GpioD0.setActiveType(Gpio.ACTIVE_HIGH);

            // Step 4. Register an event callback.
            mWiegand34GpioD0.registerGpioCallback(data_pulse0);
            mWiegand34GpioD1.registerGpioCallback(data_pulse1);
        } catch (IOException e) {
            Log.e(TAG, "Error on PeripheralIO API", e);
        }
    }

    // Step 4. Register an event callback.
    private GpioCallback data_pulse0 = new GpioCallback() {
        @Override
        public boolean onGpioEdge(Gpio gpio) {
            stream += "0";
            kick_timer();
            // Step 5. Return true to keep callback active.
            return true;
        }
    };

    private GpioCallback data_pulse1 = new GpioCallback() {
        @Override
        public boolean onGpioEdge(Gpio gpio) {
            stream += "1";
            kick_timer();
            // Step 5. Return true to keep callback active.
            return true;
        }
    };

    private void kick_timer() {
        if (timer == null) {
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    wiegand_stream_done();
                }
            }, 200);
        }
    }

    private void wiegand_stream_done() {
        String bitstream = stream;
        stream = "";
        timer = null;
        if (bitstream.length() != 34)
            return;
        validate_bits(bitstream);
    }

    private void validate_bits(String bitstream){
        //Validate bits
        if (bitstream.length() != 34) {
            return;
        }

        int lparity = Integer.parseInt(bitstream.substring(0,1));
        long userid = Long.parseLong(bitstream.substring(1,33), 2);
        int rparity = Integer.parseInt(bitstream.substring(33,34));

        int calculated_lparity = 0;
        int calculated_rparity = 1;

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Step 6. Close the resource
        //if (mWiegand34GpioD0 != null && mWiegand34GpioD1 != null) {
        if (mWiegand34GpioD0 != null) {
            mWiegand34GpioD0.unregisterGpioCallback(data_pulse0);
            mWiegand34GpioD1.unregisterGpioCallback(data_pulse1);
            try {
                mWiegand34GpioD0.close();
                //mWiegand34GpioD1.close();
            } catch (IOException e) {
                Log.e(TAG, "Error on PeripheralIO API", e);
            }
        }
    }
}

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }*/

