package com.lqr.util;

import com.lqr.util.date.DateUtil;

public class MissionTwo {

	public static void main(String[] args) {
		new MissionTwo().doThingTwo();
	}

	public void doThingTwo() {
		System.out.println(DateUtil.getTodayTextYMDHMS()+": Like do something From 0602");
	}

}
