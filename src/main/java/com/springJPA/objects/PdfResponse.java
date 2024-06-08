package com.springJPA.objects;

import org.springframework.stereotype.Component;

@Component
public class PdfResponse {
	
	private String message;
	private String status;
	private String pdfResponse;
	
	public PdfResponse() {
		super();
	}
	
	public PdfResponse(String message, String status, String pdfResponse) {
		super();
		this.message = message;
		this.status = status;
		this.pdfResponse = pdfResponse;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPdfResponse() {
		return pdfResponse;
	}
	public void setPdfResponse(String pdfResponse) {
		this.pdfResponse = pdfResponse;
	}

	@Override
	public String toString() {
		return "PdfResponse [message=" + message + ", status=" + status + ", pdfResponse=" + pdfResponse + "]";
	}
	

}
