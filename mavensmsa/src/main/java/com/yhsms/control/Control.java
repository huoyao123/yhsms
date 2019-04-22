package com.yhsms.control;

import java.io.IOException;
import java.net.ServerSocket;
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
					//System.out.println(u.getCaid());
					if(u!=null){
						break;
					}
					this.view.println("卡号或密码输入错误!");
				}
				this.view.println("欢迎"+u.getUname()+"光临雅惠餐厅");
			
				while(true){
					this.view.uShow();
					int ch = this.ui.getInt("请选择:");
					if(ch==1){
						//点菜
						Map<Integer, String> show = this.service.ShowM();
						Set<Integer> keySet = show.keySet();
						System.out.println("菜品编号"+"\t"+"菜名");
						for (Integer m : keySet) {
							System.out.println(m+"\t"+show.get(m));
						}
						int s = this.ui.getInt("请选择：");
						Map<Integer, String> showall = this.service.showAllMenu(s);
						Set<Integer> keySet2 = showall.keySet();
						System.out.println("菜品编号"+"\t"+"菜名"+"\t"+"单价");
						for (Integer mm : keySet2) {
							System.out.println(mm+"\t"+showall.get(mm));
						}
						this.addMenu(u.getCaid());
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
					}else if(ch==6){
						System.out.println("退出成功");
						System.exit(0);
						
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
					       this.empaddMenu(em.getEid(), caid);
					}else{
						this.empaddMenu(em.getEid(), 0);
					}
					     	}else if(s==2){
								//办卡
								String ca = this.addCard();
								System.out.println(ca);
							}else if(s==3){
								//结账
						String e = this.ui.getString("是否有会员卡？y/n");
					    if(e.equals("y")){
							 this.payM();
								}
					    else{
				      double sum = this.service.jiezhang(0);
						String sm = this.service.paycard(0, sum);
						this.view.println(sm);
								}
							}else if(s==4){
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
						}
					}
					else if(ejob.equals("经理")){
						while(true){
							view.managerView();
							int m = ui.getInt("请选择：");
							if(m==1){
								//加员工信息
								this.addEmp();
								
							}else if(m==2){
								//修改菜单
								String a = this.updateMenu();
								System.out.println(a);
							}else if(m==3){
								//查看菜单
								
							}
							else if(m==4){
								//冻结卡
							}else if(m==5){
								//查看月销量
							}else if(m==6){
								//设置特价菜
							}else if(m==7){
								//设置优惠额度
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
				int id = this.ui.getInt("请选择菜的编号：");
				int num=this.ui.getInt("请输入数量：");
				this.service.emporder(eid, cid, id, num);
				String u = this.ui.getString("是否继续点菜？y/n");
				if(u.equals("n")){
					break;
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
				String u = this.ui.getString("是否继续点菜？y/n");
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
		//客户管理订单的方法
		public void selectOrder(){
			int id = this.ui.getInt("请输入卡号：");
			System.out.println("1.查看当前订单");
			System.out.println("2.查看所有订单");
			int i = this.ui.getInt("请选择：");
			if(i==1){
				Map<Integer, String> order = this.service.selectnoworder(id);
				Set<Integer> o = order.keySet();
				System.out.println("订单编号"+"\t"+"菜品编号"+"\t"+"数量"+"\t"+"金额");
				for (Integer it : o) {
					System.out.println(it+"\t"+order.get(it));
				}
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

         return  this.service.paycard(id, sum);

		}
		//修改订单的方法
		public String updateOrder(int cid){
			this.view.println("1.修改菜品");
			this.view.println("2.删除当前订单");
			this.view.println("0.不修改");
			int m = this.ui.getInt("请选择：");
			if(m==1){
				while(true){
					int d = this.ui.getInt("输入要修改的编号：");
					int num = this.ui.getInt("请输入要修改的数量：");
					String s = this.service.updateorder(cid, d, num);
					this.view.println(s);
					int n = this.ui.getInt("是否继续？0（yes）/1（no）");
					if(n==1){
						break;
					}
				}
			}else if(m==2){
				String ch = this.ui.getString("是否要删除当前订单？y/n");
				if(ch.equals("y")){
					int oid = this.ui.getInt("请输入订单号：");
					this.service.deleteorder(oid);
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
			e.setEid(this.ui.getInt("输入员工编号："));
			e.setEname(this.ui.getString("输入员工姓名："));
			e.setEaccount(this.ui.getString("输入员工账号："));
			e.setEpass(this.ui.getString("输入员工密码："));
			e.setEjob(this.ui.getString("输入员工职位："));
			e.setEloc(this.ui.getString("输入员工工作地址："));
			e.setEnote(this.ui.getString(""));
			return this.service.addEmp(e);
		}
		//修改菜单的方法
		public String updateMenu(){
			this.view.UpdateMenu();
			int i = this.ui.getInt("请选择：");
			//添加菜品
			if(i==1){
				int mid = this.ui.getInt("请添加的菜品的编号：");
				String mname = this.ui.getString("添加菜品的名字：");
				double mprice = this.ui.getDouble("菜品的价格：");
				//String mnote = this.ui.getString("");
				int mtid = this.ui.getInt("菜品类型编号：");
				String s = this.service.addMenus(new Menum(mid, mname, mprice, ""), mtid);
			    return s;
			}//删除菜类型
			else if(i==5){
				int mid = this.ui.getInt("请输入菜类型的编号：");
				String s = this.service.deletemetype(mid);
				return s;
			}//删除菜品
			else if(i==2){
				int mid = this.ui.getInt("请输入菜的编号：");
				String s = this.service.deleteM(mid);
				return s;
			}//修改菜品的类型
			else if(i==3){
				int mid = this.ui.getInt("请输入菜类型编号：");
				String name = this.ui.getString("菜的类型名字：");
				String s = this.service.updatemetype(mid, name);
				return s;
			}//修改菜的价格
			else if(i==4){
				int mid = this.ui.getInt("请输入菜的编号：");
				double price = this.ui.getDouble("请输入菜的价格：");
				String s = this.service.updatemenu(mid, price);
				return s;
			}//删除菜的类型
			else if(i==5){
				int mid = this.ui.getInt("请输入菜的类型编号：");
				String s = this.service.deletemetype(mid);
				return s;
			}//添加菜的类型
			else if(i==6){
				int mid = this.ui.getInt("请输入菜的类型编号：");
				String mtname = this.ui.getString("请输入菜的类型名字：");
				String s = this.service.addmenuType(mid, mtname);
				return s;
			}
			return "修改成功";
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		}
		
		