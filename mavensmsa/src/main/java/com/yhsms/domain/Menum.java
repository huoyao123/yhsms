package com.yhsms.domain;

import java.io.Serializable;

public class Menum implements Serializable{
	private int mid;
	private String mname;
	private double mprice;
	private Menutype mt;
	private String mnote;
	
	public Menum() {
		super();
	}

	public Menum(int mid, String mname, double mprice, String mnote) {
		super();
		this.mid = mid;
		this.mname = mname;
		this.mprice = mprice;
		this.mnote = mnote;
		//this.mt = mt;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public double getMprice() {
		return mprice;
	}

	public void setMprice(double mprice) {
		this.mprice = mprice;
	}

	public String getMnote() {
		return mnote;
	}

	public void setMnote(String mnote) {
		this.mnote = mnote;
	}

	public Menutype getMt() {
		return mt;
	}

	public void setMt(Menutype mt) {
		this.mt = mt;
	}

     @Override
    public String toString() {
    	
    	return this.mid+"\t"+this.mname+"\t"+this.mprice+"\t";
    }

	
	
}
