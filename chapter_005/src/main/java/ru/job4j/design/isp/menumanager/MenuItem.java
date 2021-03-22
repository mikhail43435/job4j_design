package ru.job4j.design.isp.menumanager;

import java.util.ArrayList;
import java.util.List;

public abstract class MenuItem {

    protected String name;
    protected List<MenuItem> childList = new ArrayList<>();
    private int level;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public List<MenuItem> getchildList() {
        return childList;
    }

    public void execute() {
        System.out.println("Executing action by " + name);
    }
}