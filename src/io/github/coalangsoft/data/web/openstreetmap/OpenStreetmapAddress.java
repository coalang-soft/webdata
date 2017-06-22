package io.github.coalangsoft.data.web.openstreetmap;

import io.github.coalangsoft.data.location.Address;
import io.github.coalangsoft.data.parse.RawData;

import java.util.Map;

public class OpenStreetmapAddress implements Address{
	
	private String road;
	private String suburb;
	private String hamlet;
	private String stateDistrict;
	private String state;
	private String postcode;
	private String country;
	private String countryCode;

	public OpenStreetmapAddress(RawData m){
		if(m == null){return;}
		this.road = (String) m.get("road");
		this.suburb = (String) m.get("suburb");
		this.hamlet = (String) m.get("hamlet");
		this.stateDistrict = (String) m.get("state_district");
		this.state = (String) m.get("state");
		this.postcode = (String) m.get("postcode");
		this.country = (String) m.get("country");
		this.countryCode = (String) m.get("country_code");
	}

	public String getRoad() {
		return road;
	}

	public String getSuburb() {
		return suburb;
	}

	public String getHamlet() {
		return hamlet;
	}

	public String getStateDistrict() {
		return stateDistrict;
	}

	public String getState() {
		return state;
	}

	public String getPostcode() {
		return postcode;
	}

	public String getCountry() {
		return country;
	}

	public String getCountryCode() {
		return countryCode;
	}
	
}
