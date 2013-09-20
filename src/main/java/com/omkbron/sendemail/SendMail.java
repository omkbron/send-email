package com.omkbron.sendemail;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @author omkbron
 *
 */
public class SendMail {
	private BeanMail beanMail;
	private Session session; 
	private Message message;
	private Multipart multipart;

	public void initialize(BeanMail beanMail) {
		this.beanMail = beanMail;
	}

	public void setupMail() throws AddressException, MessagingException, IOException, TemplateException {
		if (Boolean.parseBoolean(beanMail.getMailProps().getProperty("mail.smtp.auth"))){
			session = Session.getDefaultInstance(beanMail.getMailProps(),
					new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(beanMail.getUserName(), beanMail.getPassword());
				}
			});
		} else {
			session = Session.getDefaultInstance(beanMail.getMailProps());
			session.setDebug(false);
		}
		
		message = new MimeMessage(session);
		
		message.setFrom(beanMail.getFrom());
		message.setRecipients(Message.RecipientType.TO, beanMail.getRecipients());
		message.setRecipients(Message.RecipientType.BCC, beanMail.getRecipients());
		message.setSubject(beanMail.getSubject());
		message.setSentDate(new Date());
		message.saveChanges();
		
		addBodyMessage();
	}
	
	public void addBodyMessage() throws IOException, TemplateException, MessagingException {
		BodyPart body = new MimeBodyPart();
		
		Configuration cfg = new Configuration();
		Template template = cfg.getTemplate(beanMail.getHtmlTemplate());
		Writer out = new StringWriter();
		template.process(beanMail.getHtmlBodyProps(), out);
		
		body.setContent(out.toString(), "text/html");
		
		multipart = new MimeMultipart("related");
		multipart.addBodyPart(body);
		
		addAttachments();
		addCidImages();
		
		message.setContent(multipart, "text/html");
	}

	private void addAttachments() throws MessagingException {
		BodyPart body;
		if (beanMail.getAttachments() != null) {
			for (Attachment attachment : beanMail.getAttachments()) {
				body = new MimeBodyPart();
				DataSource fds = new FileDataSource(attachment.getFileName());
				body.setDataHandler(new DataHandler(fds));
				body.setFileName(attachment.getFileName());
				multipart.addBodyPart(body);			
			}
		}
	}
	
	private void addCidImages() throws MessagingException {
		BodyPart body;
		if (beanMail.getCidImages() != null) {
			for (CidImage cidImage : beanMail.getCidImages()) {
				body = new MimeBodyPart();
				DataSource fds = new FileDataSource(cidImage.getFileName());
				body.setDataHandler(new DataHandler(fds));
				body.setHeader("Content-ID", cidImage.getCid());
				multipart.addBodyPart(body);
			}
		}
	}

	public void send() throws MessagingException {
		Transport.send(message);
		
		System.out.println("Mensaje enviado...");
	}
}
