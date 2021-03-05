package ru.job4j.design.srp.reports.textgenerators;

import ru.job4j.design.srp.reports.util.DateFormatter;
import ru.job4j.design.srp.reports.Employee;
import ru.job4j.design.srp.reports.model.TextGenerator;

import java.util.List;

/**
 * Стандартный шаблон
 */

public class TextGeneratorStandard implements TextGenerator {

    @Override
    public String generate(List<Employee> list) {
        StringBuilder result = new StringBuilder();
        list.sort((o1, o2) -> (int) (o1.getSalary() - o2.getSalary()));
        result.append("Name; Hired; Fired; Salary;");
        result.append(System.lineSeparator());
        for (Employee employee : list) {
            result
                    .append(employee.getName())
                    .append(";")
                    .append(DateFormatter.getFormattedDate(employee.getHired()))
                    .append(";")
                    .append(DateFormatter.getFormattedDate(employee.getFired()))
                    .append(";")
                    .append(employee.getSalary())
                    .append(";")
                    .append(System.lineSeparator());
        }
        return result.toString();
    }
}