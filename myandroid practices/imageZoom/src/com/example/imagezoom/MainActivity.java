package com.example.imagezoom;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.webkit.WebView;

public class MainActivity extends Activity {

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		WebView webview = (WebView)findViewById(R.id.myWV);
		webview.getSettings().setBuiltInZoomControls(true);
		webview.loadDataWithBaseURL("file:///android_asset/", "<img src='file:///android_res/drawable/images.jpg' />", "text/html", "utf-8", null);
	}


}
