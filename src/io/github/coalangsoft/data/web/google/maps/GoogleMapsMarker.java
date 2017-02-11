package io.github.coalangsoft.data.web.google.maps;

import io.github.coalangsoft.data.location.GPSLocation;

public class GoogleMapsMarker {
	
	private static final char SEPERATOR = '|';
	
	private GoogleMapsColor color;
	private String location;
	private Character label;
	
	public GoogleMapsMarker setColor(GoogleMapsColor c){
		this.color = c;
		return this;
	}
	
	public GoogleMapsMarker setLocation(GPSLocation l){
		this.location = l.getLat() + "," + l.getLon();
		return this;
	}
	
	public GoogleMapsMarker setLocation(String s){
		this.location = s;
		return this;
	}
	
	public GoogleMapsMarker setLabel(char c){
		if(Character.isAlphabetic(c)){
			this.label = Character.toUpperCase(c);
		}else{
			this.label = null;
		}
		return this;
	}
	
	public String toString(){
		boolean needSeperator = false;
		StringBuilder b = new StringBuilder();
		if(color != null){
			b.append("color:" + color);
			needSeperator = true;
		}
		if(label != null){
			if(needSeperator){
				b.append(SEPERATOR);
			}
			b.append("label:" + label);
			needSeperator = true;
		}
		
		//Location
		if(needSeperator){
			b.append(SEPERATOR);
			needSeperator = true;
		}
		b.append(makeLocationString());
		
		return b.toString();
	}

	private String makeLocationString() {
		if(location != null){
			return location;
		}else{
			throw new IllegalStateException("No location specified!");
		}
	}
	
}
