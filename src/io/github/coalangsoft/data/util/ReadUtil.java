package io.github.coalangsoft.data.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class ReadUtil {
	
	public static String readLine(InputStream stream){
		Scanner s = new Scanner(stream);
		StringBuilder b = new StringBuilder();
		while(s.hasNextLine()){
			b.append(s.nextLine());
		}
		s.close();
		return b.toString();
	}
	
	public static String readLine(URL url) throws IOException{
		return readLine(url.openStream());
	}
	
}
