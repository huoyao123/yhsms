package com.yhsms.DaoImpl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.softi.util.DBUtil;
import com.yhsms.Dao.menutypeDao;
import com.yhsms.domain.Menutype;


public class menutypeDaoImpl implements menutypeDao {

	private DBUtil db;

	@Override
	//添加菜类型的方法
	public boolean addmetype(int mtid, String mtname) {
		this.db = new DBUtil();
		String sql="insert into menutype values(?,?,?) ";
		try {
			int i = this.db.update(sql,mtid,mtname,"");
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
	//查询所有菜的类型
	public Map<Integer,String> selectmetype() {
		Map<Integer,String> map = new HashMap<Integer, String>();
		this.db = new DBUtil();
		String sql="select * from menutype where mtnote is null";
		try {
			ResultSet rs = this.db.query(sql);
			System.out.println("类型编号"+"\t"+"类型");
			while(rs.next()){
				map.put(rs.getInt("mtid"), rs.getString("mtname"));
			}
			return map;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			this.db.closed();
		}

	}
	@Override
	//修改菜类型
	public boolean updatemetype(int mtid,String mtname) {
		this.db = new DBUtil();
		String sql="update menutype set mtname='"+mtname+"' where mtid="+mtid;
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
	//删除菜类型
	public boolean deletemetype(int mtid) {
		this.db = new DBUtil();
		String sql="update menutype set mtnote='已下架' where mtid="+mtid;
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
	//修改菜类型状态
	public boolean updatemtnote(int mtid) {
		this.db = new DBUtil();
		String sql="update menutype set mtnote='' where mtid="+mtid;
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
