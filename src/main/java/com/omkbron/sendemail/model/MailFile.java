package com.omkbron.sendemail.model;

public class MailFile {
	protected String pathFile;
	protected String fileName;
	
	public MailFile(String pathFile, String fileName) {
		this.pathFile = pathFile;
		this.fileName = fileName;
	}

	public String getPathFile() {
		return pathFile;
	}

	public void setPathFile(String pathFile) {
		this.pathFile = pathFile;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
