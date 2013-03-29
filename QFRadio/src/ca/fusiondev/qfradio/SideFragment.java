package ca.fusiondev.qfradio;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.xml.sax.Parser;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SideFragment extends Fragment {
	View view;
	SQLiteDatabase db;
	DBHelper dbHelper;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		dbHelper = new DBHelper(getActivity());
		db = dbHelper.getWritableDatabase();
		view = inflater.inflate(R.layout.new_activity, container, false);
		Button comment = (Button) view.findViewById(R.id.button1); 
		
		comment.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText enteredName = (EditText) view.findViewById(R.id.text_name);
				EditText enteredLocation = (EditText) view.findViewById(R.id.text_location);
				EditText enteredComment = (EditText) view.findViewById(R.id.text_comment);
				
				ContentValues cv = new ContentValues();
				cv.put(DBHelper.NAME, enteredName.getText().toString());
				cv.put(DBHelper.COMMENT, enteredComment.getText().toString());
				cv.put(DBHelper.LOCATION,enteredLocation.getText().toString());
				cv.put(DBHelper.TIME, new Date().getTime());
				
				db.insert(DBHelper.TABLE_NAME, null, cv);
			}
		});
		
		
		Button queryButton = (Button) view.findViewById(R.id.button_query);
		queryButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String[] columns = {DBHelper.NAME,DBHelper.COMMENT,DBHelper.LOCATION,DBHelper.TIME};
				Cursor cursor = db.query(DBHelper.TABLE_NAME, columns, null, null, null, null, null);
				cursor.moveToFirst();
				
				while(cursor.moveToNext()){
					
					String name = cursor.getString(cursor.getColumnIndex(DBHelper.NAME));
					String comment = cursor.getString(cursor.getColumnIndex(DBHelper.COMMENT));
					String location = cursor.getString(cursor.getColumnIndex(DBHelper.LOCATION));
					Integer time = cursor.getInt(cursor.getColumnIndex(DBHelper.TIME));
					
						Date date = new Date(time);
						Toast.makeText(getActivity(),"Name: " + name + "\nComment: "+comment+"\nLocation: "+location+"\nTime: "+ date.toString(),Toast.LENGTH_SHORT).show();
						
				}
				
				cursor.close();
			}
		});
		
		return view;
		
	}

}
