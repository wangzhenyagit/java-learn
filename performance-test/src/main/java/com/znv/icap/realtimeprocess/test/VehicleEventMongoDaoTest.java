package com.znv.icap.realtimeprocess.test;

import com.alibaba.fastjson.JSONObject;

public enum VehicleEventMongoDaoTest {
	INSTANCE;
	
	public void TestJSONObjectParse(String jsonString) {
		long begin = System.currentTimeMillis();
		long count = 1000*1000;
		for(int i=0; i<count; i++) {
			JSONObject.parse(jsonString);
			}
		long end = System.currentTimeMillis();
		double per = count/(end - begin)*1000/10000;
		System.out.println("spend : " + (end - begin) + "ms, " + per + "w per sencond");
	}

	public static void main(String args[]) {
		String jsonStringLong= "{\"event_id\":6514434455237558314,\"event_index\":1,\"bayonet_id\":\"11000001802003223\",\"lane_id\":\"11000001802003223001\",\"event_type\":0,\"pass_time\":\"2018-01-24 10:12:45\",\"vehicle_plate\":\"苏A12345\",\"speed\":121,\"min_speed\":22,\"max_speed\":555,\"vehicle_category\":0,\"vehicle_type\":0,\"vehicle_length\":5,\"vehicle_color\":0,\"plate_color\":0,\"plate_category\":0,\"direction\":\"1\",\"vehicle_state\":\"1\",\"justbreak_rule\":\"\",\"video_path\":\"\",\"vehicle_logo\":0,\"vehicle_sublogo\":\"0\",\"vehicle_model\":0,\"plate_center\":\"{X=6360,Y=2552}\",\"plate_verge\":\"{Left=6072,Top=2464,Right=6648,Bottom=2640}\",\"lane_picture\":\"http://10.45.157.186:8080/group2/M00/B6/E1/Ci2du1pn66OAfrYYAAekqFlLc2c762.jpg\",\"hour\":2018012410,\"paltedim_id\":\"0000\",\"vehicledim_id\":\"0000000000\",\"enum_val\":\"0000000000000000\",\"plate_picture\":\"{X=6360,Y=2552}{Left=6072,Top=2464,Right=6648,Bottom=2640}\"}";
		String jsonStringShort = "{\"event_id\":6514434455237558314}";
		
		VehicleEventMongoDaoTest.INSTANCE.TestJSONObjectParse(jsonStringLong);
		VehicleEventMongoDaoTest.INSTANCE.TestJSONObjectParse(jsonStringShort);
	}
}
