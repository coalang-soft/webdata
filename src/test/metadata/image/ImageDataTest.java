package test.metadata.image;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

//JavaXT: http://www.javaxt.com/downloads/
import javaxt.io.Image;

import io.github.coalangsoft.data.location.GPSLocation;
import io.github.coalangsoft.data.web.google.maps.GoogleMapType;
import io.github.coalangsoft.data.web.google.maps.GoogleMapsColor;
import io.github.coalangsoft.data.web.google.maps.GoogleMapsMarker;
import io.github.coalangsoft.data.web.google.maps.StaticGoogleMap;
import io.github.coalangsoft.data.web.openstreetmap.OpenStreetmapAccess;

public class ImageDataTest {
	
	private static int zoom = 1;
	
	public static void main(String[] args) throws MalformedURLException, IOException {
		
		JFileChooser c = new JFileChooser();
		c.showOpenDialog(null);
		
		//Load image
		File f = c.getSelectedFile();
		Image i = new Image(f);
		
		//Location data
		GPSLocation l = GPSLocation.byXY(i.getGPSCoordinate());
		
		//Map generation
		final StaticGoogleMap map = new StaticGoogleMap();
		map	.setCenter(l)
			.setFormat("jpg")
			.setLanguage("de")
			.setSize(300, 300)
			.setType(GoogleMapType.hybrid)
//			.addMarker(
//				new GoogleMapsMarker()	.setColor(GoogleMapsColor.red)
//										.setLabel('K')
//										.setLocation("Kiel")
//			)
//			.addMarker(
//				new GoogleMapsMarker()	.setColor(GoogleMapsColor.yellow)
//										.setLabel('D')
//										.setLocation("Duesseldorf"))
//			.addMarker(
//				new GoogleMapsMarker()	.setColor(GoogleMapsColor.orange)
//										.setLabel('M')
//										.setLocation("Muenchen"))
			.addMarker(
				new GoogleMapsMarker()	.setColor(GoogleMapsColor.blue)
										.setLabel('B')
										.setLocation(l))
			.setZoom(zoom)
			;
		
		ImageIcon icon = new ImageIcon(makeImage(map));
        final JLabel label = new JLabel(icon);
        
        final JFrame frame = new JFrame();
        frame.setTitle(OpenStreetmapAccess.byGpsLocation(l).getDescription());
        label.addMouseWheelListener(new MouseWheelListener() {
			
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				if(e.getWheelRotation() > 0){
					if(zoom < 20){
						zoom++;
						map.setZoom(zoom);
					}
				}else if(zoom > 1){
					zoom--;
					map.setZoom(zoom);
				}
				try {
					label.setIcon(new ImageIcon(makeImage(map)));
					frame.repaint();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
        
        frame.add(label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
	}
	
	public static BufferedImage makeImage(StaticGoogleMap map) throws MalformedURLException, IOException{
		return ImageIO.read(map.toUrl());
	}
	
}
