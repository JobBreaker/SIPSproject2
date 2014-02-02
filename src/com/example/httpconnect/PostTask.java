package com.example.httpconnect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.sipsproject2.MemberActivity;
import com.example.sipsproject2.PrivillegeActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

public class PostTask extends AsyncTask<String, Void, String>{
	private Context mContext;
	private ArrayList<BasicNameValuePair> mData;
	public final String RESULT_OK="1";
	public final String RESULT_FAILED ="0";
	public PostTask(Context context,ArrayList<BasicNameValuePair>data){
		mContext = context;
		mData = data;
	}
	protected void onPreExecute(){
        
    }
	@Override
	protected String doInBackground(String... params) {
		return PostData(params[0]);
	}

	@SuppressLint("ShowToast")
	@Override
	protected void onPostExecute(String result){
		try {
			JSONObject json = new JSONObject(result);
			String requestNumber = json.getString(Request.RESPOND_REQUEST);
			if (requestNumber.equals(Request.REQUEST_VALET)) {
	    	((PrivillegeActivity)mContext).cannotConnectToServer(result);}
			else if (requestNumber.equals(Request.REQUEST_RESERVATION)){
				((PrivillegeActivity)mContext).cannotConnectToServer(result);
			}
			else if (requestNumber.equals(Request.REQUEST_LOGIN)){
				((MemberActivity)mContext).checkLogin(json.getInt(Request.RESPOND_STATUS));
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
