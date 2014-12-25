package tw.android;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

public class TakeAPhotoActivity extends Activity {

	private static final String TAG = "TakeAPhotoActivity";
	private SurfaceView sv;
	// private ImageView iv;
	private Button takePhotoBtn, hidePhotoBtn, autoFocus,picList;
	// 相機 callback
	private CameraCallback cc;
	// 快門 callback
	private ShCallback sc;
	// 處理 raw data callback
	private RawCallback rc;
	// 處理 jpg callback
	private JpgCallback jc;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.take_a_photo_activity);

		this.sv = (SurfaceView) this.findViewById(R.id.sv);
		this.takePhotoBtn = (Button) this.findViewById(R.id.takePhotoBtn);
		this.hidePhotoBtn = (Button) this.findViewById(R.id.hidePhotoBtn);
		this.autoFocus = (Button) this.findViewById(R.id.autoFocus);
		this.picList= (Button) this.findViewById(R.id.picList);
		
		this.cc = new CameraCallback();
		this.sc = new ShCallback();
		this.rc = new RawCallback();
		this.jc = new JpgCallback(this);

		Log.d(TAG, "設定預覽視窗...");
		SurfaceHolder sh = this.sv.getHolder();
		sh.addCallback(this.cc);
		sh.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

		Log.d(TAG, "設定拍照頁面...");
		this.hidePhotoBtn.setVisibility(View.GONE);

		// 按鈕外按自動對焦
		autoFocus.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				cc.getCarema().autoFocus(new AutoFocusCallback() {
					@Override
					public void onAutoFocus(boolean success, Camera camera) {

					}
				});
			}
		});
		//前往照片列表
		picList.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				goToPicList();
			}
		});
		
	}

	public void takePhoto(View v) {
		Log.d(TAG, "拍照...");
		// 需要三個 callback：快門、處理 raw data、處理 jpg
		// 拍照時自動對焦
		this.cc.getCarema().autoFocus(new AutoFocusCallback() {
			@Override
			public void onAutoFocus(boolean success, Camera camera) {
				if (success) {
					camera.takePicture(sc, rc, jc);
				}
			}
		});
	}

	public void hidePhoto(View v) {
		Log.d(TAG, "設定拍照頁面...");
		this.takePhotoBtn.setVisibility(View.VISIBLE);// 顯示拍照按鈕
		this.hidePhotoBtn.setVisibility(View.GONE); // 隱藏重拍按鈕

		Log.d(TAG, "回到拍照功能，需重新啟動預覽...");
		this.cc.getCarema().startPreview();
	}

	public void showPhoto(String picPath) {
		Log.d(TAG, "取得照片路徑：" + picPath);
		Log.d(TAG, "設定照片頁面...");
		this.takePhotoBtn.setVisibility(View.GONE);
		this.hidePhotoBtn.setVisibility(View.VISIBLE);

	}

	//前往照片列表
	 public void goToPicList(){
		 Intent intent=new Intent(this,PhotoList.class);
		 startActivity(intent);
	 }

}
