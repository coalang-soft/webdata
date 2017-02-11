package test.metadata.image;

import io.github.coalangsoft.data.web.wikipedia.WikipediaAccess;
import io.github.coalangsoft.data.web.wikipedia.WikipediaSearch;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Locale;
import java.util.Scanner;

import javax.script.ScriptException;

public class DataTest {
	
	public static void main(String[] args) throws MalformedURLException, IOException, ScriptException {
		
		WikipediaAccess a = new WikipediaAccess(Locale.getDefault().getLanguage());
		WikipediaSearch s = a.byQueryUrl(a.byQuery(new Scanner(System.in).nextLine()));
		
		for(int i = 0; i < s.getResultCount(); i++){
			System.out.println(s.getUrl(i));
		}
		
	}
	
}
