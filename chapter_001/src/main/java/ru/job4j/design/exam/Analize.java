package ru.job4j.design.exam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Analize {

    public static Info diff(List<User> previous, List<User> current) {
        Info info = new Info();
        Map<Integer, String> map = new HashMap<>();
        for (User u : previous) {
            map.put(u.id, u.name);
        }
/*        for (User u : current) {
            if (map.containsKey(u.id)) {
                if (!map.get(u.id).equals(u.name)) {
                    info.changed++;
                }
            } else {
                info.added++;
            }
        }
        Consumer<User> cons = e -> {
            if (map.containsKey(e.id)) {
                if (!map.get(e.id).equals(e.name)) {
                    info.changed++;
                }
            } else {
                info.added++;
            }
        };*/
        current.forEach(e -> {
            if (map.containsKey(e.id)) {
                if (!map.get(e.id).equals(e.name)) {
                    info.changed++;
                }
            } else {
                info.added++;
            }
        });
        info.deleted = previous.size() - current.size() + info.added;
        return info;
    }

    public static class User {
        private final int id;
        private final String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            User user = (User) o;
            return id == user.id
                    && Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }

    public static class Info {
        private int added;
        private int changed;
        private int deleted;

        public int getAdded() {
            return added;
        }

        public int getChanged() {
            return changed;
        }

        public int getDeleted() {
            return deleted;
        }
    }

}