package com.example.sipsproject2;



import com.example.scanner.ZBarConstants;
import com.example.scanner.ZBarScannerActivity;
import com.example.sipstool.PreferencesName;
import com.example.sqlite.LogDatasource;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TrackingActivity extends Activity {
	private static final int ZBAR_SCANNER_REQUEST = 0;
    private static final int ZBAR_QR_SCANNER_REQUEST = 1;
    private static final int HISTORY_REQUEST = 100;
    private static final int SCAN_BUTTON = 1000;
    private static final int TRACKING_BUTTON = 2000;
    SharedPreferences share;
    TextView tv1;
    RelativeLayout layout,route_layout;
    boolean layout_yourcar_visible = false;
    boolean layout_route_visible =false;
    private LogDatasource datasource;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tracking);
		Button qrButton = (Button)findViewById(R.id.butt_track_scan);
		Button historyButton = (Button)findViewById(R.id.butt_track_show_history);
		share = getSharedPreferences(PreferencesName.PREFERENCES_NAME, Context.MODE_PRIVATE);
		route_layout = (RelativeLayout)findViewById(R.id.car_route_layout);
		RelativeLayout carButton = (RelativeLayout)findViewById(R.id.butt_your_car);
		RelativeLayout trackingButton = (RelativeLayout)findViewById(R.id.butt_tracking);
		layout = (RelativeLayout)findViewById(R.id.car_detail_layout);
		tv1 = (TextView)findViewById(R.id.car_pos);
		tv1.setText("Your car is "+share.getString(PreferencesName.PREF_KEY_CAR_POSITION, "no data"));
		//database SQL Load


		OnClickListener buttonClick = new OnClickListener(){

			@Override
			public void onClick(View v) {
				//Intent qrActivity = new Intent(v.getContext(),ZBarScannerActivity.class);
				switch (v.getId()){
				case R.id.butt_track_show_history:
					Intent historyIntent =  new Intent(v.getContext(),HistoryActivity.class);
					startActivityForResult(historyIntent,HISTORY_REQUEST);
					break;
				case R.id.butt_track_scan:
					startActivityQR(SCAN_BUTTON);
					//startActivityForResult(qrActivity,ZBAR_SCANNER_REQUEST);
					break;
				case R.id.butt_your_car:
					if (layout_yourcar_visible == false){
						layout.setVisibility(View.VISIBLE);
						route_layout.setVisibility(View.INVISIBLE);
						layout_yourcar_visible = true;
						layout_route_visible = false;
					}
					else{
						layout.setVisibility(View.INVISIBLE);
						layout_yourcar_visible = false;
					}
					break;
				case R.id.butt_tracking:
					if (layout_route_visible == false){
						layout.setVisibility(View.INVISIBLE);
						route_layout.setVisibility(View.VISIBLE);
						layout_yourcar_visible = false;
						layout_route_visible = true;
					}
					else{
						route_layout.setVisibility(View.INVISIBLE);
						layout_route_visible = false;
					}
					startActivityQR(TRACKING_BUTTON);
					//startActivityForResult(qrActivity,ZBAR_SCANNER_REQUEST);
					break;
					default:
						break;
				}}};		
		
		// setClickListener
		carButton.setOnClickListener(buttonClick);
		trackingButton.setOnClickListener(buttonClick);
		historyButton.setOnClickListener(buttonClick);
		qrButton.setOnClickListener(buttonClick);
	}
	
	private void startActivityQR(int code){
		String title="",content="";
		final Intent qrActivity = new Intent(TrackingActivity.this,ZBarScannerActivity.class);
		AlertDialog.Builder alert_builder = new AlertDialog.Builder(this);
		switch (code){
		case SCAN_BUTTON:
			title = "SCAN YOUR QR POSITION";
			content = "Do you want to scan your current qr position";
			break;
		case TRACKING_BUTTON:
			title = "TRACKING TO YOUR CAR";
			content = "Do you want to scan qr for route to your car";
			break;
		default:
			break;
		}
		alert_builder.setTitle(title);
		alert_builder.setMessage(content)
		.setCancelable(false)
		.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				startActivityForResult(qrActivity,ZBAR_SCANNER_REQUEST);
				
			}
		})
		.setNegativeButton("No", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				route_layout.setVisibility(View.INVISIBLE);
				layout_route_visible = false;
				dialog.cancel();
			}
		});
		AlertDialog alertDialog = alert_builder.create();
		alertDialog.show();
	}
	
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ZBAR_SCANNER_REQUEST:
            case ZBAR_QR_SCANNER_REQUEST:
                if (resultCode == RESULT_OK) {
              	   //get current date time with Date()
            		datasource = new LogDatasource(this);
            	    datasource.open();
            	    datasource.createComment(data.getStringExtra(ZBarConstants.SCAN_RESULT));
                    tv1.setText("Your car is " + data.getStringExtra(ZBarConstants.SCAN_RESULT));
                    SharedPreferences.Editor edit=share.edit();
                    edit.putString(PreferencesName.PREF_KEY_CAR_POSITION, data.getStringExtra(ZBarConstants.SCAN_RESULT));
                    edit.commit();
                    datasource.close();
                } else if(resultCode == RESULT_CANCELED && data != null) {
                    String error = data.getStringExtra(ZBarConstants.ERROR_INFO);
                    if(!TextUtils.isEmpty(error)) {
                        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case HISTORY_REQUEST:
            	if (resultCode== RESULT_OK){
            		datasource = new LogDatasource(this);
                    tv1.setText("Your car is " + data.getStringExtra("position"));
                    datasource.close();
            	}
            default:
            	break;
        }
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tracking, menu);
		return true;
	}

}
