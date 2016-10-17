package servlet;

import dao.AbstractDAO;
import dao.CarDAO;
import entity.Car;

/**
 * Created by alex on 17.10.16.
 */
public class Starter {

    public static void main(String[] args) {
        AbstractDAO<Car> dao = new CarDAO();
        Car car = new Car("mers", 1111, "black", 100000);
        dao.save(car);
    }
}
