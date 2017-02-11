package io.github.coalangsoft.data.web.openstreetmap;

import io.github.coalangsoft.data.location.GPSLocation;
import io.github.coalangsoft.data.location.Location;

import java.util.Map;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

public class OpenStreetmapData implements Location{
	
	private String displayName;
	private OpenStreetmapAddress address;
	private OpenStreetmapType type;
	private GPSLocation gpsLocation;

	public OpenStreetmapData(String json){
		try {
			ScriptObjectMirror o = (ScriptObjectMirror) new ScriptEngineManager().getEngineByExtension("js").eval("JSON.parse('" + json + "')");
			this.displayName = (String) o.get("display_name");
			this.address = new OpenStreetmapAddress((Map<String, Object>) o.get("address"));
			try{this.type = OpenStreetmapType.valueOf((String) o.get("osm_type"));}catch(RuntimeException e){}
			try{this.gpsLocation = parseGpsLocation(o.get("lat"), o.get("lon"));}catch(RuntimeException e){}
		} catch (ScriptException e) {
			throw new RuntimeException(e);
		}
	}

	private GPSLocation parseGpsLocation(Object lat, Object lon) {
		return new GPSLocation(Double.parseDouble((String) lat), Double.parseDouble((String) lon));
	}

	public String getDisplayName() {
		return displayName;
	}

	public OpenStreetmapAddress getAddress() {
		return address;
	}

	public OpenStreetmapType getType() {
		return type;
	}

	public GPSLocation getGpsLocation() {
		return gpsLocation;
	}

	@Override
	public String getDescription() {
		return getDisplayName();
	}
	
}
