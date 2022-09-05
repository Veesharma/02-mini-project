package com.varun.service;

import java.awt.Color;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.varun.entity.EligibilityDlts;
import com.varun.repository.ReportRepo;
import com.varun.searchRequest.SearchRequest;
import com.varun.searchResponse.SearchResponse;

@Service
public class ReportServiceImp implements ReportService {

	@Autowired
	private ReportRepo repo;
	
	@Override
	public List<String> getUniquePlaneName() {
	
		return repo.findPlanName();
	}

	@Override
	public List<String> getUniquePlaneStatus() {
		return repo.findPlanStatus();
	}

	@Override
	public List<SearchResponse> getSearch(SearchRequest request) {
		
		String planName = request.getPlanName();
		String planstatus = request.getPlanStatus();
		EligibilityDlts queryBuilder = new EligibilityDlts();
		
		if(planName != null && !planName.equals("")) {
			queryBuilder.setPlanName(planName);
		}
		
		if(planstatus != null && !planstatus.equals("")) {
			queryBuilder.setPlanStatus(planstatus);
		}
		
		LocalDate startDate = request.getStartDate();
		
		if(startDate != null) {
			queryBuilder.setPlanStartDate(startDate);
		}
		
		LocalDate endDate = request.getEndDate();
		
		if(endDate != null) {
			queryBuilder.setPlanEndDate(endDate);
		}
		Example<EligibilityDlts> emample = Example.of(queryBuilder);
		
		
		List<SearchResponse> response = new ArrayList();
		List<EligibilityDlts> entities = repo.findAll(emample);
			
		for(EligibilityDlts entity : entities) {
			
			SearchResponse str = new SearchResponse();
			BeanUtils.copyProperties(entity, str);
			System.out.print("============================");
			response.add(str);
		}
		
		
		return response;
	}

	@Override
	public void generateExcel(HttpServletResponse response) throws Exception{
		
		List<EligibilityDlts> entities = repo.findAll();
		 
		HSSFWorkbook workBook = new HSSFWorkbook();
		HSSFSheet sheet = workBook.createSheet();
		HSSFRow headerRow = sheet.createRow(5);
		
		headerRow.createCell(0).setCellValue("Name");
		headerRow.createCell(1).setCellValue("Email");
		headerRow.createCell(2).setCellValue("Mobile");
		headerRow.createCell(3).setCellValue("Gender");
		headerRow.createCell(4).setCellValue("SSN");
		
		
		entities.forEach(entity -> {
			int i =1;
			HSSFRow dataRow = sheet.createRow(i);
			dataRow.createCell(0).setCellValue(entity.getName());
			dataRow.createCell(1).setCellValue(entity.getEmail());
			dataRow.createCell(2).setCellValue(entity.getMobilenumber());
			dataRow.createCell(3).setCellValue(entity.getGender());
			dataRow.createCell(4).setCellValue(entity.getSsn());
			

			i++;
			
		});
		ServletOutputStream outputStream = response.getOutputStream();
		workBook.write(outputStream);
		workBook.close();
		outputStream.close();
		
	}

	@Override
	public void generatePdf(HttpServletResponse response) throws Exception{
		List<EligibilityDlts> entities = repo.findAll();
		
		Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph("Search Report", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
         
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table.setSpacingBefore(10);
         
        document.add(table);
        
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
         
        font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("Name", font));
         
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("E-mail", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Phone No", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Gender", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("SSN", font));
        table.addCell(cell);  
        
        for(EligibilityDlts entity : entities) {
        	 table.addCell(entity.getName());
        	 table.addCell(entity.getEmail());
        	 table.addCell(String.valueOf(entity.getMobilenumber()));
        	 table.addCell(String.valueOf(entity.getGender()));
        	 table.addCell(String.valueOf(entity.getSsn()));
        }
        document.close();
	}

}
;