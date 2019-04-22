package com.yhsms.UserInput;

import java.util.Scanner;

public class UserInput {
	//接受用户输入的String
		public String getString(String msg) {
			System.out.println(msg);
			Scanner reader = new Scanner(System.in);
			return reader.next();
		}
		//接受用户输入的整数
		public int getInt(String msg) {
			
		while(true) {
			try {
				System.out.println(msg);
				Scanner reader = new Scanner(System.in);
				return reader.nextInt();
			} catch (Exception e) {
				System.out.println("输入内容格式不正确，请输入整数类型");
			}
		}
		}
		//接受用户输入小数
		public double getDouble(String msg) {
			while(true) {
				try {
					System.out.println(msg);
					Scanner reader = new Scanner(System.in);
					return reader.nextDouble();
				} catch (Exception e) {
					System.out.println("输入内容格式不正确，请输入小数类型");
				}
			}
		}

}
