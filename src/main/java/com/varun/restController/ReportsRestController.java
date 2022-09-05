package com.varun.restController;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.varun.searchRequest.SearchRequest;
import com.varun.searchResponse.SearchResponse;
import com.varun.service.ReportService;

@RestController
public class ReportsRestController {
	
	@Autowired
	private ReportService service;
	
	@GetMapping("/plans")
	public ResponseEntity<List<String>> getPlanName() {
		List<String> uniquePlaneName = service.getUniquePlaneName();
		return new ResponseEntity(uniquePlaneName, HttpStatus.OK);
		
	}
	
	@GetMapping("/statues")
	public ResponseEntity<List<String>> getPlanStatus(){
		List<String> uniquePlaneStatus = service.getUniquePlaneStatus();
		return new ResponseEntity<>(uniquePlaneStatus, HttpStatus.OK);
		
	}
	
	@PostMapping("/search")
	public ResponseEntity<List<SearchResponse>> search(@RequestBody SearchRequest searchRequest){
		List<SearchResponse> response = service.getSearch(searchRequest);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	@GetMapping("/excel")
	public void excelReport(HttpServletResponse response) throws Exception {
		
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Dispostion";
		String headervalue = "attachment;filename=data.xlsx";
		response.setHeader(headerKey, headervalue);
		service.generateExcel(response);
	}

	@GetMapping("/pdf")
	public void pdfReport(HttpServletResponse response) throws Exception{
		
		response.setContentType("application/pdf");
		String headerKey = "Content-Dispostion";
		String headerValue = "attachment;filename=data.pdf";
		response.setHeader(headerKey, headerValue);
		
		service.generatePdf(response);
		
	}
	

}
