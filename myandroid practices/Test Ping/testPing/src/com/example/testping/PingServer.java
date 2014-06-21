package com.example.testping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

import android.os.StrictMode;

public class PingServer {
	

	
	 static String host = "74.125.224.72"; //GoogleIpAddress
	public static int  PingResult = 0;


	
	public static void StartPing() {
		// TODO Auto-generated method stub
		 StrictMode.ThreadPolicy policy = 
				    new StrictMode.ThreadPolicy.Builder().permitAll().build();      
				        StrictMode.setThreadPolicy(policy);
		
		InetAddress addr = null;
		try {
			addr = InetAddress.getByName(host.toString());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (addr.isReachable(5000)) {
				
				DoSomething();
			} else {
				
				DoSomething();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
		}

	}
	
	public static void DoSomething(){
	
		try {
			String pingCmd = "ping -c 3 " + host;
			String pingResult = "";
			Runtime r = Runtime.getRuntime();
			Process p = r.exec(pingCmd);
			BufferedReader in = new BufferedReader(new
			InputStreamReader(p.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
		
			if(inputLine.indexOf("time")>0){
				
				inputLine  =  inputLine.substring(inputLine.indexOf("time"));
				
				inputLine  =  inputLine.substring(inputLine.indexOf("="));
				
				inputLine = inputLine.substring(1, inputLine.indexOf(" "));
				String[] arr = inputLine.split(" "); 
				inputLine = arr[0].substring(0);
				PingResult = Integer.parseInt(inputLine);
				
				
				break;
			}
			
			
			pingResult += inputLine;
			}
			in.close();
			}//try
			catch (IOException e) {
			System.out.println(e);
			}	
	}
}
