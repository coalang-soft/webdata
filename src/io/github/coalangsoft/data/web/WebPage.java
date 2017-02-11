package io.github.coalangsoft.data.web;

import java.net.MalformedURLException;
import java.net.URL;

public interface WebPage {
	
	String getTitle();
	URL toPageUrl() throws MalformedURLException;
	
}
