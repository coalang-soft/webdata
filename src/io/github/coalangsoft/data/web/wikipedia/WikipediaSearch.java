package io.github.coalangsoft.data.web.wikipedia;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.script.ScriptException;

import io.github.coalangsoft.data.parse.RawData;

public class WikipediaSearch {
	
	private String query;
	private RawData titles;
	private RawData descriptions;
	private RawData urls;
	private Integer resultCount;
	private String language;

	public WikipediaSearch(String language, RawData m){
		this.query = (String) m.at(0);
		this.titles = (RawData) m.at(1);
		this.descriptions = (RawData) m.at(2);
		this.urls = (RawData) m.at(3);
		
		this.resultCount = (Integer) titles.get("length");
		this.language = language;
	}
	
	public URL getUrl(int index) throws MalformedURLException{
		return new URL((String) urls.at(index));
	}
	
	public String getTitle(int index) {
		return (String) titles.at(index);
	}
	
	public String getDescription(int index) {
		return (String) descriptions.at(index);
	}
	
	public WikipediaPage getPage(int index) throws ScriptException, IOException{
		return new WikipediaAccess(language).byPageName(getTitle(index).replace(' ', '_'));
	}

	public String getQuery() {
		return query;
	}

	public Integer getResultCount() {
		return resultCount;
	}
	
}
