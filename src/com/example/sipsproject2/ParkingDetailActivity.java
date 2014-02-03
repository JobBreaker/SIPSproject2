package com.example.sipsproject2;

import java.util.ArrayList;
import java.util.Locale;

import com.example.httpconnect.Request;
import com.example.json.ParkingDetailObject;
import com.example.sipstool.Constants;
import com.example.sipstool.ReservationDialog;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ParkingDetailActivity extends Activity implements LocationListener {
	Button seeButton,reservationButton;
	private LocationManager locationManager;
	private String provider;
	private double lat=13.7500,lng=100.5167;
	long id;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_parking_detail);
		
		TextView nameTV = (TextView)findViewById(R.id.tv_parking_detail_full_name);
		TextView attractionTV = (TextView)findViewById(R.id.tv_parking_detail_full_attarction);
		TextView addressTV =(TextView)findViewById(R.id.tv_parking_detail_full_address);
		TextView privillegeTV =(TextView)findViewById(R.id.tv_parking_detail_full_privillege);
		TextView contactTV =(TextView)findViewById(R.id.tv_parking_detail_full_contact);
		TextView shoppingTV =(TextView)findViewById(R.id.tv_parking_detail_full_shopping);
		TextView diningTV =(TextView)findViewById(R.id.tv_parking_detail_full_dinning); 
		ImageView logoImage = (ImageView)findViewById(R.id.imv_parking_detail_logo);
		//ImageView mapImage = (ImageView)findViewById(R.id.imv_parking_detail_map);
		seeButton = (Button)findViewById(R.id.butt_parking_see_map);
		reservationButton = (Button)findViewById(R.id.butt_parking_reservation);
		Intent i = getIntent();
		id = i.getLongExtra("id", 0);//Receive id from intent
		ParkingDetailObject objects = receiveData(id);
		// Receive Data from Server
		nameTV.setText(objects.getName());
		attractionTV.setText("ATTRACTIONS: "+objects.getAttractions());
		addressTV.setText("ADDRESS: "+objects.getAddress());
		privillegeTV.setText("PRIVILLEGE: "+objects.getPrivillege());
		contactTV.setText("Contact: "+objects.getContact());
		shoppingTV.setText("Shopping Store: "+objects.getShopping());
		diningTV.setText("Dining Restaurants: "+objects.getDining());
		if (objects.getLogotype()==1){
		logoImage.setImageResource(R.drawable.central);}
		else{
			logoImage.setImageResource(R.drawable.themall2);
		}
		//============set Layout Complete============
			locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		  	Criteria criteria = new Criteria();
		    provider = locationManager.getBestProvider(criteria, false);
		    Location location = locationManager.getLastKnownLocation(provider);
		    if (location!=null){
		    	lat = (int)location.getLatitude();
		    	lng = (int)location.getLongitude();
		    	Toast.makeText(this, "lat"+lat+" lng"+lng, Toast.LENGTH_LONG).show();
		    }
		    else{
		    /*	boolean enabled = locationManager
		    			  .isProviderEnabled(LocationManager.GPS_PROVIDER);

		    			// check if enabled and if not send user to the GSP settings
		    			// Better solution would be to display a dialog and suggesting to 
		    			// go to the settings
		    			if (!enabled) {
		    			  Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
		    			  startActivity(intent);
		    			} */
		    	
		    }
		OnClickListener click = setClickListener();
		seeButton.setOnClickListener(click);
		reservationButton.setOnClickListener(click); 
		
	}

	private OnClickListener setClickListener(){
		OnClickListener click = new OnClickListener(){

			@Override
			public void onClick(View v) {
				switch (v.getId()){
				case R.id.butt_parking_see_map:
					Intent i = new Intent(ParkingDetailActivity.this,ParkingMapFloorActivity.class);
					i.putExtra("id", id);
					startActivity(i);
					break;
				case R.id.butt_parking_reservation:
					ReservationDialog reservation = new ReservationDialog(ParkingDetailActivity.this,id,Request.REQUEST_RESERVATION_FROM_PARKING);
					reservation.setTitle("Reservation");
					reservation.show();
					break;
				}
				
			}
			
		};
		return click;
	}
	public static ParkingDetailObject receiveData(long id){
		ArrayList<ParkingDetailObject> o=Constants.getParkingObject();
		for(int i=0;i<o.size();i++){
			ParkingDetailObject items = o.get(i);
			if (items.getId()==id){
			 return items;
			 }
		}
		return null;
	}
	 @Override
	  protected void onResume() {
	    super.onResume();
	    locationManager.requestLocationUpdates(provider, 400, 1, this);
	  }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.parking_detail, menu);
		return true;
	}
	 protected void onPause() {
		    super.onPause();
		    locationManager.removeUpdates(this);
		  }

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		String message = String.format("New Location \n Longitude: %1$s \n Latitude: %2$s",location.getLongitude(),location.getLatitude());
		 Toast.makeText(ParkingDetailActivity.this,message,Toast.LENGTH_LONG).show();
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		 Toast.makeText(ParkingDetailActivity.this,"Provider disabled by the user. GPS turned off",Toast.LENGTH_LONG).show();

		
	}

	@Override
	public void onProviderEnabled(String provider) {
		 Toast.makeText(ParkingDetailActivity.this,"Provider enabled by the user. GPS turned on",Toast.LENGTH_LONG).show();
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		Toast.makeText(ParkingDetailActivity.this,"Provider status changed",Toast.LENGTH_LONG).show();
	}
	
	public void ReservationMessage(boolean status,String message) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);		
		if (status==true)
		{		
			builder.setTitle("Reservation Complete");
			builder.setMessage("Your Reservation is comfirmed\nPlease");
			builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
				}
			});			
			}
		else{
			builder.setTitle("Reservation Failed");
			builder.setMessage(message);
			builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					
				}
			});
		}
		builder.show();
	}

}
