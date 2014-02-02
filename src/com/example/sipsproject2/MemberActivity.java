package com.example.sipsproject2;

import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;

import com.example.httpconnect.PostTask;
import com.example.sipstool.PreferencesName;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MemberActivity extends Activity {
	public final String REQUEST_LOGIN = "9000";
	SharedPreferences share;
	EditText userBox,passBox;
	String user,password;
	boolean isLogin;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_member);
		share =getSharedPreferences(PreferencesName.PREFERENCES_NAME, Context.MODE_PRIVATE);
		isLogin = share.getBoolean(PreferencesName.PREF_KEY_LOGIN_STATUS, false);
		Button loginButton = (Button)findViewById(R.id.butt_member_login);
		userBox = (EditText)findViewById(R.id.member_id_box);
		passBox = (EditText)findViewById(R.id.member_pass_box);
		loginButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ArrayList<BasicNameValuePair>data = new ArrayList<BasicNameValuePair>();
				user = userBox.getText().toString();
				password = passBox.getText().toString();
				data.add(new BasicNameValuePair("request", REQUEST_LOGIN));
				data.add(new BasicNameValuePair("user", user));
				data.add(new BasicNameValuePair("pass", password));
				 PostTask task = new PostTask(MemberActivity.this, data);
		    	 task.execute("http://158.108.34.17/mobile/login/login.php");
			}
		});			
	}
	public void checkLogin(int bool){
		if (bool ==1){
		isLogin = true;
		SharedPreferences.Editor edit = share.edit();
		edit.putBoolean(PreferencesName.PREF_KEY_LOGIN_STATUS, true);
		edit.putString(PreferencesName.PREF_KEY_USER_ID, user);
		edit.putString(PreferencesName.PREF_KEY_PASSWORD, password);
		edit.commit();
		}
		else{
			Toast.makeText(this, "Login Failed Incorrect User Or Password", Toast.LENGTH_SHORT).show();
		}
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
