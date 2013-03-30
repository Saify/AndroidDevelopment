package ca.fusiondev.qfradio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends BaseActivity {

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	
	
	
	public void onClick(View v){
		Intent intent = new Intent(this,CommentsActivity.class);
		intent.putExtra("screenText", "Hello World");
		startActivity(intent);
	}

	

}
