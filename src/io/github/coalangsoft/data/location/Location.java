package io.github.coalangsoft.data.location;


public interface Location {
	
	GPSLocation getGpsLocation();
	String getDescription();
	Address getAddress();
	
}
