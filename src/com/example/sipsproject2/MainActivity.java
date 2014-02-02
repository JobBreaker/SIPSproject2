package com.example.sipsproject2;
 
import static com.example.sipstool.Constants.IMAGES;

import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;

import com.example.sipstool.Constants.Extra;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
 
public class MainActivity extends TabActivity {
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);		
		Resources ressources = getResources(); 
		final TabHost tabHost = getTabHost(); 

		Intent parkingIntent = new Intent().setClass(this, ParkingActivity.class);
		TabSpec tabSpecParking = tabHost.newTabSpec("ParkingActivity").setIndicator("",ressources.getDrawable(R.drawable.butt_parking))
				.setContent(parkingIntent);
		
		Intent homeIntent = new Intent().setClass(this, HomeActivity.class);
		TabSpec tabSpecHome = tabHost.newTabSpec("HomeActivity").setIndicator("",ressources.getDrawable(R.drawable.home))
				.setContent(homeIntent);
		
		Intent privillegeIntent = new Intent().setClass(this, PrivillegeActivity.class);
		TabSpec tabSpecPrivillege = tabHost.newTabSpec("PrivillegeActivity").setIndicator("",ressources.getDrawable(R.drawable.privillege))
				.setContent(privillegeIntent);
		
		Intent promotionIntent = new Intent().setClass(this, PromotionActivity.class);
		promotionIntent.putExtra(Extra.IMAGES, IMAGES);
		TabSpec tabSpecPromotion = tabHost.newTabSpec("PromotionActivity").setIndicator("",ressources.getDrawable(R.drawable.promotion))
				.setContent(promotionIntent);
		
		Intent trackingIntent = new Intent().setClass(this, TrackingActivity.class);
		TabSpec tabSpecTracking = tabHost.newTabSpec("TrackingActivity").setIndicator("",ressources.getDrawable(R.drawable.tracking))
				.setContent(trackingIntent);
		
		// add all tabs 
		//tabHost.addTab(tabSpecAndroid);
	//	tabHost.addTab(tabSpecApple);
	//	tabHost.addTab(tabSpecWindows);
		//tabHost.addTab(tabSpecBerry);
		tabHost.addTab(tabSpecParking);
		tabHost.addTab(tabSpecPrivillege);
		tabHost.addTab(tabSpecHome);
		tabHost.addTab(tabSpecPromotion);
		tabHost.addTab(tabSpecTracking);
		//set Windows tab as default (zero based)
		tabHost.setCurrentTab(2);		
	}
	
	
	public void onBackPressed() {
		imageLoader.stop();
		super.onBackPressed();
	}
 
}