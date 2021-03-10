package ru.job4j.design.srp.reports.textformatters;

import ru.job4j.design.srp.reports.model.TextFormatter;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ToJSON implements TextFormatter {

    @Override
    public String format(String text) {
        String[] line;
        Map<String, String> map = new HashMap<>();
        String[] splitText = text.split(System.lineSeparator());
        String[] header = splitText[0].split(";");
        if (splitText.length < 2) {
            throw new IllegalArgumentException();
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Report type", "Employee report");
        for (int indexOut = 1; indexOut < splitText.length; indexOut++) {
            line = splitText[indexOut].split(";");
            for (int indexIn = 0; indexIn < line.length; indexIn++) {
                map.put(header[indexIn].trim(), line[indexIn].trim());
            }
            jsonObject.put("Employee " + indexOut, map);
            map.clear();
        }
        return jsonObject.toString();
    }
}