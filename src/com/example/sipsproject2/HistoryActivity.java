package com.example.sipsproject2;

import java.util.List;


import com.example.sqlite.LogDatasource;
import com.example.sqlite.QrLog;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HistoryActivity extends ListActivity {
  private LogDatasource datasource;
  List<QrLog> values;
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_history);

    datasource = new LogDatasource(this);
    datasource.open();

    values = datasource.getAllComments();

    // use the SimpleCursorAdapter to show the
    // elements in a ListView
    ArrayAdapter<QrLog> adapter = new ArrayAdapter<QrLog>(this,
        android.R.layout.simple_list_item_1, values);
    setListAdapter(adapter);
    ListView list = getListView();
    list.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			Intent data = new Intent();
			data.putExtra("position", values.get(position).getComment());
			if (getParent()==null){
				setResult(Activity.RESULT_OK,data);
				finish();
			}
			else{
				setResult(Activity.RESULT_OK,data);
				finish();
			}
			
		}
    	
    	
	});
  }

  // Will be called via the onClick attribute
  // of the buttons in main.xml
  public void onClick(View view) {
   // @SuppressWarnings("unchecked")
    //ArrayAdapter<QrLog> adapter = (ArrayAdapter<QrLog>) getListAdapter();
    int id = view.getId(); 
    if (id== R.id.add){
      datasource.deleteAllComment();
      reload();
      
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