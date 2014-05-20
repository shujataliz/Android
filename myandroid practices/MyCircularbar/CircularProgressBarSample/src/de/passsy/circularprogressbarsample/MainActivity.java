package de.passsy.circularprogressbarsample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	MyProgressAnimator circularProgress;
	
	@Override
	protected void onCreate(final Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		float mySongDuration = 1.23f;// 1min 2 seconds
		
		circularProgress = new MyProgressAnimator(this,(HoloCircularProgressBar) findViewById(R.id.holoCircularProgressBar1), mySongDuration);
		circularProgress.setZero();

		((Button) findViewById(R.id.btn1)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				circularProgress.startProgress();
			}
		});

		((Button) findViewById(R.id.btn2)).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				circularProgress.Pause();
			}
		});

		((Button) findViewById(R.id.btn3)).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				circularProgress.setZero();

			}
		});

	}

}
