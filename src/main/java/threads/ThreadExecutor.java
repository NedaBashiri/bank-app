package threads;

import threads.interfaces.Task;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadExecutor {

    private final ExecutorService executorService;

    public ThreadExecutor(int numThreads) {
        this.executorService = Executors.newFixedThreadPool(numThreads);
    }

    public void executeTasks(List<Task> tasks) {
        for (Runnable task : tasks) {
            executorService.execute(task);
        }
        shutdown();
    }

    public void shutdown() {
        executorService.shutdown();
    }
}
