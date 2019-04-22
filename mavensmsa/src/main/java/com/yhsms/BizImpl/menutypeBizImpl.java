package com.yhsms.BizImpl;

import java.util.List;
import java.util.Map;

import com.yhsms.Dao.menutypeDao;
import com.yhsms.DaoImpl.menutypeDaoImpl;
import com.yhsms.biz.menutypeBiz;
import com.yhsms.domain.Menutype;

public class menutypeBizImpl implements menutypeBiz {
	
	private menutypeDao dao;
	
	
	

	public menutypeBizImpl() {
		this.dao= new menutypeDaoImpl();
	}

	//添加菜类型的方法
	public String addmetype(int mtid, String mtname) {
		
		return this.dao.addmetype(mtid, mtname)?"添加成功":"添加失败";
	}

	//查询所有菜的类型
	public Map<Integer,String> selectmetype() {
		
		return this.dao.selectmetype();
	}

	//修改菜类型
	public String updatemetype(int mtid,String mtname) {
		
		return this.dao.updatemetype(mtid,mtname)?"修改成功":"修改失败";
	}

	//删除菜类型
	public String deletemetype(int mtid) {
		
		return this.dao.deletemetype(mtid)?"删除成功":"删除失败";
	}

	//修改菜类型状态
	public String updatemtnote(int mtid) {
		
		return this.dao.updatemtnote(mtid)?"修改成功":"修改失败";
	}

}
