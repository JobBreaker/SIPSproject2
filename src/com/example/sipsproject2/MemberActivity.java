package com.example.sipsproject2;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MemberActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_member);
		
	}
	@Override
	  public void onBackPressed() {
	// TODO Auto-generated method stub
		super.onBackPressed();
	    Log.e("back", "pressed accepted");
	    //Intent intent = new Intent(this, HomeActivity.class); Activity parentActivity = (HomeActivity)getParent();
	    finish();
	    //parentActivity.replaceContentView("HomeActivity", intent, Intent.FLAG_ACTIVITY_REORDER_TO_FRONT );
	  
	 }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.member, menu);
		return true;
	}

}
