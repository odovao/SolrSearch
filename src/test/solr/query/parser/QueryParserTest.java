package test.solr.query.parser;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.solr.query.parser.QueryParser;

public class QueryParserTest
{
    
    private QueryParser parser = new QueryParser(); 
    
    @Test
    public void shouldSplitStringBySpace() {
        String queryString = "red green";
        assertEquals(parser.splitQueryString(queryString).size(), 2);
    }
    
    @Test
    public void shouldAND() {
        List<String> queryString = parser.splitQueryString("red AND green");
        
        assertEquals(parser.splitQueryString("red + green"), parser.parseOperators(queryString));
    }
    
    @Test
    public void shouldOR() {
        List<String> queryString = parser.splitQueryString("red OR green");
        assertEquals(parser.splitQueryString("red green"), parser.parseOperators(queryString));
    }
    
    @Test
    public void shouldNOT() {
        List<String> queryString = parser.splitQueryString("red NOT green");
        assertEquals(parser.splitQueryString("red - green"), parser.parseOperators(queryString));
    }
    
    @Test
    public void shouldORDERBY() {
        String queryString = "red green ORDERBY pubdate";
        assertEquals("red +green", parser.splitQueryString(queryString));
    }
}
