package io.github.coalangsoft.data.web.google.maps.path;

import io.github.coalangsoft.data.location.GPSLocation;
import io.github.coalangsoft.data.web.google.maps.GoogleMapsPath;

public class GoogleMapsRectangle {
	
	private double lat,lon,w,h;
	
	public GoogleMapsRectangle(GPSLocation a, double radius){
		lat = a.getLat() - radius;
		lon = a.getLon() - radius;
		w = h = radius * 2;	
	}
	
	public GoogleMapsPath toPath(){
		return new GoogleMapsPath()
		.add(new GPSLocation(lat,lon))
		.add(new GPSLocation(lat,lon+h))
		.add(new GPSLocation(lat+w,lon+h))
		.add(new GPSLocation(lat+w,lon))
		.add(new GPSLocation(lat,lon));
	}

}
