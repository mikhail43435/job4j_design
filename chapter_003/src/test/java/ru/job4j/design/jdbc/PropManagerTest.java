package ru.job4j.design.jdbc;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PropManagerTest  {

    @Test
    public void mainTest() {
        PropManager pm = new PropManager("app.properties");
        assertThat(pm.getKeyValue("url"),
                is("jdbc:postgresql://localhost:5432/idea_db"));
    }
}