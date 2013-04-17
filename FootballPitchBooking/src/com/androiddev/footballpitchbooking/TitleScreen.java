package com.androiddev.footballpitchbooking;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class TitleScreen extends Activity {
	MediaPlayer titleSound;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.title_screen);
		titleSound = MediaPlayer.create(TitleScreen.this, R.raw.title);
		titleSound.start();
		Thread titleScreenThread = new Thread(){
			public void run(){
				try{
					sleep(1000);
				}catch (InterruptedException e) {
				
				}finally{
					Intent intent = new Intent("android.intent.action.MENU");
					startActivity(intent);
				}
			}
		};
		titleScreenThread.start();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.title_screen, menu);
		return true;
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		titleSound.release();
		finish();
			
	}

}
