package ru.example.threadsdemo.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.example.threadsdemo.service.TaskService;

@Component
public class ThreadRunner implements CommandLineRunner {

    private final TaskService taskService;

    public ThreadRunner(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Потоки ДО запуска:");
        taskService.printActiveThreads();

        taskService.startViaThread(5);
        taskService.startViaRunnable(5);

        Thread.sleep(500);

        System.out.println("Потоки ВО ВРЕМЯ выполнения:");
        taskService.printActiveThreads();

        Thread.sleep(500);

        System.out.println("Потоки ПОСЛЕ завершения:");
        taskService.printActiveThreads();
    }
}