package com.example.sipsproject2;

import java.util.List;


import com.example.sqlite.LogDatasource;
import com.example.sqlite.QrLog;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

public class HistoryActivity extends ListActivity {
  private LogDatasource datasource;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_history);

    datasource = new LogDatasource(this);
    datasource.open();

    List<QrLog> values = datasource.getAllComments();

    // use the SimpleCursorAdapter to show the
    // elements in a ListView
    ArrayAdapter<QrLog> adapter = new ArrayAdapter<QrLog>(this,
        android.R.layout.simple_list_item_1, values);
    setListAdapter(adapter);
  }

  // Will be called via the onClick attribute
  // of the buttons in main.xml
  public void onClick(View view) {
   // @SuppressWarnings("unchecked")
    //ArrayAdapter<QrLog> adapter = (ArrayAdapter<QrLog>) getListAdapter();
    switch (view.getId()) {
    case R.id.add:
      datasource.deleteAllComment();
      reload();
      break;
    }
   // adapter.notifyDataSetChanged();
  }

  @Override
  protected void onResume() {
    datasource.open();
    super.onResume();
  }

  @Override
  protected void onPause() {
    datasource.close();
    super.onPause();
  }
  public void reload() {

	    Intent intent = getIntent();
	    overridePendingTransition(0, 0);
	    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
	    finish();

	    overridePendingTransition(0, 0);
	    startActivity(intent);
	}

} 