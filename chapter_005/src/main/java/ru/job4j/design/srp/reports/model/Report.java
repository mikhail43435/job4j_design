package ru.job4j.design.srp.reports.model;

import ru.job4j.design.srp.reports.Employee;

import java.util.function.Predicate;

public interface Report {
    String generate(Predicate<Employee> filter, TextGenerator textGenerator);

    String format(String text, TextFormatter textFormatter);
}