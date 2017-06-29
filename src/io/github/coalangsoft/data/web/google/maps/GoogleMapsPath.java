package io.github.coalangsoft.data.web.google.maps;

import io.github.coalangsoft.data.location.GPSLocation;

import java.util.ArrayList;

public class GoogleMapsPath {
	
	private ArrayList<String> points = new ArrayList<String>();
	private GoogleMapsColor color;
	private Integer width;

	public GoogleMapsPath add(GPSLocation l){
		points.add(l.getLat() + "," + l.getLon());
		return this;
	}
	public GoogleMapsPath add(String l){
		points.add(l);
		return this;
	}
	
	public GoogleMapsPath setColor(GoogleMapsColor c){
		this.color = c;
		return this;
	}
	public GoogleMapsPath setWidth(int width){
		this.width = width;
		return this;
	}
	
	public String toString(){
		String ret = "";
		
		if(color != null){
			ret = ret + "color:" + color;
		}
		if(width != null){
			if(!ret.isEmpty()){ret = ret + "|";}
			ret = "weight:" + width;
		}
		
		for(int i = 0; i < points.size(); i++){
			if(!ret.isEmpty()){
				ret = ret + "|";
			}
			ret = ret + points.get(i);
		}
		return ret;
	}
	
}
