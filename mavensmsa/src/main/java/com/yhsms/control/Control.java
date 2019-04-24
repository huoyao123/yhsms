package com.yhsms.control;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.yhsms.UserInput.UserInput;
import com.yhsms.domain.Card;
import com.yhsms.domain.Employee;
import com.yhsms.domain.Menum;
import com.yhsms.service.Service;
import com.yhsms.view.MyView;




//负责接收客户端连接 创建线程
public class Control {

	//属性
	private MyView view;
	private UserInput ui;
	private Service service;
	public static final String IP="10.10.49.43";
	public static final int PORT=6666;

	//构造方法
	public Control() {
		this.view = new MyView();
		this.ui = new UserInput();
		//创建代理对象
		service=ProxyClient.getClient(Service.class, IP, PORT);
	}


	//这是总的控制语句
	public void start(){
		view.welcome();
		Card u=null;
		int i = ui.getInt("请选择：");
		if(i==1){
			while(true){
				int id=this.ui.getInt("请输入卡号：");
				String pass=this.ui.getString("请输入密码：");
				u=this.service.LoginUser(id, pass);

				if(u!=null){

					if(u.getCanote()!=null){
						System.out.println("该卡被"+u.getCanote()+"!");
						System.exit(0);
					}
					break;


				}
				this.view.println("卡号或密码输入错误!");
			}
			this.view.println("欢迎"+u.getUname()+"光临雅惠餐厅");

			while(true){
				this.view.uShow();
				int ch = this.ui.getInt("请选择:");
				if(ch==1){
					while(true){
						//点菜
						Map<Integer, String> show = this.service.ShowM();
						Set<Integer> keySet = show.keySet();
						System.out.println("菜品编号"+"\t"+"菜名");
						for (Integer m : keySet) {
							System.out.println(m+"\t"+show.get(m));
						}
						this.view.println("0.退出");
						int s = this.ui.getInt("请选择：");
						if(s==0){
							break;
						}else{
							Map<Integer, String> showall = this.service.showAllMenu(s);
							Set<Integer> keySet2 = showall.keySet();
							System.out.println("菜品编号"+"\t"+"菜名"+"\t"+"单价");
							for (Integer mm : keySet2) {
								System.out.println(mm+"\t"+showall.get(mm));
							}
							this.addMenu(u.getCaid());
						}

					}

				}else if(ch==2){
					//查看余额
					String m = this.selectMoney();
					System.out.println(m);
				}else if(ch==3){
					//管理订单
					this.selectOrder();
				}else if(ch==4){
					//修改订单
					String s = this.updateOrder(u.getCaid());
					this.view.println(s);
				}
				else if(ch==5){
					//结账
					System.out.println(this.payM());
				}else if(ch==0){
					System.out.println("退出成功");
					System.exit(0);

				}else if(ch==6){
					//查看积分
					this.view.println("积分为："+this.service.UserSelectFen(u.getCaid()));
				}
			}


		}else if(i==2){
			Employee em=null;
			while(true){
				String account=this.ui.getString("请输入账号：");
				String password=this.ui.getString("请输入账号密码：");
				em= this.service.Login(account, password);
				if(em!=null){
					break;
				}
				this.view.println("卡号或密码输入错误!");
			}
			String ejob = em.getEjob();
			if(ejob.equals("员工")){
				while(true){
					view.emptView();
					int s = ui.getInt("请选择：");
					if(s==1){
						//点菜

						String e = this.ui.getString("是否有会员卡？y/n");
						if(e.equals("y")){
							int caid = this.ui.getInt("请说卡号：");
							Card c = this.service.selectCar(caid);
							if(c!=null){
								System.out.println(this.empaddMenu(em.getEid(), caid)); 
							}else{
								System.out.println("卡号不存在");
							}

						}else{
							System.out.println(this.empaddMenu(em.getEid(), 0));
						}
					}else if(s==2){
						//办卡
						String ca = this.addCard();
						System.out.println(ca);
					}else if(s==3){
						//结账
						this.ShowOrder(em.getEid());
					}
					else if(s==4){
						//挂失
						String sms = this.lock();
						this.view.println(sms);
					}else if(s==5){
						//充值
						int id = this.ui.getInt("输入卡号：");
						double m = this.ui.getDouble("输入充值金额：");
						String a = this.service.addMoney(id, m);
						this.view.println(a);
					}
					else if(s==6){
						//查看当前订单
						this.empSelect();
					}else if(s==7){
						//修改当前订单
						int cid = this.empSelect();
						this.view.println(this.updateOrder(cid));
						
					}else if(s==8){
						//兑换积分
						this.EmpD();
					}else if(s==9){
						//查看客户积分
						int cid = this.ui.getInt("请输入卡号：");
						System.out.println("当前积分："+this.service.UserSelectFen(cid));
					}
					else if(s==0){
						System.out.println("退出成功");
						System.exit(0);

					}
				}
			}else if(ejob.equals("服务员") && em.getEnote()==null){
				this.view.println("抱歉您的权限不足不能登录！");
				System.exit(0);
			}else if(em.getEnote()!=null){
				this.view.println("抱歉您"+em.getEnote());
				System.exit(0);
			}
			else if(ejob.equals("经理")){
				while(true){
					view.managerView();
					int m = ui.getInt("请选择：");
					if(m==1){
						//加员工信息
						String s = this.addEmp();
						System.out.println(s);
					}else if(m==2){
						//查询所有员工
						this.findAllemp();
					}else if(m==3){
						//修改员工信息
						this.UpdateEmp();
					}else if(m==4){
						//查看员工月销量
						this.selectMonthbyID();
					}else if(m==5){
						//开除员工
						int eid = this.ui.getInt("输入员工编号：");
						this.view.println(this.service.deleteEmp(eid));
					}else if(m==6){
						//修改菜单
						String a = this.updateMenu();
						System.out.println(a);
					}else if(m==7){
						//查看菜单
						this.selectAllm();
					}else if(m==8){
						//管理特价菜
						this.setSpecial();
					}else if(m==9){
						//设置优惠额度
						this.setYouhui();
					}else if(m==10){
						//查看月销量
						this.findMonth(m);
					}else if(m==11){
						//冻结卡
						System.out.println(this.lockcard());
					}else if(m==12){
						//查看所有客户信息
						this.selectAlluser();
					}else if(m==0){
						System.out.println("退出成功");
						System.exit(0);

					}
				}
			}
		}else if(i==0){
			System.out.println("退出成功");
			System.exit(0);
		}
	}
	//员工点菜的方法
	public String empaddMenu(int eid,int cid){
		//点菜
		while(true){
			Map<Integer, String> show = this.service.ShowM();
			Set<Integer> keySet = show.keySet();
			System.out.println("菜品编号"+"\t"+"菜名");
			for (Integer m : keySet) {
				System.out.println(m+"\t"+show.get(m));
			}
			this.view.println("0.退出");
			int ss = this.ui.getInt("请选择：");
			if(ss==0){
				break;
			}
			Map<Integer, String> showall = this.service.showAllMenu(ss);
			Set<Integer> keySet2 = showall.keySet();
			System.out.println("菜品编号"+"\t"+"菜名"+"\t"+"单价");
			for (Integer mm : keySet2) {
				System.out.println(mm+"\t"+showall.get(mm));
			}
			while(true){
				int id = this.ui.getInt("请选择菜的编号：");
				int num=this.ui.getInt("请输入数量：");
				this.service.emporder(eid, cid, id, num);
				String u = this.ui.getString("是否继续点此类菜？y/n");
				if(u.equals("n")){
					break;
				}
			}
			
		}
		return "点餐成功";
	}
	//点菜的方法
	private String addMenu(int cid) {
		//点菜
		while(true){
			int id = this.ui.getInt("请选择菜的编号：");
			int num=this.ui.getInt("请输入数量：");
			String s = this.service.addMenu(cid,id,num);
			this.view.println(s);
			String u = this.ui.getString("是否继续点此类型菜？y/n");
			if(u.equals("n")){
				break;
			}
		}
		return "点餐成功";
	}
	//客户查看余额 的方法
	public String selectMoney(){
		int id  = this.ui.getInt("请输入卡号：");
		return this.service.selectmoney(id);
	}
	//员工查看当前订单
	public int empSelect(){
		int cid = this.ui.getInt("请输入卡号：");
		//如果没有会员卡则卡号为0
		Map<Integer, String> order = this.service.selectnoworder(cid);
		Set<Integer> o = order.keySet();
		System.out.println("订单编号"+"\t"+"菜品编号"+"\t"+"菜品名称"+"\t"+"数量"+"\t"+"单价"+"\t"+"金额");
		for (Integer it : o) {
			System.out.println(it+"\t"+order.get(it));
		}
		double sum = this.service.selectSum(cid);
		this.view.println("总额是："+sum);
		return cid;
	}
	//客户管理订单的方法
	public void selectOrder(){

		System.out.println("1.查看当前订单");
		System.out.println("2.查看所有订单");
		int i = this.ui.getInt("请选择：");
		int id = this.ui.getInt("请输入卡号：");
		if(i==1){
			Map<Integer, String> order = this.service.selectnoworder(id);
			
			Set<Integer> o = order.keySet();
			System.out.println("订单编号"+"\t"+"菜品编号"+"\t"+"菜品名称"+"\t"+"数量"+"\t"+"单价"+"\t"+"金额");
			for (Integer it : o) {
				System.out.println(it+"\t"+order.get(it));
			}
			double sum = this.service.selectSum(id);
			this.view.println("总额是："+sum);
		}else if(i==2){
			Map<Integer, String> selectall = this.service.selectordersBycaid(id);
			Set<Integer> all = selectall.keySet();
			System.out.println("订单编号"+"\t"+"菜品编号"+"\t"+"数量"+"\t"+"金额");
			for (Integer it : all) {
				System.out.println(it+"\t"+selectall.get(it));
			}
			while(true){
				String ch = this.ui.getString("是否要删除某一订单？y/n");
				if(ch.equals("y")){
					int mid = this.ui.getInt("请输入订单号：");
					this.service.deleteorder( mid);
				}else if(ch.endsWith("n")){
					this.view.println("退出");
					break;

				}

			}
		}
	}
	//创建一个结账的方法
	public String payM(){
		int id = this.ui.getInt("请输入卡号：");
		String money = this.service.selectmoney(id);
		double sum = this.service.jiezhang(id);
		int i = money.indexOf("：");
		String sub = money.substring(i+1,money.length());

		double mon=Double.parseDouble(sub);
		System.out.println("消费之前卡内余额："+mon);
		if(mon<sum){
			return "余额不足";
		}
         
		return  this.service.paycard(id, sum)+"\n"+
		 this.service.intoJifen(id, sum);

	}
	//修改订单的方法
	public String updateOrder(int cid){
		this.view.println("1.修改订单");
		this.view.println("2.删除当前订单");
		this.view.println("0.不修改");
		int m = this.ui.getInt("请选择：");
		if(m==1){
			while(true){
				int d = this.ui.getInt("输入要修改的订单编号：");
				System.out.println("编号"+"\t"+"菜名"+"\t"+"数量"+"\t"+"金额");
				System.out.println(this.service.selectorderByoid(d));
				String ch = this.ui.getString("是否要修改当前订单？y/n");
				if(ch.equals("y")){
					int num = this.ui.getInt("请输入要修改的数量：");
					String s = this.service.updateorder(d, num);
					this.view.println(s);
				}else{
					return "";
				}
				int n = this.ui.getInt("是否继续？0（yes）/1（no）");
				if(n==1){
					break;
				}
			}
		}else if(m==2){
			while(true){
				int oid = this.ui.getInt("请输入订单号：");
				System.out.println("编号"+"\t"+"菜名"+"\t"+"数量"+"\t"+"金额");
				System.out.println(this.service.selectorderByoid(oid));
				String ch = this.ui.getString("是否要删除当前订单？y/n");
				if(ch.equals("y")){
					this.service.deleteorder(oid);
				}
				int n = this.ui.getInt("是否继续？0（yes）/1（no）");
				if(n==1){
					break;
				}
			}
		}
		else if(m==0){
			return "不做修改";
		}
		return "修改订单成功！";
	}
	//办卡的方法
	public String addCard(){

		int id = this.ui.getInt("录入卡号：");
		String name = this.ui.getString("输入姓名：");
		String pass = this.ui.getString("输入密码：");
		String type = this.ui.getString("输入会员卡类型：");
		double dis = this.ui.getDouble("输入折扣：");
		double my = this.ui.getDouble("输入卡的金额：");
		// String no = this.ui.getString("输入note：");
		Card c=new Card(id, name, pass, type, dis, my, "");
		String ca = this.service.addCard(c);
		return  ca+"!";


	}
	//设置一个挂失的方法
	public String lock(){
		int id = this.ui.getInt("输入卡号:");
		return this.service.Lock(id, "挂失");
	}
	//添加员工的方法
	public String addEmp(){
		Employee e=new Employee();
		int eid=this.ui.getInt("输入员工编号：");
		String ename=this.ui.getString("输入员工姓名：");
		String eaccount=this.ui.getString("输入员工账号：");
		String epass=this.ui.getString("输入员工密码：");
		String ejob=this.ui.getString("输入员工职位：");
		String eloc=this.ui.getString("输入员工工作地址：");
		//e.setEnote(this.ui.getString(""));
		String addEmp = this.service.addEmp(new Employee(eid, ename, eaccount, epass, ejob, eloc, ""));
		return addEmp;
	}
	//修改菜单的方法
	public String updateMenu(){
		while(true){
			this.view.UpdateMenu();
			int i = this.ui.getInt("请选择：");
			//添加菜品
			if(i==1){
				while(true){
					int mid = this.ui.getInt("请添加的菜品的编号：");
					String mname = this.ui.getString("添加菜品的名字：");
					double mprice = this.ui.getDouble("菜品的价格：");
					//String mnote = this.ui.getString("");
					int mtid = this.ui.getInt("菜品类型编号：");
					String s = this.service.addMenus(new Menum(mid, mname, mprice, ""), mtid);
					String u1 = this.ui.getString("是否继续添加？y/n");
					if(u1.equals("n")){
						break;
					}
				}
			}
			//2.管理菜品上架情况
			else if(i==2){
				System.out.println("1.下架菜品");
				System.out.println("2.上架菜品");
				int q = this.ui.getInt("请选择：");
				if(q==1){
					while(true){
						int mid = this.ui.getInt("请输入菜的编号：");
						String s = this.service.deleteM(mid);
						System.out.println(s);
						String u = this.ui.getString("是否继续？y/n");
						if(u.equals("n")){
							break;
						}
					}	
				}else{
					while(true){
						int mid = this.ui.getInt("请输入菜的编号：");
						String s = this.service.upmenum(mid);
						System.out.println(s);
						String u = this.ui.getString("是否继续？y/n");
						if(u.equals("n")){
							break;
						}
					}	
				}
			}//修改菜的价格
			else if(i==3){
				while(true){
					int mid = this.ui.getInt("请输入菜的编号：");
					double price = this.ui.getDouble("请输入菜的价格：");
					String s = this.service.updatemenu(mid, price);
					String u = this.ui.getString("是否继续？y/n");
					if(u.equals("n")){
						break;
					}
				}
			}//添加菜的类型
			else if(i==4){
				while(true){
					int mid = this.ui.getInt("请输入菜的类型编号：");
					String mtname = this.ui.getString("请输入菜的类型名字：");
					String s = this.service.addmenuType(mid, mtname);
					String u = this.ui.getString("是否继续添加？y/n");
					if(u.equals("n")){
						break;
					}
				}

			}
			//修改菜品的类型
			else if(i==5){
				while(true){
					int mid = this.ui.getInt("请输入菜类型编号：");
					String name = this.ui.getString("菜的类型名字：");
					String s = this.service.updatemetype(mid, name);
					System.out.println(s);
					String u = this.ui.getString("是否继续？y/n");
					if(u.equals("n")){
						break;
					}

				}
			}//6.管理菜品类型上架情况
			else if(i==6){
				System.out.println("1.下架菜类型");
				System.out.println("2.上架菜类型");
				int q = this.ui.getInt("请选择：");
				if(q==1){
					while(true){
						int mid = this.ui.getInt("请输入菜的类型编号：");
						String s = this.service.deletemetype(mid);
						System.out.println(s);
						String u = this.ui.getString("是否继续？y/n");
						if(u.equals("n")){
							break;
						}
					}
				}else{
					while(true){
						int mtid = this.ui.getInt("请输入菜的类型编号：");
						String s = this.service.updatemtnote(mtid);
						System.out.println(s);
						String u = this.ui.getString("是否继续？y/n");
						if(u.equals("n")){
							break;
						}
					}
				}

			}

			else if(i==0){
				break;
			}

		}
		return "修改成功";

	}
	//查看菜单的方法
	public void selectAllm(){
		Map<Integer, String> map = this.service.empselectmetype();
		System.out.println("类型编号"+"\t"+"类型"+"\t"+"备注");
		Set<Integer> keySet = map.keySet();
		for (Integer i : keySet) {
			System.out.println(i+"\t"+map.get(i));
		}

		Map<Integer, String> findall = this.service.selectAllmenu();
		Set<Integer> key = findall.keySet();
		System.out.println("菜品编号"+"\t"+"菜名"+"\t"+"单价"+"\t"+"所属类型"+"\t"+"备注");
		for (Integer it : key) {
			System.out.println(it+"\t"+findall.get(it));
		}
	}
	//设置一个冻结卡的方法
	public String lockcard(){
		int id = this.ui.getInt("输入卡号:");
		return this.service.Lock(id, "冻结");
	}
	//查看月销量的方法
	public void findMonth(int m){
		int month = this.ui.getInt("请输入月份：");
		List<String> list = this.service.selectmonth(month);
		System.out.println("菜品编号"+"\t"+"菜名"+"\t"+"销量");
		for (String s : list) {
			System.out.println(s);
		}
	}
	//管理特价菜
	public void setSpecial(){
		this.view.println("1.添加特价菜");
		this.view.println("2.取消特价菜");
		this.view.println("3.显示特价菜");
		int i = this.ui.getInt("请选择：");
		if(i==1){
			int mid = this.ui.getInt("请输入菜品编号：");
			String s = this.service.setspecial(mid);
			this.view.println(s);
		}else if(i==2){
			int mid = this.ui.getInt("请输入菜品编号：");
			String s = this.service.deleteSpecial(mid);
			this.view.println(s);
		}else if(i==3){
			List<String> list = this.service.selectspecial();
			System.out.println("菜品编号"+"\t"+"菜名"+"\t"+"单价");
			for (String s : list) {
				System.out.println(s);
			}
		}

	}
	//设置优惠额度
	public void setYouhui(){
		System.out.println("1.设置vip优惠额度");
		System.out.println("2.设置svip优惠额度");
		int ch = this.ui.getInt("请选择：");
		if(ch==1){
			double vip = this.service.setVip(this.ui.getDouble("输入优惠度："));
			if(vip>0){
				System.out.println("设置成功！vip优惠额度为"+vip);
			}else{
				System.out.println("设置失败！");
			}
		}else if(ch==2){
			double svip = this.service.setSVip(this.ui.getDouble("输入优惠度："));
			if(svip>0){
				System.out.println("设置成功！svip优惠额度为"+svip);
			}else{
				System.out.println("设置失败！");
			}
		}
	}
	//查询所有员工
	public void findAllemp(){
		Map<Integer, String> emp = this.service.finfAll();
		Set<Integer> key = emp.keySet();
		System.out.println("员工编号"+"\t"+"员工姓名"+"\t"+"工作职位"+"\t"+"工作地点");
		for (Integer it: key) {
			System.out.println(it+"\t"+emp.get(it));
		}
	}
	//员工结账并打印小票
	public void ShowOrder(int eid){
		int p = this.ui.getInt("请选择支付方式：1.会员卡 / 2.现金");
		if(p==1){
			int caid = this.ui.getInt("请输入卡号：");
			this.showDing(eid);
			this.showMenu(caid);
			this.showHou(caid);
			this.view.println("\t欢迎您的下次光临");
		}else if(p==2){
			this.showDing(eid);
			this.showMenu(0);
			double sum = this.service.jiezhang(0);
			String sysdate = this.service.sysdate();
			System.out.println("下单时间："+sysdate);
			this.view.println("满100减10活动");
			if(sum>=100){
				sum=sum-10;
				this.view.println("总计："+sum);
				double money = this.ui.getDouble("实付：");
				this.view.println("找零："+(money-sum));
			}else{
				this.view.println("总计："+sum);
				double money = this.ui.getDouble("实付：");
				this.view.println("找零："+(money-sum));
			}
			this.view.println("\t欢迎您的下次光临");
		}
	}


	public void showMenu(int caid){
		//this.service.Print(caid);
		Map<Integer, String> map = this.service.selectnoworder(caid);
		Set<Integer> keySet = map.keySet();
		System.out.println("编号"+"\t"+"菜品编号"+"\t"+"菜名"+"\t"+"数量"+"\t"+"单价"+"\t"+"金额");
		for (Integer i : keySet) {
			System.out.println(i+"\t"+map.get(i));
		}

	}
	public void showDing(int id){
		this.view.println("\t欢迎光临雅惠餐厅");
		this.view.println("\t员工"+id+"为您服务");
	}
	public void showHou(int caid){
		double sum = this.service.jiezhang(caid);
		this.view.println("总计："+sum);
		String s = this.service.paycard(caid, sum)+"\n"+this.service.intoJifen(caid, sum);
		this.view.println(s);

	}
	//查看员工销量
	public void selectMonthbyID(){
		int eid = this.ui.getInt("输入员工号：");
		//根据员工号查询员工
		Employee e = this.service.selectById(eid);
		if(e==null){
			this.view.println("输入的员工号不存在");
		}else{
			Map<Integer, String> month = this.service.selectorderbyeid(eid);
			Set<Integer> keySet = month.keySet();
			int num=0;
			System.out.println("编号"+"\t"+"菜品编号"+"\t"+"数量"+"\t"+"金额"+"\t"+"备注"+"\t"+"下单时间");
			for (Integer it : keySet) {
				System.out.println(it+"\t"+month.get(it));
				num++;
			}
			System.out.println("总销量单数："+num);
		}

	}
	//修改员工信息的方法
	public void UpdateEmp(){
		System.out.println("1.修改员工职位");
		System.out.println("2.修改员工工作地址");
		int ch = this.ui.getInt("请选择：");
		if(ch==1){
			int eid = this.ui.getInt("输入员工编号：");
			String ejob = this.ui.getString("输入员工职位：");
			System.out.println(this.service.updateemp(eid, ejob));
		}else if(ch==2){
			int eid = this.ui.getInt("输入员工编号：");
			String eloc = this.ui.getString("输入员工工作地址：");
			System.out.println(this.service.updataLoc(eid, eloc));
		}
	}
	//查看所有客户信息
	public void selectAlluser(){
		List<Card> list = this.service.selectAlluser();
		System.out.println("会员卡号"+"\t"+"会员姓名"+"\t"+"会员类型"+"\t"+"优惠额度"+"\t"+"余额"+"\t"+"备注"+"\t"+"积分");
		for (Card c : list) {
			System.out.print(c);
			int f = this.service.UserSelectFen(c.getCaid());
		System.out.println("\t"+f);
			
		}
	}
	//员工兑换积分的方法
	public void EmpD(){
		int cid = this.ui.getInt("请输入卡号：");
		this.view.println("1.一套毛巾   10分");
		this.view.println("2.茶具一套   20分");
		this.view.println("3.双人被       30分");
	
			int i = this.ui.getInt("请选择：");
			if(i==1){
				this.view.println(this.service.DFen(cid, 10)); 
			}else if(i==2){
				this.view.println(this.service.DFen(cid, 20)); 
			}else if(i==3){
				this.view.println(this.service.DFen(cid, 30)); 
			
		}
		
		
	}



		}
		
		