package collect_01;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
    }
}
