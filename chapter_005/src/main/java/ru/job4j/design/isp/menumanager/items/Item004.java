package ru.job4j.design.isp.menumanager.items;

import ru.job4j.design.isp.menumanager.MenuItem;

public class Item004 extends MenuItem {

    public Item004() {
        this.name = "Element 004";
        this.childList.add(new Item005());
    }
}
