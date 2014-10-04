package com.example.testservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service{

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId){
		Toast.makeText(this, "Service started", Toast.LENGTH_LONG).show();
		return START_STICKY;
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		Toast.makeText(this, "Service destroyed", Toast.LENGTH_LONG).show();
	}

}
