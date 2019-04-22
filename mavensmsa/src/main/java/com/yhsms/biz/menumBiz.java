package com.yhsms.biz;

import java.awt.Menu;
import java.util.List;
import java.util.Map;

import com.yhsms.domain.Menum;
import com.yhsms.domain.Menutype;

public interface menumBiz {
	
	
	
	//添加菜品
	public String addmenu(Menum m,int mtid);
	

	//客户查询所有菜
	public Map<Integer,String> userseletemenu(int mtid);
	
	
	//经理查询所有菜
	public Map<Integer,String> empseletemenu();

	//显示特价菜
	public Map<Integer,String> selectspecial();
	
	//删除菜
	public String deletemenu(int mid);
	
	//修改菜的价钱
	public String updatemenu(int mid,double price);
	
	//设置特价菜
	public String setspecial(int mid);
	
	

}
