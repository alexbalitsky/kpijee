package dao;

import entity.Car;
import entity.Driver;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;

/**
 * Created by alex on 17.10.16.
 */
@Stateless
@Local
public class DriverDAO extends AbstractDAO<Driver>{
    public DriverDAO() {
        super(Driver.class);
    }

    public List<Driver> getByCarIdThatAreNotInList(Car car, List<Long> driversOfCarIds){
        TypedQuery<Driver> query = em.createQuery("from Driver d where :car member d.cars and d.id in :driversOfCarIds", Driver.class);
        query.setParameter("car", car);
        query.setParameter("driversOfCarIds", driversOfCarIds);

        List<Driver> drivers = query.getResultList();
        return drivers;
    }
}
