package horlly.test.three.priorityQueue;
import java.time.LocalDate;
import java.util.PriorityQueue;

public class PriorityQueueTest{
    public static void main(String[] args) {
        PriorityQueue<LocalDate> qp=new PriorityQueue<LocalDate>();
        qp.add(LocalDate.of(1906,12,9));
        qp.add(LocalDate.of(1815,12,10));
        qp.add(LocalDate.of(1903,12,3));
        qp.add(LocalDate.of(1910,6,22));

        System.out.println("Iterating over elements...");
        for (LocalDate date:qp
             ) {
            System.out.println(date);
        }
        System.out.println("Removing elements...");
        while(!qp.isEmpty())
             System.out.println(qp.remove());
    }
}
