package ru.job4j.design.srp.reports;

import ru.job4j.design.srp.reports.model.Store;
import ru.job4j.design.srp.reports.model.Report;
import ru.job4j.design.srp.reports.model.TextFormatter;
import ru.job4j.design.srp.reports.model.TextGenerator;

import java.util.function.Predicate;

public class ReportEngine implements Report {

    private final Store store;

    public ReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter, TextGenerator textGenerator) {
        return textGenerator.generate(store.findBy(filter));
    }

    @Override
    public String format(String text, TextFormatter formatter) {
        return formatter.format(text);
    }
}