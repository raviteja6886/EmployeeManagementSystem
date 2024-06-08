package com.springJPA.objects;

/**
 * 
 */
public class EmailRequest {
	private String toMailAddres;
    private String msgBody;
    private String subject;
    private String attachment;
	public EmailRequest() {
		super();
	}
	public EmailRequest(String toMailAddres, String msgBody, String subject, String attachment) {
		super();
		this.toMailAddres = toMailAddres;
		this.msgBody = msgBody;
		this.subject = subject;
		this.attachment = attachment;
	}
	public String getToMailAddres() {
		return toMailAddres;
	}
	public void setToMailAddres(String toMailAddres) {
		this.toMailAddres = toMailAddres;
	}
	public String getMsgBody() {
		return msgBody;
	}
	public void setMsgBody(String msgBody) {
		this.msgBody = msgBody;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	@Override
	public String toString() {
		return "EmailRequest [toMailAddres=" + toMailAddres + ", msgBody=" + msgBody + ", subject=" + subject
				+ ", attachment=" + attachment + "]";
	}
    
     
}
