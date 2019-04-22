package com.yhsms.domain;

import java.io.Serializable;

public class Menutype implements Serializable {

	private int mtid;
	private String mtname;
	private String mtnote;
	
	
	public Menutype(int mtid) {
		super();
	}


	public Menutype(int mtid, String mtname, String mtnote) {
		super();
		this.mtid = mtid;
		this.mtname = mtname;
		this.mtnote = mtnote;
	}

	
	public int getMtid() {
		return mtid;
	}


	public void setMtid(int mtid) {
		this.mtid = mtid;
	}


	public String getMtname() {
		return mtname;
	}


	public void setMtname(String mtname) {
		this.mtname = mtname;
	}


	public String getMtnote() {
		return mtnote;
	}


	public void setMtnote(String mtnote) {
		this.mtnote = mtnote;
	}

	@Override
	public String toString() {
		
		return this.mtid+"\t"+this.mtname+"\t"+this.mtnote;
	}
	
}
