package org.example;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ComplexTaskExecutor {
    private final int numberOfTasks;
    private final CyclicBarrier cyclicBarrier;
    private final ExecutorService executorService;


    public ComplexTaskExecutor(int numberOfTasks) {
        this.numberOfTasks = numberOfTasks;
        this.cyclicBarrier = new CyclicBarrier(numberOfTasks, this::mergeResults);
        this.executorService = Executors.newFixedThreadPool(numberOfTasks);
    }


    public void executeTasks(int numberOfTasks) {
        for (int i = 0; i < numberOfTasks; i++) {
            final int taskId = i;
            executorService.submit(() -> {
                ComplexTask task = new ComplexTask(taskId);
                task.execute();
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
    }

    private void mergeResults() {
        System.out.println(Thread.currentThread().getName() + " объединяем результаты всех задач");
        System.out.println("Результаты объеденены");
    }

    public void shutdown() {
        executorService.shutdown();
    }
}
