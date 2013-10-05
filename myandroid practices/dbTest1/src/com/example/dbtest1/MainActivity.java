package com.example.dbtest1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
	 myQueries();
	}

	private void myQueries() {
		// TODO Auto-generated method stub
		ArrayList<ArrayList<String>> stringList =  DBAdapter.executeQueryOnDatabase(this, "Select name from animals");
		for (int i = 0; i < stringList.size(); i++) {
			Log.d("item "+ i , ""+stringList.get(i));
		}
		
		String[] from = { "flag","txt" };

		ListView myList = (ListView)findViewById(R.id.list1);
        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();
	   	
        
	   	for(int i=0;i<stringList.size();i++){
	   	 HashMap<String, String> hm = new HashMap<String,String>();
	   	 hm.put("txt", ""+ stringList.get(i));
	   	 hm.put("flag", ""+R.drawable.ic_launcher); 
	     aList.add(hm);
	   }
        // Ids of views in listview_layout
        int[] to = { R.id.flag,R.id.txt};
        SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.list_item_tpl, from, to);
        
        myList.setAdapter(adapter);
		
	}

	

}
