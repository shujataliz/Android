package com.example.xmlpullparser;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends Activity {

	List headlines;
    List links;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getData();
	}
	private void getData() {
		// TODO Auto-generated method stub
		// Initializing instance variables
		headlines = new ArrayList();
		links = new ArrayList();
		 
		try {
		   // URL url = new URL("http://feeds.pcworld.com/pcworld/latestnews");
			 URL url = new URL("http://www.pcworld.com/index.rss");
		 
		    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		    factory.setNamespaceAware(false);
		    XmlPullParser xpp = factory.newPullParser();
		    Log.d("success11","scu");
		        // We will get the XML from an input stream
		   xpp.setInput(getInputStream(url), "UTF_8");
		   Log.d("success22","scu");
		 
		        /* We will parse the XML content looking for the "<title>" tag which appears inside the "<item>" tag.
		         * However, we should take in consideration that the rss feed name also is enclosed in a "<title>" tag.
		         * As we know, every feed begins with these lines: "<channel><title>Feed_Name</title>...."
		         * so we should skip the "<title>" tag which is a child of "<channel>" tag,
		         * and take in consideration only "<title>" tag which is a child of "<item>"
		         *
		         * In order to achieve this, we will make use of a boolean variable.
		         */
		    boolean insideItem = false;
		 
		        // Returns the type of current event: START_TAG, END_TAG, etc..
		    int eventType = xpp.getEventType();
		    while (eventType != XmlPullParser.END_DOCUMENT) {
		        if (eventType == XmlPullParser.START_TAG) {
		 
		            if (xpp.getName().equalsIgnoreCase("item")) {
		                insideItem = true;
		            } else if (xpp.getName().equalsIgnoreCase("title")) {
		                if (insideItem)
		                    headlines.add(xpp.nextText()); //extract the headline
		            } else if (xpp.getName().equalsIgnoreCase("link")) {
		                if (insideItem)
		                    links.add(xpp.nextText()); //extract the link of article
		            }
		        }else if(eventType==XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item")){
		            insideItem=false;
		        }
		 
		        eventType = xpp.next(); //move to next element
		    }
		 
		} catch (MalformedURLException e) {
		    e.printStackTrace();
		} catch (XmlPullParserException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		 
		// Binding data
		Log.d("success1","scu");
		ArrayAdapter adapter = new ArrayAdapter(this,
		        android.R.layout.simple_list_item_1, headlines);
		 ListView lv = (ListView)findViewById(R.id.list1);
		 lv.setAdapter(adapter);
		 lv.setOnItemClickListener(listItem);
		//setListAdapter(adapter);
	}
	public InputStream getInputStream(URL url) {
		   try {
		       return url.openConnection().getInputStream();
		   } catch (IOException e) {
		       return null;
		     }
		}
	
	private OnItemClickListener listItem = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long arg3) {
			// TODO Auto-generated method stub
			Uri uri = Uri.parse((String) links.get(position));
			Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(intent);
			
			
		}
//		@Override
//		protected void onListItemClick(ListView l, View v, int position, long id) {
//		 
//		}
		
		
	};
	


}
