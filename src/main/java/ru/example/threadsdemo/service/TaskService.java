package ru.example.threadsdemo.service;

import org.springframework.stereotype.Service;
import ru.example.threadsdemo.task.CounterTask;
import ru.example.threadsdemo.task.CounterWorker;

@Service
public class TaskService {

    public void startViaRunnable(int iterations) {
        Thread t = new Thread(new CounterTask(iterations), "LoggerThread");
        t.start();
    }

    public void startViaThread(int iterations) {
        CounterWorker worker = new CounterWorker("CounterWorker", iterations);
        worker.start();
    }

    public void printActiveThreads() {
        System.out.println("Активные потоки:");
        Thread.getAllStackTraces().keySet().stream()
                .sorted((a, b) -> a.getName().compareToIgnoreCase(b.getName()))
                .forEach(t -> System.out.printf(
                        "%s | priority=%d | daemon=%b | state=%s%n",
                        t.getName(), t.getPriority(), t.isDaemon(), t.getState()));
        System.out.println("------------------------------");
    }
}