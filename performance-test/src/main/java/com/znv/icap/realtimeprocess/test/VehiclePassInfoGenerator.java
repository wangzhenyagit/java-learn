package com.znv.icap.realtimeprocess.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

import com.alibaba.fastjson.JSONObject;

public enum VehiclePassInfoGenerator {
	INSTANCE;

	static LongAdder eventId = new LongAdder();
	static AtomicLong eventIDLong = new AtomicLong(1000*1000*1000);
	
	// 获取一条随机的过车记录
	public VehiclePassInfoBean GetRandomVehiclePassInfo(){
		VehiclePassInfoBean vehiclePassInfoBean = new VehiclePassInfoBean();
		vehiclePassInfoBean.setPlate(GetRandomPlate());
		vehiclePassInfoBean.setPassTime(GetRandomData());
		
		Random random = new Random(System.nanoTime());
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		vehiclePassInfoBean.setEventId(uuid);
		
		//vehiclePassInfoBean.setEventId(Integer.toString((int) eventId.sum()));
		//eventId.increment();
		//vehiclePassInfoBean.setEventId(Integer.toString((int) eventIDLong.decrementAndGet()));
		
		vehiclePassInfoBean.setEventType(random.nextInt(17));
		vehiclePassInfoBean.setLandId(Integer.toString(random.nextInt(100)));
		String picture = "";
		for(int i=0; i<100; i++){
			picture += "abcdlsidjs";
		}
		vehiclePassInfoBean.setLandPicture(picture);
		vehiclePassInfoBean.setPlatePicture("");
		vehiclePassInfoBean.setPlateCategory(random.nextInt(39));
		vehiclePassInfoBean.setPlateColor(random.nextInt(12));
		vehiclePassInfoBean.setVehicleCategory(random.nextInt(2));
		vehiclePassInfoBean.setVehicleType(random.nextInt(3));
		vehiclePassInfoBean.setVehicleLength(2 + random.nextInt(3));
		vehiclePassInfoBean.setVehicleColor(random.nextInt(12));
		vehiclePassInfoBean.setSpeed(50 + random.nextInt(300));
		
		return vehiclePassInfoBean;
	}
	
	public String GetRandomPlate() {
		char[] provinceBuffer = {'京','津','沪','渝','冀','豫','云','辽','黑','湘','皖',
				'鲁','新','苏','浙','赣','鄂','桂','甘','晋','蒙','陕','吉','闽','贵','粤','青','藏','川','宁','琼'};
		Random random = new Random();
		StringBuilder plate = new StringBuilder();
		plate.append(provinceBuffer[random.nextInt(provinceBuffer.length)]);
		plate.append((char) (random.nextInt(24) + 65));
		for(int i=0; i<5; i++){
			if(random.nextBoolean()){
				plate.append((char) (random.nextInt(24) + 65));
			}
			else{
				plate.append(random.nextInt(10));
			}
		}
		return plate.toString();
	}
	
	private String GetRandomData(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		 try {
			Date start = format.parse("2017-09-01 00:00:00");
			Date end = format.parse("2017-12-01 00:00:00");
			
			long random = start.getTime() + (long)(Math.random() * (end.getTime() - start.getTime()));
			Date randomData = new Date(random); 
			return format.format(randomData);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
         
		return null;
	}

	List<String> GetDrivingRecordLand(){
		return null;
	}
	
	// 获取一条完整的行车记录一个车经过10个卡口
	List<VehiclePassInfoBean> GetDrivingRecord(){
		return null;
	}
	
	// 获取一条跟车记录
	List<VehiclePassInfoBean> GetTrailingRecord(List<VehiclePassInfoBean> drivingRecord){
		return null;
	}
	
	// 获取一组记录，包括过车和跟车
	List<VehiclePassInfoBean> GetTrailingRecord(){
		return null;
	}
	
	// 获取bulk的待发送数据，可以直接发送给ES
//	StringBuilder GetBulkData(){
//		VehiclePassInfoGenerator vehiclePassInfoGenerator = new VehiclePassInfoGenerator();	
//		StringBuilder bulkData = new StringBuilder();
//		int countPerBulk = TestConfigEnum.instance.getBulkNum();
//		for(int i=0; i<countPerBulk; i++){
//			VehiclePassInfoBean vehiclePassInfoBean = vehiclePassInfoGenerator.GetRandomVehiclePassInfo();
//			bulkData.append(vehiclePassInfoBean.toString());
//			bulkData.append("\n");
//		}
//		return bulkData;
//	}
	
	static public void BulkInserToES(String bulkData){
//        HttpClient httpClient = HttpClients.createDefault();  
//        //HttpPost httpPost = new HttpPost("http://10.45.157.55:9200/index1/passinfo/_bulk"); 
//        HttpPost httpPost = new HttpPost("http://10.72.76.140:9200/myindex/passinfo/_bulk"); 
//        StringEntity entity = new StringEntity(bulkData.toString(), "utf-8");
//		httpPost.setEntity(entity);
//		try {
//			CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpPost);
//		    try {  
//		        HttpEntity httpEntity = closeableHttpResponse.getEntity();  
//		        if (httpEntity != null) {  
//		            JSONObject jsonObject = JSONObject.parseObject(EntityUtils.toString(httpEntity, "UTF-8"));
////		            System.out.print("error : " + jsonObject.getString("errors") + 
////		            		", success num : " +  jsonObject.getJSONArray("items").size() + "\r\n");
//		        }  
//		    } finally {  
//		    	closeableHttpResponse.close();  
//		    } 
//		} catch (ClientProtocolException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {  
//		    // 关闭连接,释放资源    
//		    try {  
//		    	httpClient.close();  
//		    } catch (IOException e) {  
//		        e.printStackTrace();  
//		    }  
//		} 
}
	
	public static void main(String[] args) {
//		System.out.println("Insert sum ");
//		VehiclePassInfoGenerator vehiclePassInfoGenerator = new VehiclePassInfoGenerator();
//		System.out.println(vehiclePassInfoGenerator.GetBulkData().toString().length());
		//System.out.println(vehiclePassInfoGenerator.GetBulkData().toString());
//		long sum = 0;
//		LongAdder count = new LongAdder();
//		final long testBeginTime = System.currentTimeMillis();
//		while(true){
//			vehiclePassInfoGenerator.GetBulkData().toString();
//			count.increment();
//			long costTime = (System.currentTimeMillis() - testBeginTime);
//			System.out.println("avg per second : " + 1.0*count.sum()/costTime*1000);
//		}
			
	}
}

