package com.znv.icap.realtimeprocess.test;

public enum TestConfigEnum {
	instance ;
	private int bulkNum = 1000*1;
	private int targetNum = 1000*1000*1000; // 裴总目标10亿数据
	private int currentConsumerNum = 10;
	
	public int getBulkNum() {
		return bulkNum;
	}
	public int getTargetNum() {
		return targetNum;
	}
	public int getCurrentConsumerNum() {
		return currentConsumerNum;
	}
}

