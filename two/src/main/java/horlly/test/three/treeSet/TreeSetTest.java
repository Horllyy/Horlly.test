package horlly.test.three.treeSet;

import java.util.Comparator;
import java.util.NavigableSet;
import java.util.SortedSet;
import java.util.TreeSet;

public class TreeSetTest {
    public static void main(String[] args) {
        SortedSet<Item> parts=new TreeSet<>();
        parts.add(new Item("Toaster",1234));
        parts.add(new Item("Toasterer",1233));
        parts.add(new Item("Widget",4562));
        parts.add(new Item("Modem",9912));
        parts.add(new Item("Horlly",3701));
        System.out.println(parts);

        NavigableSet<Item> sortByDescription=new TreeSet<>(Comparator.comparing(Item::getDescription));//这个比手动实现一个comparator相比容易多了，也更清晰。
        //这个方法还可以在comparing之后继续.thenComparing来第二阶段比较。
        sortByDescription.addAll(parts);
        System.out.println(sortByDescription);
    }
}
