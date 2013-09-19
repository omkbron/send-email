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
import javax.mail.internet.InternetAddress;

import com.omkbron.sendemail.BeanMail;
import com.omkbron.sendemail.SendMail;

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

		BeanMail beanMail = new BeanMail(props, props.getProperty("email.username"),
				props.getProperty("email.password"), new InternetAddress(props.getProperty("email.from")),
				new InternetAddress[] {
						new InternetAddress("ovelasco@magnabyte.com.mx"),
						new InternetAddress("eesqueda@magnabyte.com.mx"),
						new InternetAddress("iortega@magnabyte.com.mx")},
				props.getProperty("email.subject"), props.getProperty("email.template"),
				getHtmlBodyProps());

		sendMail.setup(beanMail);
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
