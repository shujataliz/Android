package com.sauces.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.widget.CheckedTextView;

public class formula {
	List<CheckedTextView> foundarr=new ArrayList<CheckedTextView>() ; 
	

private char[] fn(){
	//         return random array filled of 26 rand nos
		
	int arr[]=new int[26];
	char arr1[]=new char[26];
	for(int i=0;i<26;i++)
	{	arr[i]=i;	}
	shuffle(arr);
	
	for(int i=0;i<26;i++){
		arr1[i]=(char)(arr[i]+65);	
		}
	arr=null;
	return arr1;
}


public void isdropping(){
	if(!foundarr.isEmpty()){
		for (int i = 0; i < foundarr.size(); i++) {
			CheckedTextView a=(CheckedTextView)foundarr.get(i);
			//params = new RelativeLayout.LayoutParams( gamepage.obj.WW/9, gamepage.obj.HH/8);
			
		//	Log.d("reached12",(String) a.getText()+" , "+Integer.toString(a.getTop())+","+Integer.toString(gamepage.obj.HH-gamepage.obj.HH/2));
			if(a.getTop()>=gamepage.obj.HH-gamepage.obj.HH/3){
			//	Log.d("reached",(String) a.getText());				
				//gamepage.obj.bricks[a.getId()].setText(a.getText().toString().trim());
				a.setText("");
				foundarr.remove(i);
			}
		}
	}
}


public void shuffle(int[] ar) {
	// TODO Auto-generated method stub	

	  Random rnd = new Random();
	    for (int i = ar.length - 1; i >= 0; i--)
	    {
	      int index = rnd.nextInt(i + 1);
	      
	      // Simple swap
	      int a = ar[index];
	      ar[index] = ar[i];
	      ar[i] = a;
	    }
	

}


public char[][] fillRandChars(int len) {
	
	char[][] arr=new char[len][];
	for(int i=0;i<len;i++){
		arr[i]= fn();					
	}
	return arr;	
}




public int rand(int init,int end){
	int  a= (int) (((Math.random()*100))%(init-end));
	return (Math.round(a))+init;
}



	
	
	
	
}
