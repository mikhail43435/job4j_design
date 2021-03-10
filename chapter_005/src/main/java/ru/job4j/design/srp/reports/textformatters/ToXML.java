package ru.job4j.design.srp.reports.textformatters;

import ru.job4j.design.srp.reports.model.TextFormatter;

public class ToXML implements TextFormatter {

    @Override
    public String format(String text) {
        String result = "";
        String[] line;
        String[] splitText = text.split(System.lineSeparator());
        if (splitText.length < 2) {
            throw new IllegalArgumentException();
        }
        String[] header = splitText[0].split(";");
        for (int index = 0; index < header.length; index++) {
            header[index] = header[index].trim().replace(" ", "_");
        }
        result = result
                + "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + System.lineSeparator()
                + "<report>"
                + System.lineSeparator()
                + "    <report_type>Employee report</report_type>"
                + System.lineSeparator()
                + "    <report_body>"
                + System.lineSeparator();
        for (int indexOut = 1; indexOut < splitText.length; indexOut++) {
            line = splitText[indexOut].split(";");
            result += "        <employee_" + (indexOut - 1) + ">" + System.lineSeparator();
            for (int indexIn = 0; indexIn < line.length; indexIn++) {
                result = result
                        + "            <" + header[indexIn] + ">"
                        + line[indexIn].trim().replace("<>", "")
                        + "</" + header[indexIn] + ">"
                        + System.lineSeparator();
            }
            result += "        </employee_" + (indexOut - 1) + ">" + System.lineSeparator();
        }
        result = result
                + "    </report_body>"
                + System.lineSeparator()
                + "</report>";
        return result;
    }
}