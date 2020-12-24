package ru.job4j.design.jdbc;

import java.sql.*;

public class TableEditor implements AutoCloseable {
    private Connection connection;

    public TableEditor() throws SQLException, ClassNotFoundException {
        initConnection();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        TableEditor tableEditor = new TableEditor();
        tableEditor.createTable("test_1");
        tableEditor.dropTable("test_1");
        tableEditor.addColumn("test_1", "Col_1", "varchar");
        tableEditor.renameColumn("test_1", "Col_1", "Col_2");
        tableEditor.dropColumn("test_1", "Col_1");
    }

    /**
     * Создание соедение с БД
     * параметры подключения получает с помощью класса PropManager
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private void initConnection() throws SQLException {
        //Class.forName("org.postgresql.Driver");
        PropManager pm = new PropManager("app.properties");
        connection = DriverManager.getConnection(
                pm.getKeyValue("url"),
                pm.getKeyValue("login"),
                pm.getKeyValue("password")
        );
    }

    public void createTable(String tableName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "create table if not exists %s(%s, %s);",
                    tableName,
                    "id serial primary key",
                    "name varchar(255)"
            );
            statement.execute(sql);
        }
    }

    public void dropTable(String table) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "drop table %s;",
                    table
            );
            statement.execute(sql);
        }
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "alter table %s add column %s %s;",
                    tableName,
                    columnName,
                    type
            );
            statement.execute(sql);
        }
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "alter table %s drop column %s;",
                    tableName,
                    columnName
            );
            statement.execute(sql);
        }
    }

    public void renameColumn(String tableName,
                             String columnName,
                             String newColumnName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "alter table %s rename column %s to %s;",
                    tableName,
                    columnName,
                    newColumnName
            );
            statement.execute(sql);
        }
    }

    public String getScheme(String tableName) throws SQLException {
        StringBuilder scheme = new StringBuilder();
        DatabaseMetaData metaData = connection.getMetaData();
        try (ResultSet columns = metaData.getColumns(null, null, tableName, null)) {
            scheme.append(String.format("%-15s %-15s%n", "column", "type"));
            while (columns.next()) {
                scheme.append(String.format("%-15s %-15s%n",
                        columns.getString("COLUMN_NAME"),
                        columns.getString("TYPE_NAME")));
            }
        }
        return scheme.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}