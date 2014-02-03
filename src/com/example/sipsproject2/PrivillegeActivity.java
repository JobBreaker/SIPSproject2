package com.example.sipsproject2;


import org.json.JSONException;
import org.json.JSONObject;

import com.example.httpconnect.Request;
import com.example.sipstool.LoginDialog;
import com.example.sipstool.PreferencesName;
import com.example.sipstool.ReservationDialog;
import com.example.sipstool.ValetDialogBox;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class PrivillegeActivity extends Activity {
	RelativeLayout valetButton,reservationButton;
	SharedPreferences share;
	OnClickListener onclick1;
	boolean check = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_privillege);
		valetButton = (RelativeLayout)findViewById(R.id.butt_pri_valet);
		reservationButton = (RelativeLayout)findViewById(R.id.butt_reserve);
		share = getSharedPreferences(PreferencesName.PREFERENCES_NAME, Context.MODE_PRIVATE);
		onclick1 = new OnClickListener(){

			@Override
			public void onClick(View v) {
				switch(v.getId()){
				case R.id.butt_pri_valet:
					showValetDialog();
					break;
				case R.id.butt_reserve:
					if (share.getBoolean(PreferencesName.PREF_KEY_LOGIN_STATUS, false)){
					showReservationDialog();}
					else{
						
						AlertDialog.Builder builder = new AlertDialog.Builder(PrivillegeActivity.this);
						builder.setTitle("Please Login First");
						builder.setMessage("Reservation support privillege member only.Please login first");
						builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog2, int which) {
								LoginDialog dialog = new LoginDialog(PrivillegeActivity.this,Request.REQUEST_LOGIN_RESERVATION);
								dialog.show();
							}
						});
						builder.show();
				
					}
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
		ReservationDialog reservation = new ReservationDialog(PrivillegeActivity.this,1,Request.REQUEST_RESERVATION_FROM_PARKING);
		reservation.setTitle("Reservation");
		reservation.show();	
	}
	
	private void showValetDialog() {
		ValetDialogBox valet = new ValetDialogBox(PrivillegeActivity.this);
		valet.setTitle("Velet Service Request");
		valet.show();
	}
	
	public void ValetCompleteMessage(boolean status) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
		
		if (status==true)
		{		
			builder.setTitle("Thank you");
			builder.setMessage("Thank you for you visit\nYour car will reach you in 5 min.");
			builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			});
			
			}
		else{
			builder.setTitle("Sorry");
			builder.setMessage("Your valetId or telephone number is not found in our service");
			builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			});
		}
		builder.show();
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
					// TODO Auto-generated method stub
				}
			});			
			}
		else{
			builder.setTitle("Reservation Failed");
			builder.setMessage(message);
			builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			});
		}
		builder.show();
	}
	public void Login(boolean status,String message) {
		SharedPreferences.Editor edit = share.edit();	
		if (status==true)
		{   String firstname,lastname,tel;
			JSONObject json;
			try {
				json = new JSONObject(message);
				firstname = json.getString("firstname");
				lastname = json.getString("lastname");
				tel = json.getString("tel");
				edit.putString(PreferencesName.PREF_KEY_FIRSTNAME, firstname);
				edit.putString(PreferencesName.PREF_KEY_LASTNAME, lastname);
				edit.putString(PreferencesName.PREF_KEY_TELEPHONE, tel);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			edit.putBoolean(PreferencesName.PREF_KEY_LOGIN_STATUS, true);
			}
		else{
			edit.putBoolean(PreferencesName.PREF_KEY_LOGIN_STATUS, false);
			Toast.makeText(this, "Incorrect user or password", Toast.LENGTH_SHORT).show();
		}
		edit.commit();
	}

}
