package future;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class MatchCounter implements Callable<Integer> {
    private File directory;
    private String keyword;

    public MatchCounter(File directory, String keyword){
        this.directory=directory;
        this.keyword=keyword;
    }

    public Integer call(){
        int count=0;
        try {
            File[] files = directory.listFiles();
            List<Future<Integer>> results = new ArrayList<>();

            for (File file : files
            ) {
                System.out.println(files.length);
                System.out.println(file.getName());//这里显示null
                if (file.isDirectory()) {
                    //总之这里有错 if里显示false
//                if (true) {
                    MatchCounter counter = new MatchCounter(file, keyword);
                    FutureTask<Integer> task = new FutureTask<>(counter);
                    results.add(task);
                    Thread t = new Thread(task);
                    t.start();
                    System.out.println("thread t start good");
                } else {
//                    System.out.println("file.isDirectory="+file.isDirectory()+",so no directory");
                    if (search(file)) count++;
                }
            }

            for (Future<Integer> result : results
                ) {
                    count+=result.get();
            }
        }
        catch (InterruptedException e)
        {
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return count;
    }

    public boolean search(File file)
    {
        try(Scanner in=new Scanner(System.in))
        {
            boolean found=false;
            while(!found&&in.hasNextLine())
            {
                String line=in.nextLine();
                if(line.contains(keyword)) found=true;
            }
            return found;
        }
    }

}
