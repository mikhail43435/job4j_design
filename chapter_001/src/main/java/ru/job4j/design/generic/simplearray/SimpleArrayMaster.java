package ru.job4j.design.generic.simplearray;

public class SimpleArrayMaster {
    public static void main(String[] args) {

        SimpleArray<String> array = new SimpleArray<String>(5);

        array.add("1");
        array.add("2");
        array.add("3");
        array.add("4");
        array.add("5");

        System.out.println(array);
    }
}
