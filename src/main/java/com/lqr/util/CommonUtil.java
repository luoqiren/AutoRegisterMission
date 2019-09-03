package com.lqr.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Date;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class CommonUtil {
	
	public static int getASCvalue(){return 1;}
	public static int getDESCvalue(){return 0;}
	
	/**
	 * generation the random code , 4-length
	 * @return 
	 */
	public static String genericValidCode(){
		int codeLength = 4 ;
		int [] seed = {0,1,2,3,4,5,6,7,8,9};
		int ranArr = 0;
		Random random = new Random();
		for(int i = 0; i < codeLength ; i++){
			int j = random.nextInt(seed.length - i);
			ranArr = ranArr*10 + seed[j];
			seed[j] = seed[seed.length - 1 - i];
		}
		
		if(ranArr<=999) {return genericValidCode();}
		
		return String.valueOf(ranArr);
	}
	/**
	 * get current date
	 * @return
	 */
	public static String getCurrentDate(){
		Calendar cal = Calendar.getInstance();
		return new Date(cal.getTimeInMillis()).toString();
	}
	/**
	* <p>Description: </p>
	* @return
	*/
	public static Date getCurrentSQLDate(){
		Calendar cal = Calendar.getInstance();
		return new Date(cal.getTimeInMillis());
	}
	/**
	 * ��OBJECTת��Ϊmap, null�ֶβ�ת��
	* <p>Description: </p>
	* @date 2017-12-25 
	* @param obj
	* @return
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public static Map<String, Object> objectToMap(Object obj) throws Exception{
		if(obj == null){  
            return null;  
        }
		Map<String,Object> reMap = new HashMap<String,Object>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for(int i = 0; i < fields.length; i++){
            Field subField = fields[i];
            subField.setAccessible(true);
            if(Modifier.isStatic(subField.getModifiers())){
            	continue ; 
            }
            Object value = subField.get(obj);
			if(value!=null){
				reMap.put(fields[i].getName(), value);
			}
        }  
        return reMap;
	}
	
	public static java.util.Date getCurrentUtilDate() {
		return new java.util.Date();
	}
	
	public static String getCurrentUtilDateFormatString(){
		Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date date = new java.util.Date();
		return format.format(date);
	}
	
	
	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 1000; i++) {
			System.out.println(genericValidCode());
		}
		
	}
}
