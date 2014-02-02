package com.example.sipstool;

import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;

import com.example.httpconnect.PostTask;
import com.example.httpconnect.Request;
import com.example.sipsproject2.PrivillegeActivity;
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


public class ValetDialogBox extends Dialog implements android.view.View.OnClickListener{
	public final String REQUEST_VALET = "1000";
	Context context;
	public Dialog dialog;
	public Button yesButton,noButton;
	public EditText valetEditText,telEditBox;
	private String request;
	public ValetDialogBox(Activity a) {
		super(a);
		this.context = a;
		this.request = Request.REQUEST_LOGIN;
		// TODO Auto-generated constructor stub
	}
	public ValetDialogBox(Activity a,String request) {
		super(a);
		this.context = a;
		this.request = request;
		// TODO Auto-generated constructor stub
	}
	@Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    setContentView(R.layout.valet_dialog_box);
	    SharedPreferences share = context.getSharedPreferences(PreferencesName.PREFERENCES_NAME, Context.MODE_PRIVATE);
	    String tel = share.getString(PreferencesName.PREF_KEY_TELEPHONE, "");
	    yesButton = (Button) findViewById(R.id.btn_yes);
	    noButton = (Button) findViewById(R.id.btn_no);
	    valetEditText = (EditText) findViewById(R.id.valet_id_input);
	    telEditBox = (EditText)findViewById(R.id.tel_input);
	    if (!(tel.isEmpty())){
	    	telEditBox.setText(tel);
	    }
	    yesButton.setOnClickListener(this);
	    noButton.setOnClickListener(this);
	  }
	
	@Override
	public void onClick(View v) {
		ArrayList<BasicNameValuePair>data = new ArrayList<BasicNameValuePair>();
		// TODO Auto-generated method stub
		 switch (v.getId()) {
		    case R.id.btn_yes:
		      String valetId = valetEditText.getText().toString();
		      String tel = telEditBox.getText().toString();
		      if (valetId.isEmpty()){
		    	  Toast.makeText(context, "Please input valetId", Toast.LENGTH_SHORT).show();
		      }
		      else{
		    	  data.add(new BasicNameValuePair("valet_id",valetId));
		    	  data.add(new BasicNameValuePair("tel", tel));
		    	  data.add(new BasicNameValuePair("request", request));
		    	  PostTask task = new PostTask(context, data);
		    	  task.execute("http://158.108.34.17/mobile/valet/insert_valet.php");
		    	  dismiss();}
		      break;
		    case R.id.btn_no:
		    	//((PrivillegeActivity)context).cannotConnectToServer();
		      dismiss();
		      break;
		    default:
		      break;
		    }
	}
	}


