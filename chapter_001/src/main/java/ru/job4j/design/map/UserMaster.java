package ru.job4j.design.map;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class UserMaster {
    public static void main(String[] args) {

        User user1 = new User("f", 2,
                new GregorianCalendar(2017, 0 , 25) );
        User user2 = new User("f", 2,
                new GregorianCalendar(2017, 0 , 25) );

        //System.out.println(user1.equals(user2));

        Map map = new HashMap<User, Object>();
        map.put(user1, null);
        map.put(user2, null);

        //System.out.println(map);
        //System.out.println("=====================");

        UserNoString user1ns = new UserNoString(2, new GregorianCalendar(2017, 0 , 25));
        UserNoString user2ns = new UserNoString(2, new GregorianCalendar(2017, 0 , 25));

        Map map11 = new HashMap<UserNoString, Object>();

        map11.put(user1ns, null);
        map11.put(user2ns, null);

        //System.out.println(map);
    }
}
