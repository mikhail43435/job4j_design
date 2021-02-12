package ru.job4j.design.serialization.jaxb;

import ru.job4j.design.serialization.json.MyJsonObjectCar;
import ru.job4j.design.serialization.json.MyJsonObjectCarOwner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class MainCarObject {
    public static void main(String[] args) throws Exception {

        MyJsonObjectCar car = new MyJsonObjectCar("Tesla", 300,
                true,
                new MyJsonObjectCarOwner("David", "45 Ocean view"),
                new Integer[]{20, 34, 45});

        // Получаем контекст для доступа к АПИ
        JAXBContext context = JAXBContext.newInstance(MyJsonObjectCar.class);

        // Создаем сериализатор
        Marshaller marshaller = context.createMarshaller();

        // Указываем, что нам нужно форматирование
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            // Сериализуем
            marshaller.marshal(car, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }

        // Для сериализации нам нужно создать десериализатор
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            // десериализуем
            MyJsonObjectCar result = (MyJsonObjectCar) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }

        //marshaller.marshal(book, new File("./book.xml"));
    }
}