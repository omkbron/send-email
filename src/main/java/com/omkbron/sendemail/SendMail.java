package com.omkbron.sendemail;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

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

public class SendMail {
	private BeanMail beanMail;

	public void setup(BeanMail beanMail) {
		this.beanMail = beanMail;
	}

	public void send() throws AddressException, MessagingException, IOException, TemplateException {
		Session session = Session.getDefaultInstance(beanMail.getMailProps(),
				new Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(beanMail.getUserName(), beanMail.getPassword());
					}
				});
		
		Message message = new MimeMessage(session);
		
		message.setFrom(beanMail.getFrom());
		message.setRecipients(Message.RecipientType.TO, beanMail.getRecipients());
		message.setSubject(beanMail.getSubject());
		
		BodyPart body = new MimeBodyPart();
		
		Configuration cfg = new Configuration();
		Template template = cfg.getTemplate(beanMail.getHtmlTemplate());
		Map<String, String> rootMap = getHtmlBodyProps();
		Writer out = new StringWriter();
		template.process(rootMap, out);
		
		body.setContent(out.toString(), "text/html");
		
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(body);
		
		message.setContent(multipart, "text/html");
		
		Transport.send(message);
		
		System.out.println("Enviado...");
	}

	private Map<String, String> getHtmlBodyProps() {
		Map<String, String> htmlBodyProps = new HashMap<String, String>();
		htmlBodyProps.put("to", "Juan Camaney");
		htmlBodyProps.put("from", "Omar Velasco");
		htmlBodyProps.put("body", "El cuerpo del mensaje como propiedad");
		return htmlBodyProps;
	}
}
