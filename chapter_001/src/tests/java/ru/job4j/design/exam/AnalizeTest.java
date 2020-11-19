package ru.job4j.design.exam;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;
import ru.job4j.design.exam.Analize.*;
import java.util.ArrayList;
import java.util.List;

public class AnalizeTest {

    @Test
    public void whenNothingHasBeenChanged() {
        List<Analize.User> listPrev = new ArrayList<>();
        listPrev.add(new User(1, "Bill"));
        listPrev.add(new User(2, "John"));
        listPrev.add(new User(3, "Sam"));
        List<Analize.User> listCur = new ArrayList<>();
        listCur.add(new User(1, "Bill"));
        listCur.add(new User(2, "John"));
        listCur.add(new User(3, "Sam"));
        Info info = Analize.diff(listPrev, listCur);
        assertThat(info.getAdded(), is(0));
        assertThat(info.getChanged(), is(0));
        assertThat(info.getDeleted(), is(0));
    }

    @Test
    public void when1UserDeleted1Changed1Addded() {
        List<Analize.User> listPrev = new ArrayList<>();
        listPrev.add(new User(1, "Bill"));
        listPrev.add(new User(2, "John"));
        listPrev.add(new User(3, "Sam"));
        List<Analize.User> listCur = new ArrayList<>();
        listCur.add(new User(1, "Bill"));
        listCur.add(new User(3, "SamSam"));
        listCur.add(new User(5, "Bill"));
        Info info = Analize.diff(listPrev, listCur);
        assertThat(info.getAdded(), is(1));
        assertThat(info.getChanged(), is(1));
        assertThat(info.getDeleted(), is(1));
    }

    @Test
    public void whenAllDeleted() {
        List<Analize.User> listPrev = new ArrayList<>();
        listPrev.add(new User(1, "Bill"));
        listPrev.add(new User(2, "John"));
        List<Analize.User> listCur = new ArrayList<>();
        Info info = Analize.diff(listPrev, listCur);
        assertThat(info.getAdded(), is(0));
        assertThat(info.getChanged(), is(0));
        assertThat(info.getDeleted(), is(2));
    }

    @Test
    public void whenAllChanged() {
        List<Analize.User> listPrev = new ArrayList<>();
        listPrev.add(new User(1, "Bill"));
        listPrev.add(new User(2, "John"));
        listPrev.add(new User(3, "Sam"));
        List<Analize.User> listCur = new ArrayList<>();
        listCur.add(new User(1, "Bill 2"));
        listCur.add(new User(2, "John 3"));
        listCur.add(new User(3, "Sam 4"));
        Info info = Analize.diff(listPrev, listCur);
        assertThat(info.getAdded(), is(0));
        assertThat(info.getChanged(), is(3));
        assertThat(info.getDeleted(), is(0));
    }
}