package com.example.sipsproject2;



import com.example.json.ParkingDetailObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class ParkingMapFloorActivity extends Activity {
	TextView nameTextView,detailTextView;
	ImageView logo;
	Button refreshButton;
	Spinner spinner;
	ParkingDetailObject parking;
	long id;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_parking_map_floor);
		Intent i = getIntent();
		id = i.getLongExtra("id", 0);//Receive id from intent
		nameTextView = (TextView)findViewById(R.id.tv_header_parking_name);
		detailTextView = (TextView)findViewById(R.id.tv_header_parking_detail);
		logo = (ImageView)findViewById(R.id.header_parking_image);
		spinner = (Spinner)findViewById(R.id.spinner_car_map);
		parking = ParkingDetailActivity.receiveData(id);
		setHeader(parking);
		logo.setImageResource(R.drawable.central);
		setItemOnSpinner();
	}

	private void setHeader(ParkingDetailObject parking2) {
		nameTextView.setText(parking2.getName());
		String detail = "Address : " +parking2.getAddress() +"\n"
				+ "Contact : " + parking2.getContact() + "\n"
				+ "Attraction : "+parking2.getAttractions();
	   detailTextView.setText(detail);
	}

	
	public void setItemOnSpinner(){

		ArrayAdapter<CharSequence> dataAdapter = ArrayAdapter.createFromResource(this, R.array.parking_floor, 
				android.R.layout.simple_spinner_item);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(dataAdapter);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				parent.getItemAtPosition(position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			
				
			}
		
		
		});
		//dataAdapter.setDropDownViewResource(android.R.layout.s)
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.parking_map_floor, menu);
		return true;
	}

}
