package com.omkbron.sendemail.service;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.omkbron.sendemail.model.Attachment;
import com.omkbron.sendemail.model.BeanMail;
import com.omkbron.sendemail.model.BeanMailConstructor;
import com.omkbron.sendemail.model.CidImage;
import com.omkbron.sendemail.util.SendMailException;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @author omkbron
 *
 */
public class SendMailTemplate implements SendMail {
	private BeanMail beanMail;
	private Session session; 
	private Message message;
	private Multipart multipart;
	private String messageStatus;
	private boolean readyToSend;
	
	public String getMessageStatus() {
		return messageStatus;
	}
	
	public void setMessageStatus(String messageStatus) {
		this.messageStatus = messageStatus;
	}

	public void setupMail(BeanMail beanMailArg) throws SendMailException {
		this.beanMail = beanMailArg;
		
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
		
		try {
			message.setFrom(new InternetAddress(beanMail.getFrom()));
			message.setRecipients(Message.RecipientType.TO, getRecipients(beanMail.getRecipients()));
			message.setRecipients(Message.RecipientType.BCC, getRecipients(beanMail.getRecipientsBcc()));
			message.setSubject(beanMail.getSubject());
			message.setSentDate(new Date());
			message.saveChanges();
			
			addBodyMessage();
		} catch (MessagingException ex) {
			throw new SendMailException("Error al crear el mensaje para enviar", ex.getCause());
		} 
		readyToSend = true;
	}
	
	private Address[] getRecipients(String[] beanMailRecipients) throws AddressException {
		InternetAddress [] recipients;
		int numRecipients = 0;
		if (beanMailRecipients != null) {
			numRecipients = beanMailRecipients.length;
			recipients = new InternetAddress[numRecipients];
			
			for (int i = 0; i < numRecipients; i++) {
				InternetAddress internetAddress = new InternetAddress(beanMailRecipients[i]);
				recipients[i] = internetAddress;
			}
			
			return recipients;
		}
		
		return new InternetAddress[numRecipients];
	}

	public void addBodyMessage() {
		BodyPart body = new MimeBodyPart();
		
		Configuration cfg = new Configuration();
		Writer out = new StringWriter();
		try {
		cfg.setDirectoryForTemplateLoading(new File(beanMail.getDirectoryHtmlTemplate()));
		Template template = cfg.getTemplate(beanMail.getHtmlTemplate());
		template.process(beanMail.getHtmlBodyProps(), out);
		body.setContent(out.toString(), "text/html");
		
		multipart = new MimeMultipart("related");
		multipart.addBodyPart(body);
		
		addAttachments();
		addCidImages();
		
		message.setContent(multipart, "text/html");
		} catch (TemplateException e) {
			throw new SendMailException("Error al procesar el template de Freemarker", e.getCause());
		} catch (IOException e) {
			throw new SendMailException("Error al cargar el template de Freemarker", e.getCause());
		} catch (MessagingException e) {
			throw new SendMailException("Error al crear el cuerpo del mensaje", e.getCause());
		}
		
	}

	private void addAttachments() {
		BodyPart body;
		if (beanMail.getAttachments() != null) {
			for (Attachment attachment : beanMail.getAttachments()) {
				body = new MimeBodyPart();
				DataSource fds = new FileDataSource(attachment.getPathFile() + attachment.getFileName());
				try {
					body.setDataHandler(new DataHandler(fds));
					body.setFileName(attachment.getFileName());
					multipart.addBodyPart(body);			
				} catch (MessagingException e) {
					throw new SendMailException("Error al agregar los archivos adjuntos", e.getCause());
				}
			}
		}
	}
	
	private void addCidImages() {
		BodyPart body;
		if (beanMail.getCidImages() != null) {
			for (CidImage cidImage : beanMail.getCidImages()) {
				body = new MimeBodyPart();
				DataSource fds = new FileDataSource(cidImage.getPathFile() + cidImage.getFileName());
				try {
					body.setDataHandler(new DataHandler(fds));
					body.setHeader("Content-ID", cidImage.getCid());
					multipart.addBodyPart(body);
				} catch (MessagingException e) {
					throw new SendMailException("Error al agregar las imagenes embebidas", e.getCause());
				}
			}
		}
	}

	public void send() {
		try {
			if (readyToSend) {
				Transport.send(message);
				messageStatus = "ENVIADO";
			} else {
				throw new SendMailException("Primero se debe invocar el metodo 'setupMail'");
			}
		} catch (MessagingException e) {
			e.printStackTrace();
			messageStatus = e.getMessage();
		}
	}
	
	@Override
	public void setupMail(BeanMailConstructor beanMailConstructor) throws SendMailException {
		setupMail(beanMailConstructor.constructBeanMail());
	}
}
