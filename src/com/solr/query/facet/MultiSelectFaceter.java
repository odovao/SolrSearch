package com.solr.query.facet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultiSelectFaceter {
	
	private Map<String, Facet> multiFacets = new HashMap<String, Facet>();
	
	
	public void addSelectedFacet (Facet selected) throws Exception {
		if (!multiFacets.containsKey(selected.getFacetField())) {
			multiFacets.put(selected.getFacetField(), selected);
		} else {
			if (multiFacets.get(selected.getFacetField()).Equals(selected)) {
				multiFacets.remove(selected.getFacetField());
			} else { 
				Facet current = multiFacets.get(selected.getFacetField());
				current.addValue(selected.getFacetValue());
			}
		}
	}
	
	public List<Facet> getMultiSelecFacet() {
		
		List<Facet> facets = new ArrayList<Facet>();
		for (Map.Entry<String, Facet> facetEntry: multiFacets.entrySet()) {
			facets.add(facetEntry.getValue());
		}
		return facets;
	}
	
	public String getFacetsQueryString () {
		String queryString = "";
		for (Facet f: getMultiSelecFacet()) {
			queryString += "&facet.field={!ex=" + f.getFacetField() + "}" + f.getFacetField();
		}
		return queryString;
	}
	
	
	public String getFiltersQueryString () {
		String queryString = "";
		for (Facet f: getMultiSelecFacet()) {
			queryString += "&fq={!tag=" + f.getFacetField() + "}" + f.getFacetField() + ":" + f.getQueryString();
		}
		return queryString;
	}
	

	
	public String toString () {
		return getFacetsQueryString() + getFiltersQueryString();
	}
}
