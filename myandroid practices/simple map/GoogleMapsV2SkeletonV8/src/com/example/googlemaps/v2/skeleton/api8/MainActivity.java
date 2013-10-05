package com.example.googlemaps.v2.skeleton.api8;


import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends FragmentActivity implements LocationListener
//implements  GoogleMap.InfoWindowAdapter 
{

	private GoogleMap googleMap;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
		googleMap = mapFragment.getMap();
		googleMap.setMyLocationEnabled(true);
		
		
		Log.d("lat : "+googleMap.getMyLocation().getLatitude(),"long"+googleMap.getMyLocation().getLongitude());
		
		googleMap.addMarker(new MarkerOptions()
        .position(new LatLng(24.887507,67.15573))
        .snippet("Population: 4,137,400")
        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher))
        .title("Hello world"));
		
		googleMap.setOnMarkerClickListener(myMarkerClicked);
	}
	
	private OnMarkerClickListener myMarkerClicked = new OnMarkerClickListener() {
		
		@Override
		public boolean onMarkerClick(Marker arg0) {
			// TODO Auto-generated method stub
			Log.d("marker	","clicked");
			return false;
		}
	};


	@Override
	public void onLocationChanged(Location arg0) {
		// TODO Auto-generated method stub
		Log.d("lat"+arg0.getLatitude(), ""+arg0.getLongitude());
		
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}


//	@Override
//	public View getInfoContents(Marker arg0) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public View getInfoWindow(Marker arg0) {
//		// TODO Auto-generated method stub
//		
//		//LayoutInflater inflater = (LayoutInflater)getApplicationContext().getSystemService      (Context.LAYOUT_INFLATER_SERVICE);
//	//	View view = inflater.inflate(R.layout.infowindow,null);
//		return null;
//	}



}
