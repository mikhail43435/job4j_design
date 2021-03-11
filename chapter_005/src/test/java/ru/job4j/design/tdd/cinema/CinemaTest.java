package ru.job4j.design.tdd.cinema;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class CinemaTest {

    @Test
    public void buy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, Calendar.OCTOBER, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        //assertThat(ticket, is(new Ticket3D()));
    }

    @Test
    public void find() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        //assertThat(sessions, is(Arrays.asList(new Session3D())));
    }

    @Test
    public void add() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        //assertThat(sessions, is(Arrays.asList(new Session3D())));
    }

    // added tests

    @Test
    public void findThenNotFound() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        //assertThat(sessions, is(sessions.isEmpty()));
    }

    @Test
    public void findThenInputNull() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(null);
        //Assert.assertNull(sessions);
    }

    @Test
    public void addThenNotAdded() {
        Cinema cinema = new Cinema3D();
        //assertThat(cinema.add(null), is(false));
    }

    @Test
    public void buyThenInvalidRowAndColumn() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, Calendar.OCTOBER, 10, 23, 00);
        Ticket ticket = cinema.buy(account, -1, 0, date);
        //Assert.assertNull(ticket);
    }
}