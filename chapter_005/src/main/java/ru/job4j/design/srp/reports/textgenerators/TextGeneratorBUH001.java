package ru.job4j.design.srp.reports.textgenerators;

import ru.job4j.design.srp.reports.Employee;
import ru.job4j.design.srp.reports.model.TextGenerator;

import java.util.List;

/**
 * Отдел бухгалтерии попросил изменить вид зарплаты
 */

public class TextGeneratorBUH001 implements TextGenerator {

    @Override
    public String generate(List<Employee> list) {
        double currencyRate = 745485L;
        StringBuilder result = new StringBuilder();
        result.append("Name; Salary USD;");
        result.append(System.lineSeparator());
        for (Employee employee : list) {
            result
                    .append(employee.getName())
                    .append(";")
                    .append(String.format("%.2f", convertSalaryToUSD(employee.getSalary(), currencyRate)))
                    .append(";")
                    .append(System.lineSeparator());
        }
        return result.toString();
    }

    private double convertSalaryToUSD(double salary, double rate) {
        return salary / (rate / 10000);
    }
}
