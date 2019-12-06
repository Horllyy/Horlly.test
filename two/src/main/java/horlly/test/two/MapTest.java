package horlly.test.two;

import java.util.*;

public class MapTest {
    public static void main(String[] args) {
        Map<String,Employee> staff=new HashMap<>();
        staff.put("144-25-5464", new Employee("Amy Lee"));
        staff.put("567-24-2546", new Employee("Harry Hacker"));
        staff.put("157-62-7935", new Employee("Gary Cooper"));
        staff.put("456-62-5527", new Employee("Francesca Cruz"));

        Map<String,Employee> staff2=new LinkedHashMap<>();
        staff2.put("144-25-5464", new Employee("Amy Lee"));
        staff2.put("567-24-2546", new Employee("Harry Hacker"));
        staff2.put("157-62-7935", new Employee("Gary Cooper"));
        staff2.put("456-62-5527", new Employee("Francesca Cruz"));

        //打印所有成员
        System.out.println(staff);
        //删除某一成员
        staff.remove("567-24-2546");
        //替代一个成员
        staff.put("456-62-5527", new Employee("Francesca Miller"));
        //查看一个成员
        System.out.println(staff.get("157-62-7935"));
        //遍历映射
        staff.forEach((k,v)->System.out.println("key="+k+",value="+v));
        System.out.println("下面是LinkedHashMap的打印：");
        staff2.forEach((k,v)->System.out.println("key="+k+",value="+v));
    }
}

