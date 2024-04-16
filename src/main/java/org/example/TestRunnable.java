package org.example;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class TestRunnable implements Runnable {
    private final int numberOfTasks;
    private final ComplexTaskExecutor taskExecutor;

    public TestRunnable(int numberOfTasks) {
        this.numberOfTasks = numberOfTasks;
        taskExecutor = new ComplexTaskExecutor(numberOfTasks);
    }

    @Override
    public void run() {
        log.info(Thread.currentThread().getName() + " started the test.");

        taskExecutor.executeTasks(numberOfTasks);

        log.info(Thread.currentThread().getName() + " completed the test.");
    }
}
