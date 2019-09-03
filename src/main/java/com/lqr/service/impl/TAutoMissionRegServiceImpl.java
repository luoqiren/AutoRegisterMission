package com.lqr.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lqr.dao.TAutoMissionRegDao;
import com.lqr.model.TAutoMissionReg;
import com.lqr.service.TAutoMissionRegService;
import com.lqr.util.CommonUtil;

@Service("tAutoMissionRegServiceImpl")
@Transactional
public class TAutoMissionRegServiceImpl implements TAutoMissionRegService {

	@Resource
	private TAutoMissionRegDao tAutoMissionRegDao;
	
	@Override
	public List<TAutoMissionReg> listTAutoMissionRegByCondition(TAutoMissionReg tAutoMissionReg) 
			{
		Map<String, Object> paramMap=null;
		try {
			paramMap = CommonUtil.objectToMap(tAutoMissionReg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.tAutoMissionRegDao.findByClassProperties(TAutoMissionReg.class, paramMap);
	}

}
