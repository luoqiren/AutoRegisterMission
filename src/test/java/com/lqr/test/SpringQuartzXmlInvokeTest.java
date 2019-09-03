package com.lqr.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringQuartzXmlInvokeTest {
	public static void main(String[] args) { // 启动Spring 容器
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
				"classpath:AutoMissionInvokeReg.xml");
		ctx.start();
		System.out.println("initContext successfully");
	}
}
