package ru.job4j.design.generic.store;

import static org.hamcrest.Matchers.is;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class MemStoreTest {

    @Test
    public void whenReplace() {
        MemStore<Base> memStore = new MemStore<>();
        memStore.add(new User("23"));
        memStore.add(new User("54"));
        memStore.add(new User("24"));
        User newUser = new User("77");
        assertThat(memStore.replace("24", newUser ), is(true));
        assertThat(memStore.findById("77"), is(newUser));
        Assert.assertNull(memStore.findById("24"));
    }

    @Test
    public void whenReplaceWrongId() {
        MemStore<Base> memStore = new MemStore<>();
        memStore.add(new User("23"));
        memStore.add(new User("54"));
        memStore.add(new User("24"));
        User newUser = new User("77");
        assertThat(memStore.replace("333", newUser ), is(false));
    }

    @Test
    public void whenDelete() {
        MemStore<Base> memStore = new MemStore<>();
        memStore.add(new User("23"));
        memStore.add(new User("54"));
        memStore.add(new User("24"));
        assertThat(memStore.delete("24"), is(true));
    }

    @Test
    public void whenDeleteWrongId() {
        MemStore<Base> memStore = new MemStore<>();
        memStore.add(new User("23"));
        memStore.add(new User("54"));
        memStore.add(new User("24"));
        assertThat(memStore.delete("44"), is(false));
    }

    @Test
    public void whenIsFound() {
        MemStore<Base> memStore = new MemStore<>();
        memStore.add(new User("23"));
        memStore.add(new User("54"));
        memStore.add(new User("24"));
        Assert.assertNotNull(memStore.findById("54"));
    }

    @Test
    public void whenNoFound() {
        MemStore<Base> memStore = new MemStore<>();
        memStore.add(new User("23"));
        memStore.add(new User("54"));
        memStore.add(new User("24"));
        User newUser = new User("77");
        Assert.assertNull(memStore.findById("333"));
    }
}