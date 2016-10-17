package dao;

import entity.Car;
import interceptor.TxInterceptorBinding;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by obalitskyi on 10/3/16.
 */

@TxInterceptorBinding
public class CarDAO extends AbstractDAO<Car>{

    public CarDAO() {
        super(Car.class);
    }

    //TODO
}
