package ru.example.threadsdemo.task;

public class CounterTask implements Runnable {

    private final int iterations;

    public CounterTask(int iterations) {
        this.iterations = iterations;
    }

    @Override
    public void run() {
        for (int i = 1; i <= iterations; i++) {
            System.out.printf("[Runnable] Поток: %s, номер: %d%n",
                    Thread.currentThread().getName(), i);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Поток прерван: " + Thread.currentThread().getName());
                return;
            }
        }
    }
}