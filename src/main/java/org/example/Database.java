package org.example;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class Database {
    private static Connection connection;
    private static Statement statement;

    public static void connectDB() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/database.db");
        statement = connection.createStatement();
    }

    public static void createTableCake() throws SQLException {
        statement.execute("CREATE TABLE IF NOT EXISTS Cakes (" +
                "manufacturer TEXT," +
                "price DOUBLE," +
                "weight DOUBLE," +
                "diameter DOUBLE," +
                "dough TEXT," +
                "filling TEXT)");
    }

    public static void createTableConfection() throws SQLException {
        statement.execute("CREATE TABLE IF NOT EXISTS Confections (" +
                "manufacturer TEXT," +
                "price DOUBLE," +
                "weight DOUBLE)");
    }

    public static void inputDataConfection(ArrayList<? extends Confection> connections) throws SQLException {
        for(Confection confection : connections){
            if (confection instanceof Cake){
                Cake cake = (Cake) confection;
                statement.execute(String.format("INSERT INTO Cakes " +
                                "VALUES ('%s', '%.2f', '%.2f', '%.2f', '%s', '%s')", cake.get_manufacturer(), cake.get_price(), cake.get_weight(),
                        cake.get_diameter(),
                        cake.get_dough(),
                        cake.get_filling()));
            }
            else {
                statement.execute(String.format("INSERT INTO Confections " +
                        "VALUES ('%s', '%.2f', '%.2f')", confection.get_manufacturer(), confection.get_price(), confection.get_weight()));
            }
        }
    }

    public static void deleteData() throws SQLException {
        statement.execute("DROP TABLE IF EXISTS Confections");
        statement.execute("DROP TABLE IF EXISTS Cakes");
    }

    public static ArrayList<Confection> readData() throws SQLException {
        var resultSet = statement.executeQuery("SELECT * FROM Confections");
        return getDataFromResultSet(resultSet);
    }

    public static ArrayList<Confection> getConfectionsFilteredByManufacturer(String manufacturer) throws SQLException {
        var resultSet = statement.executeQuery(String.format("SELECT * FROM Confections WHERE manufacturer = '%s'", manufacturer));
        return getDataFromResultSet(resultSet);
    }

    public static ArrayList<Confection> getConfectionsFilteredByPrice(double price) throws SQLException {
        var resultSet = statement.executeQuery(String.format("SELECT * FROM Confections WHERE price <= '%f'", price));
        return getDataFromResultSet(resultSet);
    }

    private static ArrayList<Confection> getDataFromResultSet(ResultSet resultSet) throws SQLException {
        var confections = new ArrayList<Confection>();
        while(resultSet.next()){
            var manufacturer = resultSet.getString("manufacturer");
            var price = resultSet.getDouble("price");
            var weight = resultSet.getDouble("weight");
            var confection = new Confection(manufacturer, price, weight);
            confections.add(confection);
        }
        return confections;
    }

    public static void closeDb() throws SQLException {
        statement.close();
        connection.close();
    }
}
