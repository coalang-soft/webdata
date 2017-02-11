package io.github.coalangsoft.data.web.wikipedia;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.script.ScriptException;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

public class WikipediaSearch {
	
	private String query;
	private ScriptObjectMirror titles;
	private ScriptObjectMirror descriptions;
	private ScriptObjectMirror urls;
	private Integer resultCount;
	private String language;

	public WikipediaSearch(String language, ScriptObjectMirror m){
		this.query = (String) m.getSlot(0);
		this.titles = (ScriptObjectMirror) m.getSlot(1);
		this.descriptions = (ScriptObjectMirror) m.getSlot(2);
		this.urls = (ScriptObjectMirror) m.getSlot(3);
		
		this.resultCount = (Integer) titles.get("length");
		this.language = language;
	}
	
	public URL getUrl(int index) throws MalformedURLException{
		return new URL((String) urls.getSlot(index));
	}
	
	public String getTitle(int index) {
		return (String) titles.getSlot(index);
	}
	
	public String getDescription(int index) {
		return (String) descriptions.getSlot(index);
	}
	
	public WikipediaPage getPage(int index) throws MalformedURLException, ScriptException, IOException{
		return new WikipediaAccess(language).byPageName(getTitle(index).replace(' ', '_'));
	}

	public String getQuery() {
		return query;
	}

	public Integer getResultCount() {
		return resultCount;
	}
	
}
