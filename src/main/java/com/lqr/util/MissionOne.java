package com.lqr.util;

import com.lqr.util.date.DateUtil;

public class MissionOne {

	public static void main(String[] args) {
		new MissionOne().doThings();
	}

	public void doThings() {
		System.out.println(DateUtil.getTodayTextYMDHMS()+": Like do something From 0601");
	}

}
