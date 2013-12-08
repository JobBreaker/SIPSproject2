package com.example.sipsproject2;

import com.example.json.ParkingDetailObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

public class ParkingDetailActivity extends Activity {

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
		ImageView mapImage = (ImageView)findViewById(R.id.imv_parking_detail_map);
		Intent i = getIntent();
		long id = i.getLongExtra("id", 0);//Receive id from intent
		ParkingDetailObject objects = receiveData(id);
		// Receive Data from Server
		nameTV.setText(objects.getName());
		attractionTV.setText("ATTRACTIONS:"+objects.getAttractions());
		addressTV.setText("ADDRESS:"+objects.getAddress());
		privillegeTV.setText("PRIVILLEGE:"+objects.getPrivillege());
		contactTV.setText("Contact:"+objects.getContact());
		shoppingTV.setText("Shopping Store:"+objects.getShopping());
		diningTV.setText("Dining Restaurants:"+objects.getDining());
		logoImage.setImageResource(R.drawable.central);
		mapImage.setImageResource(R.drawable.map);
	}

	
	public ParkingDetailObject receiveData(long id){
		ParkingDetailObject o=new ParkingDetailObject(id);
		o.setName("Central Ladprao");
		o.setAttractions("shopping,dining");
		o.setPrivillege("valet,reservation");
		o.setAddress("1691 Phoholyothin Rd,Chatuchak,Bangkok,Thailand");
		o.setContact("Tel.(66)2561 1234,Email: cglb@chr.co.th");
		o.setShopping("WRANGERLER LEVEL DR.MARTEEN OKAKA INUMUCHI GORINDSE BG KKNT DOKE ADIDOS PIKA");
		o.setDining("FUJICHI KOKOCHIBUNYA MISUKI SHABICHI OYAYA KFG MCDONUT SUKURABU BEERU SIZZSELER");
		return o;
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.parking_detail, menu);
		return true;
	}

}
