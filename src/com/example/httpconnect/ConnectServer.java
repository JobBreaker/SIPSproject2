package com.example.httpconnect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.sipsproject2.PrivillegeActivity;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class ConnectServer extends AsyncTask<String, Integer, String>{
	public final int RESULT_OK =1;
	public final int RESULT_FAILED=0;
	private Context mContext;
	private ArrayList<BasicNameValuePair>mData;
	private String mUrl;
	public ConnectServer(Context context,ArrayList<BasicNameValuePair>data){
		mContext = context;
		mData = data;

	}
	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		return SendRequest(params[0]);
	}
	protected void onPostExcute (String result) {
		Toast.makeText(mContext, result, Toast.LENGTH_LONG).show();
	}
	private String SendRequest(String url) {
		String strResult="";
		try{
		String dest = url+"?"+setRequestBody();
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
