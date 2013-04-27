package com.androiddev.connectbluetoothdevices;

import java.util.ArrayList;
import java.util.Set;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainBluetooth extends Activity implements OnClickListener,OnItemClickListener {

	ArrayAdapter<String> listAdapter;
	Button searchDevice;
	ListView lvDevices;
	BluetoothAdapter btAdapter;
	Set<BluetoothDevice> devicesArray;
	ArrayList<String> pairedDevices;
	IntentFilter filter;
	BroadcastReceiver bluetoothReceiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_bluetooth);
		init();
		checkBluetoothEnabled();
		getPairedDevices();

	}

	private void startDiscovry() {
		btAdapter.cancelDiscovery();
		btAdapter.startDiscovery();
		
	}


	private void checkBluetoothEnabled() {
		if (btAdapter == null) {
			Toast.makeText(this, "No Bluetooth found on this device",
					Toast.LENGTH_SHORT).show();
			finish();
		} else if (!btAdapter.isEnabled()) {
			Intent enableBTIntent = new Intent(
					BluetoothAdapter.ACTION_REQUEST_ENABLE);
			startActivityForResult(enableBTIntent, 1);
		}
	}

	private void getPairedDevices() {
		devicesArray = btAdapter.getBondedDevices();
		if (devicesArray.size() > 0) {
			for (BluetoothDevice device : devicesArray) {
				pairedDevices.add(device.toString());
				listAdapter.add(device.getName());
			}
			lvDevices.setAdapter(listAdapter);
		}
	}
	private void init() {
		lvDevices = (ListView) findViewById(R.id.lvDevicesList);
		lvDevices.setOnItemClickListener(this);
		listAdapter = new ArrayAdapter<String>(MainBluetooth.this,
				android.R.layout.simple_list_item_1, 0);
		searchDevice = (Button) findViewById(R.id.bSearchDevice);
		searchDevice.setOnClickListener(this);
		btAdapter = BluetoothAdapter.getDefaultAdapter();
		pairedDevices = new ArrayList<String>();
		filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
		bluetoothReceiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				String action = intent.getAction();
				if (BluetoothDevice.ACTION_FOUND.endsWith(action)) {
					BluetoothDevice newDevice = intent
							.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
					listAdapter.add(newDevice.getName());
					lvDevices.setAdapter(listAdapter);
					if(!pairedDevices.contains(newDevice)){
						pairedDevices.add(newDevice.getName());
					}
				} else if (BluetoothAdapter.ACTION_DISCOVERY_STARTED
						.equals(action)) {

				} else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED
						.equals(action)) {

				} else if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {
					if (btAdapter.getState() == BluetoothAdapter.STATE_OFF) {
						checkBluetoothEnabled();
					}
				}
			}
		};
		registerReceiver(bluetoothReceiver, filter);
		filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
		registerReceiver(bluetoothReceiver, filter);
		filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
		registerReceiver(bluetoothReceiver, filter);
		filter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
		registerReceiver(bluetoothReceiver, filter);

	}

	@Override
	protected void onPause() {
		super.onPause();
		unregisterReceiver(bluetoothReceiver);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_bluetooth, menu);
		return true;
	}
	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_CANCELED) {
			Toast.makeText(this, "Bluetooth must be enabled",
					Toast.LENGTH_SHORT).show();
			finish();
		}
	}

	@Override
	public void onClick(View v) {
	switch(v.getId()){
	case R.id.bSearchDevice:
		startDiscovry();
		break;
	}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		String s= listAdapter.getItem(arg2);
		if(pairedDevices.contains(s)){
			Toast.makeText(this, "Paired Device", Toast.LENGTH_SHORT).show();
		}else{
			Toast.makeText(this, "UnPaired Device", Toast.LENGTH_SHORT).show();

		}
	}
}
