package com.example.httpconnect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.sipsproject2.MemberActivity;
import com.example.sipsproject2.ParkingDetailActivity;
import com.example.sipsproject2.PrivillegeActivity;
import com.example.sipsproject2.TrackingActivity;


import android.annotation.SuppressLint;

import android.app.ProgressDialog;
import android.content.Context;

import android.os.AsyncTask;


public class PostTask extends AsyncTask<String, Void, String>{
	private Context mContext;
	private ArrayList<BasicNameValuePair> mData;
	private  ProgressDialog dialog;
	public final String RESULT_OK="1";
	public final String RESULT_FAILED ="0";
	public PostTask(Context context,ArrayList<BasicNameValuePair>data){
		mContext = context;
		mData = data;
		dialog = new ProgressDialog(mContext);
	}
	protected void onPreExecute(){
        dialog.setMessage("Please Waiting...");
        dialog.setTitle("Send Request");
        dialog.show();
    }
	@Override
	protected String doInBackground(String... params) {
		return PostData(params[0]);
	}

	@SuppressLint("ShowToast")
	@Override
	protected void onPostExecute(String result){
		dialog.dismiss();
		try {
			JSONObject json = new JSONObject(result);
			String requestNumber = json.getString(Request.RESPOND_REQUEST);
			if (requestNumber.equals(Request.REQUEST_VALET)) {
			   if (json.getString(Request.RESPOND_STATUS).equals(RESULT_OK))
			   ((PrivillegeActivity)mContext).ValetCompleteMessage(true);
			   else{
				   ((PrivillegeActivity)mContext).ValetCompleteMessage(false);
			   }
			}
			else if (requestNumber.equals(Request.REQUEST_RESERVATION)){
				if (json.getString(Request.RESPOND_STATUS).equals(RESULT_OK)){
				((PrivillegeActivity)mContext).ReservationMessage(true,json.getString(Request.RESPOND_MESSAGE));
				}
				else{
				((PrivillegeActivity)mContext).ReservationMessage(false,json.getString(Request.RESPOND_MESSAGE));
				}
			}
			else if (requestNumber.equals(Request.REQUEST_LOGIN)){
				((MemberActivity)mContext).checkLogin(json.getInt(Request.RESPOND_STATUS),json.getString(Request.RESPOND_MESSAGE));	//checkLogin(json.getInt(Request.RESPOND_STATUS));
			}
			else if(requestNumber.equals(Request.REQUEST_LOGIN_RESERVATION)){
				if (json.getString(Request.RESPOND_STATUS).equals(RESULT_OK)){
					((PrivillegeActivity)mContext).Login(true,json.getString(Request.RESPOND_MESSAGE));
					}
					else{
					((PrivillegeActivity)mContext).Login(false,json.getString(Request.RESPOND_MESSAGE));
					}
			}
			else if (requestNumber.equals(Request.REQUEST_TRACKING)){
				if (json.getString(Request.RESPOND_STATUS).equals(RESULT_OK)){
					((TrackingActivity)mContext).loadWebview( true, json.getString(Request.RESPOND_MESSAGE));
				}
				else{
					((TrackingActivity)mContext).loadWebview( false, json.getString(Request.RESPOND_MESSAGE));
				}
			}
			else if(requestNumber.equals(Request.REQUEST_RESERVATION_FROM_PARKING)){
				if (json.getString(Request.RESPOND_STATUS).equals(RESULT_OK)){
					((ParkingDetailActivity)mContext).ReservationMessage(true,json.getString(Request.RESPOND_MESSAGE));
					}
					else{
					((ParkingDetailActivity)mContext).ReservationMessage(false,json.getString(Request.RESPOND_MESSAGE));
					}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private String PostData(String strUrl){
		String strResult="";
		try{
		String dest = strUrl+"?"+setRequestBody();
		URL host =new URL(dest);
		HttpURLConnection con = (HttpURLConnection)host.openConnection();
		strResult = readStream(con.getInputStream());
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return strResult;
		
	}
	private String readStream(InputStream inputStream) {
		BufferedReader reader=null;
		StringBuilder sb = new StringBuilder();
		try{
			reader = new BufferedReader(new InputStreamReader(inputStream));
			String line;
			while ((line = reader.readLine())!=null){
				sb.append(line+"\n");
			}
		}catch (IOException e){
			e.printStackTrace();
		}
		finally{
			if (reader!=null){
				try {
					reader.close();
				}catch (IOException e){
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}

	private String setRequestBody() throws UnsupportedEncodingException{
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		for (int i =0;i<mData.size();i++){
			NameValuePair item = mData.get(i);
			sb.append(item.getName());
			sb.append("=");
			sb.append(item.getValue());
			if (i!=(mData.size()-1)){
				sb.append("&");
			}
		}
		return sb.toString();
	}
	
	

}
