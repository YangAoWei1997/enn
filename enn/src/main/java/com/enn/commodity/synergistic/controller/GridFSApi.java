//package com.enn.commodity.synergistic.controller;
//
//import com.enn.commodity.synergistic.entity.MyException;
//import com.mongodb.BasicDBObject;
//import com.mongodb.DBObject;
//import com.mongodb.client.MongoIterable;
//import com.mongodb.client.gridfs.GridFSBucket;
//import com.mongodb.client.gridfs.GridFSBuckets;
//import com.mongodb.client.gridfs.GridFSDownloadStream;
//import com.mongodb.client.gridfs.GridFSFindIterable;
//import com.mongodb.client.gridfs.model.GridFSFile;
//import com.mongodb.gridfs.GridFSDBFile;
//
//import net.sf.json.JSONObject;
//
//import org.apache.commons.io.IOUtils;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.MongoDbFactory;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//
//import org.springframework.data.mongodb.gridfs.GridFsOperations;
//import org.springframework.data.mongodb.gridfs.GridFsResource;
//import org.springframework.data.mongodb.gridfs.GridFsTemplate;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Date;
//
//import java.util.UUID;
//
//
//
//@RestController
//@RequestMapping("/img")
//@CrossOrigin
//public class GridFSApi {
//	
//	
//	
//    private static Logger LOGGER = Logger.getLogger(GridFSApi.class);
//  
//    @Autowired
//    private GridFSBucket gridFSBucket;
//    
//    @Autowired
//    private GridFsTemplate gridFsTemplate;
//
//    @Autowired
//    GridFsOperations gridOperations;
//	@Autowired
//	MongoTemplate mongoTemplate ;
//	
//	
//    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    public JSONObject save(@RequestParam(value = "file", required = true) MultipartFile file) {
//
//    	JSONObject responseJson=new JSONObject();
//        LOGGER.info("Saving file..");
//        DBObject metaData = new BasicDBObject();
//        metaData.put("createdDate", new Date());
//
//        String fileName = UUID.randomUUID().toString();
//
//        LOGGER.info("File Name: " + fileName);
//
//        InputStream inputStream = null;
//        try {
//            inputStream = file.getInputStream();
//            gridFsTemplate.store(inputStream, fileName, "image", metaData);
//            LOGGER.info("File saved: " + fileName);
//        } catch (IOException e) {
//            LOGGER.error("IOException: " + e);
//            throw new RuntimeException("System Exception while handling request");
//        }
//        LOGGER.info("File return: " + fileName);
//        responseJson.put("fileName", fileName);
//        return responseJson;
//    }
//
//    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public JSONObject get(@RequestParam("fileName") String fileName) throws IOException {
//    	
//    	//responseObject:方法最终返回的返回
//		JSONObject responseObject=new JSONObject();
//		//判断前端传的json是否为空
//		if(fileName==null) {
//			throw new MyException(80,"前端传值为null");
//		}
//    	
//    	LOGGER.info("filename:"+fileName);
//    	
//        LOGGER.info("Getting file.." + fileName);
//        GridFSFindIterable result = gridFsTemplate.find(new Query().addCriteria(Criteria.where("filename").is(fileName)));
//
//        if (result == null) {
//            LOGGER.info("File not found" + fileName);
//            throw new RuntimeException("No file with name: " + fileName);
//        }
//        
//        GridFSFile gridFSFile = result.first();
//        
//        if (gridFSFile == null) {
//            LOGGER.info("File not found" + fileName);
////            throw new RuntimeException("No file with name: " + fileName);
//            
//            responseObject.put("errno", 90);
//    		responseObject.put("error", "图片不存在");
//    		responseObject.put("data", new JSONObject());
//    		return responseObject;
//        }
//        
//        
//		GridFSDownloadStream gridFSDownloadStream = gridFSBucket.openDownloadStream(gridFSFile.getObjectId());
//        
//        GridFsResource gridFsResource=new GridFsResource(gridFSFile, gridFSDownloadStream); 
////        GridFsResource gridFsResource=convertGridFSFile2Resource(gridFSFile);
//        
////		GridFsResource[] txtFiles = gridOperations.getResources(fileName);
////		if (txtFiles == null) {
////          LOGGER.info("File not found" + fileName);
////          throw new RuntimeException("No file with name: " + fileName);
////      }
////		System.out.println("111:"+txtFiles.toString());
////        LOGGER.info("File found " + fileName);
//        
//        responseObject.put("errno", 0);
//		responseObject.put("error", "success");
//        responseObject.put("data", IOUtils.toByteArray(gridFsResource.getInputStream()));
//        return responseObject;
//    }
//    
//   
//   
//
//    
//
//    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    public JSONObject delete(@RequestParam(value = "fileName", required = true) String fileName) {
//    	
//    	//responseObject:方法最终返回的返回
//		JSONObject responseObject=new JSONObject();
//		//判断前端传的json是否为空
//		if(fileName==null) {
//			throw new MyException(80,"前端传值为null");
//		}
//    	
//    	LOGGER.info("filename:"+fileName);
//    	
//        LOGGER.info("Deleting file.." + fileName);
//        gridFsTemplate.delete(new Query().addCriteria(Criteria.where("filename").is(fileName)));
//        
//        responseObject.put("errno", 0);
//		responseObject.put("error", "success");
//		responseObject.put("data", new JSONObject());
//        LOGGER.info("File deleted " + fileName);
//        
//        return responseObject;
//    }
//}
