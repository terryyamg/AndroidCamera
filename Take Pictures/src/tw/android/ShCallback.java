package tw.android;

import android.hardware.Camera.ShutterCallback;
import android.util.Log;

public class ShCallback implements ShutterCallback {

    private static final String TAG = "ShCallback";

    @Override
    public void onShutter() {
        Log.d(TAG, "啟動快門...");
    }
}
