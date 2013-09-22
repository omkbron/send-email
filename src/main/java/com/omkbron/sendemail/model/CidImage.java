package com.omkbron.sendemail.model;

public class CidImage extends MailFile {
	private String cid;
	
	public CidImage(String cid, String pathFile, String fileName) {
		super(pathFile, fileName);
		this.cid = cid;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = "<" + cid + ">";
	}

}