package org.example;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class ComplexTaskExecutor {
    private final CyclicBarrier barrier;
    private final List<Integer> results;


    public ComplexTaskExecutor(int numberOfTasks) {
        this.results = new ArrayList<>();
        this.barrier = new CyclicBarrier(numberOfTasks, this::mergeResults);
    }

    public void executeTasks(int numberOfTasks) {
        ExecutorService executor = Executors.newFixedThreadPool(numberOfTasks);

        for (int i = 0; i < numberOfTasks; i++) {
            executor.submit(() -> {
                ComplexTask task = new ComplexTask();
                task.execute();
                try {
                    barrier.await();
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        executor.shutdown();
    }

    private void mergeResults() {
        //Логика объединения результатов работы потоков
        int totalResult = 0;
        for (int result : results) {
            totalResult += result;
        }
        log.info("Merged result: " + totalResult);
    }
}
