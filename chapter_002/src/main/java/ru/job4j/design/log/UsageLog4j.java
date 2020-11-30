package ru.job4j.design.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
/*        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");
        String name = "Petr Arsentev";
        int age = 33;
        LOG.debug("User info name : {}, age : {}", name, age);*/

        byte b = 1;
        short s = 2;
        int i = 3;
        long l = 4;
        double d = 5.5;
        float f = 6.6F;
        boolean bln = true;
        char c = 'c';
        LOG.debug("byte {}, short {}, int {}, long {},"
                + " double {}, float {}, boolean {},"
                + " char {}", b, s, i, l, d, f, bln, c);

    }
}