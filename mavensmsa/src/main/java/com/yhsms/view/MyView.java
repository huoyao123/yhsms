package com.yhsms.view;

import java.awt.Menu;
import java.util.List;

import com.yhsms.domain.Menum;



public class MyView {
	public static void main(String[] args) {
		
	}
	public void welcome() {
		System.out.println("\t欢迎光临雅惠餐厅");
		System.out.println("================================");
		System.out.println("1.客户登录");
		System.out.println("2.员工登录");
	    System.out.println("0.退出系统");
		System.out.println("---------------------------------");
	}
	public void println(String msg) {
		System.out.println(msg);
	}
	public void uShow(){
		System.out.println("1.点菜");
		System.out.println("2.查看余额");
		System.out.println("3.查看订单");
		System.out.println("4.修改订单");
		System.out.println("5.结账");
		System.out.println("6.查看积分");
		System.out.println("0.退出");
	}
	public void showMenu(){
		System.out.println("1.热菜");
		System.out.println("2.凉菜");
		
		System.out.println("3.管理订单");
		System.out.println("4.结账");
		System.out.println("5.退出");
	}
	
	public void emptView() {
	
		System.out.println("1.点菜");
		System.out.println("2.办卡");
		System.out.println("3.结账");
		System.out.println("4.挂失");
		System.out.println("5.充值");
		//System.out.println("6.修改菜");
		System.out.println("6.查看当前订单");
		System.out.println("7.修改当前订单");
		System.out.println("8.会员卡积分兑换");
		System.out.println("9.查看客户积分");
		System.out.println("0.退出");
		
	}
	public void showCard(){
		System.out.println("1.会员卡");
		System.out.println("2.超级会员卡");
	}
	
	public void managerView(){
		
		System.out.println("1.添加员工信息");
		System.out.println("2.查询所有员工");
		System.out.println("3.修改员工信息");
		System.out.println("4.查看员工销量");
		System.out.println("5.开除员工");
		System.out.println("6.修改菜单");
		System.out.println("7.查看菜单");	
		System.out.println("8.管理特价菜");
		System.out.println("9.设置优惠额度");	
		System.out.println("10.查看月销量");
		System.out.println("11.冻结卡");
		System.out.println("12.查看所有客户信息");
		System.out.println("0.退出");
		//System.out.println("9.查询员工的订单情况");
		
	}
	public void UpdateMenu(){
		System.out.println("1.添加菜品");
		System.out.println("2.管理菜品上架情况");
		System.out.println("3.修改菜的价格");
		System.out.println("4.添加菜类型");
		System.out.println("5.更改菜品的类型");
		System.out.println("6.管理菜品类型上架情况");
		System.out.println("0.退出");
	}
	public void showArray(List<Menum> list) {
		System.out.println("编号\t菜名\t单价\t类型编号");
		for (Menum mn : list) {
			System.out.println(mn);
		}
	
	}
}
