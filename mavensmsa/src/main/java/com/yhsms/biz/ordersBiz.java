package com.yhsms.biz;

import java.util.Map;

import com.yhsms.domain.Orders;

public interface ordersBiz {
	//获取系统时间
		public String sysdate();
		
		//添加订单
		public String addorders(int caid,int mid,int num);
		
		//根据卡号显示所有订单
		public Map<Integer,String> selectordersBycaid(int caid);
		
		//根据卡号显示当前订单
		public Map<Integer,String> selectnoworder(int caid);
		
		//员工下单
		public String emporder(int eid,int caid,int mid,int num);
		
		//根据员工号显示所有订单
		public Map<Integer,String> selectordersByeid(int eid);
		
		//删除订单
		public void deleteorder(int oid);
		
		//修改订单
		public String updateorder(int caid,int mid,int num);
		
		//结账
		public double jiezhang(int caid);
		
		//查看月账单
		public Map<Integer,String> month(int date);

}
