package com.omkbron.sendemail;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;

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

	/**
	 * Inicializa el objeto <code>BeanMail</code> <p>
	 * 
	 * El cual sirve para configurar los parametros necesarios para enviar el mail <p>
	 * @param beanMail BeanMail object
	 */
	public void setup(BeanMail beanMail) {
		this.beanMail = beanMail;
	}

	/**
	 * Envía el mail con la configuracion obtenida del objeto
	 * <code>BeanMail</code> <p> inicializado con el metodo
	 * <code>setup</code>
	 * 
	 * @throws AddressException
	 * @throws MessagingException
	 * @throws IOException
	 * @throws TemplateException
	 */
	public void send() throws AddressException, MessagingException, IOException, TemplateException {
		Session session; 
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
		
		Message message = new MimeMessage(session);
		
		message.setFrom(beanMail.getFrom());
		message.setRecipients(Message.RecipientType.TO, beanMail.getRecipients());
		message.setSubject(beanMail.getSubject());
		message.setSentDate(new Date());
		message.saveChanges();
		
		BodyPart body = new MimeBodyPart();
		
		Configuration cfg = new Configuration();
		Template template = cfg.getTemplate(beanMail.getHtmlTemplate());
		Writer out = new StringWriter();
		template.process(beanMail.getHtmlBodyProps(), out);
		
		body.setContent(out.toString(), "text/html");
		
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(body);
		
		message.setContent(multipart, "text/html");
		
		Transport.send(message);
		
		System.out.println("Mensaje enviado...");
	}

}
