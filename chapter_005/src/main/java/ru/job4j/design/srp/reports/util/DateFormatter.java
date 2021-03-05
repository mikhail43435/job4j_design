package ru.job4j.design.srp.reports.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public final class DateFormatter {

    private static final long EMPTY_DATE_TIME_IN_MILLIS;
    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("dd/MM/yyyy");

    static {
        Calendar emptyDate = Calendar.getInstance();
        emptyDate.clear();
        EMPTY_DATE_TIME_IN_MILLIS = emptyDate.getTimeInMillis();
    }

    private DateFormatter() {
        //not called
    }

    public static String getFormattedDate(Calendar date) {
        if (date.getTimeInMillis() == EMPTY_DATE_TIME_IN_MILLIS) {
            return "<>";
        } else {
            return FORMATTER.format(date.getTime());
        }
    }
}