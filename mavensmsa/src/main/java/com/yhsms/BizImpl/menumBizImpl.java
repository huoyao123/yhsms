package com.yhsms.BizImpl;

import java.awt.Menu;
import java.util.List;
import java.util.Map;

import com.yhsms.Dao.menuDao;
import com.yhsms.DaoImpl.menuDaoImpl;
import com.yhsms.biz.menumBiz;
import com.yhsms.domain.Menum;
import com.yhsms.domain.Menutype;

public class menumBizImpl implements menumBiz {
	
	private menuDao dao;
	
	

	public menumBizImpl() {
		this.dao = new menuDaoImpl();
	}


	//添加菜品
	public String addmenu(Menum m,int mtid) {
		
		return this.dao.addmenu(m,mtid)?"添加成功":"添加失败";
	}

	//客户查询所有菜
	public Map<Integer,String> userseletemenu(int mtid) {
		
		return this.dao.userseletemenu(mtid);
	}
	
	
	//经理查询所有菜
	public Map<Integer,String> empseletemenu() {
		
		return this.dao.empseletemenu();
	}

	//删除菜
	public String deletemenu(int mid) {
		
		return this.dao.deletemenu(mid)?"删除成功":"删除失败";
	}

	//修改菜的价钱
	public String updatemenu(int mid, double price) {
		
		return this.dao.updatemenu(mid,price)?"修改成功":"修改失败";
	}

	//设置特价菜
	public String setspecial(int mid) {
		
		return this.dao.setspecial(mid)?"设置成功":"设置失败";
	}

	//显示特价菜
	public Map<Integer, String> selectspecial() {
		
		return this.dao.selectspecial();
	}


}
