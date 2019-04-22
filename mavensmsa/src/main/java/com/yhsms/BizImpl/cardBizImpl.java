package com.yhsms.BizImpl;

import com.yhsms.Dao.cardDao;
import com.yhsms.DaoImpl.cardDaoImpl;
import com.yhsms.biz.cardBiz;
import com.yhsms.domain.Card;

public class cardBizImpl implements cardBiz {

	private cardDao dao;


	
	public cardBizImpl() {
		this.dao= new cardDaoImpl();
	}

	//用户登录
	public Card LoginUser(int caid,String capass) {

		return this.dao.LoginUser(caid,capass);
	}


	//开卡的方法
	public String OpenCard(Card c) {

		return this.dao.OpenCard(c)?"开卡成功":"开卡失败";
	}

	//挂失冻结卡
	public String GuaCard(int caid, String note) {
		
		return this.dao.GuaCard(caid,note)?"办理成功":"办理失败";
	}
	

	//查看余额
	public String selectmoney(int caid) {

		return this.dao.selectmoney(caid);
	}

	//设置充值优惠
	//@Override
	//public String setaddmoney(double money,double remoney) {

		//return this.dao.setaddmoney(money,remoney);
	//}

	//会员充值
	public String addmoney(int caid,double money){

		return this.dao.addmoney(caid,money);
	}


	//设置vip会员优惠额度
	public double setVip(double vip) {

		return this.dao.setVip(vip);
	}

	//设置Svip会员优惠额度
	public double setSVip(double svip){

		return this.dao.setSVip(svip);
	}

	//付款
	public String paycard(int caid, double sum) {

		return this.dao.paycard(caid,sum);
	}

	//退款
	public String returncard(int caid, double money) {

		return this.dao.returncard(caid,money)?"退款成功":"退款失败";
	}

	

}
