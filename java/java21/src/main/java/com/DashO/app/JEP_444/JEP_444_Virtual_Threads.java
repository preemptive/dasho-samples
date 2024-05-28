package com.DashO.app.JEP_444;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.IntStream;

public class JEP_444_Virtual_Threads {
    // JEP 444 - Using ofVirtual method unstarted to create a thread and use start and join method
    public static void jep444VirtualThreadExample() throws InterruptedException {
        var virtualThread = Thread.ofVirtual().unstarted(() ->
                System.out.println("Current Thread= " + Thread.currentThread() + " Thread created by Virtual method"));
        System.out.println("Current Thread id = " + Thread.currentThread().getId());
        virtualThread.start();
        virtualThread.join();
    }

    // JEP 444 - Using startVirtualThread and isVirtual method to create a thread
    // Check whether the thread is virtual or not using isVirtual method
    public static void startVirtualThreadExample() {
        Runnable runnable = () -> System.out.println("Current thread is virtual= " + Thread.currentThread().isVirtual());
        Thread.startVirtualThread(runnable);
    }

    // Using Executors method to create a thread, working in the thread-per-request style
    public static void executorsExample() {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            System.out.println("Task created by Executors Method of thread");
            IntStream.range(0, 5).forEach(task -> {
                executor.submit(() -> {
                    try {
                        Thread.sleep(Duration.ofSeconds(1));
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }

                    System.out.println("Task = " + task);
                });
            });
        }
    }

    // Thread Builder
    public static void threadBuilderExample() {
        Runnable runnable = () -> System.out.println("Thread created by using Thread Builder method");
        Thread.Builder builder = Thread.ofVirtual();
        builder.start(runnable);
    }

    // Thread Builder factory
    public static void threadBuilderFactoryExample() {
        Runnable task = () -> System.out.println("Thread created by using Thread Builder Factory method");
        ThreadFactory factory = Thread.ofVirtual().factory();
        Thread virtualThreadFromAFactory = factory.newThread(task);
        virtualThreadFromAFactory.start();
    }

    // Getting data from URL using virtual threads
    public static void handleUrlVirtualThreads() {
        int numberOfThreads = 2;
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, numberOfThreads).forEach(threadNumber -> executor.submit(() -> {
                try {
                    var url = new URL("https://openjdk.org/jeps/444");
                    try (var readUrl = url.openStream()) {
                        System.out.println(new String(readUrl.readNBytes(109), StandardCharsets.UTF_8));
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }));
        }
    }

    public static void JEP_444_Method_Call() throws InterruptedException {
        jep444VirtualThreadExample();
        startVirtualThreadExample();
        executorsExample();
        threadBuilderExample();
        threadBuilderFactoryExample();
        handleUrlVirtualThreads();
    }
}
