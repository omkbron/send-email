package com.omkbron.sendemail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import freemarker.template.TemplateException;

public class Principal {
	public static void main(String[] args) throws AddressException, MessagingException, IOException, TemplateException {
		SendMail sendMail = new SendMail();
		Properties props = new Properties();
		InputStream stream = Principal.class.getClassLoader().getResourceAsStream("mail.properties");
		try {
			if (stream != null) {
				props.load(stream);
			} else {
				throw new FileNotFoundException("No se encontro el archivo de propiedades");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		sendMail.setup(null);
		sendMail.send();
	}
}
