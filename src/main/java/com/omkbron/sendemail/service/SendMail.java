package com.omkbron.sendemail.service;

import com.omkbron.sendemail.model.BeanMail;
import com.omkbron.sendemail.model.BeanMailConstructor;
import com.omkbron.sendemail.util.SendMailException;

public interface SendMail {
	
	void setupMail(BeanMail beanMail) throws SendMailException;

	void setupMail(BeanMailConstructor beanMailConstructor) throws SendMailException;
	
}
