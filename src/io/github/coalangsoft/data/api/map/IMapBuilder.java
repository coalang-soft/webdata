package io.github.coalangsoft.data.api.map;

import io.github.coalangsoft.data.location.GPSLocation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matthias on 30.06.2017.
 */
public interface IMapBuilder<MapType, MarkerType, PathType> {

    String makeBaseUrl();
    String makeMapTypeArg(MapType t);
    String makeFormatArg(String format);
    String makeSizeArg(int w, int h);
    String makePathArg(PathType path);
    String makeMarkerArg(List<MarkerType> marker);
    String makeZoomArg(double zoom);
    String makeCenterArg(GPSLocation l);
    char makeSeparator();
    GPSLocation findCenter(ArrayList<MarkerType> markers);
	String makeBoundingBoxArg(GPSLocation gpsLocation, GPSLocation gpsLocation2);
}
