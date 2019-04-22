package com.yhsms.BizImpl;

import java.util.List;
import java.util.Map;

import com.yhsms.Dao.empDao;
import com.yhsms.DaoImpl.empDaoImpl;
import com.yhsms.biz.empBiz;
import com.yhsms.domain.Employee;

public class empBizImpl implements empBiz{
	
	private empDao dao;
	

	public empBizImpl() {
	this.dao=new empDaoImpl();
	}

	//员工登录
	public Employee logemp(String eaccount,String epass){
		
		return this.dao.logemp(eaccount,epass);
	}

	//添加员工的方法
	public String addemp(Employee e) {
		
		return this.dao.addemp(e)?"添加成功":"添加失败";
	}

	//查询所有员工的方法
	public Map<Integer,String> selectEmp() {
		
		return this.dao.selectemp();
	}

	//修改员工信息的方法
	public String updateemp(int eid,String ejob) {
		
		return this.dao.updateemp(eid,ejob)?"修改成功":"修改失败";
	}

	//删除员工的方法
	public String deleteemp(int eid) {
		
		return this.dao.deleteemp(eid)?"删除成功":"删除失败";
	}

}
