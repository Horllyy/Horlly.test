package horlly.test.two;

import java.util.*;

//enum Weekday {
//    MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY,SUNDAY
//}
public class EnumSetExample {

    enum Weekday {
        MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY,SUNDAY
    }
    //放在里面一样ok

    public static void main(String[] args) {
        //创建一个包含指定所有元素的枚举集元素类型
        EnumSet<Weekday> always = EnumSet.allOf(Weekday.class);
        System.out.println(always);
        //创建具有指定元素类型的空枚举集
        EnumSet<Weekday> never = EnumSet.noneOf(Weekday.class);
        System.out.println(never);
        //创建一个最初包含所有元素的枚举集由两个指定端点定义的范围。 返回的集合将包含端点本身
        EnumSet<Weekday> range = EnumSet.range(Weekday.MONDAY, Weekday.FRIDAY);
        System.out.println(range);
        //创建最初包含指定元素的枚举集。
        EnumSet<Weekday> mwf = EnumSet.of(Weekday.MONDAY, Weekday.WEDNESDAY, Weekday.FRIDAY);
        System.out.println(mwf);

        EnumMap<Weekday,Employee> personInCharge=new EnumMap<>(Weekday.class);
        personInCharge.put(Weekday.FRIDAY,new Employee("Alice"));
        personInCharge.put(Weekday.MONDAY,new Employee("Tony"));

        Set<EnumMap.Entry<Weekday,Employee>> entrySet=personInCharge.entrySet();
        for (EnumMap.Entry<Weekday,Employee> entry:entrySet)
        {
            System.out.println("key="+entry.getKey()+"\tvalue="+entry.getValue());
        }

    }

}
