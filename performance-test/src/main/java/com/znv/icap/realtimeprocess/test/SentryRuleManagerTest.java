package com.znv.icap.realtimeprocess.test;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

import javax.xml.crypto.Data;

import com.znv.icap.realtimeprocess.bean.SentryRuleBean;
import com.znv.icap.realtimeprocess.sentryrulemanager.ConcurrentSentryRuleManager;
import com.znv.icap.realtimeprocess.sentryrulemanager.SentryRuleManager;

public class SentryRuleManagerTest {
	private final int blackPlateNum = 1000*1000;
	private HashSet<String> blackPlates = new HashSet<String>(blackPlateNum);
	
	public void runTest() {
		// 构造100w的车牌库
		System.out.println("Begin construct plates ... ");
		for(int i=0; i<blackPlateNum; i++) {
			String plate = VehiclePassInfoGenerator.INSTANCE.GetRandomPlate();
			
			while(blackPlates.contains(plate)) {
				plate = VehiclePassInfoGenerator.INSTANCE.GetRandomPlate();
			}
			
			blackPlates.add(plate);
		}
		System.out.println("End construct plates ... ");
		
		for (String vehiclePlate : blackPlates) {
			SentryRuleBean sentryRuleBean = new SentryRuleBean();
			sentryRuleBean.setVehiclePlate(vehiclePlate);
			
			Date beginTime = new Date(System.currentTimeMillis());
			Date endDate = new Date(System.currentTimeMillis() + 365*24*60*1000);
			
			sentryRuleBean.setBeginTime(beginTime);
			sentryRuleBean.setBeginTime(endDate);
			
			SentryRuleManager.INSTANCE.getSentryRuleManager().addOrUpdateRule(sentryRuleBean);
		}
		System.out.println("End init SentryRuleManager.  ");

		int nCount = 1000*1000*1000;
		long start = System.currentTimeMillis();
//		for(int i=0; i<nCount; i++) {
//			String plate = VehiclePassInfoGenerator.INSTANCE.GetRandomPlate();
//			SentryRuleManager.INSTANCE.getSentryRuleManager().getBlackDeploySentryRuleBean(plate);
//		}
		
		for(String plate : blackPlates) {
			SentryRuleBean rule = ConcurrentSentryRuleManager.INSTANCE.getBlackDeploySentryRuleBean(plate);
			assert(rule != null);
		}
		long end = System.currentTimeMillis();
		
		System.out.println("num per second process : " + nCount/(end -start)/10);
		
		// 拷贝其中的1w车牌，用作随机过车事件
		// 随机过车进行黑名单判断，其中有1%的为车牌黑名单
	}

	public static void main(String[] args) {
		new SentryRuleManagerTest().runTest();
//		int nCount = 1000*1000*10;
//		long start = System.currentTimeMillis();
//		for(int i=0; i<nCount; i++) {
//			String plate = VehiclePassInfoGenerator.INSTANCE.GetRandomPlate();
//			String uuid = UUID.randomUUID().toString();
//			System.out.println(plate);
//		}
//		long end = System.currentTimeMillis();
//		System.out.println("num per second process : " + nCount/(end -start)/10);
	}

}
