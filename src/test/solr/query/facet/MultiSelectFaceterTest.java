package test.solr.query.facet;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.solr.query.facet.Facet;
import com.solr.query.facet.MultiSelectFaceter;


public class MultiSelectFaceterTest {
	
	Facet diesel;
	Facet petrol;
	Facet english;
	MultiSelectFaceter multiFaceter;
	
	@Before
	public void init () {
		diesel = new Facet("fuel", "diesel");
		petrol = new Facet("fuel", "petrol");
		english = new Facet("language", "english");	
	}
	
	
	@Test
	public void shouldAddFacetFieldFilter () throws Exception {
		multiFaceter = new MultiSelectFaceter();
		multiFaceter.addSelectedFacet(diesel);
		multiFaceter.addSelectedFacet(english);
		assertEquals("&fq={!tag=fuel}fuel:diesel&fq={!tag=language}language:english" , multiFaceter.getFiltersQueryString());
	}
	
	@Test
	public void shouldDeleteFacetFieldFilter () throws Exception {
		multiFaceter = new MultiSelectFaceter();
		multiFaceter.addSelectedFacet(diesel);
		multiFaceter.addSelectedFacet(english);
		multiFaceter.addSelectedFacet(diesel);
		assertEquals("&fq={!tag=language}language:english" , multiFaceter.getFiltersQueryString());
	}
	
	@Test
	public void shouldAddFacetFilterValues () throws Exception {
		multiFaceter = new MultiSelectFaceter();
		multiFaceter.addSelectedFacet(diesel);
		multiFaceter.addSelectedFacet(english);
		multiFaceter.addSelectedFacet(petrol);
		assertEquals("&fq={!tag=fuel}fuel:diesel OR petrol&fq={!tag=language}language:english" , multiFaceter.getFiltersQueryString());
	}
	
	@Test
	public void shouldDeleteFacetFilterValues () throws Exception {
		multiFaceter = new MultiSelectFaceter();
		multiFaceter.addSelectedFacet(diesel);
		multiFaceter.addSelectedFacet(english);
		multiFaceter.addSelectedFacet(petrol);
		multiFaceter.addSelectedFacet(petrol);
		assertEquals("&fq={!tag=fuel}fuel:diesel&fq={!tag=language}language:english" , multiFaceter.getFiltersQueryString());
		multiFaceter.addSelectedFacet(diesel);
		assertEquals("&fq={!tag=language}language:english" , multiFaceter.getFiltersQueryString());
		multiFaceter.addSelectedFacet(english);
		assertEquals("" , multiFaceter.getFiltersQueryString());
	}
	
	@Test
	public void shuoldAddFacetField () throws Exception { 
		multiFaceter = new MultiSelectFaceter();
		multiFaceter.addSelectedFacet(diesel);
		multiFaceter.addSelectedFacet(english);
		assertEquals("&facet.field={!ex=fuel}fuel&facet.field={!ex=language}language" , multiFaceter.getFacetsQueryString());
	}
	
	@Test
	public void shuoldDeleteFacetField () throws Exception { 
		multiFaceter = new MultiSelectFaceter();
		multiFaceter.addSelectedFacet(diesel);
		multiFaceter.addSelectedFacet(english);
		multiFaceter.addSelectedFacet(diesel);
		assertEquals("&facet.field={!ex=language}language" , multiFaceter.getFacetsQueryString());
	}
	
	@After
	public void tearDown () {
		multiFaceter = null;
		diesel = null;
		petrol = null;
		english = null;
	}
}


