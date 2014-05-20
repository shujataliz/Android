package de.passsy.circularprogressbarsample;

import java.util.Timer;
import java.util.TimerTask;

import android.graphics.Color;

public class MyProgressAnimator {

	Timer timer;
	MainActivity ctx;

	HoloCircularProgressBar mHoloCircularProgressBar;
	float cProgress;
	float addFactor;

	public MyProgressAnimator(MainActivity dd, HoloCircularProgressBar bb, float TotalDuration) {
		// TODO Auto-generated constructor stub
		ctx = dd;
		cProgress = 0;
		addFactor = TotalDuration / 100;
		mHoloCircularProgressBar = bb;

	}

	public void startProgress() {

		mHoloCircularProgressBar.setMarkerProgress(cProgress);
		mHoloCircularProgressBar.setProgressColor(Color.CYAN);
		timer = new Timer();
		TimerTask updateProfile = new CustomTimerTask();
		timer.scheduleAtFixedRate(updateProfile, 0, 1000);
	}

	public void setZero() {
		cProgress = 0f;
		Pause();
		mHoloCircularProgressBar.setProgress(cProgress);
		mHoloCircularProgressBar.setMarkerProgress(cProgress);

	}

	public void Pause() {
		if (timer != null)
			timer.cancel();
	}

	public class CustomTimerTask extends TimerTask {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			ctx.runOnUiThread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					cProgress += addFactor;
					mHoloCircularProgressBar.setProgress(cProgress);
					mHoloCircularProgressBar.setMarkerProgress(cProgress);
				}
			});

		}

	}

}
