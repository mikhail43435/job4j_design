package ru.job4j.design.isp.menumanager.items;

import ru.job4j.design.isp.menumanager.MenuItem;

public class Item001 extends MenuItem {

    public Item001() {
        this.name = "Element 001";
        this.childList.add(new Item002());
        this.childList.add(new Item003());
        this.childList.add(new Item004());
    }
}