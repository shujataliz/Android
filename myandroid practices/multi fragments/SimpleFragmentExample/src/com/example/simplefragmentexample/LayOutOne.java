package com.example.simplefragmentexample;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;



public class LayOutOne extends Fragment {

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		 ViewGroup root = (ViewGroup) inflater.inflate(R.layout.layout_one, null);
		 init(root);
		return root;
	}
	OnButtonPressListener buttonListener;

	
	 @Override
	    public void onAttach(Activity activity) {
	        super.onAttach(activity);
	        try {	        
	        	buttonListener = (OnButtonPressListener) getActivity();
	        } catch (ClassCastException e) {
	            throw new ClassCastException(activity.toString() + " must implement OnFileSelectedListener");
	        }
	    }
     void init(ViewGroup root){
    	 initbtns(root);
    	 
     }
     
     private void initbtns(ViewGroup root) {
 		// TODO Auto-generated method stub
 		Button but=(Button)root.findViewById(R.id.button11);
    	 but.setOnClickListener(new View.OnClickListener() {
 			
 			@Override
 			public void onClick(View v) {
 				// TODO Auto-generated method stub
 				buttonListener.onButtonPressed("Message From First Fragment");
 			}
 		});
    	 Button but2=(Button)root.findViewById(R.id.button22);
    	 but2.setOnClickListener(new View.OnClickListener() {
 			
 			@Override
 			public void onClick(View v) {
 				// TODO Auto-generated method stub
 				buttonListener.onButtonPressed("Message From Second Fragment");
 			}
 		});
    	 Button but3=(Button)root.findViewById(R.id.button33);
    	 but3.setOnClickListener(new View.OnClickListener() {
 			
 			@Override
 			public void onClick(View v) {
 				// TODO Auto-generated method stub
 				buttonListener.onButtonPressed("Message From third Fragment");
 			}
 		});
    	 Button but4=(Button)root.findViewById(R.id.button44);
    	 but4.setOnClickListener(new View.OnClickListener() {
 			
 			@Override
 			public void onClick(View v) {
 				// TODO Auto-generated method stub
 				buttonListener.onButtonPressed("Message From fourth Fragment");
 			}
 		});
//    	 Button but5=(Button)root.findViewById(R.id.button5);
//    	 but5.setOnClickListener(new View.OnClickListener() {
// 			
// 			@Override
// 			public void onClick(View v) {
// 				// TODO Auto-generated method stub
// 				buttonListener.onButtonPressed("Message From fifth Fragment");
// 			}
// 		});
 		
 	}
	
}
