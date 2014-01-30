package com.example.httpconnect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
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

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class ConnectServer extends AsyncTask<String, Integer, String>{
	private HttpPost httppost;
	private HttpGet httpget;
	private HttpClient httpclient;
	private List<NameValuePair> nameValuePairs;
	private DialogConnect dialogConnect;
	private Context context;
	private String url;

	
	public ConnectServer(Context context,String URL){
		this.context = context;
		this.httpclient = new DefaultHttpClient();
		httppost = new HttpPost(url);
		this.url = url;
		this.nameValuePairs = new ArrayList<NameValuePair>();
		dialogConnect = new DialogConnect(this.context, this);
		dialogConnect.setTitle("SIPS Project");
		dialogConnect.setMessage("Send Request");
	}

	
	public void addValue(String key,String value){
		this.nameValuePairs.add(new BasicNameValuePair(key, value));
	}
	
	
	protected void onPreExecute() {
		dialogConnect.show();
	}

	protected String doInBackground(String... params) {
		InputStream is = null;
		String result = null;
		
	
        try {
        	String check = nameValuePairs.toString();
        	List<NameValuePair> list = new ArrayList<NameValuePair>();

        	
        	httppost.setEntity(new UrlEncodedFormEntity(list));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();           
        	BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
        	StringBuilder sb = new StringBuilder();
        	String line = null;
        	while ((line = reader.readLine()) != null) {
        		sb.append(line + "\n");
        	}
        	is.close();
        	result = sb.toString();
        	
        } catch (ClientProtocolException e) {
        	Log.e("ConnectServer", e.toString());
        } catch (IOException e) {
        	Log.e("ConnectServer", e.toString());
        }
		return result;
	}
	

	protected void onPostExecute(String result) {
			
		if(result != null){			
			try {		
				String data="";
	            JSONObject jObject = new JSONObject(result);	            	           
	            if(jObject.getString("request").equals("1000")){            	
	            	Toast.makeText(context, data, Toast.LENGTH_LONG);
	            }
	            else{
	            	Toast.makeText(context, "Not Found your ValetId", Toast.LENGTH_LONG).show();  	
	            }
	        } catch (JSONException e) {
	            Log.e("ConnectServer", "Error parsing data " + e.toString());
	            ((PrivillegeActivity)context).errorConnectToServer();
	        }
		}else{
			((PrivillegeActivity)context).cannotConnectToServer();
		}
		dialogConnect.dismiss();
	}
}
