package com.example.sipsproject2;
 
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
 
public class MainActivity extends TabActivity {
 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
 
		Resources ressources = getResources(); 
		final TabHost tabHost = getTabHost(); 
	//	Button homeButton = (Button)findViewById(R.id.home_button);
		// Android tab
		/*Intent intentAndroid = new Intent().setClass(this, AndroidActivity.class);
		TabSpec tabSpecAndroid = tabHost
		  .newTabSpec("Android")
		  .setIndicator("", ressources.getDrawable(R.drawable.icon_android_config))
		  .setContent(intentAndroid);
 */
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
 
}