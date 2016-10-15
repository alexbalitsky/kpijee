package dao;

import model.Car;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by obalitskyi on 10/3/16.
 * class for specific CRUD operations
 */
public class CarCRUD extends AbstractCRUD<Car> {

    public CarCRUD(String DBName, String tableName) {
        super(DBName, tableName);

    }

    @Override
    public void addRecord(Car car, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("insert into cars (brand, number, colour, price) values (?, ?, ?, ?);");
        statement.setString(1, car.getBrand());
        statement.setInt(2, car.getNumber());
        statement.setString(3, car.getColour());
        statement.setInt(4, car.getPrice());
        statement.executeUpdate();
    }

    @Override
    public void updateRecord(Car car, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE cars set brand = ?, number = ?, colour = ?, price = ? where id = ?;");
        statement.setString(1, car.getBrand());
        statement.setInt(2, car.getNumber());
        statement.setString(3, car.getColour());
        statement.setInt(4, car.getPrice());
        statement.setLong(5, car.getId());
        statement.executeUpdate();
    }

    @Override
    public void deleteRecord(Car car, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("delete from cars where id = ?;");
        statement.setLong(1, car.getId());
        statement.executeUpdate();
    }

    @Override
    public List<Car> getRecords(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        List<Car> cars = new LinkedList<Car>();

        ResultSet rs = statement.executeQuery("select id, brand, number, colour, price from cars;");
        try {
            while (rs.next()) {
                Car car = new Car(rs.getString("brand"), rs.getInt("number"), rs.getString("colour"), rs.getInt("price"));
                car.setId(rs.getLong("id"));
                cars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cars;
    }

    public void deleteTooExpensiveCars(Integer barrier, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("delete from  cars where price > ?;");
        statement.setInt(1, barrier);
        statement.executeUpdate();
    }

    public void changePrice(Car car, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("update cars set price = ? where id = ?;");
        statement.setInt(1, car.getPrice());
        statement.setLong(2, car.getId());
        statement.executeUpdate();
    }


}
