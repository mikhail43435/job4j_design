package ru.job4j.design.intro;

import ru.job4j.design.gcdemo.Person;

import java.lang.instrument.Instrumentation;

public class GCTest {

    private static final long KB = 1024;
    private static final long MB = KB * KB;
    private static final Runtime ENVIRONMENT = Runtime.getRuntime();
    private static Instrumentation instrumentation;

    public static void main(String[] args) {
        long freeMemory = ENVIRONMENT.freeMemory();
        long freeMemory1 = ENVIRONMENT.freeMemory();
        long totalMemory = ENVIRONMENT.totalMemory();
        long maxMemory = ENVIRONMENT.maxMemory();
        System.out.println("=== Environment state ===");
        System.out.printf("Free: %d MB%n", freeMemory / MB);
        System.out.printf("Total: %d MB%n", totalMemory / MB);
        System.out.printf("Max: %d MB%n", maxMemory / MB);

        Person person = new Person(1, "1");
        Person person1 = new Person(2, "2");

        freeMemory = ENVIRONMENT.freeMemory();
        totalMemory = ENVIRONMENT.totalMemory();
        maxMemory = ENVIRONMENT.maxMemory();

        System.out.println("=== Environment state ===");
        System.out.printf("Free: %d MB%n", freeMemory / MB);
        System.out.printf("Total: %d MB%n", totalMemory / MB);
        System.out.printf("Max: %d MB%n", maxMemory / MB);
        System.out.println((freeMemory1 - freeMemory) / KB);
        System.out.println(ObjectSizeFetcher.getObjectSize(person));
    }
}

