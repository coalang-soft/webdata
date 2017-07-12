package io.github.coalangsoft.data.api.map;

import io.github.coalangsoft.data.location.GPSLocation;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Matthias on 30.06.2017.
 */
public class MapBuilder<MapType, MarkerType, PathType> {

    private IMapBuilder<MapType, MarkerType, PathType> iBuilder;

    public MapBuilder(IMapBuilder<MapType, MarkerType, PathType> b){
        iBuilder = b;
        SEPERATOR = b.makeSeparator();
    }

    private final char SEPERATOR;

    private GPSLocation center;
    private int zoom = -1;
    private GPSLocation[] bounds;
    private ArrayList<MarkerType> markers;
    private int[] size;
    private String format;
    private MapType type;
    private ArrayList<PathType> paths;

    {
        markers = new ArrayList<MarkerType>();
        paths = new ArrayList<PathType>();
    }

    public MapBuilder<MapType, MarkerType, PathType> setCenter(GPSLocation l){
        this.center = l;
        return this;
    }

    public MapBuilder<MapType, MarkerType, PathType> setZoom(int zoom){
        this.zoom = zoom;
        return this;
    }
    public MapBuilder<MapType, MarkerType, PathType> setBounds(GPSLocation tl, GPSLocation br){
        this.bounds = new GPSLocation[]{tl, br};
        return this;
    }

    public MapBuilder<MapType, MarkerType, PathType> addMarker(MarkerType marker){
        this.markers.add(marker);
        return this;
    }

    public MapBuilder<MapType, MarkerType, PathType> addPath(PathType path){
        this.paths.add(path);
        return this;
    }

    public MapBuilder<MapType, MarkerType, PathType> setSize(int w, int h){
        this.size = new int[]{w,h};
        return this;
    }

    public MapBuilder<MapType, MarkerType, PathType> setFormat(String format){
        this.format = format;
        return this;
    }

    public MapBuilder<MapType, MarkerType, PathType> setType(MapType type) {
        this.type = type;
        return this;
    }

    public URL toUrl() throws MalformedURLException {
        StringBuilder b = new StringBuilder();
        b.append(iBuilder.makeBaseUrl());

        boolean needSeperator = false;

        if(zoom >= 0){
            b.append(iBuilder.makeZoomArg(zoom));
        }else{
        	b.append(iBuilder.makeBoundingBoxArg(bounds[0], bounds[1]));
        }
        needSeperator = true;
        
        if(format != null){
            if(needSeperator){
                b.append(SEPERATOR);
            }
            b.append(iBuilder.makeFormatArg(format));
            needSeperator = true;
        }
        if(type != null){
            if(needSeperator){
                b.append(SEPERATOR);
            }
            b.append(iBuilder.makeMapTypeArg(type));
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
                b.append(iBuilder.makePathArg(paths.get(i)));
            }
            needSeperator = true;
        }

        if(needSeperator){
            b.append(SEPERATOR);
        }

        if(center != null){
            b.append(iBuilder.makeCenterArg(center));
            b.append(SEPERATOR);
        }
        b.append(iBuilder.makeSizeArg(size[0], size[1]));

        if(markers.size() > 0){
            if(needSeperator){
                b.append(SEPERATOR);
            }
            b.append(iBuilder.makeMarkerArg(markers));
        }

        return new URL(b.toString());

    }

}
