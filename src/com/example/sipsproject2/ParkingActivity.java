package com.example.sipsproject2;

import java.util.ArrayList;
import java.util.List;

import com.example.json.ParkingObject;
import com.example.sipstool.ParkingListAdapter;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ParkingActivity extends ListActivity {
	ListView listview;
	ParkingListAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_parking);
		List<ParkingObject> list = genParkingObject(10);
		adapter = new ParkingListAdapter(this, list);
		getListView().setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				Intent i = new Intent(v.getContext(),ParkingDetailActivity.class);
				ParkingObject object = (ParkingObject) parent.getItemAtPosition(position);
				i.putExtra("id", object.getId());
				try {
				startActivity(i);}
				catch (Exception e){
					Log.e("error", "Can start Activity");
				}
				// TODO Auto-generated method stub
				
			}});
		setListAdapter(adapter);

	}
	
	
	
	 
	private List<ParkingObject> genParkingObject(int n){
		List<ParkingObject> list = new ArrayList<ParkingObject>();
		while (n>0){
			list.add(sampleObject());
			n--;
		}
		return list;
		
		
	}
	private ParkingObject sampleObject(){
		ParkingObject sam = new ParkingObject(1000);
		sam.setParkingDetail("Central", "DinnerCinema", "valetparking");
		sam.setCarCapacity(10000, 1000);
		return sam;
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.parking, menu);
		return true;
	}

}

