package dao;

import com.sun.istack.internal.NotNull;
import model.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by obalitskyi on 10/4/16.
 * super class for CRUD operations
 */

public abstract class AbstractCRUD<T> {
    private String DBName;
    private String tableName;

    public AbstractCRUD(String DBName, String tableName) {
        this.DBName = DBName;
        this.tableName = tableName;
    }

    public abstract void addRecord(T record, Connection connection) throws SQLException;

    public abstract void updateRecord(T record, Connection connection) throws SQLException;

    public abstract void deleteRecord(T record, Connection connection) throws SQLException;

    public abstract List<T> getRecords(Connection connection) throws SQLException;

    public void createDB(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("create database if not exists " + DBName + ";");
    }

    public void createTable(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("create table if not exists " + tableName + "("
                + "id INT NOT NULL AUTO_INCREMENT,"
                + "brand VARCHAR(100) NOT NULL,"
                + "number INT NOT NULL,"
                + "colour VARCHAR(100) NOT NULL,"
                + "price INT NOT NULL,"
                + "PRIMARY KEY (id)"
                + ");");
    }

    public void dropTable(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("drop table if exists " + tableName + ";");
    }
}
