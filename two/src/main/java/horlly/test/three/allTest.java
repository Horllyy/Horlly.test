package horlly.test.three;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

public class allTest {

    public static void main( String[] args ) {
        // TODO Auto-generated method stub

//         LinkedListDemo();
//         arrayListDemo();
//         HashSetDemo();
//         TreeSetDemo();
//         LinkedHashSetDemo();
//         HashTableDemo();
//         LinkedHashMapDemo();
         HashMapDemo();
//        TreeMapDemo();
//        printChange(5, 1.11);
    }

    /**
     * 根据应收金额 dcost, 和dCash实收现金计算找零多少
     * @param dCash
     *            现金
     * @param dCost
     *            金额
     * 输出找零金额
     */
    public static void printChange( double dCash , double dCost ) {
        if (dCash < 0 || dCost < 0 || dCash < dCost) {
            return;
        }
        DecimalFormat format = new DecimalFormat("0.00");
        // System.out.println(format.format(dCash));
        // System.out.println(format.format(dCost));//用于对double类型数据的数据小数点后几位指定
        /*
         * 对于金额使用BigDecimal处理，可以截尾
         * 在需要精确答案的地方,要避免使用float和double;对于货币计算,使用int,long或BigDecimal会更好.
         */
        BigDecimal bigDecimal = new BigDecimal(format.format(dCash));

        BigDecimal decimal = bigDecimal
                .subtract(new BigDecimal(format
                        .format(dCost)));//需要找零的钱
        // System.out.println(decimal.doubleValue());
        double charges[] = { 100, 50, 20, 10, 5, 2, 1, 0.5, 0.2, 0.1, 0.05,
                0.02, 0.01 };

        double money = decimal.doubleValue();// 获取找零的金额，
        // System.out.println(money);
        int chargesNum[] = new int[charges.length];
        for (int i = 0; i < charges.length; i++)
            chargesNum[i] = 0;// 记录每一个面额的纸币有多少长
        int i = 0;
        while (money > 0) {

            while (charges[i] > money)
                i++;
            money -= charges[i];
            money = Double.valueOf(format.format(money)).doubleValue();// 对减去一个浮点型数字后进行小数点格式化，格式化带两个小数点的实数。
            System.out.println(money);
            chargesNum[i] += 1;

        }
        // 把找零的数字输出
        System.out.print("找零金额：" + decimal.doubleValue() + " ：");
        for (int j = 0; j < chargesNum.length; j++) {
            if (chargesNum[j] > 0) {
                if (j < 7) {
                    System.out.print(chargesNum[j] + "张" + charges[j] + "元  ");
                } else if (j < 10) {
                    System.out.print(chargesNum[j] + "张" + charges[j] + "角  ");
                } else {
                    System.out.print(chargesNum[j] + "张" + charges[j] + "分  ");
                }

            }
        }
    }

    /**
     * 关于map接口的实例
     */

    /**
     * 无序、基于哈希表的Map接口实现，顺序结构，键和值都不容许是null，值可重复，线程安全，同步， 输出： 无序的
     */
    public static void HashTableDemo() {
        Hashtable<Integer, String> map = new Hashtable();
        map.put(1, "北京");
        map.put(2, "武汉");
        map.put(3, "天津");
        map.put(6, "山西");
        map.put(7, "北京");
        // map.put(null, "山西sdf");// 键不能为null
        // map.put(4, null);// 值不允许为null
        // map.put(null, null);
        Set<Integer> keys = map.keySet(); // 返回是一个key的set集合
        Iterator<Integer> it = keys.iterator(); // 迭代器：将keys中元素赋值给it变量
        // 遍历出来结果是无序的
        while (it.hasNext()) // //hasNext:检查序列中是否还有元素
        {
            Integer obj = it.next(); // 使用next()获得序列中的下一个元素，即获取键
            System.out.println(obj + "=" + map.get(obj));// get：获取某一个键对应的值
        }

    }

    /**
     * 离散结构，基于插入顺序的有序，基于哈希表的Map接口实现，键和值都允许为Null，key不可以重复，
     * key只能有一个null，如果再次插入一个key为null的键值对，将覆盖原来的key为null的键值对；
     * 同键不同值会覆盖原来的值。
     * 不同键值可以重复，线程不安全（不同步）
     */
    public static void LinkedHashMapDemo() {
        // LinkeHasdMap：离散结构，有序，基于哈希表的Map接口实现，键和值都允许为Null，值可以重复，线程不安全（不同步）
        LinkedHashMap<Integer, String> map = new LinkedHashMap<Integer, String>();
        map.put(1, "北京");
        map.put(2, "武汉");
        map.put(3, "天津");
        map.put(15, "sadf");

        map.put(6, "山西");
        map.put(5, "sdf");
        map.put(null, "山西");
        map.put(4, null);// 键允许为null
        map.put(null, null);// 键值为null,替换前面的null，山西
        Set<Integer> keys = map.keySet(); // 返回是一个set集合
        Iterator<Integer> it = keys.iterator(); // 迭代器：将keys中元素赋值给it变量<br>　　　　　//遍历出来结果是有序的
        while (it.hasNext()) // //hasNext:检查序列中是否还有元素
        {
            Object obj = it.next(); // 使用next()获得序列中的下一个元素，即获取键
            System.out.println(obj + "=" + map.get(obj));// get：获取某一个键对应的值

        }

    }

    /**
     * 基于数组和链表实现，无序，键和值可以为null，但是key必须是唯一；这也成为hashSet构建的依据；依据key的hashcode来查找数组中的存储位置，每个数组
     * 元素只是一个Map.Entry元素，如果有多个map元素的hashcode一致，那么当前数组元素位置的Map.Entry
     * 的所有元素构成一个单链表。
     *  key只能有一个null，如果再次插入一个key为null的键值对，将覆盖原来的key为null的键值对；
     *
     */
    public static void HashMapDemo() {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "北京");
        map.put(2, "武汉");
        map.put(23, "sdfsd");
        map.put(5, "asdf");
        map.put(3, "天津");
        map.put(6, "山西");
        map.put(null, "山西");
        map.put(4, null);// 键允许为null
        map.put(null, null);// 键值为null,替换前面的null，山西

//        Set<Integer> keys = map.keySet(); // 返回是一个set集合
//        Iterator<Integer> it = keys.iterator(); // 迭代器：将keys中元素赋值给it变量
//        // 遍历出来结果是无序的</span>
//        while (it.hasNext()) // //hasNext:检查序列中是否还有元素
//        {
//            Object obj = it.next(); // 使用next()获得序列中的下一个元素，即获取键
//            System.out.println(obj + "=" + map.get(obj));// get：获取某一个键对应的值
//        }

        System.out.println("以下是forEach输出：");
        for (Map.Entry<Integer,String> entry:map.entrySet()
             ) {
            System.out.println("key="+entry.getKey()+",value="+entry.getValue());
        }
        System.out.println("以下是forEach+lambda输出：");
        map.forEach((k,v)->System.out.println("key="+k+",value="+v));
    }

    /**
     * 基于红黑树（Red-Back tree）的NavigableMap实现，该映射根据其键的自然顺序进行排序，
     * 或者根据创建映射时提供的Comparator进行排序，具体取决于使用的构造方法
     *
     * 特点：
     *
     * 　　键不允许为null，并且数据类型一致，值允许是null
     *
     * 　　实现自然排序
     *
     * 　　线程不安全（不同步）
     */
    public static void TreeMapDemo() {
        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(1, "北京");
        map.put(2, "武汉");
        map.put(3, "天津");
        // map.put(null, "天津"); key不能为null
        map.put(6, "东");
        map.put(6, "西");
        map.put(6, null);// value可以为null
        map.put(6, "山西");//相同的key多次put会被后来的值覆盖掉
//        Set keys = map.keySet(); // 返回是一个set集合
//        Iterator it = keys.iterator(); // 迭代器：将keys中元素赋值给it变量
//        // 遍历出来结果是按照输入时顺序进行排序</span>
//        while (it.hasNext()) // //hasNext:检查序列中是否还有元素
//        {
//            Object obj = it.next(); // 使用next()获得序列中的下一个元素，即获取键
//            System.out.println(obj + "=" + map.get(obj));// get：获取某一个键对应的值
//        }

//        map.forEach((k,v)->System.out.println("key="+k+",value="+v));//forEach+lambda表达式打印效率高

        for(Map.Entry<Integer,String> entry:map.entrySet()){
            System.out.println("key:"+entry.getKey()+",:value:"+entry.getValue());
        }//good 小柳真棒！
    }

    /**
     * 关于set的所有实现类，只有treeset不可以插入null的元素
     */
    /**
     * 通过链表实现的set，无重复元素，允许Null值，有序，线程不安全的 输出：按输入顺序输出结果
     */
    public static void LinkedHashSetDemo() {
        LinkedHashSet<String> set = new LinkedHashSet();// 无序，链表结果
        set.add("chain");
        set.add("vincent");
        set.add("ddd");
        set.add("lcj");
        set.add("aw");
        set.add(null); // 允许添加null值
        set.add("beijing");
        Iterator it = set.iterator(); // 遍历结果是有序：即按照写入的顺序排序
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    /***
     * TreeSet无重复元素，不允许null值，默认依据自然排序，线程不安全的（不同步）。 输出：按照字母排序
     */
    public static void TreeSetDemo() {
//        Comparator<? super String> comparator = null;
//        new TreeSet<String>(comparator);// 也可以指定一个比较器用于比较每个元素
        TreeSet<String> set = new TreeSet();// TreeSet:有序排序
        set.add("chain");
        set.add("chain");//同样 随你加 当你白写
        set.add("lcj");
        set.add("aw");
        set.add("haha");
        set.add("beijing");
        set.add("beijjing");
//         set.add(null);//输入null会报错
//        Iterator<String> it = set.iterator();
//        while (it.hasNext()) // hasNext:检查序列中是否还有元素
//        {
//            System.out.println(it.next()); // 使用next()获得序列中的下一个元素
//        }
        for (String item:set
             ) {
            System.out.println(item);
        }

    }

    /**
     * 顺序结构，基于hashmap实现的hashset，set中元素作为hashmap中的key存在。
     * 无重复元素，允许null值，无序的，线程不安全。只能通过Iterator遍历元素 输出：元素无顺序
     */
    public static void HashSetDemo() {
        HashSet<String> set = new HashSet<>();
        set.add("200");
        set.add("lcj");
        set.add("zz");
        set.add("aa");
        set.add("aa");//检验得 你随便加 我不报错但是你加不进去我当你白写
        set.add("dd");
        set.add("dd");//检验得 你随便加 我不报错但是你加不进去我当你白写
        set.add("dad");
        set.add("vdd");
        set.add("werwe");
        set.add("wrw"); // 不可添加重复元素：
        set.add(null);// 可添加null值
        // 无法使用for循环遍历set集合
        // for (int i = 0 ;i<set.size();i++)
        // {
        // System.out.println(set.);//无法获取列表下标
        // }

//        Iterator it = set.iterator(); // Iterator迭代器
//        while (it.hasNext()) // hasNext:检查序列中是否还有元素
//        {
//            System.out.println(it.next());// 使用next()获得序列中的下一个元素
//        }//使用迭代器也可以很好输出噢

//        System.out.println(set);//直接输出也可以

        for (String item:set
             ) {
            System.out.println(item);
        }//foreach语句也可以完美输出噢 个人感觉比迭代器好用方便


    }

    /**
     * 关于list都是可以存在null的
     */
    /**
     * 数据可以为null,基于数组实现的动态数组，默认情况下动态数据的增长因子是0.5。 默认变为原来的1.5大小。
     * 顺序结构，容量动态扩充，线程不安全。 输出：基于加入元素add方法调用顺序输出
     */
    public static void arrayListDemo() {
        ArrayList<String> list = new ArrayList<>();
        list.add(null);
        list.add("sdfsd");
        list.add("vincent");
        list.add(null);
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String string = (String) iterator.next();
            System.out.println(string);
        }
    }

    /**
     * 关于LinkedList的操作，可以插入null的元素,相当于数据部分为null。 链表结构，可以当做 队列，列表来使用。
     * 方法：pop:弹出置顶元素 　　　　　peek：获取连接的第一个元素 　　　　　addFirst：给链表头添加元素
     * 　　　　　addLast：给链表尾添加元素 　　　　 使用next()获得序列中的下一个元素，使用hasNext()检查序列中是否还有元素。
     * 输出是基于add元素的顺序输出
     */
    public static void LinkedListDemo() {
        LinkedList<String> list = new LinkedList<>();
        list.add("100");
        list.add("beijing");
        list.add("123");
        list.add("zhongguo");
        for (int i = 0 ; i< list.size();i++)
        {
            System.out.println(list.get(i));
        }
        System.out.println("==================");
        list.addFirst("lcj"); //给链表头添加元素
        list.addLast("haha");//给链表末添加元素</span>
        list.addFirst(null);
        list.addLast(null);
        list.addLast(null);
        Iterator it = list.iterator();//迭代器
        while (it. hasNext ())//　　2)使用next()获得序列中的下一个元素。3)使用hasNext()检查序列中是否还有元素。
        {
            System.out.println(it.next());
        }
//         System.out.println("---------------------------");
//         System.out.println(list.<span style="color: #ff0000;">peek</span>());  //获取第一个迭代器中第一个元素
        System.out.println("------------------");
        System.out.println(list. pop()); //弹出置顶元素
        System.out.println("弹出之后元素》》");
        System.out.println("------------------");
        it = list. iterator ();  //重新获取迭代器
        while (it.hasNext())
        {
            System.out.println(it.next());
        }
    }

}
