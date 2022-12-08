package ru.lessonsvtb.lesson9;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static Connection connection;
    private static Statement statement;

    public static void main(String[] args) throws Exception {
        try {
            connect();
            createTableFromClass(Person.class);
            List<Person> personList = Arrays.asList(
                    new Person("Andrew", 24, "andrew@gmail.com"),
                    new Person("Max", 23, "max@gmail.com"),
                    new Person("Andy", 45, "andy@gmail.com"),
                    new Person("Jack", 63, "jack@gmail.com"),
                    new Person("Valera", 21, "valera@gmail.com"));
            for (Person person :personList) {
                insertObjectIntoTable(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();

        }
    }

    public static void connect() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:main.db");
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Unable to connect");
        }
    }

    public static void disconnect() {
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTableFromClass(Class c) throws SQLException {
        if (!(c.isAnnotationPresent(Table.class))) {
            throw new RuntimeException("No Table annotation found");
        } else {
            List<Field> fields = new ArrayList<>(Arrays.asList(c.getDeclaredFields()));
            fields.forEach(field -> {
                if (!(field.isAnnotationPresent(Column.class))) fields.remove(field);
            });

            String SQL = "CREATE TABLE " + c.getSimpleName() + " ( ";

            for (Field f : fields) {
                if (f.getType().toString().contains("java.lang.String")) {
                    SQL += f.getName() + " TEXT, ";
                } else if (f.getType().toString().equals("int"))
                    SQL += f.getName() + " INTEGER, ";
            }

            statement.execute(SQL + "id INTEGER PRIMARY KEY);");
        }
    }

    public static void insertObjectIntoTable(Object o) throws IllegalAccessException, SQLException {
        if (!(o.getClass().isAnnotationPresent(Table.class))) {
            throw new RuntimeException("No Table annotation found");
        } else {
            Class oClass = o.getClass();
            List<Field> fields = new ArrayList<>(Arrays.asList(oClass.getDeclaredFields()));
            fields.forEach(field -> {
                if (!(field.isAnnotationPresent(Column.class))) fields.remove(field);
            });

            String value;
            List<String> fieldNames = new ArrayList<>();
            List<String> values = new ArrayList<>();
            for (Field f : fields) {
                fieldNames.add(f.getName());
                f.setAccessible(true);
                value = f.get(o).toString();
                if (f.getType().toString().contains("java.lang.String"))
                    value = "'" + value + "'";
                values.add(value);
            }
            String SQL = String.format("INSERT INTO %s (%s) VALUES (%s);",
                    oClass.getSimpleName(),
                    fieldNames.toString().replace('[', ' ').replace(']', ' '),
                    values.toString().replace('[', ' ').replace(']', ' '));

            //System.out.println(SQL);
            statement.execute(SQL);
        }
    }


}
