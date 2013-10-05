package com.samir;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private ProgressDialog pDialog;
	private ItemXMLHandler myXMLHandler;
	//private String rssFeed = "https://www.dropbox.com/s/t4o5wo6gdcnhgj8/imagelistview.xml?dl=1";
	private String rssFeed = "http://98.189.229.163/Mcgladrey_Service_URL/Service.asmx/GetGuestList?format=xml";
    private TextView textview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		textview =(TextView)findViewById(R.id.textView1);
	}

	public void doParsing(View v){
		
		if (isNetworkAvailable()) {
			textview.setText("Loading...Please wait...");
			new AsyncData().execute(rssFeed);
		} else {
			showToast("No Network Connection!!!");
		}	
	}
	class AsyncData extends AsyncTask<String, Void, Void> {

		@Override
		protected void onPreExecute() {
			pDialog = new ProgressDialog(MainActivity.this);
			pDialog.setTitle("Loading....");
			pDialog.setMessage("Please wait...");
			pDialog.show();
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(String... params) {

			try {
				SAXParserFactory spf = SAXParserFactory.newInstance();
				SAXParser sp = spf.newSAXParser();
				XMLReader xr = sp.getXMLReader();

				myXMLHandler = new ItemXMLHandler();
				xr.setContentHandler(myXMLHandler);

				URL _url = new URL(params[0]);

				xr.parse(new InputSource(_url.openStream()));

			} catch (ParserConfigurationException pce) {
				Log.e("SAX XML", "sax parse error", pce);
			} catch (SAXException se) {
				Log.e("SAX XML", "sax error", se);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;

		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);

			textview.setText("Done!!!");
			if (pDialog != null && pDialog.isShowing()) {
				pDialog.dismiss();
			}

			ArrayList<String> itemsList = myXMLHandler.getItemsList();

			if (null != itemsList && itemsList.size() != 0) {
				for (int index = 0; index < itemsList.size(); index++) {
					String objString = itemsList.get(index);

//					System.out.println(">>>>>>>>>>>>>>>" + index);
//					System.out.println("ID :: " + objString.getId());
//					System.out.println("TITLE :: " + objString.getTitle());
//					System.out.println("DESC :: " + objString.getDesc());
//					System.out.println("PUBDATE :: " + objString.getPubDate());
//					System.out.println("LINK :: " + objString.getLink());
				}
			}
		}
	}

	public void showToast(String msg) {
		Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
	}

	public boolean isNetworkAvailable() {
		ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			return false;
		} else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}
}
