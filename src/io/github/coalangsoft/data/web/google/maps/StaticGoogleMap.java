package io.github.coalangsoft.data.web.google.maps;

import io.github.coalangsoft.data.location.GPSLocation;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class StaticGoogleMap {
	
	private static final char SEPERATOR = '&';
	
	private String center;
	private int zoom = -1;
	private ArrayList<GoogleMapsMarker> markers;
	private int[] size;
	private String format;
	private String language;
	private GoogleMapType type;
	private ArrayList<GoogleMapsPath> paths;
	
	{
		markers = new ArrayList<GoogleMapsMarker>();
		paths = new ArrayList<GoogleMapsPath>();
	}
	
	public StaticGoogleMap setCenter(GPSLocation l){
		this.center = l.getLat() + "," + l.getLon();
		return this;
	}
	
	public StaticGoogleMap setCenter(String s){
		this.center = s;
		return this;
	}
	
	public StaticGoogleMap setZoom(int zoom){
		this.zoom = zoom;
		return this;
	}
	
	public StaticGoogleMap addMarker(GoogleMapsMarker marker){
		this.markers.add(marker);
		return this;
	}
	
	public StaticGoogleMap addPath(GoogleMapsPath path){
		this.paths.add(path);
		return this;
	}
	
	public StaticGoogleMap setSize(int w, int h){
		this.size = new int[]{w,h};
		return this;
	}
	
	public StaticGoogleMap setFormat(String format){
		this.format = format;
		return this;
	}
	
	public StaticGoogleMap setLanguage(String language){
		this.language = language;
		return this;
	}
	
	public StaticGoogleMap setType(GoogleMapType type){
		this.type = type;
		return this;
	}
	
	public URL toUrl() throws MalformedURLException{
		StringBuilder b = new StringBuilder();
		b.append("https://maps.googleapis.com/maps/api/staticmap?");
		
		boolean needSeperator = false;
		
		if(zoom > 0){
			b.append("zoom=" + zoom);
			needSeperator = true;
		}
		if(format != null){
			if(needSeperator){
				b.append(SEPERATOR);
			}
			b.append("format=" + format);
			needSeperator = true;
		}
		if(language != null){
			if(needSeperator){
				b.append(SEPERATOR);
			}
			b.append("language=" + language);
			needSeperator = true;
		}
		if(type != null){
			if(needSeperator){
				b.append(SEPERATOR);
			}
			b.append("maptype=" + type);
			needSeperator = true;
		}
		if(paths.size() != 0){
			if(needSeperator){
				b.append(SEPERATOR);
			}
			for(int i = 0; i < paths.size(); i++){
				if(needSeperator){
					b.append(SEPERATOR);
				}
				b.append("path=" + paths.get(i));
			}
			needSeperator = true;
		}
		
		if(needSeperator){
			b.append(SEPERATOR);
		}
		
		String c;
		if((c = makeCenterString()) != null){
			b.append("center=" + c);
			b.append(SEPERATOR);
		}
		b.append("size=" + size[0] + "x" + size[1]);
		
		if(markers.size() > 0){
			b.append(makeMarkersString());
		}
		
		return new URL(b.toString());
		
	}
	
	private String makeMarkersString() {
		StringBuilder b = new StringBuilder();
		for(int i = 0; i < markers.size(); i++){
			b.append(SEPERATOR + "markers=");
			b.append(markers.get(i).toString());
		}
		return b.toString();
	}

	private String makeCenterString() {
		if(center != null){
			return center;
		}else{
			return null;
		}
	}
	
}
