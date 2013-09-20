package com.omkbron.sendemail;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.internet.InternetAddress;

public class BeanMail {
	private Properties mailProps;
	private String userName;
	private String password;
	private InternetAddress from;
	private InternetAddress [] recipients;
	private String subject;
	private String htmlTemplate;
	private Map<String, Object> htmlBodyProps;
	private List<Attachment> attachments;
	private List<CidImage> cidImages;

	public BeanMail(Properties mailProps, String userName, String password,
			InternetAddress from, InternetAddress[] recipients, String subject,
			String htmlTemplate, Map<String, Object> htmlBodyProps) {
		this.mailProps = mailProps;
		this.userName = userName;
		this.password = password;
		this.from = from;
		this.recipients = recipients;
		this.subject = subject;
		this.htmlTemplate = htmlTemplate;
		this.htmlBodyProps = htmlBodyProps;
	}
	
	public BeanMail(Properties mailProps, String userName, String password,
			InternetAddress from, InternetAddress[] recipients, String subject,
			String htmlTemplate, Map<String, Object> htmlBodyProps,
			List<Attachment> attachments, List<CidImage> cidImages) {
		this(mailProps, userName, password, from, recipients, subject, htmlTemplate, htmlBodyProps);
		this.attachments = attachments;
		this.cidImages = cidImages;
	}
	
	public BeanMail(Properties mailProps, String userName, String password,
			InternetAddress from, InternetAddress[] recipients,
			String subject, String htmlTemplate,
			Map<String, Object> htmlBodyProps, Attachment[] attachments,
			CidImage[] cidImages) {
		this(mailProps, userName, password, from, recipients, subject, htmlTemplate, htmlBodyProps);
		this.attachments = Arrays.asList(attachments);
		this.cidImages = Arrays.asList(cidImages);
	}

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

	public InternetAddress getFrom() {
		return from;
	}

	public void setFrom(InternetAddress from) {
		this.from = from;
	}
	
	public InternetAddress[] getRecipients() {
		return recipients;
	}
	
	public void setRecipients(InternetAddress[] recipients) {
		this.recipients = recipients;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
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

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public List<CidImage> getCidImages() {
		return cidImages;
	}

	public void setCidImages(List<CidImage> cidImages) {
		this.cidImages = cidImages;
	}
	
}
