package com.example.sipstool;

import com.example.sipsproject2.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;


public class ValetDialogBox extends Dialog implements android.view.View.OnClickListener{
	public Activity context;
	public Dialog dialog;
	public Button yesButton,noButton;
	public ValetDialogBox(Activity a) {
		super(a);
		this.context = a;
		// TODO Auto-generated constructor stub
	}
	@Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    setContentView(R.layout.valet_dialog_box);
	    yesButton = (Button) findViewById(R.id.btn_yes);
	    noButton = (Button) findViewById(R.id.btn_no);
	    yesButton.setOnClickListener(this);
	    noButton.setOnClickListener(this);
	    

	  }
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		 switch (v.getId()) {
		    case R.id.btn_yes:
		      dismiss();
		      break;
		    case R.id.btn_no:
		      dismiss();
		      break;
		    default:
		      break;
		    }
		    dismiss();
		  }

		
	}


