package com.omkbron.sendemail.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class BeanMail {
	private Properties mailProps;
	private String userName;
	private String password;
	private String from;
	private String[] recipients;
	private String[] recipientsBcc;
	private String subject;
	private String directoryHtmlTemplate;
	private String htmlTemplate;
	private Map<String, Object> htmlBodyProps;
	private Attachment[] attachments;
	private CidImage[] cidImages;

	public Properties getMailProps() {
		return mailProps;
	}

	public void setMailProps(Properties mailProps) {
		this.mailProps = mailProps;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String[] getRecipients() {
		return recipients;
	}

	public void setRecipients(String... recipients) {
		this.recipients = recipients;
	}

	public String[] getRecipientsBcc() {
		return recipientsBcc;
	}

	public void setRecipientsBcc(String... recipientsBcc) {
		this.recipientsBcc = recipientsBcc;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getDirectoryHtmlTemplate() {
		return directoryHtmlTemplate;
	}
	
	public void setDirectoryHtmlTemplate(String directoryHtmlTemplate) {
		this.directoryHtmlTemplate = directoryHtmlTemplate;
	}

	public String getHtmlTemplate() {
		return htmlTemplate;
	}

	public void setHtmlTemplate(String htmlTemplate) {
		this.htmlTemplate = htmlTemplate;
	}

	public Map<String, Object> getHtmlBodyProps() {
		return htmlBodyProps;
	}

	public void setHtmlBodyProps(Map<String, Object> htmlBodyProps) {
		this.htmlBodyProps = htmlBodyProps;
	}

	public Attachment[] getAttachments() {
		return attachments;
	}

	public void setAttachments(Attachment... attachments) {
		this.attachments = attachments;
	}

	public CidImage[] getCidImages() {
		return cidImages;
	}

	public void setCidImages(CidImage... cidImages) {
		this.cidImages = cidImages;
	}

}
