package com.yhsms.Dao;

import java.util.Map;

import com.yhsms.domain.Menutype;

public interface menutypeDao {
	//添加菜类型的方法
	public boolean addmetype(int mtid, String mtname);
	//查询所有菜的类型
	public Map<Integer,String> selectmetype();
	//修改菜类型
	public boolean updatemetype(int mtid,String mtname);
	//删除菜类型
	public boolean deletemetype(int mtid);
	//修改菜类型状态
	public boolean updatemtnote(int mtid);
}
