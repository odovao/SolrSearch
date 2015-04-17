package com.solr.query.parser;


public class BooleanParser implements ElementParser
{

    private static final String PLUS = "+";
    private static final String MINUS = "-";

    @Override
    public String parse(String element)
    {
        switch (element)
        {
            case QueryParser.SEARCH_OPERATOR_AND:
                return PLUS;
            case QueryParser.SEARCH_OPERATOR_NOT:
                return MINUS;
            default:
                return null;
        }
    }
}
