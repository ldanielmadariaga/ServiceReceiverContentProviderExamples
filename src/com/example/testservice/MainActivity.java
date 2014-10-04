package com.example.testservice;

import android.app.Activity;
import android.app.Fragment;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

	public void startService(View view) {
		Intent intent = new Intent(getBaseContext(), MyService.class);
		startService(intent);
	}

	public void stopService(View view) {
		Intent intent = new Intent(getBaseContext(), MyService.class);
		stopService(intent);
	}

	public void broadcastIntent(View view) {
		Intent intent = new Intent();
		intent.setAction(getResources().getString(R.string.customIntent));
		sendBroadcast(intent);
	}

	public void addName(View view) {
		ContentValues student = new ContentValues();
		student.put(StudentsProvider.NAME,
				((EditText) findViewById(R.id.txtName)).getText().toString());
		student.put(StudentsProvider.GRADE,
				((EditText) findViewById(R.id.txtGrade)).getText().toString());
		Uri uri = getContentResolver().insert(StudentsProvider.CONTENT_URI,
				student);
		Toast.makeText(getBaseContext(), uri.toString(), Toast.LENGTH_LONG)
				.show();
	}

	public void retrieveStudents(View view) {
		String URL = "content://com.example.provider.College/students";
		Uri students = Uri.parse(URL);
		Cursor cursor = managedQuery(students, null, null, null, "name");
		if (cursor.moveToFirst()) {
			do {
				Toast.makeText(
						this,
						cursor.getString(cursor
								.getColumnIndex(StudentsProvider._ID))
								+ ", "
								+ cursor.getString(cursor
										.getColumnIndex(StudentsProvider.NAME))
								+ ", "
								+ cursor.getString(cursor
										.getColumnIndex(StudentsProvider.GRADE)),
						Toast.LENGTH_SHORT).show();
			} while (cursor.moveToNext());
		}
	}
}
