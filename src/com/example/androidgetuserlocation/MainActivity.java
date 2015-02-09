package com.example.androidgetuserlocation;

import com.example.androidgetuserlocation.LocationHelper.LocationResult;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class MainActivity extends Activity {

	final String TAG = "MainActivity.java";

	Button buttonStart;
	Button buttonStop;
	
	LocationResult locationResult;
	LocationHelper locationHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// initialize buttons
		buttonStart = (Button) findViewById(R.id.buttonStart);
		buttonStop = (Button) findViewById(R.id.buttonStop);
		
		// set button on click listeners
		buttonStart.setOnClickListener(new OnClickListenerButtonStart());
		buttonStop.setOnClickListener(new OnClickListenerButtonEnd());
		
		// to get location updates, initialize LocationResult
		this.locationResult = new LocationResult(){
		    @Override
		    public void gotLocation(Location location){
		    	
		        //Got the location!
		    	if(location!=null){
		    		
			    	double latitude = location.getLatitude();
			    	double longitude = location.getLongitude();
			    	
			    	Log.e(TAG, "lat: " + latitude + ", long: " + longitude);
			    	
			    	// here you can save the latitude and longitude values
			    	// maybe in your text file or database
			    	
		    	}else{
		    		Log.e(TAG, "Location is null.");
		    	}
		    	
		    }

		};
		
		// initialize our useful class, 
		this.locationHelper = new LocationHelper();
        locationHelper.getLocation(MainActivity.this, this.locationResult);
	}

	// prevent exiting the app using back pressed
	// so getting user location can run in the background
	@Override
	public void onBackPressed() {
		
	    new AlertDialog.Builder(MainActivity.this)
        .setTitle("User Location App")
        .setMessage("This will end the app. Use the home button instead.")
        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                
                dialog.cancel();
            }
        }).show();
	    
	}
}
