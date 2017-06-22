package io.github.coalangsoft.data.web.wikipedia;

import io.github.coalangsoft.data.parse.RawData;
import io.github.coalangsoft.data.web.WebPage;

import java.net.MalformedURLException;
import java.net.URL;
import jdk.nashorn.api.scripting.ScriptObjectMirror;

public class WikipediaPage implements WebPage{
	
	private Integer pageid;
	private String title;
	private String language;

	public WikipediaPage(String language, RawData m){
		RawData pages = (RawData) ((RawData) m.get("query")).get("pages");
		String[] keys = pages.keys();
		if(keys.length != 1){
			throw new RuntimeException("More or less than one page found!");
		}

		RawData page = (RawData) pages.get(keys[0]);
		
		this.pageid = (Integer) page.get("pageid");
		this.title = (String) page.get("title");
		this.language = language;
	}
	
	public URL toPageUrl() throws MalformedURLException{
		return new URL("https://" + language + ".wikipedia.org/?curid=" + pageid);
	}

	public Integer getPageid() {
		return pageid;
	}

	public String getTitle() {
		return title;
	}

	public String getLanguage() {
		return language;
	}
	
}
