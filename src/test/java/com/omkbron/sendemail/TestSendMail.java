package com.omkbron.sendemail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;

import com.omkbron.sendemail.model.BeanMail;
import com.omkbron.sendemail.model.BeanMailConstructor;
import com.omkbron.sendemail.service.SendMailTemplate;

public class TestSendMail {
	@Test
	public void testSendMail() {
		SendMailTemplate sendMail = new SendMailTemplate();
		final Properties props = new Properties();
		InputStream stream = TestSendMail.class.getClassLoader()
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

		final String[] recipients = new String[] {
//			"ovelasco@magnabyte.com.mx",
//			"eesqueda@magnabyte.com.mx",
			"omvp29@hotmail.com"
		};
		
		sendMail.setupMail(new BeanMailConstructor() {
			
			@Override
			public BeanMail constructBeanMail() {
				BeanMail beanMail = new BeanMail();
				beanMail.setMailProps(props);
				beanMail.setUserName(props.getProperty("email.username"));
				beanMail.setPassword(props.getProperty("email.password"));
				beanMail.setFrom(props.getProperty("email.from"));
				beanMail.setRecipients(recipients);
				beanMail.setSubject(props.getProperty("email.subject"));
				beanMail.setDirectoryHtmlTemplate(props.getProperty("email.dirtemplate"));
				beanMail.setHtmlTemplate(props.getProperty("email.template"));
				beanMail.setHtmlBodyProps(getHtmlBodyProps());
				return beanMail;
			}
		});
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
