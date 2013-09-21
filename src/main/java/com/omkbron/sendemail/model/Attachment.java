package com.omkbron.sendemail.model;

public class Attachment {
	private String pathFile;
	private String fileName;

	public Attachment(String pathFile, String fileName) {
		super();
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
