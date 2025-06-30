package org.example;

public class ComplexTask {
    private final int taskId;
    private int result;

    public ComplexTask(int taskId) {
        this.taskId = taskId;
    }

    public int getResult() {
        return result;
    }

    public void execute(){
        System.out.println(Thread.currentThread().getName() + " задача " + taskId);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        result = taskId * 5;
        System.out.println(Thread.currentThread().getName() + " задача " + taskId + " с результатом " + result);
    }
}
