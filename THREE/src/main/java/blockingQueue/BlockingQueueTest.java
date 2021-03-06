package blockingQueue;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTest {
    private static final int FILE_QUEUE_SIZE = 10;
    private static final int SEARCH_THREADS = 10;
    private static final File DUMMY = new File("");
    private static BlockingQueue<File> queue = new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            System.out.println("Enter base directory(e.g /opt/jdk1.8.0/src)");
            String directory = in.nextLine();
            System.out.println("Enter keyword (e.g.volatile):");
            String keyword = in.nextLine();

            System.out.println(keyword);

            Runnable enumrator = () -> {
                try {
                    enumerate(new File(directory));
                    queue.put(DUMMY);

                } catch (InterruptedException e) {
                }
            };

            System.out.println("enumrator good.");

            new Thread(enumrator).start();
//            System.out.println(enumrator.toString());
            for (int i = 0; i < SEARCH_THREADS; i++) {
                Runnable searcher = () -> {
                    try {
                        boolean done = false;
                        while (!done) {
                            File file = queue.take();
                            if (file == DUMMY) {
                                queue.put(file);
                                done=true;
                            } else search(file, keyword);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                    }
                };
                new Thread(searcher).start();
                System.out.println("thread:"+Thread.currentThread().getName()+"good.");
            }
        }
    }

    public static void enumerate(File directory) throws InterruptedException
    {
        File[] files=directory.listFiles();
        for (File file:files
             ) {
            if(file.isDirectory()) enumerate(file);
            else queue.put(file);
        }
    }

    public static void search(File file,String keyword) throws IOException
    {
        try(Scanner in=new Scanner(System.in))
        {
            System.out.println("enter search");
            int lineNumber=0;
            System.out.println(in.hasNext());
            System.out.println("hasNextline?"+in.hasNextLine());
            while(in.hasNextLine())
            {
                System.out.println("search good");
                lineNumber++;
                String line=in.nextLine();
                if(line.contains(keyword))
                    System.out.printf("%s:%d:%s%n",file.getPath(),lineNumber,line);
            }
        }
    }
}