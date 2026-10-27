package ru.example.threadsdemo.task;

public class CounterWorker extends Thread {

    private final int iterations;

    public CounterWorker(String name, int iterations) {
        super(name);
        this.iterations = iterations;
    }

    @Override
    public void run() {
        for (int i = 1; i <= iterations; i++) {
            System.out.printf("[Thread]   Поток: %s, номер: %d%n",
                    getName(), i);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                interrupt();
                System.out.println("Поток прерван: " + getName());
                return;
            }
        }
    }
}