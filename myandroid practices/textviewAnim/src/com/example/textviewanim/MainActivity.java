package com.example.textviewanim;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		  WebView web = (WebView) findViewById(R.id.myWebView);
		    web.loadUrl("file:///android_asset/www/index.html");
		
		  
		    
		    Handler myHandler = new Handler();
		    myHandler.postDelayed(mMyRunnable, 1200);
		
		
		            
		           
		    
		  
		    
	}
	
	private Runnable mMyRunnable =  new Runnable()
	{
	    @Override
	    public void run()
	    {
	       //Change state here
	    	  RelativeLayout mSwitcher = (RelativeLayout)findViewById(R.id.txtView);
	    	  mSwitcher.bringToFront();
//	    	 final EditText mSwitcher = (EditText) findViewById(R.id.edtAnim);
//	    	 final EditText mSwitcher2 = (EditText) findViewById(R.id.edtAnim);

			    
			    final Animation in = new AlphaAnimation(0.0f, 1.5f);
			    in.setDuration(3000);

			    final Animation out = new AlphaAnimation(1.5f, 0.0f);
			    out.setDuration(3000);
			    
	            mSwitcher.startAnimation(out);
			    mSwitcher.startAnimation(in);
	    }
	 };



}
