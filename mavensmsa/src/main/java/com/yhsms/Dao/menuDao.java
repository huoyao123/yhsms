package com.yhsms.Dao;

import java.awt.Menu;
import java.util.List;
import java.util.Map;

import com.yhsms.domain.Menum;
import com.yhsms.domain.Menutype;

public interface menuDao {
	//添加菜品
	public boolean addmenu(Menum m,int mtid);
	//客户查询所有菜
	public Map<Integer,String> userseletemenu(int mtid);
	//经理查询所有的菜
	public Map<Integer,String> empseletemenu();
   //删除菜
	public boolean deletemenu(int mid);
	//修改菜的价钱
	public boolean updatemenu(int mid, double price);
	//设置特价菜
	public boolean setspecial(int mid);
	//显示特价菜
	public Map<Integer, String> selectspecial();


}
