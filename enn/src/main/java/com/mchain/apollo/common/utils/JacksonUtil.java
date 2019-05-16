package com.mchain.apollo.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * json字符与对像转换
 *
 * @author Alex Meng
 */
public final class JacksonUtil {

    private static volatile JacksonUtil _instance;
    private static ObjectMapper objectMapper = null;

    private JacksonUtil() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
    }

    public static JacksonUtil getInstance() {
        if(_instance == null) {
            synchronized (JacksonUtil.class) {
                if (_instance == null) {
                    _instance = new JacksonUtil();
                }
            }
        }
        return _instance;
    }

    public void destroy() {
        _instance = null;

    }

    /**
     * javaBean、列表数组转换为json字符串
     */
    public String toJSON(Object obj) throws Exception {
        return objectMapper.writeValueAsString(obj);
    }

    /**
     * javaBean、列表数组转换为json字符串,忽略空值
     */
    public String obj2jsonIgnoreNull(Object obj) throws Exception {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return objectMapper.writeValueAsString(obj);
    }

    /**
     * json 转JavaBean
     */

    public <T> T json2pojo(String jsonString, Class<T> clazz) throws Exception {
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);  
        return objectMapper.readValue(jsonString, clazz);
    }

    /**
     * json字符串转换为map
     */
    public <T> Map<String, Object> json2map(String jsonString) throws Exception {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return objectMapper.readValue(jsonString, Map.class);
    }

    /**
     * json字符串转换为map
     */
    public <T> Map<String, T> json2map(String jsonString, Class<T> clazz) throws Exception {
        Map<String, Map<String, Object>> map = objectMapper.readValue(jsonString, new TypeReference<Map<String, T>>() {
        });
        Map<String, T> result = new HashMap<String, T>();
        for (Map.Entry<String, Map<String, Object>> entry : map.entrySet()) {
            result.put(entry.getKey(), map2pojo(entry.getValue(), clazz));
        }
        return result;
    }

    /**
     * 深度转换json成map
     *
     * @param json
     * @return
     */
    public Map<String, Object> json2mapDeeply(String json) throws Exception {
        return json2MapRecursion(json);
    }

    /**
     * 把json解析成list，如果list内部的元素存在jsonString，继续解析
     *
     * @param json
     * @return
     * @throws Exception
     */
    private List<Object> json2ListRecursion(String json) throws Exception {
        if (json == null) {
            return null;
        }

        List<Object> list = objectMapper.readValue(json, List.class);

        for (Object obj : list) {
            if (obj != null && obj instanceof String) {
                String str = (String) obj;
                if (str.startsWith("[")) {
                    obj = json2ListRecursion(str);
                } else if (obj.toString().startsWith("{")) {
                    obj = json2MapRecursion(str);
                }
            }
        }

        return list;
    }

    /**
     * 把json解析成map，如果map内部的value存在jsonString，继续解析
     *
     * @param json
     * @return
     * @throws Exception
     */
    private Map<String, Object> json2MapRecursion(String json) throws Exception {
        if (json == null) {
            return null;
        }

        Map<String, Object> map = objectMapper.readValue(json, Map.class);

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Object obj = entry.getValue();
            if (obj != null && obj instanceof String) {
                String str = ((String) obj);

                if (str.startsWith("[")) {
                    List<?> list = json2ListRecursion(str);
                    map.put(entry.getKey(), list);
                } else if (str.startsWith("{")) {
                    Map<String, Object> mapRecursion = json2MapRecursion(str);
                    map.put(entry.getKey(), mapRecursion);
                }
            }
        }

        return map;
    }

    /**
     * 与javaBean json数组字符串转换为列表
     */
    public <T> List<T> json2list(String jsonArrayStr, Class<T> clazz) throws Exception {

        JavaType javaType = getCollectionType(ArrayList.class, clazz);
        List<T> lst = (List<T>) objectMapper.readValue(jsonArrayStr, javaType);
        return lst;
    }


    /**
     * 获取泛型的Collection Type
     *
     * @param collectionClass 泛型的Collection
     * @param elementClasses  元素类
     * @return JavaType Java类型
     * @since 1.0
     */
    public JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }


    /**
     * map  转JavaBean
     */
    public <T> T map2pojo(Map map, Class<T> clazz) {
        return objectMapper.convertValue(map, clazz);
    }

    /**
     * map 转json
     *
     * @param map
     * @return
     */
    public String mapToJson(Map map) {
        try {
            return objectMapper.writeValueAsString(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * map  转JavaBean
     */
    public <T> T obj2pojo(Object obj, Class<T> clazz) {
        return objectMapper.convertValue(obj, clazz);
    }

    /**
     * 使用泛型方法，把json字符串转换为相应的JavaBean对象。
     * (1)转换为普通JavaBean：readValue(json,Student.class)
     * (2)转换为List,如List<Student>,将第二个参数传递为Student
     * [].class.然后使用Arrays.asList();方法把得到的数组转换为特定类型的List
     *
     * @param jsonStr
     * @param valueType
     * @return
     */
    public <T> T readValue(String jsonStr, Class<T> valueType) {
        try {
            return objectMapper.readValue(jsonStr, valueType);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * json数组转List
     *
     * @param jsonStr
     * @param valueTypeRef
     * @return
     */
    public <T> T readValue(String jsonStr, TypeReference<T> valueTypeRef) {
        try {
            return objectMapper.readValue(jsonStr, valueTypeRef);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public String extractJsonValue(String jsonStr, String key) {
        JSONObject jsonObject = new JSONObject().fromObject(jsonStr);

        if (!jsonObject.has(key) || jsonObject.getString(key) == null)
            return "";
        else
            return jsonObject.getString(key);
    }

    public <T> T extractJsonArray(String jsonStr, String key, Class<T> valueType) {
        JSONObject jsonObject = new JSONObject().fromObject(jsonStr);

        if (!jsonObject.has(key) || jsonObject.getString(key) == null)
            return null;
        else {
            String value = jsonObject.getString(key).toString();
            if (value == null || value.equals(""))
                value = "[]";
            return readValue("{\"" + key + "\":" + value + "}", valueType);
        }
    }

}