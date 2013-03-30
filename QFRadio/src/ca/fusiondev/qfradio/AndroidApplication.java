package ca.fusiondev.qfradio;

import android.app.Application;
import android.widget.Toast;

public class AndroidApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		Toast.makeText(this, "Application onCreate", Toast.LENGTH_SHORT).show();
	}
}
