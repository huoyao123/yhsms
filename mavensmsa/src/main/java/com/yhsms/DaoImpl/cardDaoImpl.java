package com.yhsms.DaoImpl;


import java.sql.ResultSet;
import java.sql.SQLException;

import com.softi.util.DBUtil;
import com.yhsms.Dao.cardDao;
import com.yhsms.domain.Card;


public class cardDaoImpl implements cardDao {

	private DBUtil db;
	@Override
	//用户登录
	public Card LoginUser(int caid,String capass) {
		db=new DBUtil();
		String sql="select * from card where caid="+caid;
		try {
			ResultSet rs = this.db.query(sql);
			while(rs.next()){
				if(rs.getString("capass").equals(capass) && rs.getString("canote")==null){
					Card c=new Card(rs.getInt("caid"),rs.getString("uname"),rs.getString("capass"),rs.getString("catype"),rs.getDouble("discount"),rs.getDouble("money"),rs.getString("canote"));
				return c;
					}
					
				}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			this.db.closed();
		}
		return null;
	}

	@Override
	//开卡的方法
	public boolean OpenCard(Card c) {
		db=new DBUtil();
		
		String sql="insert into card values(?,?,?,?,?,?,?)";
		try {
			int i = this.db.update(sql,c.getCaid(),c.getUname(),c.getCapass(),c.getCatype(),c.getDiscount(),c.getMoney(),c.getCanote());
			return i>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			this.db.closed();
		}
	}
	
	
	
	@Override
	//挂失冻结卡
	public boolean GuaCard(int caid,String note) {
		db= new DBUtil();
		String sql="update card set canote='"+note+"' where caid="+caid;
		try {
			int i = this.db.update(sql);
			return i>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			this.db.closed();
		}


	}
	@Override
	//查看余额
	public String selectmoney(int caid) {
		db= new DBUtil();
		String sql="select money,canote from card where caid="+caid;
		try {
			ResultSet rs = this.db.query(sql);
			while(rs.next()){
				
				if(rs.getString("canote")!=null){
					return "该卡被"+rs.getString("canote");
				}
				return "余额为："+rs.getDouble("money");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			this.db.closed();
		}
		return null;

	}

//	//设置充值优惠
//	@Override
//	public String setaddmoney(double money,double remoney) {//满money返remony
//
//		return "满"+money+"减"+remoney;
//
//	}

	@Override
	//会员充值
	public String addmoney(int caid,double money){//会员充值金额
		db= new DBUtil();
		String str="select canote from card where caid="+caid;
		
		try {
		ResultSet rs = this.db.query(str);
		while(rs.next()){
			if(rs.getString("canote")!=null){
				return "该卡被"+rs.getString("canote");
				
			}
		}
		
		if(money>=200){
			money=money+100;
		}else if(money>=500){
			money=money+300;
		}
		
		String sql="update card set money= money+"+money+"where caid=caid";
			int i = this.db.update(sql);
			if(i>0){
				String now = this.selectmoney(caid);
					return "充值成功，"+now;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "充值失败";
		}
		return "充值失败";

	}
	@Override
	//设置vip会员优惠额度
	public double setVip(double vip) {
		db= new DBUtil();
		String sql="update card set discount="+vip+" where catype='vip'";
		try {
			int i = this.db.update(sql);
			if(i>0){
				return vip;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}finally{
			this.db.closed();
		}
		return 0;

	}
	@Override
	//设置svip会员优惠额度
	public double setSVip(double svip) {
		db= new DBUtil();
		String sql="update card set discount='"+svip+"' where catype='svip'";
		try {
			int i = this.db.update(sql);
			if(i>0){
				return svip;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}finally{
			this.db.closed();
		}
		return 0;

	}
	
	@Override
	//付款
	public String paycard(int caid, double sum) {
		double hou = 0;
		double discount=0;
		db= new DBUtil();
		String str="select discount from card where caid="+caid;
		
		try {
			ResultSet rs = this.db.query(str);
			while(rs.next()){
				discount=rs.getDouble("discount");
				hou=sum*discount;
				
			}
			String sql="update card set money=money-"+hou+" where caid="+caid;
			int i = this.db.update(sql);
			if(i>0){
				System.out.println("消费为"+sum+",打"+discount+"折后实际消费为"+hou);
				
				return this.selectmoney(caid);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			this.db.closed();
		}
		return null;

	}
	@Override
	//退款
	public boolean returncard(int caid, double money) {
		db= new DBUtil();
		String sql="update card set money= (select money from card where caid="+caid+")+"+money;
		try {
			int i = this.db.update(sql);
			return i>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			this.db.closed();
		}

	}
}















