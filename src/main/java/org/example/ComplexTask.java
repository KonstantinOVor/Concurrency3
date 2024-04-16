package org.example;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ComplexTask {
    public void execute() {
        log.info(Thread.currentThread().getName() + " is executing a complex task.");
        // Имитация выполнения сложной задачи
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
