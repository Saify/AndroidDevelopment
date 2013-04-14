package com.androiddev.footballpitchbooking;

import java.util.Random;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class TextPlay extends Activity implements View.OnClickListener {

	Button btnCmd;
	ToggleButton passTog;
	TextView display;
	EditText input;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text);

		setVars();
		passTog.setOnClickListener(this);
		btnCmd.setOnClickListener(this);
	}

	private void setVars() {
		btnCmd = (Button) findViewById(R.id.btCommand);
		passTog = (ToggleButton) findViewById(R.id.tbCommand);
		display = (TextView) findViewById(R.id.tvCommand);
		input = (EditText) findViewById(R.id.etCommand);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.btCommand: {
			String check = input.getText().toString();
			if (check.contentEquals("left")) {
				display.setGravity(Gravity.LEFT);
			} else if (check.contentEquals("center")) {
				display.setGravity(Gravity.CENTER);

			} else if (check.contentEquals("right")) {
				display.setGravity(Gravity.RIGHT);

			} else if (check.contentEquals("blue")) {
				display.setTextColor(Color.BLUE);
			} else if (check.contentEquals("WTF")) {
				Random crazy = new Random();
				display.setText("WTF!!!!!");
				display.setTextSize(crazy.nextInt(75));
				display.setTextColor(Color.rgb(crazy.nextInt(256),
						crazy.nextInt(256), crazy.nextInt(256)));

				switch (crazy.nextInt(3)) {
				case 1:
					display.setGravity(Gravity.LEFT);
					break;
				case 2:
					display.setGravity(Gravity.RIGHT);
					break;
				case 3:
					display.setGravity(Gravity.CENTER);

					break;
				}

			} else {
				display.setText("Invalid");
				display.setGravity(Gravity.CENTER);
				display.setTextColor(Color.MAGENTA);
			}

		}
			break;
		case R.id.tbCommand: {
			if (passTog.isChecked()) {
				input.setInputType(InputType.TYPE_CLASS_TEXT
						| InputType.TYPE_TEXT_VARIATION_PASSWORD);
			} else {
				input.setInputType(InputType.TYPE_CLASS_TEXT);
			}
		}
			break;
		}

	}
}
