package ru.job4j.design.jdbc;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {
    private final Properties cfg;
    private final String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            rd.lines().forEach(line -> users.add(new User(line.split(";")[0], line.split(";")[1]))
            );
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {

            try (PreparedStatement ps = cnt.prepareStatement(
            "CREATE TABLE IF NOT EXISTS users(id serial primary key," +
                    " name varchar(255), email varchar(255));")) {
                ps.execute();
            }

            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement(
                        "insert into users (name, email) values (?, ?)")) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (FileInputStream in = new FileInputStream("./chapter_003/data/spammer/app.properties")) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, "./chapter_003/data/spammer/dump.txt");
        db.save(db.load());
    }
}