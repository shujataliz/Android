package com.sauces.game;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;


public class SpellinggameActivity extends Activity {
	  private final int SPLASH_DISPLAY_LENGHT = 3000;
	  int credits,score;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscr);
        
     
        new Handler().postDelayed(new Runnable(){

                public void run() {

                        /* Create an Intent that will start the Menu-Activity. */

                        Intent mainIntent = new Intent(SpellinggameActivity.this,gamepage.class);

                        SpellinggameActivity.this.startActivity(mainIntent);

                        SpellinggameActivity.this.finish();

                }

        }, SPLASH_DISPLAY_LENGHT);
    }
    
    
}