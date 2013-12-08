package com.example.sipsproject2;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.RelativeLayout;

public class HomeActivity extends Activity {
	RelativeLayout settingView,aboutView,memberView,aboutDetail;
	OnClickListener onclick1;
	boolean detailVisible;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		settingView = (RelativeLayout)findViewById(R.id.butt_setting);
		aboutView = (RelativeLayout)findViewById(R.id.butt_about);
		memberView = (RelativeLayout)findViewById(R.id.butt_member);
		aboutDetail = (RelativeLayout)findViewById(R.id.about_detail);
		// set onclickButton
		onclick1 = new OnClickListener(){
			@Override
			public void onClick(View v) {
				switch (v.getId()){
				case R.id.butt_about:
					
					break;
				case R.id.butt_member:
					Intent memberActivity = new Intent(v.getContext(),MemberActivity.class);
					//StringBuffer urlString = new StringBuffer();
					startActivityForResult(memberActivity,12);
					//ActivityGroup1.replaceContentView("memberActivity", memberActivity);
					break;
				default:
					break;
					
				}	
			}
			
		};
		
		//set onclickListener
		settingView.setOnClickListener(onclick1);
		aboutView.setOnClickListener(onclick1);
		memberView.setOnClickListener(onclick1);
		
	}
	protected void onActivityResult(int requestCode, int resultCode,
            Intent data) {
        if (requestCode == 12) {
            if (resultCode == RESULT_OK) {
                // A contact was picked.  Here we will just display it
                // to the user.
            }
        }
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}
}
	



	