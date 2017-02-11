
package io.github.coalangsoft.data.web.openstreetmap;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import javax.script.ScriptException;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

import io.github.coalangsoft.data.location.GPSLocation;
import io.github.coalangsoft.data.util.JsonUtil;
import io.github.coalangsoft.data.util.ReadUtil;

public class OpenStreetmapAccess {
	
	public static OpenStreetmapData byGpsLocation(GPSLocation l) throws IOException{
		String json = ReadUtil.readLine(urlByGpsLocation(l).openStream());
		try {
			return new OpenStreetmapData(json);
		} catch (ScriptException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static URL urlByGpsLocation(GPSLocation l) throws MalformedURLException{
		return new URL(
			"https://nominatim.openstreetmap.org/reverse?format=json&lat=" + l.getLat() + 
			"&lon=" + l.getLon()
		);
	}
	
	public static URL webpageByGpsLocation(GPSLocation l, int zoom) throws MalformedURLException{
		return new URL(
			"http://www.openstreetmap.org/#map=" + zoom + 
			"/" + l.getLat() + 
			"/" + l.getLon()
		);
	}
	
	public static URL byQuery(String query) throws MalformedURLException{
		return new URL(
			"https://nominatim.openstreetmap.org/search?format=json&q=" + query
		);
	}
	
	public static OpenStreetmapData[] byQueryUrl(URL url) throws IOException{
		String json = ReadUtil.readLine(url.openStream());
		try {
			ScriptObjectMirror m = JsonUtil.parse(json);
			if(!m.isArray()){
				throw new RuntimeException("JSON result is not an Array!");
			}
			int length = (int) m.get("length");
			OpenStreetmapData[] res = new OpenStreetmapData[length];
			for(int i = 0; i < length; i++){
				res[i] = new OpenStreetmapData((Map<String,Object>) m.getSlot(i));
			}
			
			return res;
		} catch (ScriptException e) {
			throw new RuntimeException(e);
		}
	}
	
}
