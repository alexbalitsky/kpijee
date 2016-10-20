package dao;

import entity.Car;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by obalitskyi on 10/3/16.
 */
@Stateless
@Local
public class CarDAO extends AbstractDAO<Car>{

    public CarDAO() {
        super(Car.class);
    }

    //TODO
}
