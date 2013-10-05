package com.example.detectcalls;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class IncomingCallReceiver extends //BroadcastReceiver 
PhoneStateListener 

{

//	@Override
//	public void onReceive(Context context, Intent intent) {
//		Bundle bundle = intent.getExtras();
//		
//		if(null == bundle)
//			return;
//		
//		Log.i("IncomingCallReceiver",bundle.toString());
//		
//		String state = bundle.getString(TelephonyManager.EXTRA_STATE);
//				
//		Log.i("IncomingCallReceiver","State: "+ state);
//		
//		if(state.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_RINGING))
//		{
//			String phonenumber = bundle.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
//				
//			Log.i("IncomingCallReceiver","Incomng Number: " + phonenumber);
//			
//			String info = "Detect Calls sample application\nIncoming number: " + phonenumber;
//			
//			Toast.makeText(context, info, Toast.LENGTH_LONG).show();
//		}
//	}
	int count = 0;
	 Context context; //Context to make Toast if required
	 public IncomingCallReceiver(){
		 super();
		 count = 0;
	 }
	    public IncomingCallReceiver(Context context) {
	        super();
	        this.context = context;
	        count = 0;
	    }

	    @Override
	    public void onCallStateChanged(int state, String incomingNumber) {
	        super.onCallStateChanged(state, incomingNumber);
	        
	        Log.d("state","state:"+state+",count"+(count));
	        if(count >= 2){
	        	Toast.makeText(context, "call ended", Toast.LENGTH_SHORT).show();
	        }
	        if(state == 0){
	        	count++;
	        }
//	        switch (state) {
//	         
//	        case TelephonyManager.CALL_STATE_IDLE:
//	            //when Idle i.e no call means state : 0
//	        	count ++;
//	            
//	            break;
//	        case TelephonyManager.CALL_STATE_OFFHOOK:
//	            //when Off hook i.e in call
//	            //Make intent and start your service here
//	            //Toast.makeText(context, "Phone state Off hook", Toast.LENGTH_LONG).show();
//	            break;
//	        case TelephonyManager.CALL_STATE_RINGING:
//	            //when Ringing
//	            //Toast.makeText(context, "Phone state Ringing", Toast.LENGTH_LONG).show();
//	            break;
//	        default:
//	            break;
//	        }
	    }
	
	

}
