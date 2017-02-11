
package io.github.coalangsoft.data.web.openstreetmap;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import io.github.coalangsoft.data.location.GPSLocation;

public class OpenStreetmapAccess {
	
	private static String scanLine(InputStream s){
		Scanner sc = new Scanner(s);
		String ret = sc.nextLine();
		sc.close();
		return ret;
	}
	
	public static OpenStreetmapData byGpsLocation(GPSLocation l) throws IOException{
		String json = scanLine(urlByGpsLocation(l).openStream());
		return new OpenStreetmapData(json);
	}
	
	public static URL urlByGpsLocation(GPSLocation l) throws MalformedURLException{
		return new URL(
			"https://nominatim.openstreetmap.org/reverse?format=json&lat=" + l.getLat() + 
			"&lon=" + l.getLon()
		);
	}
	
	public static URL byQuery(String query) throws MalformedURLException{
		return new URL(
			"https://nominatim.openstreetmap.org/search?format=json&q=" + query
		);
	}
	
}
