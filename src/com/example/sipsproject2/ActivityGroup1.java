package com.example.sipsproject2;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ActivityGroup1 extends ActivityGroup
{
protected void onCreate(Bundle savedInstanceState) 
{ super.onCreate(savedInstanceState); replaceContentView("activity1", new Intent(this, HomeActivity.class),
		Intent.FLAG_ACTIVITY_CLEAR_TOP); }
 
public void replaceContentView(String id, Intent newIntent, int flag) { 
	View view = getLocalActivityManager().startActivity(id, newIntent.addFlags(flag)).getDecorView(); 
	this.setContentView(view); }
}