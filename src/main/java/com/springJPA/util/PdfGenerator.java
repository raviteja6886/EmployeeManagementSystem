package com.springJPA.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

import org.springframework.stereotype.Component;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.springJPA.model.Employee;
import com.springJPA.objects.PdfResponse;

@Component
public class PdfGenerator {
	
	public PdfResponse employeeList(List<Employee>empList) throws DocumentException, IOException  {
		
		//generating pdf
		PdfResponse response = new PdfResponse();
		
		String dest = "employeeDetails.pdf";
		Document doc = new Document(PageSize.A4);
		PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(dest));
		doc.open();
		//setting font in PDF
		 Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
	        font.setSize(18);
	        font.setColor(BaseColor.BLUE);
		Paragraph para = new Paragraph("Employee Details");
		para.setAlignment(Paragraph.ALIGN_CENTER);
		doc.add(para);
		PdfPTable table = new PdfPTable(4);
		 table.setWidthPercentage(100f);
	     table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f});
	     table.setSpacingBefore(10);
	     writeTableHeader(table);
	     writeTableData(table,empList);
	     doc.add(table);
	     doc.close();
	     writer.close();
	     
	     //encryption of PDF data
	     File pdfFile = new File(dest);
	     byte[]fileBytes = Files.readAllBytes(Paths.get(pdfFile.getAbsolutePath()));
	     
	     Base64.Encoder enc = Base64.getEncoder();
	     byte[]strenc =enc.encode(fileBytes);
	     String encode = new String(strenc,"UTF-8");
	     response.setMessage("pdf generated");
	     response.setStatus("success");
	     response.setPdfResponse(encode);
		return response;
		
	}

	private void writeTableData(PdfPTable table, List<Employee> empList) {
		
		for(Employee e : empList) {
			table.addCell(String.valueOf(e.getId()));
			table.addCell(String.valueOf(e.getName()));
			table.addCell(String.valueOf(e.getCountry()));
			table.addCell(String.valueOf(e.getPassword()));
		}
		
	}

	private void writeTableHeader(PdfPTable table) {
		
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(BaseColor.BLUE);
		cell.setPadding(4);
		
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(BaseColor.WHITE);
		cell.setPhrase(new Phrase("id",font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Name",font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Country",font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Password",font));
		table.addCell(cell);
	}
	
	
}
