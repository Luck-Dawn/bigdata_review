package collect_01;

import java.util.*;

/**
 * @Author：Dawn
 * @Date：2021/2/20 23:46
 * @Desc： map的3种遍历
 */
public class MapTravel {
    public static void main(String[] args) {
        Map<String,Integer> map = new HashMap<>(16);

        map.put("AA",11);
        map.put("BB",55);
        map.put("CC",66);
        map.put("DD",44);
        map.put("EE",88);

        //方式一：获取所有的key 组成的 Set
        Set<String> allKey = map.keySet();
        for (String key : allKey) {
            Integer value = map.get(key);
            System.out.println("key : " + key + "=> value :" + value);
        }

        //方式二：获取所有的value
        Collection<Integer> values = map.values();
        for (Integer value : values) {
            System.out.println(value);
        }

        Iterator<Integer> iterator = values.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        //方式三：获取 Map 中所有的 Entry（Entry是Map的内部类，一个Entry对应着一个key和一个value）组成的 Set。
        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
        for (Map.Entry<String, Integer> eachEntry : entrySet) {
            String key = eachEntry.getKey();
            Integer value = eachEntry.getValue();
            System.out.println("key : " + key + "=> value :" + value);
        }
    }
}
