package com.yhsms.biz;

import com.yhsms.domain.Card;

public interface cardBiz {

	
	//用户登录
	public Card LoginUser(int caid,String capass);
	
	//开卡的方法
	public String OpenCard(Card c);
	
	//挂失冻结卡
	public String GuaCard(int caid,String note);
	
    //查看余额
	public String selectmoney(int caid);
	
	//设置充值优惠
	//public String setaddmoney(double money,double remoney);
	
	//会员充值
	public String addmoney(int caid,double money);

	//设置vip会员优惠额度
	public double setVip(double vip);
	
	//设置Svip会员优惠额度
	public double setSVip(double svip);
	
	//付款
	public String paycard(int caid,double sum);
	
	//退款
	public String returncard(int caid,double money);

	
}
