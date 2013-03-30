package ca.fusiondev.qfradio;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

public class BaseActivity extends FragmentActivity {

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_activity, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()){
		case R.id.computer:
			Intent intent = new Intent(this,CommentsActivity.class);
			intent.putExtra("screenText", "Hello World");
			startActivity(intent);
		return true;
			
		
	
		default: return super.onContextItemSelected(item);
		}
	}
}
