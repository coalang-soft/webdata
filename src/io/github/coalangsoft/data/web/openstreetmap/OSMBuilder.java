package io.github.coalangsoft.data.web.openstreetmap;

import io.github.coalangsoft.data.api.map.IMapBuilder;
import io.github.coalangsoft.data.location.GPSLocation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matthias on 30.06.2017.
 */
public class OSMBuilder implements IMapBuilder<OSMType, GPSLocation, String[]> {

    @Override
    public String makeBaseUrl() {
        return "http://geomap.nagvis.org/?module=map&";
    }

    @Override
    public String makeMapTypeArg(OSMType t) {
        return "maptype=" + t.toString().toLowerCase();
    }

    @Override
    public String makeFormatArg(String format) {
        return "format=" + format;
    }

    @Override
    public String makeSizeArg(int w, int h) {
        return "width=" + w + "&height=" + h;
    }

    @Override
    public String makePathArg(String[] path) {
        return "polygons=" + String.join(",", path) + ",thickness:5,transparency:100";
    }

    @Override
    public String makeMarkerArg(List<GPSLocation> marker) {
        String[] s = new String[marker.size()];
        for(int i = 0; i < s.length; i++){
            GPSLocation m = marker.get(i);
            s[i] = m.getLon() + "," + m.getLat();
        }
        return "points=" + String.join(";", s);
    }

    @Override
    public String makeZoomArg(double zoom) {
        return "zoom=" + zoom;
    }

    @Override
    public String makeCenterArg(GPSLocation l) {
        return "lat=" + l.getLat() + "&lon=" + l.getLon();
    }

    @Override
    public char makeSeparator() {
        return '&';
    }

    @Override
    public GPSLocation findCenter(ArrayList<GPSLocation> markers) {
        double dx = 0, dy = 0;
        for(int i = 0; i < markers.size(); i++){
            GPSLocation l = markers.get(i);
            dx += l.getLat();
            dy += l.getLon();
        }
        dx /= (double) markers.size();
        dy /= (double) markers.size();
        return new GPSLocation(dx,dy);
    }

	@Override
	public String makeBoundingBoxArg(GPSLocation tl, GPSLocation br) {
		return "bbox=" + tl.getLon() + "," + tl.getLat() + "," + br.getLon() + "," + br.getLat();
	}

}
