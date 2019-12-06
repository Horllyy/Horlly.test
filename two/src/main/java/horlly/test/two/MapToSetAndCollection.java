package horlly.test.two;

import java.util.*;
public class MapToSetAndCollection {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("01", "李同学");
        map.put("02", "张同学");

        Set<String> set = map.keySet();
//        Iterator<String> it = set.iterator();  /////2   这个迭代器要单独用set对象定义一次？？？
//        System.out.println("key集合中的元素：");
//        while (it.hasNext()) {
//            System.out.println(it.next());
//        }
        //上面我注释掉的用iterator遍历集合已经是旧方法了 用下面的for(:)增强式更高效简便。小柳真是聪明呢hhh
        for (String key:set
             ) {
            System.out.println(key);
        }

        Collection<String> coll = map.values();  /////3   为啥 key只能处理成set对象，value只能处理成Colletion对象？？
//        it = coll.iterator();
//        System.out.println("values集合中的元素：");
//        while (it.hasNext()) {
//            System.out.println(it.next());
//        }
        for (String value:coll
        ) {
            System.out.println(value);
        }

        Set<Map.Entry<String,String>> entrySet=map.entrySet();
//        entrySet.forEach()->{
//            System.out.println();
//        };//lambda表达式可以pick一下 比for增强式更高效
        for (Map.Entry<String,String> entry:entrySet
        ) {
            System.out.println("key="+entry.getKey()+"\tvalue="+entry.getValue());
        }
    }

}