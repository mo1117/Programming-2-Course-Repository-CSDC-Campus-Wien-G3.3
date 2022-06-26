package at.ac.fhcampuswien.downloader;

import at.ac.fhcampuswien.NewsAPIException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// Class is needed for exercise 4 - ignore for exercise 3 solution
public class ParallelDownloader extends Downloader {

    // returns number of downloaded article urls
    @Override
    public int process(List<String> urls) throws NewsAPIException {

        int count = 0;  //count of downloaded articles

        //the threadpool
        ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        List<Future<String>> downloads = new ArrayList<>();

        try {
            for (int i = 0; i < urls.size(); i++) {
                int j = i;
                Callable<String> task = () -> saveUrl2File(urls.get(j));
                downloads.add(threadPool.submit(task));
            }

            for (Future<String> d : downloads)
                if (d.get() != null)
                    count++;

            return count;
        } catch (Exception e) {
            throw new NewsAPIException("Different problem occurred in " + this.getClass().getName() + ". Message: " + e.getMessage());
        }
    }
}
