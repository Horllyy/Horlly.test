package Atomic;

import java.util.concurrent.atomic.AtomicInteger;

class TicketAtomic {

    public AtomicInteger count = new AtomicInteger(100);

    public int decrement() {

        return count.getAndDecrement();
    }

    public int getCount() {
        return count.get();

    }
}
