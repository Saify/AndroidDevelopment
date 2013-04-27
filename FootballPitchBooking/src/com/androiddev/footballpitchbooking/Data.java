package com.androiddev.footballpitchbooking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Data extends Activity implements OnClickListener {
	
	public static final String BREAD = "bread";
	public static final String BASKET = "basket";
	EditText etSend;
	Button bSA, bSAFR;
	TextView tvReceived;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.get);
		init();
	}

	private void init() {
		etSend = (EditText) findViewById(R.id.etSend);
		bSA = (Button) findViewById(R.id.bSA);
		bSAFR = (Button) findViewById(R.id.bSAFR);
		tvReceived = (TextView) findViewById(R.id.tvReceived);
		bSA.setOnClickListener(this);
		bSAFR.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bSA:
			String bread = etSend.getText().toString();
			Bundle basket = new Bundle();
			basket.putString(BREAD, bread);
			Intent a = new Intent(this,OppendClass.class);
			a.putExtras(basket);
			startActivity(a);
			break;
		case R.id.bSAFR:
			Intent i = new Intent(this,OppendClass.class);
			  bread = etSend.getText().toString();
			  basket = new Bundle();
			basket.putString(BREAD, bread);
			i.putExtras(basket);
			startActivityForResult(i, 0);
			break;

		}
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == RESULT_OK){
			Bundle backpack = data.getExtras();
			String answer = backpack.getString(OppendClass.ANSWER_KEY);
			tvReceived.setText(answer);
		}
	}

}