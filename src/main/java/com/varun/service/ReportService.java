package com.varun.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;



import com.varun.searchRequest.SearchRequest;
import com.varun.searchResponse.SearchResponse;

public interface ReportService {

	public List<String> getUniquePlaneName();

	public List<String> getUniquePlaneStatus();
	
	public List<SearchResponse> getSearch(SearchRequest request);

	public void generateExcel(HttpServletResponse response)throws Exception;
//	or
//	public HttpSereveletResponse getSearch();

	public void generatePdf(HttpServletResponse response)throws Exception;

}
