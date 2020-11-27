package ru.job4j.design.io.zip;

import ru.job4j.design.io.ArgsName;

import static java.util.Objects.nonNull;

public class ArgZip {
    private final String[] args;
    private final ArgsName an;
    private final boolean isValid;

    public ArgZip(String[] args) {
        this.args = args;
        an = ArgsName.of(args);
        isValid = nonNull(an.get("d"))
                && nonNull(an.get("e"))
                && nonNull(an.get("o"));
    }

    public boolean valid() {
        return isValid;
    }

    public String directory() {
        return an.get("d");
    }

    public String exclude() {
        return an.get("e");
    }

    public String output() {
        return an.get("o");
    }
}
