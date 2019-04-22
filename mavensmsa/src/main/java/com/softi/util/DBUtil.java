package com.softi.util;


import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBUtil {
	Connection conn;
	   Statement stmt;
	   PreparedStatement pstmt;
	public DBUtil() {
		try {
				Properties prop=new Properties();
				prop.load(new FileInputStream("pro.properties"));
				Class.forName(prop.getProperty("classname")).newInstance();
//				//获取连接对象
			  conn=DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			
           catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	//创建增删改 的方法
	//1.非预编译 不需要参数
	public int update(String sql) throws SQLException{
		stmt=conn.createStatement();
		return stmt.executeUpdate(sql);
	}
	   //2.预编译 需要参数进行？赋值
	public int update(String sql,Object...arr) throws SQLException{
		//创建预编译对象
		pstmt=conn.prepareStatement(sql);
		//给？赋值
		for (int i = 0; i < arr.length; i++) {
			pstmt.setObject((i+1), arr[i]);
		}
		return pstmt.executeUpdate();
	}
	//创建查询的方法
	//1.非预编译
	public ResultSet query(String sql) throws SQLException{
		stmt=conn.createStatement();
		return stmt.executeQuery(sql);
	}
	//2.预编译
	public ResultSet query(String sql,Object...arr) throws SQLException{
		pstmt=conn.prepareStatement(sql);
		//给？赋值
		for (int i = 0; i < arr.length; i++) {
			pstmt.setObject((i+1), arr[i]);
		}
		return pstmt.executeQuery();
	}
	//创建一个关闭资源的方法
	public void closed(){
		//六、关闭资源
		try {
			if(pstmt!=null && !pstmt.isClosed())pstmt.close();
			if(conn!=null && !conn.isClosed())conn.close();
			if(stmt!=null && !stmt.isClosed())stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}  
