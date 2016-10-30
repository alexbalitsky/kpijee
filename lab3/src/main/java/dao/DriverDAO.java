package dao;

import entity.Car;
import entity.Driver;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Collection;
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

    public List<Driver> getThatAreNotInList(Car car, Set<Driver> driversOfCarIds){

        CriteriaBuilder qb = em.getCriteriaBuilder();
        CriteriaQuery cq = qb.createQuery();
        Root<Driver> driverRoot = cq.from(Driver.class);

        List<Predicate> predicates = new ArrayList<Predicate>();
        Expression<Collection<Car>> cars = driverRoot.get("cars");

        if (car != null) {
            predicates.add(qb.isMember(car, cars));
        }
        if (driversOfCarIds != null && !driversOfCarIds.isEmpty()) {
            predicates.add(qb.not(driverRoot.in(driversOfCarIds)));
        }
        //query itself
        cq.select(driverRoot)
                .where(predicates.toArray(new Predicate[]{}));
        //execute query and do something with result
        List<Driver> drivers = em.createQuery(cq).getResultList();

        return drivers;
    }
}
