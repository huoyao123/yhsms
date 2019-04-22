package com.yhsms.biz;

import java.util.List;
import java.util.Map;

import com.yhsms.domain.Employee;

public interface empBiz {
	
	//员工登录
	public Employee logemp(String eaccount,String epass);

	//添加员工的方法
	public String addemp(Employee e);
	
	//查询所有员工的方法
	public Map<Integer,String> selectEmp();
	
	//修改员工信息的方法
	public String updateemp(int eid,String ejob);
	
	//删除员工的方法
	public String deleteemp(int eid);
	
}
