package com.saleon.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;

import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;
import com.maxmind.geoip.regionName; 
import com.maxmind.geoip.timeZone; 

public class GetLocation {
	
     public String getLocation(){
    	 try { 
             LookupService cl = new LookupService("/Users/HP/Documents/workspace-sts-3.7.0.RELEASE/SaleOnService/GeoIP/GeoLiteCity.dat", 
                     LookupService.GEOIP_MEMORY_CACHE ); 
     
             URL whatismyip = new URL("http://checkip.amazonaws.com");  
             BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
             String ip = in.readLine();
             Location l1 = cl.getLocation(ip);
             Location l2 = l1; 
             cl.close();
             return l2.city;
             
         } 
         catch (IOException e) { 
             System.out.println("IO Exception"); 
         }
		return null; 
     }
	 
}