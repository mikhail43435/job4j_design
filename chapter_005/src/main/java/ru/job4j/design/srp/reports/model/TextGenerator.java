package ru.job4j.design.srp.reports.model;

import ru.job4j.design.srp.reports.Employee;

import java.util.List;

public interface TextGenerator {
    String generate(List<Employee> list);
}
