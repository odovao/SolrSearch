package com.solr.query.facet;

import java.util.ArrayList;
import java.util.List;

public class Facet {

	private String facetField;
	private List<String> facetValues;
	
	
	public Facet () {}


	public Facet(String facetField, String newFacetValue) {
		super();
		this.facetField = facetField;
		this.facetValues = new ArrayList<String>();
		facetValues.add(newFacetValue);
	}
	
	public void addValue (String value) {
		if (facetValues.contains(value)) {
			deleleValue(value);
		} else {
			facetValues.add(value);
		}
	}
	
	public void addValue (List<String> values) {
		facetValues.addAll(values);
	}
	
	public String getQueryString () {
		String queryString = "";
		for (String str: this.facetValues) {
			queryString += str + " OR ";
		}
		if (queryString.length() > 0) {
			return queryString = queryString.substring(0, queryString.length()-4);	
		} 
		return "";
	}

	
	public void deleleValue (String value) {
		facetValues.remove(value);
	}
	
	public boolean containsValue (String value) {
		return facetField.contains(value);
	}
	
	public String getFacetField() {
		return facetField;
	}
	
	public List<String> getFacetValues() {
		return facetValues;
	}
	public String getFacetValue () throws Exception {
		if (facetValues == null || facetValues.size() == 0) {
			throw new Exception();
		}
		return facetValues.get(0);
	}
	
	public boolean Equals (Facet facet) {
		if (facet.facetField.equals(this.facetField) && facet.getQueryString().equals(this.getQueryString())) {
			return true;
		}
		return false;
	}
}
