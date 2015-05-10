package com.example.shujatali.testfragment;

import android.app.ActivityGroup;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class activity1  extends ActivityGroup {
    protected static LocalActivityManager mLocalActivityManager;

    Button launchButton;
    /** Called when the activity is first created. */
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity1);
        launchButton = (Button) findViewById(R.id.button);
        launchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent activity3Intent = new Intent(v.getContext(), Activity3.class);
                StringBuffer urlString = new StringBuffer();
//Activity1 parentActivity = (Activity1)getParent();
                replaceContentView("activity3", activity3Intent);
            }
        });
    }

    public void replaceContentView(String id, Intent newIntent) {
        View view = getLocalActivityManager().startActivity(id,newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)) .getDecorView(); this.setContentView(view);
    }



}

