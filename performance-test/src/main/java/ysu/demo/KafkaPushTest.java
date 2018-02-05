package ysu.demo;

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public enum KafkaPushTest {
	INSCANCE;
	
	public void TestPush(String info) {
		 Properties props = new Properties();
		 props.put("bootstrap.servers", "10.45.152.238:8092");
		 props.put("acks", "all");
		 props.put("retries", 0);
		 props.put("batch.size", 16384);
		 props.put("linger.ms", 1);
		 props.put("buffer.memory", 33554432);
		 props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		 props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		 KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);
		 long begin = System.currentTimeMillis();
		 long count = 1000*1000*10;
		 for(int i = 0; i < count; i++) {
			 producer.send(new ProducerRecord<String, String>("my-topic", Integer.toString(i), info));
		 }
		     
		long end = System.currentTimeMillis();
		double per = count/(end - begin)*1000/10000;
		System.out.println("spend : " + (end - begin) + "ms, " + per + "w per sencond");
			
		 producer.close();
	}
	
	public static void main(String args[]) {
		final String jsonStringLong= "{\"event_id\":6514434455237558314,\"event_index\":1,\"bayonet_id\":\"11000001802003223\",\"lane_id\":\"11000001802003223001\",\"event_type\":0,\"pass_time\":\"2018-01-24 10:12:45\",\"vehicle_plate\":\"苏A12345\",\"speed\":121,\"min_speed\":22,\"max_speed\":555,\"vehicle_category\":0,\"vehicle_type\":0,\"vehicle_length\":5,\"vehicle_color\":0,\"plate_color\":0,\"plate_category\":0,\"direction\":\"1\",\"vehicle_state\":\"1\",\"justbreak_rule\":\"\",\"video_path\":\"\",\"vehicle_logo\":0,\"vehicle_sublogo\":\"0\",\"vehicle_model\":0,\"plate_center\":\"{X=6360,Y=2552}\",\"plate_verge\":\"{Left=6072,Top=2464,Right=6648,Bottom=2640}\",\"lane_picture\":\"http://10.45.157.186:8080/group2/M00/B6/E1/Ci2du1pn66OAfrYYAAekqFlLc2c762.jpg\",\"hour\":2018012410,\"paltedim_id\":\"0000\",\"vehicledim_id\":\"0000000000\",\"enum_val\":\"0000000000000000\",\"plate_picture\":\"{X=6360,Y=2552}{Left=6072,Top=2464,Right=6648,Bottom=2640}\"}";
		final String jsonStringShort = "{\"event_id\":6514434455237558314}";
		
		int threadNum = 1;
		int stringType = 1;
		for (int i = 0; i < args.length; i++) {
			threadNum = Integer.parseInt(args[0]);
			stringType = Integer.parseInt(args[1]);
		}
		
		ExecutorService executors = Executors.newFixedThreadPool(threadNum);
		for (int i = 0; i < threadNum; i++) {
			final Integer type = new Integer(stringType);
			executors.submit(new Runnable() {
				public void run() {
					if (type == 1) {
						KafkaPushTest.INSCANCE.TestPush(jsonStringLong);
					}
					else {
						KafkaPushTest.INSCANCE.TestPush(jsonStringShort);
					}
					
				}
			});
		}
		
	}
}
