# Отчёт по заданию: Основы потоков

## 1. Цель работы
Научиться создавать и запускать потоки через наследование класса Thread и реализацию интерфейса Runnable. Освоить вывод имен потоков, порядковых номеров и списка активных потоков JVM.

## 2. Архитектура приложения
Приложение разработано на базе Spring Boot и состоит из следующих компонентов:
- `ThreadsDemoApplication` — главный класс, точка входа с аннотацией `@SpringBootApplication`.
- `TaskService` (слой service) — класс сервиса, который создает и запускает потоки.
- `CounterWorker` (слой task) — класс задачи, реализующий поток через наследование от `Thread`.
- `CounterTask` (слой task) — класс задачи, реализующий поток через интерфейс `Runnable`.
- `ThreadRunner` (слой runner) — реализует `CommandLineRunner`, автоматически запускается при старте приложения, вызывает сервис и выводит списки потоков.

## 3. Реализация обоих способов создания потоков
1. **Через Thread:** Создан класс `CounterWorker extends Thread`. В конструкторе передается имя потока ("CounterWorker"). Переопределен метод `run()`, который в цикле выводит имя потока и его порядковый номер.
2. **Через Runnable:** Создан класс `CounterTask implements Runnable`. Поток создается как `new Thread(new CounterTask(5), "LoggerThread")`. В методе `run()` также выводятся имя и номер.

## 4. Вывод списка активных потоков
В классе `TaskService` реализован метод `printActiveThreads()`. Он получает список всех активных потоков с помощью `Thread.getAllStackTraces().keySet()`, сортирует их по имени и выводит имя, приоритет, флаг daemon и текущее состояние (state).

## 5. Результат работы программы
В консоли успешно отработали оба потока. Видно, что они выполняются параллельно (порядковые номера чередуются). Также успешно выведены списки активных потоков до, во время и после выполнения.

  .   ____          _            __ _ _
  /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
 ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
  \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
   '  |____| .__|_| |_|_| |_\__, | / / / /
  =========|_|==============|___/=/_/_/_/

  :: Spring Boot ::                (v4.1.2)

 2026-10-27T08:52:46.475-07:00  INFO 7660 --- [threads-demo] [           main] r.e.threadsdemo.ThreadsDemoApplication   : Starting ThreadsDemoApplication using Java 27.0.1.0.3 with PID 7660 (C:\New folder\threads-demo\target\classes started by ragemode in C:\New folder\threads-demo)
 2026-10-27T08:52:46.476-07:00  INFO 7660 --- [threads-demo] [           main] r.e.threadsdemo.ThreadsDemoApplication   : No active profile set, falling back to 1 default profile: "default"
 2026-10-27T08:52:46.712-07:00  INFO 7660 --- [threads-demo] [           main] r.e.threadsdemo.ThreadsDemoApplication   : Started ThreadsDemoApplication in 0.428 seconds (process running for 0.609)
 Потоки ДО запуска:
 Активные потоки:
 Attach Listener | priority=5 | daemon=true | state=RUNNABLE
 Common-Cleaner | priority=8 | daemon=true | state=TIMED_WAITING
 Finalizer | priority=8 | daemon=true | state=WAITING
 main | priority=5 | daemon=false | state=RUNNABLE
 Monitor Ctrl-Break | priority=5 | daemon=true | state=RUNNABLE
 Notification Thread | priority=9 | daemon=true | state=RUNNABLE
 Reference Handler | priority=10 | daemon=true | state=RUNNABLE
 Signal Dispatcher | priority=9 | daemon=true | state=RUNNABLE
 ------------------------------
 [Thread]   Поток: CounterWorker, номер: 1
 [Runnable] Поток: LoggerThread, номер: 1
 [Thread]   Поток: CounterWorker, номер: 2
 [Runnable] Поток: LoggerThread, номер: 2
 [Runnable] Поток: LoggerThread, номер: 3
 [Thread]   Поток: CounterWorker, номер: 3
 [Thread]   Поток: CounterWorker, номер: 4
 [Runnable] Поток: LoggerThread, номер: 4
 [Thread]   Поток: CounterWorker, номер: 5
 [Runnable] Поток: LoggerThread, номер: 5
 Потоки ВО ВРЕМЯ выполнения:
 Активные потоки:
 Attach Listener | priority=5 | daemon=true | state=RUNNABLE
 Common-Cleaner | priority=8 | daemon=true | state=TIMED_WAITING
 Finalizer | priority=8 | daemon=true | state=WAITING
 main | priority=5 | daemon=false | state=RUNNABLE
 Monitor Ctrl-Break | priority=5 | daemon=true | state=RUNNABLE
 Notification Thread | priority=9 | daemon=true | state=RUNNABLE
 Reference Handler | priority=10 | daemon=true | state=RUNNABLE
 Signal Dispatcher | priority=9 | daemon=true | state=RUNNABLE
 ------------------------------
 Потоки ПОСЛЕ завершения:
 Активные потоки:
 Attach Listener | priority=5 | daemon=true | state=RUNNABLE
 Common-Cleaner | priority=8 | daemon=true | state=TIMED_WAITING
 Finalizer | priority=8 | daemon=true | state=WAITING
 main | priority=5 | daemon=false | state=RUNNABLE
 Monitor Ctrl-Break | priority=5 | daemon=true | state=RUNNABLE
 Notification Thread | priority=9 | daemon=true | state=RUNNABLE
 Reference Handler | priority=10 | daemon=true | state=RUNNABLE
 Signal Dispatcher | priority=9 | daemon=true | state=RUNNABLE
 ------------------------------

 Process finished with exit code 0