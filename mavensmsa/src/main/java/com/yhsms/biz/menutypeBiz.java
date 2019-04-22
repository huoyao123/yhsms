package com.yhsms.biz;

import java.util.Map;

import com.yhsms.domain.Menutype;

public interface menutypeBiz {
	//添加菜类型的方法
	public String addmetype(int mtid,String mtname);

	//查询所有菜的类型
	public Map<Integer,String> selectmetype();
	
	//修改菜类型
	public String updatemetype(int mtid,String mtname);
	
	//删除菜类型
	public String deletemetype(int mtid);
	
	//修改菜类型状态
	public String updatemtnote(int mtid);
}
