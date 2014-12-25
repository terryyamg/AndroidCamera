package tw.android;

import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.util.Log;

public class RawCallback implements PictureCallback {

    private static final String TAG = "RawCallback";

    @Override
    public void onPictureTaken(byte[] data, Camera camera) {
        Log.d(TAG, "³B²z Raw data...");
    }

}
