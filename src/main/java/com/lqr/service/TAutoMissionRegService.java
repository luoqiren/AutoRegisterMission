package com.lqr.service;

import java.util.List;

import com.lqr.model.TAutoMissionReg;

public interface TAutoMissionRegService {
	
	public List<TAutoMissionReg> listTAutoMissionRegByCondition(TAutoMissionReg tAutoMissionReg) throws Exception;
}
