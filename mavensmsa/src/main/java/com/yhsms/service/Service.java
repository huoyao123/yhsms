package com.yhsms.service;

import java.util.List;
import java.util.Map;

import com.yhsms.domain.Card;
import com.yhsms.domain.Employee;
import com.yhsms.domain.Menum;


public interface Service {
	//用户登录的方法
		public Card LoginUser(int id,String pass);
		//根据卡号查看用户
		public Card selectCar(int cid);
		//客户查看菜类型方法
		public Map<Integer,String> ShowM();

		//经理查询所有菜的类型
		public Map<Integer,String> empselectmetype();
		//查看菜单的方法
		public Map<Integer,String> showAllMenu(int id);
		//创建一个员工登录的方法
		public Employee Login(String account,String password);
		//创建一个点菜的方法
		public String addMenu(int id,int num,int cid);
		//创建一个办卡的方法
		public String addCard(Card c);
		//创建一个挂失的方法
		public String Lock(int id,String note);
		//创建一个充值 的方法
		public String addMoney(int id,double m);
		//查看余额
		public String selectmoney(int caid);
		//设置会员优惠额度
		public double setVip(double vip);
		//设置Svip会员优惠额度
		public double setSVip(double svip);
		//付款
		public String paycard(int caid, double sum);
		//退款
		public String returncard(int caid, double money);
		//创建一个添加员工的方法
		public String addEmp(Employee e);
		//删除员工
		public String deleteEmp(int id);
		//修改员工权限的方法
		public String updateemp(int eid,String ejob);
		//修改员工工作地址
		public String updataLoc(int eid,String loc);
		//根据id查询员工信息
		public Employee selectById(int id );
		//查询所有员工信息
		public Map<Integer,String> finfAll();
		//创建一个经理查看菜单的方法
		public Map<Integer,String> showMenu();
		//修改菜的价钱
		public String updatemenu(int mid, double price);
		//创建一个添加菜单的方法
		public String addMenus(Menum m,int mtid);
		//删除菜
		public String deleteM(int mid);
		//上架菜品
		public String upmenum(int mid);
		//添加菜的类型
		public String addmenuType(int mtid,String mtname);
		//修改菜类型
		public String updatemetype(int mtid,String mtname);
		//删除菜类型
		public String deletemetype(int mtid);
		//上架菜类型
		public String updatemtnote(int mtid);
		//查看月销量
		public  List<String> selectmonth(int i);
		//设置特价菜
		public String setspecial(int mid);
		//显示特价菜
		public List<String> selectspecial();
		//取消特价菜
		public String deleteSpecial(int mid);
		//获取系统时间
		public String sysdate();

		//点菜
		public String addorders(int caid,int mid,int num);

		//根据卡号显示所有订单
		public Map<Integer,String> selectordersBycaid(int caid);

		//根据卡号显示当前订单
		public Map<Integer,String> selectnoworder(int caid);

		//员工下单
		public String emporder(int eid,int caid,int mid,int num);

		//根据员工号显示所有订单
		public Map<Integer,String> selectorderbyeid(int eid);

		//删除订单
		public void deleteorder(int oid);

		//修改菜
		public String updateorder(int mid,int num);

		//结账
		public double jiezhang(int caid);
        //查看当前订单总额
		public double selectSum(int caid);
		//经理查看菜单
		public Map<Integer,String> selectAllmenu();
		//查看所有客户信息
		public List<Card> selectAlluser();
        //一个会员卡积分的方法
		public String intoJifen(int caid,double sum);

		//根据订单号显示当前订单
		public String selectorderByoid(int oid);
		//会员卡积分兑换的方法
		public String DFen(int cid,int  num);
		//根据卡号查询积分
		public int UserSelectFen(int cid);
	}

