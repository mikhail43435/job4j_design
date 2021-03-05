package ru.job4j.design.srp.reports.textgenerators;

import ru.job4j.design.srp.reports.Employee;
import ru.job4j.design.srp.reports.model.TextGenerator;

import java.util.List;

/**
 * Отдел HR попросил выводить сотрудников
 * в порядке убывания зарплаты
 * и убрать поля даты найма и увольнения
 * (оставить ФИО и зарплату)
 */

public class TextGeneratorHR001 implements TextGenerator {

    @Override
    public String generate(List<Employee> list) {
        StringBuilder result = new StringBuilder();
        list.sort((o1, o2) -> (int) (o2.getSalary() - o1.getSalary()));
        result.append("Name; Salary;");
        result.append(System.lineSeparator());
        for (Employee employee : list) {
            result
                    .append(employee.getName())
                    .append(";")
                    .append(employee.getSalary())
                    .append(";")
                    .append(System.lineSeparator());
        }
        return result.toString();
    }
}
