package com.androiddev.footballpitchbooking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class OppendClass extends Activity implements OnClickListener, OnCheckedChangeListener{
	
	public static final String ANSWER_KEY = "answer";
	TextView tvText, tvQuestion;
	Button bReturn;
	RadioGroup rgAnswersList;
	String gotBread;
	String setData;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.send);
		init();
//		Bundle gotBasket = getIntent().getExtras();
//		gotBread = gotBasket.getString(Data.BREAD);
//		tvQuestion.setText(gotBread);
	}

	private void init() {
		tvText = (TextView) findViewById(R.id.tvText);
		tvQuestion = (TextView) findViewById(R.id.tvQuestion);
		bReturn = (Button) findViewById(R.id.bReturn);
		bReturn.setOnClickListener(this);
		rgAnswersList = (RadioGroup) findViewById(R.id.rgAnswers);
		rgAnswersList.setOnCheckedChangeListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent person = new Intent();
		Bundle backpack = new Bundle();
		backpack.putString(ANSWER_KEY, setData);
		person.putExtras(backpack);
		setResult(RESULT_OK,person);
		finish();
		
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.rCrazy:
			setData = "Yeah dude";
			tvText.setText(setData);
			break;
		case R.id.rSexy:
			setData = "I am dude...!";
			tvText.setText(setData);
			break;
		case R.id.rBoth:
			setData = "Yes, You are right.";
			tvText.setText(setData);
			break;
		}
	}

	
}
