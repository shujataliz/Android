package sh.google.maps;

import android.os.Bundle;
import com.google.android.gms.maps.SupportMapFragment;


public class MainActivity //extends MapActivity  {
extends android.support.v4.app.FragmentActivity{
	
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);

	        SupportMapFragment fragment = new SupportMapFragment();
	        getSupportFragmentManager().beginTransaction()
	                .add(android.R.id.content, fragment).commit();
	    }

	
}
