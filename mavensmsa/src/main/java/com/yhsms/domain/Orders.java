package com.yhsms.domain;

import java.io.Serializable;
import java.util.Date;

public class Orders implements Serializable{
	
	private int oid;//订单编号
    private int caid; //会员卡号
    
    //private int mid;//菜编号
    private Menum m;
    
    private int num;//数量
   // private double oprice;//金额
    private String onote;//备注
    private Date otime;//系统时间
    
    
	public Orders() {
		super();
	}


	public Orders(int num, String onote) {
		super();
		//this.oid = oid;
		//this.caid = caid;
		//this.m = m;
		this.num = num;
		//this.oprice = oprice;
		this.onote = onote;
		//this.otime = otime;
	}


//	public String getOid() {
//		return oid;
//	}
//
//
//	public void setOid(String oid) {
//		this.oid = oid;
//	}


	public int getCaid() {
		return caid;
	}


	public void setCaid(int caid) {
		this.caid = caid;
	}


	public Menum getM() {
		return m;
	}


	public void setM(Menum m) {
		this.m = m;
	}


	public int getNum() {
		return num;
	}


	public void setNum(int num) {
		this.num = num;
	}


//	public double getOprice() {
//		return oprice;
//		
//	}


//	public void setOprice(double oprice) {
//		
//		this.oprice = oprice;
//	}


	public String getOnote() {
		return onote;
	}


	public void setOnote(String onote) {
		this.onote = onote;
	}


	public Date getOtime() {
		return otime;
	}


	public void setOtime(Date otime) {
		this.otime = otime;
	}

    
    
	
}
