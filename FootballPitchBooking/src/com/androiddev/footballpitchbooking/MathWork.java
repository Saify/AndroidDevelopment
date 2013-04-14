package com.androiddev.footballpitchbooking;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MathWork extends Activity {
	
	Button add, sub;
	TextView result;
	int value = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.math_activity);
		  value = 0;
		add = (Button) findViewById(R.id.bt_add);
		sub = (Button) findViewById(R.id.bt_sub);
		result = (TextView) findViewById(R.id.tv_result);
		
		add.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				result.setText("Your Value is " + ++value );
			}
		});
		
		sub.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				result.setText("Your Value is " + --value );
			}
		});
	}

}
