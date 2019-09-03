package com.lqr.invokeUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.util.StringUtils;

public class CallMissionsInvokeCode {
	public void autoRunning() throws Exception  {
		String [] clazzName = {"com.lqr.util.MissionOne","com.lqr.util.MissionTwo"};
		String [] methodName = {"doThings","doThingTwo"};
		
		for(int i=0;i<clazzName.length;i++) {
			autoInvokeNotConstructor(clazzName[i], methodName[i]);
		}
	}
	
	public void autoInvokeNotConstructor(String className, String methodName) throws Exception {
		if(StringUtils.isEmpty(className)) {
			throw new Exception("传入的类名不能为空.");
		}
		if(StringUtils.isEmpty(methodName)) {
			throw new Exception("传入的方法名不能为空.");
		}
		
		Class<?> clazz = Class.forName(className);
		//获取类中的public方法
		Method [] method =clazz.getMethods();
		Method methodNeedInvoke = null;
		for (int i = 0; i < method.length; i++) {
			if (method[i].toString().contains(methodName)) {
				methodNeedInvoke = method[i];
				//System.out.println(method[i]);
			}
		}
		Object obj = clazz.newInstance();//获取到实例
		methodNeedInvoke.invoke(obj, new String[0]);
	}
	
	
	
	public static void main(String[] args) {
		try {
			Class<?> clazz = Class.forName("com.lqr.util.MissionOne");
			//获取类中的public方法
			Method [] method =clazz.getMethods();
			Method methodNeedInvoke = null;
			for (int i = 0; i < method.length; i++) {
				if (method[i].toString().contains("doThings")) {
					methodNeedInvoke = method[i];
					//System.out.println(method[i]);
				}
			}
			Object obj = clazz.newInstance();//获取到实例
			methodNeedInvoke.invoke(obj, new String[0]);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
