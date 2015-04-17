package com.solr.query.parser;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class QueryParser
{
    
    public static final String SEARCH_OPERATOR_OR = "OR";
    public static final String SEARCH_OPERATOR_AND = "AND";
    public static final String SEARCH_OPERATOR_NOT = "NOT";
    public static final String SEARCH_OPERATOR_ORDERBY = "ORDERBY";
    public static final String SEARCH_SPACE = " ";
    
    public String search (String queryString) {
        
        String queryResult = "";
        
        return queryResult;
    }
    
    public List<String> splitQueryString (String queryString) {
        return Arrays.asList(queryString.split(SEARCH_SPACE));
    }

    public List<String> parseOperators(List<String> queryElemens)
    {
        ListIterator<String> iterator = queryElemens.listIterator();
        while (iterator.hasNext()) {
            String element =  iterator.next();
            if (element.matches("(AND|OR|NOT)")) {
                String parsed = new BooleanParser().parse(element);
                if (parsed != null) {
                    iterator.set(parsed);
                } else {
                    iterator.remove();
                    
                }
            }
        }
        return queryElemens;
    }

}
