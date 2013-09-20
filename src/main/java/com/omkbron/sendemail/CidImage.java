package com.omkbron.sendemail;

public class CidImage {
	private String cid;
	private String fileName;
	
	public CidImage(String cid, String fileName) {
		super();
		this.cid = cid;
		this.fileName = fileName;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = "<" + cid + ">";
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
