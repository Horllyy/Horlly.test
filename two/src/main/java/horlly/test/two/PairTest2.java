package horlly.test.two;

import java.time.*;

public class PairTest2 {
    public static void main(String[] args) {
        LocalDate[] birthdays = {
                LocalDate.of(1906, 12, 9),
                LocalDate.of(1805, 12, 10),
                LocalDate.of(1903, 12, 3),
                LocalDate.of(1910, 6, 22),
        };
        Pair<LocalDate> mm = ArrayAlg.minmax(birthdays);
        System.out.println("min=" + mm.getFirst());
        System.out.println("max=" + mm.getSecond());
    }
}

    class ArrayAlg {
        public static <T extends Comparable> Pair<T> minmax(T[] a) {
            if (a == null || a.length == 0) return null;
            T min = a[0];
            T max = a[0];
            for (int i = 1; i < a.length; i++) {
                if (min.compareTo(a[i]) > 0) min = a[i];
                if (max.compareTo(a[i]) < 0) max = a[i];
            }
            return new Pair<>(min, max);
        }
    }
    class Pair<T> {
    private T first;
    private T second;

    public Pair(){first=null;second=null;}
    public Pair(T first,T second){
        this.first=first;this.second=second;
    }

    public T getFirst(){ return first;}
    public T getSecond(){return second;}

    public void setFirst(T newValue){first=newValue;}
    public void setSecond(T newValue){second=newValue;}
}

//T只是一个代号 你完全可以把它理解成object 之后你塞进什么都行。太妙了这个想法 虽然目前对我来讲还略略有些复杂 以前没接触过
//阅读真的无论在哪个领域都是最伟大最有效的快速提升自己的方式啊 站在巨人的肩膀上体会这个世界最真实的模样。爱了 真的爱了。