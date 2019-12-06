package horlly.test.two;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class SetTest {
    public static void main(String[] args) {
        Set<String> words = new HashSet<>();
        long totalTime = 0;

        try (Scanner in = new Scanner(System.in)){
            while (in.hasNext()) {
                String word = in.next();
                long callTime = System.currentTimeMillis();
                words.add(word);
                callTime = System.currentTimeMillis() - callTime;
                totalTime += callTime;
            }
        }
        Iterator<String> iter=words.iterator();
        for(int i=1;i<=20&&iter.hasNext();i++)
        {
            System.out.println(iter.next());
//            System.out.println(iter.next().hashCode());//这行会引发错误的 那么如何输出当前这个元素的hashcode呢。
        }
        System.out.println("...");
        System.out.println(words.size()+"distinct words."+totalTime+"milliseconds."+words.hashCode());

    }
}
//结束输入台结束的语句竟然是linux系统的结束语句，不是win的，巨坑。醉了。Ctrl+D。