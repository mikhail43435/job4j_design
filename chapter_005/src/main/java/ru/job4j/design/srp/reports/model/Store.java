package ru.job4j.design.srp.reports.model;

import ru.job4j.design.srp.reports.Employee;

import java.util.List;
import java.util.function.Predicate;

public interface Store {

    List<Employee> findBy(Predicate<Employee> filter);
}