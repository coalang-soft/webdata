import java.io.IOException;
import java.net.MalformedURLException;

import javax.imageio.ImageIO;
import javax.script.ScriptException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import io.github.coalangsoft.data.location.GPSLocation;
import io.github.coalangsoft.data.web.google.maps.GoogleMapType;
import io.github.coalangsoft.data.web.google.maps.StaticGoogleMap;
import io.github.coalangsoft.data.web.google.maps.path.GoogleMapsRectangle;
import io.github.coalangsoft.data.web.openstreetmap.OpenStreetmapAccess;

public class MapTest {
	
	public static void main(String[] args) throws MalformedURLException, IOException, ScriptException {
		StaticGoogleMap map = new StaticGoogleMap();
		map.setCenter(new GPSLocation(0,0));
		map.setFormat("jpg");
		map.setLanguage("de");
		map.setSize(600, 600);
		map.setType(GoogleMapType.roadmap);
		map.addPath(new GoogleMapsRectangle
				(new GPSLocation(0,0), 10)
				.toPath());

		System.out.println(map.toUrl());
		ImageIcon ico = new ImageIcon(ImageIO.read(map.toUrl()));
		JLabel l = new JLabel(ico);
		JOptionPane.showMessageDialog(null, l);

		System.out.println(OpenStreetmapAccess.byQueryUrl(OpenStreetmapAccess.byQuery("Germany"))[0].getDescription());
	}
	
}
