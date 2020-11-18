package ru.job4j.design.map;

import java.util.Calendar;
import java.util.Objects;

public class UserNoString {
    private int children;
    private Calendar birthday;

    public UserNoString(int children, Calendar birthday) {
        this.children = children;
        this.birthday = birthday;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        //if (this.hashCode() == o.hashCode()) return true;
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserNoString user = (UserNoString) o;
        return children == user.children &&
                Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(children, birthday);
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }
}