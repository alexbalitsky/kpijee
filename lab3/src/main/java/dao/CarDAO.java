package dao;

import entity.*;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.sql.*;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.Collection;
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

    public List<Car> getCarsWithoutOwners(CarOwner entity){
        CriteriaBuilder qb = em.getCriteriaBuilder();
        CriteriaQuery cq = qb.createQuery();
        Root<Car> carRoot = cq.from(Car.class);

        List<Predicate> predicates = new ArrayList<Predicate>();

        Expression<CarOwner> carOwner = carRoot.get("carOwner");

        if (entity != null) {
            predicates.add(qb.or(qb.equal(carOwner, entity), qb.isNull(carOwner)));
        }
        cq.select(carRoot)
                .where(predicates.toArray(new Predicate[]{}));
        List<Car> cars = em.createQuery(cq).getResultList();

        return cars;
    }

}
