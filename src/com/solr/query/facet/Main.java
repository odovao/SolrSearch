package com.solr.query.facet;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.request.QueryRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.parser.QueryParser;
import org.apache.solr.search.QueryUtils;

public class Main {

	public static void main(String[] args) throws SolrServerException {
		SolrServer server = new HttpSolrServer("http://localhost:8983/solr/");
		QueryResponse response = server.query(new SolrQuery("*:*"));
	}

}
