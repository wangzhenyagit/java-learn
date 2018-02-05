package ysu.demo;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;

public enum MongoBatchSaveTest 
{
	INSTANCE;
	
	public void TestBatchSaveMongo(String jsonString, int batchSize) {
		MongoClient mongoClient = new MongoClient("10.45.152.238", 37017);
        MongoCollection<Document> collection = mongoClient.getDatabase("wzy").getCollection("col");
        
        
        long begin = System.currentTimeMillis();
        long sum = 0;
        for(int i=0; i<1000; i++) {
        	// 注意这里有坑，id自动生成的化，应该是在构造Document的时候
        	// 如果在外面一直用这document的list，会报id重复的错
            List<Document> documents = new LinkedList<Document>();
            for(int j=0; j<batchSize; j++) {
            	documents.add(new Document().parse(jsonString));
            }
            
            
        	collection.insertMany(documents);
        	sum += batchSize;
        	
    		long end = System.currentTimeMillis();
    		double per = sum/(end - begin)*1000;
    		System.out.println("test count : " + i + ", spend : " + (end - begin) + "ms, " + per + "per sencond");
        }
        
	}
	

	public static void main(String args[]) {
		final String jsonStringLong= "{\"event_id\":6514434455237558314,\"event_index\":1,\"bayonet_id\":\"11000001802003223\",\"lane_id\":\"11000001802003223001\",\"event_type\":0,\"pass_time\":\"2018-01-24 10:12:45\",\"vehicle_plate\":\"苏A12345\",\"speed\":121,\"min_speed\":22,\"max_speed\":555,\"vehicle_category\":0,\"vehicle_type\":0,\"vehicle_length\":5,\"vehicle_color\":0,\"plate_color\":0,\"plate_category\":0,\"direction\":\"1\",\"vehicle_state\":\"1\",\"justbreak_rule\":\"\",\"video_path\":\"\",\"vehicle_logo\":0,\"vehicle_sublogo\":\"0\",\"vehicle_model\":0,\"plate_center\":\"{X=6360,Y=2552}\",\"plate_verge\":\"{Left=6072,Top=2464,Right=6648,Bottom=2640}\",\"lane_picture\":\"http://10.45.157.186:8080/group2/M00/B6/E1/Ci2du1pn66OAfrYYAAekqFlLc2c762.jpg\",\"hour\":2018012410,\"paltedim_id\":\"0000\",\"vehicledim_id\":\"0000000000\",\"enum_val\":\"0000000000000000\",\"plate_picture\":\"{X=6360,Y=2552}{Left=6072,Top=2464,Right=6648,Bottom=2640}\"}";
		final String jsonStringShort = "{\"event_id\":6514434455237558314}";
		
		int threadNum = 1;
		int batchSize = 1000;
		for (int i = 0; i < args.length; i++) {
			threadNum = Integer.parseInt(args[0]);
			batchSize = Integer.parseInt(args[1]);
		}
		
		ExecutorService executors = Executors.newFixedThreadPool(threadNum);
		for (int i = 0; i < threadNum; i++) {
			final Integer batch = new Integer(batchSize);
			executors.submit(new Runnable() {
				public void run() {
					MongoBatchSaveTest.INSTANCE.TestBatchSaveMongo(jsonStringLong, batch);
				}
			});
		}
	}
}
