package io.github.coalangsoft.data.location;

public class GPSLocation {
	
	private double lat;
	private double lon;

	public GPSLocation(double lat, double lon){
		this.lat = lat;
		this.lon = lon;
	}
	
	public static GPSLocation byXY(double x, double y){
		return new GPSLocation(y, x);
	}
	public static GPSLocation byXY(double[] xy){
		return byXY(xy[0], xy[1]);
	}

	public double getLat() {
		return lat;
	}

	public double getLon() {
		return lon;
	}
	
}
