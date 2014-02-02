package com.example.sipsproject2;


import org.apache.http.message.BasicNameValuePair;

import com.example.httpconnect.ConnectServer;
import com.example.sipstool.ReservationDialog;
import com.example.sipstool.ValetDialogBox;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class PrivillegeActivity extends Activity {
	RelativeLayout valetButton,reservationButton;
	OnClickListener onclick1;
	private static ConnectServer connectServer;
	private static String url_name = "http://158.108.34.17/mobile/valet/insert_valet.php";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_privillege);
		valetButton = (RelativeLayout)findViewById(R.id.butt_pri_valet);
		reservationButton = (RelativeLayout)findViewById(R.id.butt_reserve);
		onclick1 = new OnClickListener(){

			@Override
			public void onClick(View v) {
				switch(v.getId()){
				case R.id.butt_pri_valet:
					showValetDialog();
					break;
				case R.id.butt_reserve:
					showReservationDialog();
					break;
				default:
					break;			
				}	
			}
		};
		valetButton.setOnClickListener(onclick1);
		reservationButton.setOnClickListener(onclick1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.privillege, menu);
		return true;
	}
	
	private void showReservationDialog(){
		ReservationDialog reservation = new ReservationDialog(PrivillegeActivity.this);
		reservation.setTitle("Reservation");
		reservation.show();	
	}
	
	private void showValetDialog() {
		ValetDialogBox valet = new ValetDialogBox(PrivillegeActivity.this);
		valet.show();
	}
	
	public void cannotConnectToServer(String data) {
		Toast.makeText(this, data, Toast.LENGTH_LONG).show();
	}
	public void errorConnectToServer() {
		Toast.makeText(this, "Error Connectation Failed", Toast.LENGTH_LONG).show();
	}

}
