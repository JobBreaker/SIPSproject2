package com.example.sipsproject2;

import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.httpconnect.PostTask;
import com.example.sipstool.LoginDialog;
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
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MemberActivity extends Activity {
	public final String REQUEST_LOGIN = "9000";
	SharedPreferences share;
	String user,password;
	boolean isLogin;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_member);
		share =getSharedPreferences(PreferencesName.PREFERENCES_NAME, Context.MODE_PRIVATE);
		isLogin = share.getBoolean(PreferencesName.PREF_KEY_LOGIN_STATUS, false);
		if (isLogin==false){
			goLogin(MemberActivity.this);
		}
		
	}
	
	public void goLogin(Context context){
		LoginDialog dialog = new LoginDialog(MemberActivity.this);
		dialog.show();
	}
	public void FirstCheck(int bool){
		if (bool ==1){
			isLogin = true;
			SharedPreferences.Editor edit = share.edit();
			edit.putBoolean(PreferencesName.PREF_KEY_LOGIN_STATUS, true);
			edit.putString(PreferencesName.PREF_KEY_USER_ID, user);
			edit.putString(PreferencesName.PREF_KEY_PASSWORD, password);
			edit.commit();
			}
			else{
			}
		
	}
	public void checkLogin(int bool,String message){
		SharedPreferences.Editor edit = share.edit();
		String firstname,lastname,tel;
		if (bool ==1){
		isLogin = true;	
		try {
			
			JSONObject json = new JSONObject(message);
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
		edit.commit();
		}
		else{
			edit.putBoolean(PreferencesName.PREF_KEY_LOGIN_STATUS, false);
			edit.commit();
			Toast.makeText(this, "Incorrect userId or password", Toast.LENGTH_SHORT);
			finish();
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
