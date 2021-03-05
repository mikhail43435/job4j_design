package ru.job4j.design.srp.reports.textgenerators;

import ru.job4j.design.srp.reports.util.DateFormatter;
import ru.job4j.design.srp.reports.Employee;
import ru.job4j.design.srp.reports.model.TextGenerator;

import java.util.List;

/**
 * Через месяц применения системы отчетности
 * отдел программистов потребовал ответы в виде html
 */

public class TextGeneratorIT001 implements TextGenerator {

    @Override
    public String generate(List<Employee> list) {
        StringBuilder result = new StringBuilder();
        result.append("<!DOCTYPE html>")
                .append(System.lineSeparator())
                .append("<html lang=\"ru\">")
                .append(System.lineSeparator())
                .append("<head>")
                .append("Name; Hired; Fired; Salary;")
                .append("</head>")
                .append(System.lineSeparator())
                .append("<body>")
                .append(System.lineSeparator());
        for (Employee employee : list) {
            result
                    .append("<P>")
                    .append(employee.getName())
                    .append(";")
                    .append(DateFormatter.getFormattedDate(employee.getHired()))
                    .append(";")
                    .append(DateFormatter.getFormattedDate(employee.getFired()))
                    .append(";")
                    .append(employee.getSalary())
                    .append(";")
                    .append("</P>")
                    .append(System.lineSeparator());
        }
        result
                .append("</body>")
                .append(System.lineSeparator())
                .append("</html>")
                .append(System.lineSeparator());

        return result.toString();
    }
}