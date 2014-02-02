package com.example.sipstool;

import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;

import com.example.httpconnect.PostTask;
import com.example.httpconnect.Request;
import com.example.sipsproject2.MemberActivity;
import com.example.sipsproject2.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginDialog extends Dialog implements android.view.View.OnClickListener{
	SharedPreferences share;
	EditText userBox,passBox;
	String user,password,mRequest;
	Context mContext;
	boolean isLogin;
	public LoginDialog(Activity context) {
		super(context);
		mContext = context;
		mRequest = Request.REQUEST_LOGIN;
	}
	public LoginDialog(Activity context,String request) {
		super(context);
		mContext = context;
		mRequest = request;
	}
	  protected void onCreate(Bundle savedInstanceState) {
		    super.onCreate(savedInstanceState);
		    requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.login_dialog);
			share = mContext.getSharedPreferences(PreferencesName.PREFERENCES_NAME, Context.MODE_PRIVATE);
			isLogin = share.getBoolean(PreferencesName.PREF_KEY_LOGIN_STATUS, false);
			Button loginButton = (Button)findViewById(R.id.butt_member_login2);
			Button cancelButton = (Button)findViewById(R.id.butt_member_regis2);
			userBox = (EditText)findViewById(R.id.member_id_box2);
			passBox = (EditText)findViewById(R.id.member_pass_box2);
			loginButton.setOnClickListener(this);
			cancelButton.setOnClickListener(this);
	  }
	@Override
	public void onClick(View v) {
		ArrayList<BasicNameValuePair>data = new ArrayList<BasicNameValuePair>();
		
		if (v.getId()==R.id.butt_member_login2){
		user = userBox.getText().toString();
		password = passBox.getText().toString();
		SharedPreferences.Editor edit = share.edit();
		edit.putString(PreferencesName.PREF_KEY_USER_ID, user);
		edit.putString(PreferencesName.PREF_KEY_PASSWORD, password);
		edit.commit();
		data.add(new BasicNameValuePair("request", mRequest));
		data.add(new BasicNameValuePair("user", user));
		data.add(new BasicNameValuePair("pass", password));
		PostTask task = new PostTask(mContext, data);
    	task.execute("http://158.108.34.17/mobile/login/login.php");
    	dismiss();
		}
		else{
			dismiss();
		}
	}


}
