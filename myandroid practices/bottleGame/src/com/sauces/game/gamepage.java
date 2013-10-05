package com.sauces.game;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


import android.app.Activity;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class gamepage extends Activity  implements SensorEventListener{
	
	 
	 Timer myTimer=null;
	 static game obj =null;
	 
	 Button nxtlvl,playAgain;
	 anim2 n1 =new anim2();
	 static int thres_mov=10;
	 private int thisStime=0;
	 MediaPlayer myPlayer;
	 private int thres_sprite=25;
	 private int thisSprTime=0;
	 private SensorManager sensorManager;
	 ImageView imgBasket;
	 int basketPos = -1;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.gamepage);	
		Log.d("gamepage","");
		
		if(obj==null){
			Log.d("obj still null","null");
			obj=new game();
			obj.newgameStart();
			 //if(gamepage.obj.level==0 || gamepage.obj.rows==0 || gamepage.obj.HH==0){
				 	obj.imgw=(int)(48*getResources().getDisplayMetrics().density);
				 	obj.imgh=(int)(55*getResources().getDisplayMetrics().density);
					Display display = getWindowManager().getDefaultDisplay();
					obj.HH=display.getHeight();
					obj.WW=display.getWidth();		
					obj.speed=(int)(2*getResources().getDisplayMetrics().density);
					obj.iy=(int)((-380)*getResources().getDisplayMetrics().density);
					//obj.rows=gamepage.obj.CalcRows();	
					//obj.storeObj();
				// }
		}
		
		Log.d("callGamePG1","callGamePG");
		
		//playsound();		
		displayinit();   
		sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
		// add listener. The listener will be HelloAndroid (this) class
		sensorManager.registerListener(this, 
				sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL);
		
		Log.d("no of obj.rows",Integer.toString(obj.rows));		
		//startTimer();	
		Log.d("gamePage"," "+ thres_mov );
	}
	
	public void onAccuracyChanged(Sensor sensor,int accuracy){
		
	}
	public int getBottleId(){
		Random rnd = new Random();
		int n = rnd.nextInt(8);
		if(n==0)n=1;
	 	int id = getResources().getIdentifier("sauce"+n, "drawable", getPackageName());	
	 	return id;
	}
	
	public void onSensorChanged(SensorEvent event){
		
		// check sensor type
		if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
			
			// assign directions
			float x=event.values[0];
			float y=event.values[1];
			//float z=event.values[2];
			if(basketPos==-1){
				basketPos = imgBasket.getTop();
			}
			if(y<=-1){   //turn left
				RelativeLayout.LayoutParams  params;
				params = new RelativeLayout.LayoutParams(  LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
				
				params.topMargin =  basketPos;
				if(imgBasket.getLeft() > 50)
				params.leftMargin= imgBasket.getLeft() - 28;
				imgBasket.setLayoutParams(params);
			}
			else if(y>=1){ //turn right
				RelativeLayout.LayoutParams  params;
				params = new RelativeLayout.LayoutParams(  LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
				params.topMargin =  basketPos;
				if(imgBasket.getLeft() < obj.WW - 180)
				params.leftMargin= imgBasket.getLeft() + 28;
				else 
					params.leftMargin= imgBasket.getLeft();
				imgBasket.setLayoutParams(params);
				
			}
		//	xCoor.setText("X: "+x);
		//	yCoor.setText("Y: "+y);
		//	zCoor.setText("Z: "+z);
		}
	}
	 
	
	
	private OnClickListener soundClick = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			CheckedTextView btn= (CheckedTextView)arg0;
			if(btn.isChecked()){             // mute  the sound
				if (myPlayer != null) {
		        	try{
					myPlayer.stop();
		        	myPlayer.release();
		        	myPlayer = null;
		        	btn.setChecked(false);
					btn.setBackgroundResource(R.drawable.sound);
					
					//obj.myPlayer.release();
					obj.myPlayer=null;
		        	}catch(Exception e){
		        		Log.d("exception sound gamepage",""+e);
		        	}
		        }
				
			}
			else{
				if(myPlayer == null){
					playsound();
					btn.setChecked(true);
					btn.setBackgroundResource(R.drawable.soundon);
				}
				else{
					myPlayer=null;
				}
			}
			
			
		}
	};
	
	 private void playsound() {
			// TODO Auto-generated method stub    	
	    	myPlayer = MediaPlayer.create(gamepage.this, R.raw.bgsound );	
	    	myPlayer.setOnCompletionListener(new OnCompletionListener() {
				
				@Override
				public void onCompletion(MediaPlayer mp) {
					// TODO Auto-generated method stub
					myPlayer.stop();
					Log.d("sound Releasing","oof");
					myPlayer.release();			
					Log.d("sound Released","oof");
				}
			});
			myPlayer.start();	
			myPlayer.setLooping(true);
//			if(obj.myPlayer==null){
//				obj.myPlayer=MediaPlayer.create(gamepage.this, R.raw.click );	
//		    	obj.myPlayer.setOnCompletionListener(new OnCompletionListener() {
//					
//					@Override
//					public void onCompletion(MediaPlayer mp) {
//						// TODO Auto-generated method stub
//						obj.myPlayer.stop();
//						Log.d("sound Releasing","oof");
//						obj.myPlayer.release();			
//						Log.d("sound Released","oof");
//						obj.myPlayer=MediaPlayer.create(gamepage.this, R.raw.click );	
//					}
//				});
//			}
	        
		}
	   
	 
	  @Override
	    protected void onResume() {    	
	        super.onResume();
	        Log.d("pause released","asd");
			startTimer();  
			Log.d("pause released","asd");
//			CheckedTextView  btnSound= (CheckedTextView)findViewById(R.id.soundchk);
//			btnSound.setOnClickListener(soundClick);
//			if(btnSound.isChecked())
//		        playsound();
			
		
	    }
	 
	

	
	public void startTimer(){
		if(myTimer==null){
		 myTimer = new Timer();
			myTimer.schedule(new TimerTask() {
				@Override
				public void run() {					
					TimerMethod();
				}			
			},5, 2); 
		}
	}
	
	

	private void displayinit() {
		// TODO Auto-generated method stub
	 
		obj.credTxt=(TextView)findViewById(R.id.credtxt);
		obj.credTxt.setText("" +obj.credits);
		
		createBricks();
		
		
		createBalloons();
		
		Log.d("callGamePG","callGamePG");
		imgBasket=(ImageView)findViewById(R.id.imageView2);       
		//int id = getResources().getIdentifier(obj.selected.toLowerCase(), "drawable", getPackageName());  
		imgBasket.setImageResource(R.drawable.basket);	
	    
	    obj.myPlayer=MediaPlayer.create(gamepage.this, R.raw.click );	
    	obj.myPlayer.setOnCompletionListener(new OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer mp) {
				// TODO Auto-generated method stub
				obj.myPlayer.stop();
				Log.d("sound Releasing","oof");
				obj.myPlayer.release();			
				Log.d("sound Released","oof");
				obj.myPlayer=MediaPlayer.create(gamepage.this, R.raw.click );	
			}
		});
		
	}
	

	private void createBricks() {
		// TODO Auto-generated method stub
    	RelativeLayout.LayoutParams  params ;
    	RelativeLayout ly ;
    	
//		//obj.bricks=new CheckedTextView[obj.selected.length()];
//		for (int i = 0; i < obj.selected.length(); i++) {
//			
////			obj.bricks[i]=new CheckedTextView(this);			
////			obj.bricks[i].setBackgroundResource(R.drawable.images1);
////			obj.bricks[i].setText("");
////			obj.bricks[i].setTextColor(Color.RED);
////			obj.bricks[i].setTextSize(20);
////			obj.bricks[i].setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
////			
//			
//			params = new RelativeLayout.LayoutParams( obj.WW/14, obj.HH/10);
//	    	ly =(RelativeLayout)findViewById(R.id.relLayout);	    				
//	    	
//			params.leftMargin =obj.WW/3 - (obj.selected.length()-3)*15 + obj.WW/10*(i%obj.selected.length());	
//			params.topMargin=obj.HH-obj.HH/2+obj.HH/5;			
//			//ly.addView(obj.bricks[i],params);				
//		}
		
	}	
    
    public void createBalloons(){
    	int i=0,j=0;
    	obj.imgView =  new CheckedTextView[obj.rows][obj.selected.length()];
    	int[] my1 =new int[] { 1,1,1,0,0,0,0,0 };
    	obj.ff.shuffle(my1);
    	
    	Log.d("creating createBalloons",obj.rows+","+obj.selected.length());
    	 for ( i = 0; i < obj.rows; i++) 
    	 {
    		 if(obj.char_ind>0)
    				obj.char_ind--;
    		 obj.ff.shuffle(my1);
    		 for ( j = 0; j < obj.selected.length(); j++) 
			{		
    			if(my1[j]==1) 
    				addnewBall(i,j);
    			else{
    				obj.imgView[i][j]= null;
    			}
			}
			
    	 }
		   
    }
   
    public void addnewBall(int i,int j){
    	
    	obj.imgView[i][j] =new CheckedTextView(this);  //=new ImageView(this);
    	RelativeLayout.LayoutParams  params = new RelativeLayout.LayoutParams(  LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
   	RelativeLayout ly =(RelativeLayout)findViewById(R.id.relLayout);
	 	
	 	 params.topMargin =(int)  TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 
	 			 (-200*i), getResources().getDisplayMetrics());
	 	
	 	obj.imgView[i][j].setBackgroundResource(getBottleId());
	 	//obj.imgView[i][j].setPadding(15, 7, 0, 0);
	 	obj.imgView[i][j].setGravity(Gravity.CENTER);
	 	//obj.imgView[i][j].setText(Character.toString(obj.charac[j][obj.char_ind]));
	 	obj.imgView[i][j].setId(j);
		obj.imgView[i][j].setOnClickListener(obj.ballclicklistener);		
		params.leftMargin =obj.WW/3 - (obj.selected.length()-3)*15 + obj.WW/10*(j%obj.selected.length());	
		//Log.d("params leftmargin"+i+","+"j="+j ,""+params.leftMargin);
		ly.addView(obj.imgView[i][j],params); 
		
    }
    
        @Override
    protected void onStop() 
    {
        super.onStop();
        Log.d("homebutton " , "MYonStop is called");       
        stopAll();  
        saveCredits();
    	//Intent pausePG=new Intent(gamepage.this,ispausepg.class);
    	//startActivity(pausePG);         
        // insert here your instructions
    	return;
    }
	
        private void saveCredits() {
			// TODO Auto-generated method stub
			  	SharedPreferences customSharedPreference = getSharedPreferences(
			            "myCustomSharedPrefs", Activity.MODE_PRIVATE);
			    SharedPreferences.Editor editor = customSharedPreference.edit();
			    editor.putInt("creditspref", gamepage.obj.credits);
			    editor.commit();
			    Log.d("sharedpreference",""+customSharedPreference.getInt("creditspref",-500));
				Log.d("sharedpreference","saving");
			
		  }
    
    @Override
    public void onBackPressed() {
        // do something on back.
    	
    	stopAll();   	
//    	Intent pausePG=new Intent(gamepage.this,ispausepg.class);
//    	//finish();
//    	startActivity(pausePG);        
    	//onDestroy();
    	return;
    }
    

    
  
	private Runnable Timer_Tick = new Runnable() {
		
		public void run() {

		//This method runs in the same thread as the UI. 
			if(++thisSprTime>=thres_sprite){
				thisSprTime=0;
				obj.ballClicked();
				obj.ballLoop();				
			}			
			
			if(++thisStime>=thres_mov){
				thisStime=0;
				n1.movBalls(obj.rows);						
				obj.ff.isdropping();
//				if(obj.animationPlay.lvlComplete()){
//					createDialog();
//				}
//				else 
				if(obj.char_ind<=0){
					
					if(obj.isGameOver()){
						
					//	Intent lostdia=new Intent(gamepage.this,lostdialog.class);
						stopAll();
					//	finish();
					//	startActivity(lostdia);
						//onDestroy();
					}
				}
			}
			
		}
	};
	
	
	public void TimerMethod()
	{		
		this.runOnUiThread(Timer_Tick);
	}

	
	
	

	
	public void createDialog(){
		Log.d("createdialog","sadad");
	//Intent pageDia=new Intent(gamepage.this,dialogpg.class);
		stopAll();
		//finish();
		//onStop();
		//startActivity(pageDia);	
		//onDestroy();
	}
	
	public void stopAll(){   	 
	   	 if(myTimer!=null){			
				myTimer.cancel();
				myTimer = null;
				}
	     if (myPlayer != null) {
	     	myPlayer.stop();
	     	myPlayer.release();
	     	myPlayer = null;
	     }
	     
   }
	
	
	
	

}
