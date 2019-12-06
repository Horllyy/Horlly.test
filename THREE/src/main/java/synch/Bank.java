package synch;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
    private final double[] accounts;
    private Lock bankLock;
    private Condition sufficientFunts;

    public Bank(int n,double initialBalance)
    {
        accounts= new double[n];
        Arrays.fill(accounts,initialBalance);
        bankLock=new ReentrantLock();
        sufficientFunts=bankLock.newCondition();
    }

    public void transfer(int from,int to,double amount) throws InterruptedException
    {
        bankLock.lock();
        try
        {
            while(accounts[from]<amount)
                sufficientFunts.await();
            System.out.println(Thread.currentThread());
            accounts[from]-=amount;
            System.out.printf("%10.2f form %d to %d ",amount,from,to);
            accounts[from]+=amount;
            System.out.printf("total balence=%10.2f%n",getTotalBalence());
            sufficientFunts.signalAll();
        }
        finally {
            bankLock.unlock();
        }
    }

    private Object getTotalBalence() {
        bankLock.lock();
        try
        {
            double sum=0;
            for (double a:accounts
                 ) {
                sum+=a;
            }
            return sum;
        }
        finally {
            bankLock.unlock();
        }
    }

    public int size()
    {
        return accounts.length;
    }
}
