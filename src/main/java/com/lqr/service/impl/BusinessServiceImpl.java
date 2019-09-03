package com.lqr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lqr.invokeUtils.CallMissionsInvokeCode;
import com.lqr.model.TAutoMissionReg;
import com.lqr.service.BusinessService;
import com.lqr.service.TAutoMissionRegService;
import com.lqr.util.date.DateUtil;

/**
 * 
 * @author Qi
 * 简单来说自动任务分三步走
 * 1：读取数据（配置信息以及必要数据来源）【要数据来源，可以在业务2处理完成】
 * 2：处理业务数据（交付给自动任务具体实现类）
 * 3：将处理好的业务数据回写【此处可以在业务2处理完成】
 */
@Service("businessServiceImpl")
@Transactional
public class BusinessServiceImpl implements BusinessService {

	@Autowired
	private TAutoMissionRegService tAutoMissionRegService;
	
	@Override
	public void execBusiness() throws Exception {
		
		System.out.println("---start--- "+DateUtil.getTodayTextYMDHMS());
		String currentHH24MM = DateUtil.getTodayTextH24M();
		String currentMM = currentHH24MM.substring(3);
		CallMissionsInvokeCode callMissionsInvokeCode = new CallMissionsInvokeCode();
		//do read info
		TAutoMissionReg tAutoMissionRegTmp = new TAutoMissionReg();
		tAutoMissionRegTmp.setIsActive("1");
		List<TAutoMissionReg> regList = 
				this.tAutoMissionRegService.listTAutoMissionRegByCondition(tAutoMissionRegTmp);
		
		//do execute business
		String isCycle = ""; //是否循环
		String execTimeStr = ""; //是否当前时间点执行
		String execPerMinu = ""; //第几分钟执行
		String isHoliday = "";
		TAutoMissionReg tAutoMissionReg = null;
		for (int i = 0; i < regList.size(); i++) {
			try {
				tAutoMissionReg = regList.get(i);
				isCycle = tAutoMissionReg.getIsCycle();
				isHoliday = tAutoMissionReg.getIsHoliday(); //是否是节假日， 需要引入节假日判断， 待实现
				if("1".equals(isCycle)) {
					execPerMinu = tAutoMissionReg.getExecPerMinu();
					if(currentMM.equals(execPerMinu)) {
						callMissionsInvokeCode.autoInvokeNotConstructor(tAutoMissionReg.getRegClass(), tAutoMissionReg.getRegMethod());
					}else {
						System.out.println("execPerMinu:"+execPerMinu + " not execute");
					}
				}else if("0".equals(isCycle)) {
					execTimeStr = tAutoMissionReg.getExecTime();
					if(currentHH24MM.equals(execTimeStr)) {
						callMissionsInvokeCode.autoInvokeNotConstructor(tAutoMissionReg.getRegClass(), tAutoMissionReg.getRegMethod());
					}else {
						System.out.println("execTime:"+execTimeStr + " not execute");
					}
				}else {
					System.out.println("Mission do nothing, config error: "+tAutoMissionReg.getRegName()+" key:"+tAutoMissionReg.getRegKey());
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
		
		//do after execute business
		System.out.println("---end--- "+DateUtil.getTodayTextYMDHMS());
	}

}
