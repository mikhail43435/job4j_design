package ru.job4j.design.generic.store;

import static org.hamcrest.Matchers.is;
import org.junit.Assert;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;

public class RoleStoreTest {

    @Test
    public void whenReplace() {
        RoleStore memStore = new RoleStore();
        memStore.add(new Role("23"));
        memStore.add(new Role("54"));
        memStore.add(new Role("24"));
        Role newRole = new Role("77");
        assertThat(memStore.replace("24", newRole), is(true));
        assertThat(memStore.findById("77"), is(newRole));
        Assert.assertNull(memStore.findById("24"));
    }

    @Test
    public void whenReplaceWrongId() {
        RoleStore memStore = new RoleStore();
        memStore.add(new Role("23"));
        memStore.add(new Role("54"));
        memStore.add(new Role("24"));
        Role newRole = new Role("77");
        assertThat(memStore.replace("333", newRole), is(false));
    }

    @Test
    public void whenDelete() {
        RoleStore memStore = new RoleStore();
        memStore.add(new Role("23"));
        memStore.add(new Role("54"));
        memStore.add(new Role("24"));
        assertThat(memStore.delete("24"), is(true));
    }

    @Test
    public void whenDeleteWrongId() {
        RoleStore memStore = new RoleStore();
        memStore.add(new Role("23"));
        memStore.add(new Role("54"));
        memStore.add(new Role("24"));
        assertThat(memStore.delete("44"), is(false));
    }

    @Test
    public void whenIsFound() {
        RoleStore memStore = new RoleStore();
        memStore.add(new Role("23"));
        memStore.add(new Role("54"));
        memStore.add(new Role("24"));
        Assert.assertNotNull(memStore.findById("54"));
    }

    @Test
    public void whenNoFound() {
        RoleStore memStore = new RoleStore();
        memStore.add(new Role("23"));
        memStore.add(new Role("54"));
        memStore.add(new Role("24"));
        Role newRole = new Role("77");
        Assert.assertNull(memStore.findById("333"));
    }
}