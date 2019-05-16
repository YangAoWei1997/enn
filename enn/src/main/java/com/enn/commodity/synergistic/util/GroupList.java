package com.enn.commodity.synergistic.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.enn.commodity.synergistic.entity.User;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GroupList {
	
	 /**
     * 创建比较器
     */
//    public static <T> List<List<T>> dividerList(List<T> list,Comparator<? super T> comparator) {
//        List<List<T>> lists = new ArrayList<>();
//        
//        for (int i = 0; i < list.size(); i++) {
//            boolean isContain = false;
//            for (int j = 0; j < lists.size(); j++) {
//                if (lists.get(j).size() == 0||comparator.compare(lists.get(j).get(0),list.get(i)) == 0) {
//                    lists.get(j).add(list.get(i));
//                    isContain = true;
//                    break;
//                }
//            }
//            if (!isContain) {
//                List<T> newList = new ArrayList<>();
//                newList.add(list.get(i));
//                lists.add(newList);
//            }
//        }
//        return lists;
//    }
//    
//    public static void main(String[] args) {
//        List<User> list = new ArrayList<User>();
////实在不会起名字，用字母代替吧
//        list.add(new User(17,"aa"));
//        list.add(new User(15,"bb"));
//        list.add(new User(16,"cc"));
//        list.add(new User(15,"dd"));
//        list.add(new User(16,"ee"));
//        list.add(new User(17,"ff"));
//        List<List<User>> list2 = dividerList(list, new Comparator<User>() {
//
//            @Override
//            public int compare(User o1, User o2) {
//            // 按年龄分组,这里注意一点，返回的值为0，就会认为这两个Studeng是一组的，返回其他值，则认为不是，所以下面的-1可以替换为任意非0数字
//
////            return o1.getAge == o2.getAge ? 0:-1;
//            //也可以按照姓名分组，返回结果如下，因为是比较两个值是否相等，所以先后是没有区别的
//           return o1.getUserId().compareTo(o1.getUserId());
//            }
//        });
//
//       for(List<User> stList: list2){
//          stList.forEach(a-> System.out.printIn(a.getUserId()+":"+a.getUserIndex()));
//
//　　　　}
//
//
//    }
	
	
	public static void main(String[] args) {
		
//		JSONArray list = new JSONArray();
//		JSONObject obj=new JSONObject();
//		obj.put("11", "aa");
//		JSONObject obj2=new JSONObject();
//		obj2.put("11", "bb");
//		JSONObject obj3=new JSONObject();
//		obj3.put("33", "cc");
//		JSONObject obj4=new JSONObject();
//		obj4.put("44", "dd");
//		 list.add(obj);
//		 list.add(obj2);
//		 list.add(obj3);
//		 list.add(obj4);
//		Map<String, JSONObject> groupBy = list.stream().collect(Collectors.groupingBy(obj::getString("11"));
//		System.err.println("groupBy:"+groupBy.toString());
	}

}
