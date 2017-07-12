import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.script.ScriptException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import io.github.coalangsoft.data.api.map.MapBuilder;
import io.github.coalangsoft.data.web.UrlEncode;
import io.github.coalangsoft.data.web.openstreetmap.OSMBuilder;
import io.github.coalangsoft.data.web.openstreetmap.OSMType;
import io.github.coalangsoft.data.location.GPSLocation;
import io.github.coalangsoft.data.web.openstreetmap.OpenStreetmapAccess;
import io.github.coalangsoft.data.web.openstreetmap.OpenStreetmapData;

public class MapTest {
	
	public static void main(String[] args) throws MalformedURLException, IOException, ScriptException {
		MapBuilder<OSMType, GPSLocation, String[]> builder = new MapBuilder<OSMType, GPSLocation, String[]>(
				new OSMBuilder()
		);

		builder.setSize(600,600);
		addMarker(builder, "Bielefeld");
		addMarker(builder, "Halle Westf.");
		//37.43998350243088
		builder.setBounds(new GPSLocation(37.40401063819412,-121.10198606590744), new GPSLocation(37.43998350243088,-121.06601320167069));
		
		URL u = builder.toUrl();
		System.out.println(u);

		ImageIcon i = new ImageIcon();
		i.setImage(ImageIO.read(u));
		JOptionPane.showMessageDialog(null, i);
	}

	public static void addMarker(MapBuilder<?,GPSLocation,?> b, String place) throws IOException {
		OpenStreetmapData[] dat = OpenStreetmapAccess.byQueryUrl(OpenStreetmapAccess.byQuery(UrlEncode.encode(place)));
		if(dat.length > 0){
			b.addMarker(dat[0].getGpsLocation());
		}
	}
	
}
