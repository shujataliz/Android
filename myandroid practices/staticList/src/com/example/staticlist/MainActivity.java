package com.example.staticlist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;


public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Context ctx = getApplicationContext();
	   	Resources res = ctx.getResources();
		String[] options = res.getStringArray(R.array.country_names);
	   	String[] icons = res.getStringArray(R.array.country_icons);
	       // get an instance of FragmentTransaction from your Activity
	       ListView myList = (ListView)findViewById(R.id.my_list1);

	      
        String[] from = { "flag","txt" };
 
        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();
	   	
        
	   	for(int i=0;i<options.length;i++){
	   	 HashMap<String, String> hm = new HashMap<String,String>();
	   	 hm.put("txt",  options[i]);
	   	 hm.put("flag", ""+getResources().getIdentifier(icons[i], "drawable", this.getPackageName()) ); 
	     aList.add(hm);
	   }
        // Ids of views in listview_layout
        int[] to = { R.id.flag,R.id.txt};
        SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.list_item_tpl, from, to);
        
        myList.setAdapter(adapter);
        myList.setOnItemClickListener(listItemClicked);
	}

	
	private void showToast(String msg){
		Toast.makeText(this, msg, 1000).show();
	}
	private OnItemClickListener listItemClicked = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			showToast(""+arg2);
			
			
			
		}
	};
	

}
