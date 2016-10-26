package dao;

import entity.Car;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
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

    public List<Car> getCarsWithoutOwners(){
        Query query = em.createQuery("from Car c where c.carOwner is null ");
        return query.getResultList();
    }

}
