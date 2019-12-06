package Atomic;

public class TicketTest {
    public static void main(String[] args) {

        final TicketAtomic ticket = new TicketAtomic();
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (ticket.getCount() > 0) {
                        System.out.println(Thread.currentThread().getName() + " count: " + ticket.decrement());
                    }
                }
            }).start();
        }
    }
}
