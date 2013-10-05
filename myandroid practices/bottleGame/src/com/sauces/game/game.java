package com.sauces.game;

import java.util.List;
import java.util.Random;

import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckedTextView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;


public class game {
	
	CheckedTextView[][] imgView;
	
	int[] sprites=new int[3];
	int[] spritesn=new int[]{R.drawable.sauce1,R.drawable.sauce2,R.drawable.sauce3,
			R.drawable.sauce4,R.drawable.sauce5};
	String Catog="";
	int balliGround=-1;
	String[] rand12Selected=null;
	TextView credTxt=null;
	int spriteNo=0;
	int HH;
	int imgw;
	int imgh;
	int WW;
	int rows=3;
	List<String> wordsDB=null;	
	int level=0;
	formula ff=new formula();
	int clicks=0;
	//int WW;
	//int HH ;
	int wordLength=3;
	String selected="sprites";
	int wrong_c=0;
	int accuracy=0;
	//List<String> completedWords=new ArrayList<String>() ;
	int speed=3;
	CheckedTextView imgClick=null;
	int TimeSpeed=10;
	int credits=50;
	int clickspriteNo=0;
	int char_ind=26;
	int iy=-120;
	char[][] charac;//   wordLength*26
	MediaPlayer myPlayer;
	anim2 animationPlay;
	float[] ratings=null;
	
	public void storeObj(){
		animationPlay=new anim2();
		sprites[0]=R.drawable.sauce1;
    	sprites[1]=R.drawable.sauce2;
    	sprites[2]=R.drawable.sauce3;
   
	}
	
	public int getBottleId(){
		int[] arr = new int[]{R.drawable.sauce1,R.drawable.sauce2,R.drawable.sauce3,R.drawable.sauce4,
				R.drawable.sauce5,R.drawable.sauce7,R.drawable.sauce8};
		Random rnd = new Random();
		int n = rnd.nextInt(7);
		if(n<arr.length)
				
		return arr[n];
		else{
			return arr[3];
		}
	}

	
	public boolean isGameOver() {
		// TODO Auto-generated method stub
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < wordLength; j++) {
				if(imgView[i][j]!=null){
					return false;
				}
			}
		}
		return true;
	}
	
	
	public void newgameStart(){
		Log.d("IN GAME", "CALCULATING");
		char_ind=26;		
		clicks=0;
		imgClick=null;
		
		//selected=rand12Selected[level];
		wordLength=selected.length();
		//selected=wordFind();			
		Log.d("IN GAME", "CALCULATING");
		charac=ff.fillRandChars(wordLength);
		Log.d("IN GAME", "CALCULATING");
		Log.d("success"+Integer.toString(charac.length),Integer.toString(charac[0].length));
	}
	
	
	
	public void playAgain() {
		// TODO Auto-generated method stub
		
		Log.d("IN GAME Play Again", "CALCULATING");
		char_ind=26;
		clicks=0;
		imgClick=null;		
		
		charac=ff.fillRandChars(wordLength);
		Log.d("success"+Integer.toString(charac.length),Integer.toString(charac[0].length));		
	}
	
	
	public int CalcRows(){
		if(HH/100-1<=3)
			 return 3;
		else
		return (HH/100)-1;
		
	}
	
	

public OnClickListener ballclicklistener=new OnClickListener() {
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if(imgClick!=null){
			imgClick.setBackgroundResource(0);		
		}
		imgClick=(CheckedTextView)arg0;
		if(! imgClick.isChecked()){				
			clicks++;
				
			imgClick.setClickable(false);
			
			String d=Character.toString(selected.charAt(imgClick.getId()));
			if(!imgClick.getText().toString().equals(d)){
				
				credTxt.setText(""+(--credits));
				Log.d("credits success found char","game : "+credits);
				imgClick.setText(" ");
			}
			else{
				ff.foundarr.add(imgClick);
			}
			imgClick.setChecked(true);
			
			try{
				//myPlayer.setVolume(1000,1000);
				//myPlayer.release();
				if(myPlayer!=null)
				myPlayer.start();
				
			}catch(Exception e){
				Log.d("exceptions",""+e.toString());
			}
			
		}
		else{
			imgClick=null;
		}
		
	}
};


	public void releaseMem(){
		imgView = null;
	
		charac =null;
		selected="";
		
	}


public void ballLoop() {
	// TODO Auto-generated method stub
	Random rnd = new Random();
	if(balliGround!=-1){
		int i=balliGround;
		int j=0;		
		CheckedTextView imagView;
		
			for (j = 0; j < selected.length(); j++){
				if(imgView[i][j]!=null && !imgView[i][j].isChecked()){				
					imagView=(CheckedTextView)imgView[i][j];
					
					
					if(spriteNo==0 && imagView.getBottom()>HH/2 &&  imagView.getText().equals( Character.toString(selected.charAt(j)) ) ){		
						credTxt.setText(""+(--credits));	
						Log.d("credits get hh/3","" + credits );
						
					}
					imagView.setText("");
					
					
					if(spriteNo<=2 && imagView.getBottom()>HH/2){
						imagView.setBackgroundResource(sprites[spriteNo]);}
						else{
							//imagView.setBackgroundResource(0);					
						}
					
					
					
				}
			}
		
		
		if(++spriteNo>=3){
			balliGround=-1;
			spriteNo=0;		
			Log.d("balliGround","balliGround"+char_ind);
			String s="";
			if(char_ind>=0 && isballsleft(i)){
				char_ind--;
				//Log.d("char_ind"," "+char_ind);
			}
			for (j = 0; j < selected.length(); j++){
				RelativeLayout.LayoutParams  params = new RelativeLayout.LayoutParams( LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
					
				params.topMargin=  iy;
				if(imgView[i][j]!=null ){
					params.leftMargin=imgView[i][j].getLeft();
						if(/*bricks[j].getText().length()==0 && */char_ind>=0){
							imgView[i][j].setChecked(false);
							imgView[i][j].setClickable(true);
							s+=charac[j][char_ind];
							//imgView[i][j].setText();
							
							imgView[i][j].setBackgroundResource(getBottleId());
							imgView[i][j].setLayoutParams(params);
						}
						else{
							imgView[i][j].setBackgroundResource(0);
							imgView[i][j]=null;
						}
				}
			}			
			Log.d("charind"+char_ind,s);
					
			
		}		
	}
	
}




private boolean isballsleft(int i){
	boolean ret=false;	
		for (int j = 0; j < selected.length(); j++) {
			if(imgView[i][j]!=null && imgView[i][j].getBottom()>HH/2){
				ret=ret || true;
			}
		
		}	
	return ret;
}



public void ballClicked() {
	// TODO Auto-generated method stub
	if(imgClick!=null){
		
		if(++clickspriteNo<3){
			Log.d("clicked",Integer.toString(clickspriteNo));
			imgClick.setBackgroundResource(getBottleId());
		}
		else{
			clickspriteNo=0;
			imgClick.setBackgroundResource(0);
			imgClick=null;
		}		
	}
	else{
		clickspriteNo=0;
		//imgClick.setBackgroundResource(0);
		imgClick=null;
	}
	
	
}




	
	
	
}
