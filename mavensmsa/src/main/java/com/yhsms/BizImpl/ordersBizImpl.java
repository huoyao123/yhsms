package com.yhsms.BizImpl;

import java.util.Map;

import com.yhsms.Dao.orderDao;
import com.yhsms.DaoImpl.orderDaoImpl;
import com.yhsms.biz.ordersBiz;
import com.yhsms.domain.Orders;

public class ordersBizImpl implements ordersBiz {
	
	private orderDao dao;

	public ordersBizImpl() {
		this.dao=new orderDaoImpl();
	}

	//获取系统时间
	@Override
	public String sysdate() {

		return this.dao.sysdate();
	}


	//添加订单
	@Override
	public String addorders(int caid,int mid,int num) {

		return this.dao.addorders(caid, mid,num);
	}

	//根据卡号显示所有订单
	@Override
	public Map<Integer,String> selectordersBycaid(int caid) {

		return this.dao.selectordersBycaid(caid);
	}

	//根据卡号显示当前订单
	@Override
	public Map<Integer,String> selectnoworder(int caid) {

		return this.dao.selectnoworder(caid);
	}


	//员工下单
	@Override
	public String emporder(int eid,int caid,int mid,int num) {

		return this.dao.emporder(eid, caid, mid, num);
	}


	//根据员工号显示所有订单
	@Override
	public Map<Integer,String> selectordersByeid(int eid) {

		return this.dao.selectorderbyeid(eid);
	}

	//删除订单
	@Override
	public void deleteorder(int oid) {

		 this.dao.deleteorder(oid);
	}

	//修改订单
	@Override
	public String updateorder(int caid,int mid,int num) {

		return this.dao.updateorder(caid, mid, num);
	}

	//结账
	@Override
	public double jiezhang(int caid) {

		return this.dao.jiezhang(caid);
	}

	//查看月账单
	@Override
	public Map<Integer,String> month(int date){
		
		return this.dao.month(date);
		
	}

}
