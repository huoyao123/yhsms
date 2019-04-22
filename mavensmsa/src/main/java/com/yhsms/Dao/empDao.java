package com.yhsms.Dao;

import java.util.List;
import java.util.Map;

import com.yhsms.domain.Employee;

public interface empDao {

	//员工登录
	public Employee logemp(String eaccount, String epass);
	//添加员工的方法
	public boolean addemp(Employee e);
	//查询所有员工的方法
	public Map<Integer,String> selectemp();
	//修改员工权限的方法
	public boolean updateemp(int eid,String ejob);
	//删除员工的方法
	public boolean deleteemp(int eid);



}
