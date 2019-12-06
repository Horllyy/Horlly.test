package horlly.test.three.LinkedHashMap_LRU;

import java.util.Iterator;
import java.util.Map;

public class UseLinkedHashMapCacheTest {

    public static void main(String[]args){
        UseLinkedHashMapCache<Integer,String> cache = new UseLinkedHashMapCache<>(4);
        cache.put(1, "one");
        cache.put(2, "two");
        cache.put(3, "three");
        cache.put(4, "four");
        cache.put(2, "two");
        cache.put(3, "three");

        Iterator<Map.Entry<Integer,String>> it = cache.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<Integer, String> entry = it.next();
            Integer key = entry.getKey();
            System.out.print("Key:\t"+key);
            String Value = entry.getValue();  //这个无需打印...
            System.out.println();
        }
    }

}
