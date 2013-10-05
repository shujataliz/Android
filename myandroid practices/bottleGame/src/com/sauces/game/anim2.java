package com.sauces.game;

import android.app.Activity;
import android.util.Log;
import android.util.TypedValue;
import android.widget.CheckedTextView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class anim2 extends Activity  {	

	
	
	public boolean lvlComplete(){
//		for(int i=0;i<gamepage.obj.selected.length();i++){
////			if(gamepage.obj.bricks[i].getText()==""){
////				return false;
////			}			
//		}
		Log.d("success"," " +  gamepage.obj.level +", "+gamepage.obj.selected);
		return false;
	//	return true;
	}
	
	public void movBalls(int rows){
	    	int i=0,j=0;
	    	CheckedTextView imageView=null;
	    	RelativeLayout.LayoutParams  params;
	    	
	    	for ( i = 0; i < rows; i++) {	    		
				for ( j = 0; j < gamepage.obj.selected.length(); j++) 
				{
					if(gamepage.obj.imgView[i][j]!=null){
//						if(gamepage.obj.bricks[j].getText().length()!=0){
//							gamepage.obj.imgView[i][j].setBackgroundResource(0);
//							gamepage.obj.imgView[i][j].setText("");
//							gamepage.obj.imgView[i][j]=null;
//						}
//						else
						{
							imageView=(CheckedTextView)gamepage.obj.imgView[i][j];		    		 	
							if(imageView.getBottom() <= gamepage.obj.HH-gamepage.obj.HH/9){	    		 	
								params = new RelativeLayout.LayoutParams(  LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
								params.topMargin =  imageView.getTop()+gamepage.obj.speed;
								params.leftMargin= imageView.getLeft();
								//Log.d("params leftmargin"+i+","+"j="+j ,""+params.leftMargin);
								if(params.leftMargin==0){
									try{
									 params.topMargin =(-200*i);
									 params.leftMargin =gamepage.obj.WW/3 - (gamepage.obj.selected.length()-3)*15 + gamepage.obj.WW/10*(j%gamepage.obj.selected.length());
									}catch(Exception e){
										Log.d("movballs",""+e);
									}
								}
								gamepage.obj.imgView[i][j].setLayoutParams(params);					
			    		 	}
			    		 	else{
			    		 		gamepage.obj.balliGround=i;
			    		 	}
						}
					}				
					
				}
				
	    	 }
	    	
	    }
	
}


