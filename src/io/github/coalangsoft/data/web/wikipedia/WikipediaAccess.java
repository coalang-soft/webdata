package io.github.coalangsoft.data.web.wikipedia;

import io.github.coalangsoft.data.util.JsonUtil;
import io.github.coalangsoft.data.util.ReadUtil;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.script.ScriptException;

public class WikipediaAccess {
	
	private String language;

	public WikipediaAccess(String language){
		this.language = language;
	}
	
	public URL byQuery(String query) throws MalformedURLException{
		return new URL(
			"https://" + language + 
			".wikipedia.org/w/api.php?action=opensearch&search=" + 
			query
		);
	}
	
	public WikipediaSearch byQueryUrl(URL url) throws ScriptException, IOException{
		return new WikipediaSearch(
			language,
			JsonUtil.parse(ReadUtil.readLine(url))
		);
	}
	
	public URL urlByPageName(String name) throws MalformedURLException{
		return new URL(
			"https://" + language + 
			".wikipedia.org/w/api.php?action=query&titles=" + name + 
			"&format=json"
		);
	}
	
	public WikipediaPage byPageName(String name) throws MalformedURLException, ScriptException, IOException{
		return new WikipediaPage(language, JsonUtil.parse(ReadUtil.readLine(urlByPageName(name))));
	}
	
}
