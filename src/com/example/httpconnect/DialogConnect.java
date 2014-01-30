package com.example.httpconnect;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class DialogConnect extends ProgressDialog{
	
	private AsyncTask task;
	
	public DialogConnect(Context context,AsyncTask task) {
		
		super(context);
		
		this.task = task;
	}

	
	public void cancel() {
		task.cancel(true);
		super.cancel();
	}
	
}
