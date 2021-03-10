package ru.job4j.design.srp.reports.textformatters;

import ru.job4j.design.srp.reports.model.TextFormatter;

public class ToHTML implements TextFormatter {

    @Override
    public String format(String text) {
        String result = "";
        String[] splitText = text.split(System.lineSeparator());
        if (splitText.length < 2) {
            throw new IllegalArgumentException();
        }
        result = result
                + "<!DOCTYPE html>" + System.lineSeparator()
                + "<html lang=\"ru\">" + System.lineSeparator()
                + "<head>" + System.lineSeparator()
                + splitText[0] + System.lineSeparator()
                + "</head>" + System.lineSeparator()
                + "<body>" + System.lineSeparator();
        for (int index = 1; index < splitText.length; index++) {
            result = result
                    + "<P>" + splitText[index] + "</P>" + System.lineSeparator();
        }
        result = result
                + "</body>"
                + System.lineSeparator()
                + "</html>";
        return result;
    }
}