package com.omkbron.sendemail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.omkbron.sendemail.model.Attachment;
import com.omkbron.sendemail.model.BeanMail;
import com.omkbron.sendemail.model.CidImage;
import com.omkbron.sendemail.service.SendMail;

import freemarker.template.TemplateException;

public class Principal {
	public static void main(String[] args) throws AddressException,
			MessagingException, IOException, TemplateException {
		SendMail sendMail = new SendMail();
		Properties props = new Properties();
		InputStream stream = Principal.class.getClassLoader()
				.getResourceAsStream("mail.properties");
		try {
			if (stream != null) {
				props.load(stream);
			} else {
				throw new FileNotFoundException(
						"No se encontro el archivo de propiedades");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		String[] recipients = new String[] {
//			"ovelasco@magnabyte.com.mx",
//			"eesqueda@magnabyte.com.mx",
			"ovelasco@hotmail.com"
		};
		
		BeanMail beanMail = new BeanMail();
		beanMail.setMailProps(props);
		beanMail.setUserName(props.getProperty("email.username"));
		beanMail.setPassword(props.getProperty("email.password"));
		beanMail.setFrom(props.getProperty("email.from"));
		beanMail.setRecipients(recipients);
		beanMail.setSubject(props.getProperty("email.subject"));		
		beanMail.setHtmlTemplate(props.getProperty("email.template"));
		beanMail.setHtmlBodyProps(getHtmlBodyProps());
		beanMail.setAttachments(new ArrayList<Attachment>());
		beanMail.setCidImages(new ArrayList<CidImage>());
		sendMail.initialize(beanMail);
		sendMail.setupMail();
		sendMail.send();
	}
	
	private static Map<String, Object> getHtmlBodyProps() {
		Map<String, Object> htmlBodyProps = new HashMap<String, Object>();
		htmlBodyProps.put("to", "Juan Camaney");
		htmlBodyProps.put("from", "Omar Velasco");
		htmlBodyProps.put("body", "El cuerpo del mensaje como propiedad, se pueden enviar varias propiedades en un Mapa");
		
		List<String> lista = new ArrayList<String>();
		lista.add("Un valor");
		lista.add("Otro valor");
		lista.add("Uno mas");
		lista.add("El penultimo");
		lista.add("Ultimo valor");
		
		htmlBodyProps.put("lista", lista);
		
		return htmlBodyProps;
	}
}
