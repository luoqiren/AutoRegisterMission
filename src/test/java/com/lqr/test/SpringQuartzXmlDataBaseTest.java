package com.lqr.test;

import java.util.List;

import org.hibernate.Session;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.lqr.model.TAutoMissionReg;
import com.lqr.service.TAutoMissionRegService;
import com.lqr.service.impl.TAutoMissionRegServiceImpl;


public class SpringQuartzXmlDataBaseTest {
	static ClassPathXmlApplicationContext context = null;
	static HibernateTemplate h = null;
	static Session s = null;
	static {
		context = new ClassPathXmlApplicationContext(
				"classpath*:spring-common.xml");
		h = (HibernateTemplate) context.getBean("hibernateTemplate");
	}
	@Test
	public void test() throws Exception {
		TAutoMissionReg tAutoMissionRegTmp = new TAutoMissionReg();
		tAutoMissionRegTmp.setIsActive("1");
		TAutoMissionRegService autoMissionRegService = 
				(TAutoMissionRegService)
//				context.getBean(TAutoMissionRegServiceImpl.class); 
				context.getBean("tAutoMissionRegServiceImpl");
		
		List<TAutoMissionReg> list 
			= autoMissionRegService.listTAutoMissionRegByCondition(tAutoMissionRegTmp);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getRegName());
		}
	}

}
