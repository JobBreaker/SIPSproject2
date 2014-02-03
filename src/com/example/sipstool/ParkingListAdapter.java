package com.example.sipstool;

import java.util.List;

import com.example.json.ParkingDetailObject;
import com.example.json.ParkingObject;
import com.example.sipsproject2.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ParkingListAdapter extends ArrayAdapter<ParkingDetailObject>{
	private Context context;
	private List<ParkingDetailObject> objects;
	public ParkingListAdapter(Context context,List<ParkingDetailObject> objects) {
		super(context, R.layout.parking_detail, objects);
		this.context = context;
		this.objects = objects;
	}
	  @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	    LayoutInflater inflater = (LayoutInflater) context
	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View rowView = inflater.inflate(R.layout.parking_detail, parent, false);
	    TextView nameTextView = (TextView) rowView.findViewById(R.id.parking_name);
	    TextView attTextView = (TextView) rowView.findViewById(R.id.parking_attaction);
	    TextView priTextView = (TextView) rowView.findViewById(R.id.parking_privillege);
	    TextView distTextView = (TextView) rowView.findViewById(R.id.parking_parking_distance);
	    TextView numTextView = (TextView) rowView.findViewById(R.id.parking_parking_capacity);
	    ImageView imageView = (ImageView) rowView.findViewById(R.id.image_building);
	    ParkingDetailObject object = objects.get(position);
	    // Change the icon for Windows and iPhone
	    //Set Layout
	    nameTextView.setText(object.getName());
	    attTextView.setText("Attaction: "+object.getAttractions());
	    priTextView.setText("Privillege: " +object.getPrivillege());
	    distTextView.setText("Distance: "+"2km");
	    numTextView.setText("Capacity: "+String.valueOf(object.getCapacity()));
	    if (object.getLogotype()==1){
	    imageView.setImageResource(R.drawable.central);}
	    else{
	    	imageView.setImageResource(R.drawable.themall2);
	    }
	    return rowView;
	  }
	

}
