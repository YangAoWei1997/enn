//package com.enn.commodity.synergistic.util;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.MongoDbFactory;
//
//import com.mongodb.client.MongoDatabase;
//import com.mongodb.client.gridfs.GridFSBucket;
//import com.mongodb.client.gridfs.GridFSBuckets;
//
//@Configuration
//public class MongoConfig {
//	
//	@Autowired
//	MongoDbFactory dbFactory ;
//	 
//	 
//	    @Bean
//	    public GridFSBucket getGridFSBucket(){
//	        MongoDatabase db = dbFactory.getDb();
//	        return GridFSBuckets.create(db);
//	    }
//}

