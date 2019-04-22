package com.yhsms.domain;

import java.io.Serializable;

public class Employee implements Serializable{
	
	private int eid;
	private String ename;
	private String eaccount;
	private String epass;
	private String ejob;
	private String eloc;
	private String enote;
	
	
	
	public Employee() {
		super();
	}



	public Employee(int eid, String ename, String eaccount, String epass, String ejob, String eloc, String enote) {
		super();
		this.eid = eid;
		this.ename = ename;
		this.eaccount = eaccount;
		this.epass = epass;
		this.ejob = ejob;
		this.eloc = eloc;
		this.enote = enote;
	}



	public int getEid() {
		return eid;
	}



	public void setEid(int eid) {
		this.eid = eid;
	}



	public String getEname() {
		return ename;
	}



	public void setEname(String ename) {
		this.ename = ename;
	}



	public String getEaccount() {
		return eaccount;
	}



	public void setEaccount(String eaccount) {
		this.eaccount = eaccount;
	}



	public String getEpass() {
		return epass;
	}



	public void setEpass(String epass) {
		this.epass = epass;
	}



	public String getEjob() {
		return ejob;
	}



	public void setEjob(String ejob) {
		this.ejob = ejob;
	}



	public String getEloc() {
		return eloc;
	}



	public void setEloc(String eloc) {
		this.eloc = eloc;
	}



	public String getEnote() {
		return enote;
	}



	public void setEnote(String enote) {
		this.enote = enote;
	}
public String toString(){
	System.out.println("员工编号"+"\t"+"员工姓名"+"\t"+"工作地址");
	return this.eid+"\t"+this.ename+"\t"+this.eloc;

}

	
	
	

}
