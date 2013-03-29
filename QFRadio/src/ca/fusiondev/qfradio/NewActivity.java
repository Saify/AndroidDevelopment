package ca.fusiondev.qfradio;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class NewActivity extends BaseActivity {

	Fragment pictureFragment = new PictureFragment();
	Fragment sideFragment = new SideFragment();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_activity_frame);
		FragmentManager fm =  getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		Fragment sf = new SideFragment();
		ft.replace(R.id.framelayout,sf,"SIDE");
		ft.commit();
	}	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_new_activity, menu);
		return true;
	}
	
	public void changeFragment(View v){
		
		FragmentManager fm =  getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		Fragment current = fm.findFragmentByTag("SIDE");
		if(current.isVisible()){
			
			ft.replace(R.id.framelayout,pictureFragment);
		}else{
			ft.replace(R.id.framelayout,sideFragment, "SIDE");
		}
		ft.addToBackStack(null);
		ft.commit();
		
	}
	
	
	public void addComment(View v){
		EditText enteredName = (EditText) findViewById(R.id.text_name);
		EditText enteredLocation = (EditText) findViewById(R.id.text_location);
		EditText enteredComment = (EditText) findViewById(R.id.text_comment);
		
		TextView labelName = (TextView) findViewById(R.id.label_name);
		TextView labelLocation = (TextView) findViewById(R.id.label_location);
		TextView labelComment = (TextView) findViewById(R.id.label_comment);
		
		labelName.setText(enteredName.getText().toString());
		labelLocation.setText(enteredLocation.getText().toString());
		labelComment.setText(enteredComment.getText().toString());
		
		
	}
}
