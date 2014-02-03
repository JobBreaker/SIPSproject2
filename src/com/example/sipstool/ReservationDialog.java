package com.example.sipstool;

import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import com.example.httpconnect.PostTask;
import com.example.json.ReservationJSON;
import com.example.sipsproject2.R;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class ReservationDialog extends Dialog implements android.view.View.OnClickListener{
	final String mRequest;
	EditText customId,privillegeId;
	Button reservationButton,cancelButton;
	SharedPreferences share;
	Context mContext;
	String targetURL ="http://158.108.34.17/myproject/index.php/mobile";
	String [] list = Constants.mall_name;
	int mall_id = 0;
	Spinner spin;
	public ReservationDialog(Context context,long id,String request) {
		super(context);
		 mRequest=request;
		 mContext = context;
		 setContentView(R.layout.reservation_dialog);
		 customId = (EditText)findViewById(R.id.editText1);
		 privillegeId = (EditText)findViewById(R.id.editText2);
		 share = context.getSharedPreferences(PreferencesName.PREFERENCES_NAME, Context.MODE_PRIVATE);
		 if (share.getBoolean(PreferencesName.PREF_KEY_LOGIN_STATUS, false)){
			 customId.setText(share.getString(PreferencesName.PREF_KEY_USER_ID, null));
		 }
		 spin = (Spinner)findViewById(R.id.spinner1);
		 reservationButton = (Button)findViewById(R.id.btn_confirm);
		 cancelButton = (Button)findViewById(R.id.btn_cancel);
		 reservationButton.setOnClickListener(this);
		 cancelButton.setOnClickListener(this);
		 
		 mall_id = (int)id;
			ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item ,list );
					//ArrayAdapter.createFromResource(getOwnerActivity(), 
					//R.array.parking_floor, android.R.layout.simple_spinner_item);

			dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spin.setAdapter(dataAdapter);
			spin.setSelection((int)id-1);
			spin.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
						int position, long id) {
					mall_id = position+1;
					parent.getItemAtPosition(position);
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
				
					
				}
			});
		// TODO Auto-generated constructor stub
	}
	@Override
	public void onClick(View v) {
		ArrayList<BasicNameValuePair>data = new ArrayList<BasicNameValuePair>();
		switch(v.getId()){
		case R.id.btn_confirm:
			  if (share.getBoolean(PreferencesName.PREF_KEY_LOGIN_STATUS, false)){
			  data.add(new BasicNameValuePair("request", mRequest));
			  data.add(new BasicNameValuePair("user",share.getString(PreferencesName.PREF_KEY_USER_ID, "user1")));
			  data.add(new BasicNameValuePair("pass", share.getString(PreferencesName.PREF_KEY_PASSWORD, "1234")));
			  data.add(new BasicNameValuePair("mall", String.valueOf(mall_id)));
	    	  PostTask task = new PostTask(mContext, data);
	    	  task.execute(targetURL);}
	    	  dismiss();
			break;
		case R.id.btn_cancel:
			dismiss();
			break;
		default:
			break;
		}
		
	}


}
