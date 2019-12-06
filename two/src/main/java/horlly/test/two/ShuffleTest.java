package horlly.test.two;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShuffleTest {
    public static void main(String[] args) {
        List<Integer> numbers=new ArrayList<>();
        for (int j = 0; j < 49; j++) {
            numbers.add(j);
        }
        Collections.shuffle(numbers);
        List<Integer> cutList=numbers.subList(0,6);
        Collections.sort(cutList);
        System.out.println(cutList);
    }
}
