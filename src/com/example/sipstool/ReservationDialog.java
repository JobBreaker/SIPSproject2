package com.example.sipstool;

import org.json.JSONObject;

import com.example.json.ReservationJSON;
import com.example.sipsproject2.R;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class ReservationDialog extends Dialog implements android.view.View.OnClickListener{
	EditText customId,privillegeId;
	Button reservationButton,cancelButton;
	String [] list = new String[]{"Central Ladprao","Central Rama9","The Mall Ngamwongwan"};
	Spinner spin;
	public ReservationDialog(Context context) {
		super(context);
		 setContentView(R.layout.reservation_dialog);
		 customId = (EditText)findViewById(R.id.editText1);
		 privillegeId = (EditText)findViewById(R.id.editText2);
		 spin = (Spinner)findViewById(R.id.spinner1);
		 reservationButton = (Button)findViewById(R.id.btn_confirm);
		 cancelButton = (Button)findViewById(R.id.btn_cancel);
		 reservationButton.setOnClickListener(this);
		 cancelButton.setOnClickListener(this);
		 
			ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item ,list );
					//ArrayAdapter.createFromResource(getOwnerActivity(), 
					//R.array.parking_floor, android.R.layout.simple_spinner_item);

			dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spin.setAdapter(dataAdapter);
			spin.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
						int position, long id) {
					parent.getItemAtPosition(position);
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
				
					
				}
			});
		// TODO Auto-generated constructor stub
	}
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.btn_confirm:
			
			dismiss();
			break;
		case R.id.btn_cancel:
			dismiss();
			break;
		default:
			break;
		}
		
	}
	public ReservationJSON setJSON(){
		ReservationJSON json = new ReservationJSON(customId.getText().toString(), privillegeId.getText().toString()
				, spin.getSelectedItemId()*1000, "18:12:14");
		return json;
	}

}
