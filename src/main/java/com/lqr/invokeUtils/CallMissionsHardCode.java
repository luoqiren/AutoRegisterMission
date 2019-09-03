package com.lqr.invokeUtils;

import com.lqr.util.MissionOne;
import com.lqr.util.MissionTwo;

public class CallMissionsHardCode {

	public CallMissionsHardCode() {
		/*new MissionOne().doThings();
		new MissionTwo().doThings();;*/
	}

	public void callMyDefineMissions() {
		new MissionOne().doThings();
		new MissionTwo().doThingTwo();
	}
	 
	public static void main(String[] args) {
		new CallMissionsHardCode();
	}

}
