package com.example.hideshow;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;
import android.widget.Toast;

public class MainActivity extends Activity  {
 
    Button slideButton,b1, b2,b3,b4;
    SlidingDrawer slidingDrawer;
	 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        setContentView(R.layout.activity_main);
        slideButton = (Button) findViewById(R.id.slideButton);
        slidingDrawer = (SlidingDrawer) findViewById(R.id.SlidingDrawer);
        b1 = (Button) findViewById(R.id.Button01);
        b2 = (Button) findViewById(R.id.Button02);
        b3 = (Button) findViewById(R.id.Button03);
        b4 = (Button) findViewById(R.id.Button04);
        	//b1.setOnClickListener(MyClicked);
        b1.setOnClickListener(MyClicked);
        b2.setOnClickListener(MyClicked);
        b3.setOnClickListener(MyClicked);
        b4.setOnClickListener(MyClicked);
 
        slidingDrawer.setOnDrawerOpenListener(new OnDrawerOpenListener() {
            @Override
            public void onDrawerOpened() {
                slideButton.setBackgroundResource(R.drawable.ic_launcher);
            }
        });
 
        slidingDrawer.setOnDrawerCloseListener(new OnDrawerCloseListener() {
            @Override
            public void onDrawerClosed() {
                slideButton.setBackgroundResource(R.drawable.ic_launcher);
            }
        });
    }
    private android.view.View.OnClickListener MyClicked = new android.view.View.OnClickListener() {
		
	
		public void onClick(View v) {
			Button b = (Button)v;
	      //  Toast.makeText(slidingDrawerDemo.this, b.getText() + " Clicked", Toast.LENGTH_SHORT).show();
			displayToast(b.getText() + " Clicked");
		}

		
	};
	public void displayToast(String msj){
		Toast.makeText(this, msj, Toast.LENGTH_SHORT).show();
	}

    
//    @Override
//    public void onClick(View v) {
//        Button b = (Button)v;
//        Toast.makeText(slidingDrawerDemo.this, b.getText() + " Clicked", Toast.LENGTH_SHORT).show();
//    }

//	@Override
//	public void onClick(DialogInterface arg0, int arg1) {
//		// TODO Auto-generated method stub
//		
//	}
}