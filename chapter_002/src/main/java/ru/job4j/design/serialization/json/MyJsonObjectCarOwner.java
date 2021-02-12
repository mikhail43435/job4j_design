package ru.job4j.design.serialization.json;

import com.sun.xml.txw2.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAttribute;

@XmlElement(value = "CarOwner")
public class MyJsonObjectCarOwner {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private String address;

    public MyJsonObjectCarOwner() {
    }

    public MyJsonObjectCarOwner(String name, String address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return "MyJsonObjectCarOwner{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
