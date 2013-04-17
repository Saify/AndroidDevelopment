package com.androiddev.footballpitchbooking;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Camera extends Activity implements View.OnClickListener {
	ImageView iv;
	ImageButton ib;
	Button b;
	Intent i;
	final static int camraData = 0;
	Bitmap bmp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photo);
		initilize();

	}

	private void initilize() {
		iv = (ImageView) findViewById(R.id.ivCapturedPic);
		ib = (ImageButton) findViewById(R.id.ibTakePic);
		b = (Button) findViewById(R.id.bSetWallpaper);
		ib.setOnClickListener(this);
		b.setOnClickListener(this);
		InputStream is = getResources().openRawResource(R.drawable.football_ground);
		bmp = BitmapFactory.decodeStream(is);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ibTakePic:
			i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(i, camraData);
			break;

		case R.id.bSetWallpaper:
			if (bmp != null) {
				try {
					WallpaperManager.getInstance(getApplicationContext()).setBitmap(bmp);
//					getApplicationContext().setWallpaper(bmp);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			Bundle extras = data.getExtras();
			bmp = (Bitmap) extras.get("data");
			iv.setImageBitmap(bmp);
		}
	}
}
