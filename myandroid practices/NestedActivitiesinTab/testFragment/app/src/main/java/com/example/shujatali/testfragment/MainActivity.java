package com.example.shujatali.testfragment;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;


public class MainActivity
        extends TabActivity {
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Resources res = getResources(); // Resource object to get Drawables
        TabHost tabHost = getTabHost();  // The activity TabHost
        TabHost.TabSpec spec;  // Resusable TabSpec for each tab
        Intent intent;  // Reusable Intent for each tab // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent().setClass(this, activity1.class);
        spec = tabHost.newTabSpec("Activity1").setIndicator("tab1").setContent(intent);
        tabHost.addTab(spec); // Do the same for the other tabs
        intent = new Intent().setClass(this, activity2.class);
        spec = tabHost.newTabSpec("Activity2").setIndicator("tab2") .setContent(intent);
        tabHost.addTab(spec);
    }

}

