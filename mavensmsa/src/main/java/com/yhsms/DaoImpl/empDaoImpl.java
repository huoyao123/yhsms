package com.yhsms.DaoImpl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.softi.util.DBUtil;
import com.yhsms.Dao.empDao;
import com.yhsms.domain.Employee;


public class empDaoImpl implements empDao {

	private DBUtil db;

	@Override
	//员工登录
	public Employee logemp(String eaccount, String epass) {
		db=new DBUtil();
		String sql="select * from employee where eaccount="+eaccount;
		try {
			ResultSet rs = this.db.query(sql);
			while(rs.next()){
				if(rs.getString("eaccount").equals(eaccount) && rs.getString("enote")==null){
       Employee e=new Employee(rs.getInt("eid"),rs.getString("ename"),rs.getString("eaccount"),rs.getString("epass"),rs.getString("ejob"),rs.getString("eloc"),rs.getString("eloc"));
				return e;
				}else if(rs.getString("enote")!=null){
					System.out.println("该员工已被解雇"); 
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.db.closed();
		}
		return null;

	}
	@Override
	//添加员工的方法
	public boolean addemp(Employee e) {
		this.db=new DBUtil();
		String sql="insert into employee values(?,?,?,?,?,?,?)";
		try {
			int i = this.db.update(sql,e.getEid(),e.getEname(),e.getEaccount(),e.getEpass(),e.getEjob(),e.getEloc(),e.getEnote());
			return i>0;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}finally{
			this.db.closed();
		}

	}
	@Override
	//查询所有员工的方法
	public Map<Integer,String> selectemp() {
		Map<Integer,String> map =new HashMap<Integer,String>();
		this.db = new DBUtil();
		String sql="select * from employee where enote is null";
		try {
			ResultSet rs = this.db.query(sql);
			System.out.println("员工编号"+"\t"+"员工姓名"+"\t"+"工作职位"+"\t"+"工作地点");
			while(rs.next()){
				map.put(rs.getInt("eid"), rs.getString("ename")+"\t"+rs.getString("ejob")+"\t"+rs.getString("eloc"));
			}
			return map;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.db.closed();
		}
		return null;
	}
	@Override
	//修改员工权限的方法
	public boolean updateemp(int eid,String ejob) {
		this.db = new DBUtil();
		String sql="update employee set ejob='"+ejob+"' where eid="+eid;
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
	//删除员工的方法
	public boolean deleteemp(int eid) {
		this.db = new DBUtil();
		String sql="update employee set enote='已开除' where eid="+eid;
		try {
			int i = this.db.update(sql);
			return i>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}


}








