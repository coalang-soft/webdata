# WebData
Java library to work with data from the internet (e.g. maps, search, ...)

There are many good websites that provide useful information for your application.
WebData helps to use them.

# Maps and Locations
## GPS Data to Location information
Let us try to find out where an Image was taken:
```
//Load the Image (javaxt: http://www.javaxt.com/downloads/)
javaxt.io.Image i = new javaxt.io.Image(new File("Image Path"));

//Get GPS Data
GPSLocation location = GPSLocation.byXY(i.getGPSCoordinate());

//Get location information (uses openstreetmap.com)
OpenStreetmapData d = OpenStreetmapAccess.byGpsLocation(location)

//Use the location data
System.out.println(d.getDescription());

Address a = d.getAddress();
System.out.println(d.getStreet());
```
## Location to Map image
```
GPSLocation location = ...
//String location = "New+York";

//Prepare the Map image
StaticGoogleMap map = new StaticGoogleMap()
  .setCenter(location)
  .setSize(300,300) //Maximum 640,640
  .setType(GoogleMapType.hybrid)
  .addMarker(
    new GoogleMapsMarker()  .setColor(GoogleMapsColor.blue)
                            .setLabel('L')
                            .setLocation(location)
  );
  
//Create the image
BufferedImage image = ImageIO.read(map.toUrl());
```
There is a maximum of 2,500 requests per day.
