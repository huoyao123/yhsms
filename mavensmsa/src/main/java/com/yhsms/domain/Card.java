package com.yhsms.domain;

import java.io.Serializable;

public class Card implements Serializable{
	
	private int caid;
	private String uname;
	
	private String capass;
	private String catype;
	private double discount;
	private double money;
	private String canote;
	
	
	public Card() {
		super();
	}


	
	


	public Card(int caid, String uname, String capass, String catype, double discount, double money, String canote) {
		super();
		this.caid = caid;
		this.uname = uname;
		this.capass = capass;
		this.catype = catype;
		this.discount = discount;
		this.money = money;
		this.canote = canote;
	}






	public String getUname() {
		return uname;
	}



	public void setUname(String uname) {
		this.uname = uname;
	}



	public int getCaid() {
		return caid;
	}



	public void setCaid(int caid) {
		this.caid = caid;
	}



	public String getCapass() {
		return capass;
	}



	public void setCapass(String capass) {
		this.capass = capass;
	}



	public String getCatype() {
		return catype;
	}



	public void setCatype(String catype) {
		this.catype = catype;
	}



	public double getDiscount() {
		return discount;
	}



	public void setDiscount(double discount) {
		this.discount = discount;
	}



	public double getMoney() {
		return money;
	}



	public void setMoney(double money) {
		this.money = money;
	}



	public String getCanote() {
		return canote;
	}



	public void setCanote(String canote) {
		this.canote = canote;
	}



	@Override
	public String toString() {
		//System.out.println("会员卡号"+"\t"+"会员姓名"+"\t"+"会员类型"+"\t"+"优惠额度"+"\t"+"余额"+"\t"+"备注");
		return this.caid+"\t"+this.uname+"\t"
		       +this.catype+"\t"+this.discount+"\t"+this.money+"\t"+this.canote;
	}
	

}
