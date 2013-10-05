package com.example.tablayout;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MainActivity extends TabActivity {

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 
        TabHost tabHost = getTabHost();
        //getTabWidget().setOrientation(LinearLayout.VERTICAL);
//        tabHost.getTabWidget().setOrientation(LinearLayout.HORIZONTAL);
        // Tab for Photos
        TabSpec photospec = tabHost.newTabSpec("tab1");
        // setting Title and Icon for the Tab
        photospec.setIndicator("tab1", getResources().getDrawable(R.drawable.tab1));
        Intent photosIntent = new Intent(this, tab1.class);
        photospec.setContent(photosIntent);
 
        // Tab for Songs
        TabSpec songspec = tabHost.newTabSpec("tab2");
        songspec.setIndicator("tab2", getResources().getDrawable(R.drawable.tab2));
        Intent songsIntent = new Intent(this, tab2.class);
        songspec.setContent(songsIntent);
 
        // Tab for Videos
        TabSpec videospec = tabHost.newTabSpec("tab3");
        videospec.setIndicator("tab3", getResources().getDrawable(R.drawable.tab3));
        Intent videosIntent = new Intent(this, tab3.class);
        videospec.setContent(videosIntent);
 
        // Adding all TabSpec to TabHost
        tabHost.addTab(photospec); // Adding photos tab
        tabHost.addTab(songspec); // Adding songs tab
        tabHost.addTab(videospec); // Adding videos tab
    }
}