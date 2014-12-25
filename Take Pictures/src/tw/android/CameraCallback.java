package tw.android;

import java.io.IOException;

import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;


public class CameraCallback implements Callback {

    private static final String TAG = "CameraCallback";
    private Camera carema;

    public Camera getCarema() {
        return this.carema;
    }

    public void surfaceCreated(SurfaceHolder holder) {
        Log.d(TAG, "啟動相機...");
        this.carema = Camera.open();
        try {
            Log.d(TAG, "設定預覽視窗");
            this.carema.setPreviewDisplay(holder);
        }
        catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width,
            int height) {
        Log.d(TAG, "開始預覽...");
        this.carema.startPreview();
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d(TAG, "停止預覽...");
        this.carema.stopPreview();
        Log.d(TAG, "釋放相機資源...");
        this.carema.release();
        this.carema = null;
    }

	
}
