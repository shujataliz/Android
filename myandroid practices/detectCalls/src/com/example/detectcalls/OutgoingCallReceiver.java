package com.example.detectcalls;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class OutgoingCallReceiver extends BroadcastReceiver  {

	 String incoming_nr;  
	 Context mContext;  
	    private int prev_state;
	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();
		
		if(null == bundle)
			return;
		
		String phonenumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);

		Log.i("OutgoingCallReceiver",phonenumber);
		Log.i("OutgoingCallReceiver",bundle.toString());
		
		String info = "Detect Calls sample application\nOutgoing number: " + phonenumber;
		
		Toast.makeText(context, info, Toast.LENGTH_LONG).show();
	
		mContext=context;  
		
		TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        //telephonyManager.listen(new IncomingCallReceiver(context), PhoneStateListener.LISTEN_CALL_STATE);
		telephonyManager.listen(new CustomPhoneStateListener()	, PhoneStateListener.LISTEN_CALL_STATE);
        
	}
	
	
	 public class CustomPhoneStateListener extends PhoneStateListener {  
		  
	        private static final String TAG = "CustomPhoneStateListener";  
	  
	        @Override  
	        public void onCallStateChanged(int state, String incomingNumber){  
	  
	            if(incomingNumber!=null&&incomingNumber.length()>0) incoming_nr=incomingNumber;   
	  
	            switch(state){  
	                case TelephonyManager.CALL_STATE_RINGING:  
	                 //       Log.d(TAG, "CALL_STATE_RINGING");  
	                        prev_state=state;  
	                        break;  
	                case TelephonyManager.CALL_STATE_OFFHOOK:  
	               // Log.d(TAG, "CALL_STATE_OFFHOOK");  
	                prev_state=state;  
	                break;  
	                case TelephonyManager.CALL_STATE_IDLE:  
	                   // Log.d(TAG, "CALL_STATE_IDLE==>"+incoming_nr);  
	                    if((prev_state==TelephonyManager.CALL_STATE_OFFHOOK)){  
	                        prev_state=state;  
	                        //Answered Call which is ended  
	                        Toast.makeText(mContext	, "call ended",Toast.LENGTH_SHORT).show();
	                    }  
	                    if((prev_state==TelephonyManager.CALL_STATE_RINGING)){  
	                        prev_state=state;  
	                        //Rejected or Missed call  
	                    }  
	                    break;  
	  
	            }  
	        }  
	    } 
	
	
	
}
