package com.example.sipsproject2;



import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;

import com.example.httpconnect.PostTask;
import com.example.httpconnect.Request;
import com.example.scanner.ZBarConstants;
import com.example.scanner.ZBarScannerActivity;
import com.example.sipstool.PreferencesName;
import com.example.sipstool.QrResult;
import com.example.sqlite.LogDatasource;

import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
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
    static SharedPreferences share;
    TextView tv1;
    static WebView webview;
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
		webview = (WebView)findViewById(R.id.map_your_car);
		webview.getSettings().setJavaScriptEnabled(true);
		layout = (RelativeLayout)findViewById(R.id.car_detail_layout);
		tv1 = (TextView)findViewById(R.id.car_pos);
		tv1.setText("Your car is floor "+share.getInt(PreferencesName.PREF_KEY_CAR_FLOOR, 0)+" "+share.getString(PreferencesName.PREF_KEY_CAR_POSITION, "no data"));
		//database SQL Load
		String check = share.getString(PreferencesName.PREF_KEY_CAR_POSITION, "");
		if (!check.isEmpty()){
			GetSensorId();
		}

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
			alert_builder.setTitle(title);
			alert_builder.setMessage(content)
			.setCancelable(false)
			.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					startActivityForResult(qrActivity,ZBAR_QR_SCANNER_REQUEST);
					
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
			break;
		case TRACKING_BUTTON:
			title = "TRACKING TO YOUR CAR";
			content = "Do you want to scan qr for route to your car";
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
			AlertDialog alertDialog2 = alert_builder.create();
			alertDialog2.show();
			break;
		default:
			break;
		}
		
		
	}
	
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ZBAR_SCANNER_REQUEST:
            	if (resultCode== RESULT_OK){
        		QrResult qrResult2 = new QrResult(data.getStringExtra(ZBarConstants.SCAN_RESULT));
        		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        		dialog.setTitle("FIND YOUR WAY");
        	    dialog.setMessage(findPath(qrResult2));
        	    dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
				});
        	    dialog.show();
            	}
                break;
            case ZBAR_QR_SCANNER_REQUEST:
                if (resultCode == RESULT_OK) {
              	   //get current date time with Date()
            		datasource = new LogDatasource(this);
            	    datasource.open();
            	    
            	    QrResult qrResult = new QrResult(data.getStringExtra(ZBarConstants.SCAN_RESULT));
            	    datasource.createComment("floor:"+qrResult.getFloor()+" zone:"+qrResult.getZone());
                    tv1.setText("Your car is in floor" + qrResult.getFloor() + " zone "+qrResult.getZone());
                    SharedPreferences.Editor edit=share.edit();
                    edit.putInt(PreferencesName.PREF_KEY_CAR_MALL,qrResult.getMall());
                    edit.putInt(PreferencesName.PREF_KEY_CAR_FLOOR, qrResult.getFloor());
                    edit.putString(PreferencesName.PREF_KEY_CAR_POSITION, qrResult.getZone());
                    edit.commit();
                    GetSensorId();
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
                    datasource.close();
            	}
            default:
            	break;
        }
    }
    private String findPath(QrResult result){
    	String str ="Now You are on Floor "+result.getFloor()+" Zone "+result.getZone();
    	String direction="";
    	int a = share.getInt(PreferencesName.PREF_KEY_CAR_FLOOR, 0);
    	int b = result.getFloor();
    	if (a-b<0){
    		direction=" Please go up "+(b-a)+" floor.Then find your car on map";
    	}
    	else if (a-b==0){
    		direction=" Your car is on this Floor. Please find your car on map";
    	}
    	else{
    		direction=" Please go down "+(a-b)+" floor.Then find your car on map";
    	}
    	return str+"."+direction;
    }
    private void GetSensorId(){
    	ArrayList<BasicNameValuePair>data = new ArrayList<BasicNameValuePair>();
  	  data.add(new BasicNameValuePair("request",Request.REQUEST_TRACKING));
	  data.add(new BasicNameValuePair("mall", String.valueOf(share.getInt(PreferencesName.PREF_KEY_CAR_MALL, 0))));
	  data.add(new BasicNameValuePair("floor", String.valueOf(share.getInt(PreferencesName.PREF_KEY_CAR_FLOOR, 0))));
	  data.add(new BasicNameValuePair("zone", share.getString(PreferencesName.PREF_KEY_CAR_POSITION, "0")));	  
	  PostTask task = new PostTask(TrackingActivity.this, data);
	  task.execute("http://158.108.34.17/mobile/sensorid/sensor.php");
    }
    public void loadWebview(boolean result,String message){
    	String url = "http://158.108.34.17/myproject/index.php/map/track?";
    	String getBody = "";
    	if (result){
    		getBody += "mall="+String.valueOf(share.getInt(PreferencesName.PREF_KEY_CAR_MALL, 0));
    		getBody += "&floor="+String.valueOf(share.getInt(PreferencesName.PREF_KEY_CAR_FLOOR, 0));
    		getBody += "&id_sensor="+message;
    		webview.loadUrl(url+getBody);
    	}
    	else{
    		Toast.makeText(this, "Cannot find sensor id", Toast.LENGTH_SHORT).show();
    	}
    }
    public void resetWebView(){
    	webview.clearView();
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tracking, menu);
		return true;
	}


}
