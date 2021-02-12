package ru.job4j.design.serialization.json;

import com.google.gson.annotations.SerializedName;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class MyJsonObjectCar {
    @XmlAttribute
    private String model;
    private Integer power;
    private boolean isValid;
    private MyJsonObjectCarOwner owner;
    //@SerializedName("mill")
    private Integer[] millageAtTheEndOfAYear;

    public MyJsonObjectCar() {
    }

    public String getModel() {
        return model;
    }

    public Integer getPower() {
        return power;
    }

    public boolean isValid() {
        return isValid;
    }

    public MyJsonObjectCarOwner getOwner() {
        return owner;
    }

    public Integer[] getMillageAtTheEndOfAYear() {
        return millageAtTheEndOfAYear;
    }

    public MyJsonObjectCar(String model, Integer power,
                           boolean isValid, MyJsonObjectCarOwner owner,
                           Integer[] millageAtTheEndOfAYear) {
        this.model = model;
        this.power = power;
        this.isValid = isValid;
        this.owner = owner;
        this.millageAtTheEndOfAYear = millageAtTheEndOfAYear;
    }

    @Override
    public String toString() {
        return "Overrided toString" +
                "MyJsonObjectCar{" +
                "model='" + model + '\'' +
                ", power=" + power +
                ", isValid=" + isValid +
                ", owner=" + owner +
                ", millageAtTheEndOfAYear=" + Arrays.toString(millageAtTheEndOfAYear) +
                '}';
    }
}