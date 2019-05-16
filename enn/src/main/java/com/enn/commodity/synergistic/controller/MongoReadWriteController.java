//package com.enn.commodity.synergistic.controller;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.LocalDateTime;
//import java.util.Date;
//import java.util.List;
//import java.util.TimeZone;
//
//import org.apache.commons.lang.StringUtils;
//import org.apache.log4j.Logger;
//import org.apache.log4j.spi.LoggerFactory;
//import org.bson.Document;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.BasicQuery;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.mongodb.BasicDBList;
//import com.mongodb.BasicDBObject;
//import com.mongodb.DBObject;
//import com.mongodb.QueryBuilder;
//
//import net.sf.json.JSONObject;
//
//@RestController
//@RequestMapping(value = "/mongo")
//public class MongoReadWriteController {
//
//
//    @Autowired
//    private MongoTemplate mongoTemplate;
//
//    private static Logger logger = Logger.getLogger(BillOfLadingController.class);
//
////    @ApiOperation(value = "write")
////    @ApiImplicitParams({
////            @ApiImplicitParam(name = "collectionName", defaultValue = "col01", value = "collection Name", required = true, dataType = "string", paramType = "query"),
////            @ApiImplicitParam(name = "content", defaultValue = "content for store", value = "content", required = true, dataType = "string", paramType = "query")
////    })
//    @PostMapping(value = "/write", produces = "application/json;charset=UTF-8")
//    public String writeDocument(@RequestParam String collectionName, @RequestParam String content) {
//        logger.info("enter writeDocument, collectionName="+collectionName+", conent="+content);
//
//        Document document = new Document();
//        document.put("userId","u001");
//        document.put("devId","dev001");
//        //
//        document.put("length",content.length());
//        document.put("timestamp",new Date());
//        document.put("desc","my mongo insert testing");
//        document.put("content", content);
//
//        mongoTemplate.insert(document, collectionName);
//
//        JSONObject jsonObj = new JSONObject();
//        jsonObj.put("currentTime", LocalDateTime.now().toString());
//        return jsonObj.toString();
//    }
//
////    @ApiOperation(value = "query")
////    @ApiImplicitParams({
////            @ApiImplicitParam(name = "collectionName",  value = "collectionName", defaultValue = "col01", required = true, dataType = "string", paramType = "query"),
////            @ApiImplicitParam(name = "startTime", defaultValue = "2018-05-01 01:03:09", format="", value = "起始时间 GMT+8",
////                    required = false, dataType = "date", paramType = "query"),
////            @ApiImplicitParam(name = "endTime", defaultValue = "2018-05-26 15:42:18", value = "结束时间 GMT+8",
////                    required = false, dataType = "date", paramType = "query")
////    })
//    @GetMapping(value = "/query", produces = "application/json;charset=UTF-8")
//    public String queryMongo(@RequestParam String collectionName,
//                             @RequestParam(required=false, defaultValue = "") String startTime,
//                             @RequestParam(required=false, defaultValue = "") String endTime) {
////        logger.info("Enter queryMongo collectionName={}, startTime={},endTime={}", collectionName, startTime, endTime);
//        Date  startDate = convertToDate(startTime);
//        Date  endDate = convertToDate(endTime);
//
//        Query query = new Query();
//        if (startDate != null) {
//            if (endDate != null) {
//                Criteria criteria = Criteria.where("timestamp").gte(startDate).lte(endDate);
//                query.addCriteria(criteria);
//            }
//            else {
//                query.addCriteria(Criteria.where("timestamp").gte(startDate));
//            }
//        } else if (endDate != null) {
//            query.addCriteria(Criteria.where("timestamp").lte(endDate));
//        }
//
//        //query.addCriteria(Criteria.where("length").in(listOfLength));
//        query.with(new Sort(Sort.Direction.DESC, "timestamp"));
//
//        long totalCount = mongoTemplate.count(query, Document.class, collectionName);
//        List<Document> documentList = mongoTemplate.find(query, Document.class, collectionName);
//
//        JSONObject jsonObj = new JSONObject();
//        jsonObj.put("currentTime", LocalDateTime.now().toString());
//        jsonObj.put("totalCount", totalCount);
//        jsonObj.put("docList", documentList);
//        return jsonObj.toString();
//    }
//
////    @ApiOperation(value = "QueryBuilder", tags="aaa")
////    @ApiImplicitParams({
////            @ApiImplicitParam(name = "collectionName",  value = "collectionName", defaultValue = "col01", required = true, dataType = "string", paramType = "query"),
////            @ApiImplicitParam(name = "content", defaultValue = "", value = "equal,", required = false, dataType = "string", paramType = "query"),
////            @ApiImplicitParam(name = "desc", defaultValue = "", value = "equal  desc和conent是and的关系", required = false, dataType = "string", paramType = "query")
////    })
//    @GetMapping(value = "/queryByQueryBuilder", produces = "application/json;charset=UTF-8")
//    public String queryMongoByDBObject(@RequestParam String collectionName,
//                             @RequestParam(required=false, defaultValue = "") String content,
//                             @RequestParam(required=false, defaultValue = "") String desc) {
////        logger.info("Enter queryByQueryBuilder collectionName={}, content={}, desc={}", collectionName, content , desc);
//
//        //查询条件content="002"
//        QueryBuilder queryBuilder = new QueryBuilder();
//
////        Pattern pattern = Pattern.compile(content);
////        queryBuilder.put("content").regex(pattern);
//        //db.getCollection('AuditCol').find({"content":"testing"})
//        //queryBuilder.or(new BasicDBObject("desc",desc), new BasicDBObject("content",content));
//        queryBuilder.and(new BasicDBObject("desc",desc), new BasicDBObject("content",content));
//
//        Query query = new BasicQuery(queryBuilder.get());
//
//        query.with(new Sort(Sort.Direction.DESC, "timestamp"));
//
//        long totalCount = mongoTemplate.count(query, Document.class, collectionName);
//
//        //查询时指定返回的需要的字段
//        BasicDBObject fieldsObject = new BasicDBObject();
//        fieldsObject.put("content", 1);
//        fieldsObject.put("desc", 1);
//        query = new BasicQuery(queryBuilder.get(),fieldsObject);
//        List<Document> documentList = mongoTemplate.find(query, Document.class, collectionName);
//
//        JSONObject jsonObj = new JSONObject();
//        jsonObj.put("currentTime", LocalDateTime.now().toString());
//        jsonObj.put("totalCount", totalCount);
//        jsonObj.put("docList", documentList);
//        jsonObj.put("query by QueryBuilder", query.toString());
//        return jsonObj.toString();
//    }
//
////    @ApiOperation(value = "BasicQuery可以直接写query（json格式）")
////    @ApiImplicitParams({
////            @ApiImplicitParam(name = "collectionName",  value = "collectionName", defaultValue = "col01", required = true, dataType = "string", paramType = "query"),
////            @ApiImplicitParam(name = "queryJsonStr", defaultValue = "{ \"length\" : {$lt :210, $gt : 100}}", value = " { \"length\" :{$gt : 100}}", required = false, dataType = "string", paramType = "query")
////    })
//    @GetMapping(value = "/queryByJsonString", produces = "application/json;charset=UTF-8")
//    public String queryByQueryString(@RequestParam String collectionName,
//                                      @RequestParam(required=false, defaultValue = "") String queryJsonStr) {
////        logger.info("Enter queryMongo collectionName={}, queryJsonStr={}", collectionName, queryJsonStr );
//
//        //查询条件content="002"
//        //BasicQuery query1 = new BasicQuery("{ age : { $lt : 40 }, name : 'cat' }");
////        User userTest1 = mongoTemplate.findOne(query1, User.class);
//        Query query = new BasicQuery(queryJsonStr);
//        query.with(new Sort(Sort.Direction.DESC, "timestamp"));
//
//        long totalCount = mongoTemplate.count(query, Document.class, collectionName);
//        List<Document> documentList = mongoTemplate.find(query, Document.class, collectionName);
//
//        JSONObject jsonObj = new JSONObject();
//        jsonObj.put("currentTime", LocalDateTime.now().toString());
//        jsonObj.put("totalCount", totalCount);
//        jsonObj.put("docList", documentList);
//        jsonObj.put("query by BasicDBObject", query);
//        return jsonObj.toString();
//    }
//
////    @ApiOperation(value = "queryByDBObject BasicQuery可以直接写query（json格式）")
////    @ApiImplicitParams({
////            @ApiImplicitParam(name = "collectionName",  value = "collectionName", defaultValue = "col01", required = true, dataType = "string", paramType = "query"),
////            @ApiImplicitParam(name = "content", defaultValue = "", value = "", required = false, dataType = "string", paramType = "query"),
////            @ApiImplicitParam(name = "desc", defaultValue = "", value = "desc和conent是or的关系", required = false, dataType = "string", paramType = "query")
////    })
//    @GetMapping(value = "/queryByDBObject", produces = "application/json;charset=UTF-8")
//    public String queryByQueryBuilder(@RequestParam String collectionName,
//                                       @RequestParam(required=false, defaultValue = "") String content,
//                                       @RequestParam(required=false, defaultValue = "") String desc) {
////        logger.info("Enter queryMongo collectionName={}, content={}, desc={}", collectionName, content , desc);
//        //查询条件content="xxx"
//        BasicDBList basicDBList=new BasicDBList();
//        basicDBList.add(new BasicDBObject("content", content));
//        basicDBList.add(new BasicDBObject("desc", desc));
//
//        DBObject obj = new BasicDBObject();
//        obj.put("$or", basicDBList);
//
//        //查询时指定返回的需要的字段
//        BasicDBObject fieldsObject = new BasicDBObject();
//        fieldsObject.put("length", 1);
//        fieldsObject.put("content", 1);
//        fieldsObject.put("desc", 1);
//
//        Query query = new BasicQuery(obj, fieldsObject);
//        query.with(new Sort(Sort.Direction.DESC, "timestamp"));
//
//
//        long totalCount = mongoTemplate.count(query, Document.class, collectionName);
//        List<Document> documentList = mongoTemplate.find(query, Document.class, collectionName);
//
//        JSONObject jsonObj = new JSONObject();
//        jsonObj.put("currentTime", LocalDateTime.now().toString());
//        jsonObj.put("totalCount", totalCount);
//        jsonObj.put("docList", documentList);
//        jsonObj.put("query by BasicDBObject", obj.toString());
//        return jsonObj.toString();
//    }
//
//    private Date convertToDate(String dateStr) {
//        String pattern = "yyyy-MM-dd HH:mm:ss";
//        SimpleDateFormat format = new SimpleDateFormat(pattern);
//        TimeZone tz = TimeZone.getTimeZone("GMT+8");
//        format.setTimeZone(tz);
//        Date date = null;
//
//        if (StringUtils.isNotBlank(dateStr)) {
//            try {
//                date = format.parse(dateStr.trim());
//            } catch (ParseException ex) {
//                logger.warn("Can't parse dateStr="+dateStr+" with format="+pattern);
//            }
//        }
//
//        return date;
//    }
//
//}
